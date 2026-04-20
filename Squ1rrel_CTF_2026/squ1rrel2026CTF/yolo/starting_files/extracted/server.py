import base64
import os
import sys
import uuid

YOLOV7_ROOT = "/app/yolov7"
sys.path.insert(0, YOLOV7_ROOT)
os.chdir(YOLOV7_ROOT)

import cv2
import numpy as np
import torch
import yaml
from flask import Flask, jsonify, render_template, request

from models.experimental import attempt_load
from models.yolo import Model
from utils.datasets import letterbox
from utils.general import non_max_suppression, scale_coords
from utils.plots import plot_one_box

app = Flask(
    __name__,
    template_folder="/app/templates",
    static_folder="/app/static",
)
app.config["MAX_CONTENT_LENGTH"] = 16 * 1024 * 1024  # 16 MiB

DEVICE = torch.device("cpu")
WEIGHTS = "/app/yolov7-tiny.pt"
IMG_SIZE = 640
UPLOADS_DIR = "/tmp/yolov7-uploads"
os.makedirs(UPLOADS_DIR, exist_ok=True)

print(f"[*] loading yolov7 weights from {WEIGHTS} ...", flush=True)
MODEL = attempt_load(WEIGHTS, map_location=DEVICE)
MODEL.eval()
STRIDE = int(MODEL.stride.max())
NAMES = MODEL.module.names if hasattr(MODEL, "module") else MODEL.names
print(f"[*] model ready ({len(NAMES)} classes, stride={STRIDE})", flush=True)

_palette_rng = np.random.default_rng(42)
PALETTE = [tuple(int(x) for x in _palette_rng.integers(60, 255, size=3))
           for _ in NAMES]

def _decode_image(file_storage):
    raw = file_storage.read()
    if not raw:
        return None
    arr = np.frombuffer(raw, dtype=np.uint8)
    img = cv2.imdecode(arr, cv2.IMREAD_COLOR)
    return img

def _run_detection(img_bgr, conf_thres=0.25, iou_thres=0.45):
    img0 = img_bgr.copy()
    img = letterbox(img_bgr, IMG_SIZE, stride=STRIDE, auto=True)[0]
    img = img[:, :, ::-1].transpose(2, 0, 1)  # BGR->RGB, HWC->CHW
    img = np.ascontiguousarray(img)

    t = torch.from_numpy(img).to(DEVICE).float() / 255.0
    if t.ndim == 3:
        t = t.unsqueeze(0)

    with torch.no_grad():
        pred = MODEL(t, augment=False)[0]
    pred = non_max_suppression(pred, conf_thres, iou_thres)

    detections = []
    for det in pred:
        if det is None or not len(det):
            continue
        det[:, :4] = scale_coords(t.shape[2:], det[:, :4], img0.shape).round()
        for *xyxy, conf, cls in det:
            c = int(cls)
            name = NAMES[c] if c < len(NAMES) else str(c)
            color = PALETTE[c % len(PALETTE)]
            detections.append({
                "class": name,
                "class_id": c,
                "confidence": float(conf),
                "box": [float(x) for x in xyxy],
            })
            plot_one_box(xyxy, img0, label=f"{name} {float(conf):.2f}",
                         color=color, line_thickness=2)

    ok, enc = cv2.imencode(".jpg", img0, [cv2.IMWRITE_JPEG_QUALITY, 88])
    overlay_b64 = base64.b64encode(enc.tobytes()).decode() if ok else None
    return detections, overlay_b64

@app.route("/api/detect", methods=["POST"])
def api_detect():
    fs = request.files.get("image")
    if fs is None:
        return jsonify({"ok": False, "error": "field 'image' is required"}), 400

    img = _decode_image(fs)
    if img is None:
        return jsonify({"ok": False, "error": "not a decodable image"}), 400

    # hard guard: pixel budget
    h, w = img.shape[:2]
    if h > 4096 or w > 4096 or h * w > 25_000_000:
        return jsonify({"ok": False, "error": "image resolution too large"}), 400

    try:
        detections, overlay_b64 = _run_detection(img)
    except Exception as e:
        return jsonify({"ok": False, "error": f"inference failed: {type(e).__name__}"}), 500

    return jsonify({
        "ok": True,
        "count": len(detections),
        "detections": detections,
        "overlay_jpg_b64": overlay_b64,
    })

def _validate_pretrained(path):
    ckpt = torch.load(path, map_location="cpu")
    if not isinstance(ckpt, dict):
        raise ValueError("checkpoint is not a dict")
    if "model" not in ckpt:
        raise ValueError("checkpoint has no 'model' entry")
    mdl = ckpt["model"]
    embedded = getattr(mdl, "yaml", None)
    if embedded is None:
        raise ValueError("model object has no embedded yaml")
    if not isinstance(embedded, dict):
        raise ValueError("embedded yaml is not a dict")
    return embedded

@app.route("/api/model/build", methods=["POST"])
def model_build():
    cfg_fs = request.files.get("config")
    wts_fs = request.files.get("weights")

    if cfg_fs is None and wts_fs is None:
        return jsonify({
            "ok": False,
            "error": "provide a 'config' yaml (and optionally 'weights' .pt)",
        }), 400

    cfg_dict = None
    if cfg_fs is not None:
        raw = cfg_fs.read()
        try:
            cfg_dict = yaml.safe_load(raw.decode("utf-8", errors="replace"))
        except yaml.YAMLError as e:
            return jsonify({"ok": False, "error": f"yaml parse error: {e}"}), 400
        if cfg_dict is not None and not isinstance(cfg_dict, dict):
            return jsonify({"ok": False, "error": "yaml must be a mapping"}), 400

    embedded_yaml = None
    if wts_fs is not None:
        tmp_path = os.path.join(UPLOADS_DIR, f"pt-{uuid.uuid4().hex}.pt")
        wts_fs.save(tmp_path)
        try:
            embedded_yaml = _validate_pretrained(tmp_path)
        except Exception as e:
            return jsonify({
                "ok": False,
                "error": f"pretrained validation failed: {e}",
            }), 400
        finally:
            try:
                os.unlink(tmp_path)
            except FileNotFoundError:
                pass

    chosen = cfg_dict if cfg_dict is not None else embedded_yaml
    if chosen is None:
        return jsonify({"ok": False, "error": "no usable architecture"}), 400

    try:
        nc = int(chosen.get("nc", 80))
        model = Model(chosen, ch=3, nc=nc)
    except Exception as e:
        return jsonify({
            "ok": False,
            "error": f"graph build failed: {type(e).__name__}: {e}",
        }), 400

    n_modules = sum(1 for _ in model.modules())
    layer_list = []
    for i, m in enumerate(model.model):
        layer_list.append({
            "index": i,
            "type": type(m).__name__,
        })

    return jsonify({
        "ok": True,
        "message": "model graph built successfully",
        "nc": nc,
        "modules_total": n_modules,
        "layers": layer_list[:64],
    })

@app.route("/")
def index():
    return render_template("index.html",
                           class_names=list(NAMES),
                           n_classes=len(NAMES))

@app.route("/healthz")
def healthz():
    return jsonify({"ok": True, "classes": len(NAMES)})

if __name__ == "__main__":
    app.run("0.0.0.0", 5000, debug=False)
