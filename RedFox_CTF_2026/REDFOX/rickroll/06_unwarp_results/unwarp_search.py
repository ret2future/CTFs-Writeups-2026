from __future__ import annotations

import math
import subprocess
from pathlib import Path

import numpy as np
from PIL import Image, ImageOps

ROOT = Path('.')
SRC = ROOT / 'layer3.png'
OUT_DIR = ROOT / 'unwarp_candidates'
OUT_DIR.mkdir(exist_ok=True)


def load_base() -> np.ndarray:
    im = Image.open(SRC).convert('L')
    arr = np.array(im, dtype=np.uint8)
    ys, xs = np.where(arr < 245)
    if len(xs) > 0 and len(ys) > 0:
        arr = arr[ys.min() : ys.max() + 1, xs.min() : xs.max() + 1]
    # Upscale for easier OCR.
    up = Image.fromarray(arr).resize((arr.shape[1] * 3, arr.shape[0] * 3), Image.Resampling.BICUBIC)
    up = ImageOps.autocontrast(up)
    return np.array(up, dtype=np.uint8)


def sample_nearest(img: np.ndarray, x: np.ndarray, y: np.ndarray) -> np.ndarray:
    h, w = img.shape
    xi = np.clip(np.rint(x).astype(int), 0, w - 1)
    yi = np.clip(np.rint(y).astype(int), 0, h - 1)
    return img[yi, xi]


def reverse_swirl(img: np.ndarray, k: float) -> np.ndarray:
    h, w = img.shape
    cx = (w - 1) / 2.0
    cy = (h - 1) / 2.0
    yy, xx = np.indices((h, w), dtype=np.float64)
    dx = xx - cx
    dy = yy - cy
    r = np.sqrt(dx * dx + dy * dy)
    rmax = max(1.0, float(r.max()))

    theta = np.arctan2(dy, dx)
    # Inverse swirl model: angle correction strongest near center.
    src_theta = theta + k * (1.0 - (r / rmax))

    src_x = cx + r * np.cos(src_theta)
    src_y = cy + r * np.sin(src_theta)
    return sample_nearest(img, src_x, src_y)


def unwrap_polar(img: np.ndarray, out_h: int = 600, out_w: int = 1200) -> np.ndarray:
    h, w = img.shape
    cx = (w - 1) / 2.0
    cy = (h - 1) / 2.0
    rmax = min(cx, cy)

    yy, xx = np.indices((out_h, out_w), dtype=np.float64)
    r = (yy / max(1.0, (out_h - 1))) * rmax
    theta = (xx / max(1.0, (out_w - 1))) * (2.0 * math.pi)

    src_x = cx + r * np.cos(theta)
    src_y = cy + r * np.sin(theta)
    return sample_nearest(img, src_x, src_y)


def run_ocr(path: Path) -> str:
    cmd = [
        'tesseract',
        str(path),
        'stdout',
        '--psm',
        '7',
        '-c',
        'load_system_dawg=0',
        '-c',
        'load_freq_dawg=0',
        '-c',
        'tessedit_char_whitelist=ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789{}_-',
    ]
    try:
        out = subprocess.check_output(cmd, stderr=subprocess.DEVNULL)
    except Exception:
        return ''
    txt = out.decode('utf-8', errors='ignore').strip().replace('\x0c', '')
    return ' '.join(txt.split())


def score_text(txt: str) -> int:
    if not txt:
        return 0
    score = sum(ch.isalnum() for ch in txt)
    low = txt.lower()
    if '{' in txt and '}' in txt:
        score += 40
    if 'flag' in low or 'ctf' in low:
        score += 25
    if len(txt) >= 6:
        score += 5
    return score


def save_img(arr: np.ndarray, name: str) -> Path:
    p = OUT_DIR / name
    Image.fromarray(arr).save(p)
    return p


def main() -> None:
    base = load_base()
    results: list[tuple[int, str, str, Path]] = []

    # Candidate set 1: reverse swirl with moderate sweep.
    for k in np.arange(-8.0, 8.01, 1.0):
        sw = reverse_swirl(base, float(k))
        for angle in range(0, 360, 15):
            im = Image.fromarray(sw).rotate(angle, expand=True, fillcolor=255)
            for inv in (False, True):
                cur = ImageOps.invert(im) if inv else im
                arr = np.array(cur, dtype=np.uint8)
                bw = (arr < 200).astype(np.uint8) * 255
                tag = f'sw_k{str(k).replace('.', 'p')}_r{angle}_{"inv" if inv else "norm"}.png'
                path = save_img(bw.astype(np.uint8), tag)
                txt = run_ocr(path)
                sc = score_text(txt)
                if sc > 0:
                    results.append((sc, tag, txt, path))

    # Candidate set 2: polar unwrap from base and light post-rotations.
    pu = unwrap_polar(base, out_h=700, out_w=1500)
    for angle in range(0, 360, 15):
        im = Image.fromarray(pu).rotate(angle, expand=True, fillcolor=255)
        for inv in (False, True):
            cur = ImageOps.invert(im) if inv else im
            arr = np.array(cur, dtype=np.uint8)
            bw = (arr < 200).astype(np.uint8) * 255
            tag = f'polar_r{angle}_{"inv" if inv else "norm"}.png'
            path = save_img(bw.astype(np.uint8), tag)
            txt = run_ocr(path)
            sc = score_text(txt)
            if sc > 0:
                results.append((sc, tag, txt, path))

    results.sort(reverse=True, key=lambda x: x[0])
    top = results[:40]
    report = ROOT / 'unwarp_top.txt'
    with report.open('w', encoding='utf-8') as f:
        for sc, tag, txt, _ in top:
            f.write(f'{sc:03d} | {tag} | {txt}\n')

    print(f'total_scored={len(results)} top_saved={len(top)} report={report}')


if __name__ == '__main__':
    main()
