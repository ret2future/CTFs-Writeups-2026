import wave
from pathlib import Path

import numpy as np
from PIL import Image, ImageDraw, ImageOps


def draw_polyline(xs, ys, size=(2400, 1200), margin=40, step=8):
    w, h = size
    img = Image.new('L', size, 0)
    d = ImageDraw.Draw(img)

    x = xs[::step]
    y = ys[::step]

    x = (x - x.min()) / (x.max() - x.min() + 1e-9)
    y = (y - y.min()) / (y.max() - y.min() + 1e-9)

    px = margin + x * (w - 2 * margin)
    py = margin + (1.0 - y) * (h - 2 * margin)

    pts = list(zip(px.tolist(), py.tolist()))
    d.line(pts, fill=255, width=2)
    return img


def main():
    wav = Path('evidence_4b.wav')
    with wave.open(str(wav), 'rb') as w:
        n = w.getnframes()
        ch = w.getnchannels()
        data = np.frombuffer(w.readframes(n), dtype=np.int16).reshape(-1, ch)

    left = data[:, 0].astype(np.float64)
    right = data[:, 1].astype(np.float64)

    # Raw XY path from stereo channels.
    base = draw_polyline(left, right)

    out = Path('.')
    base.save(out / 'decoded_xy_raw.png')

    variants = {
        'decoded_xy_flip_h.png': ImageOps.mirror(base),
        'decoded_xy_flip_v.png': ImageOps.flip(base),
        'decoded_xy_rot_180.png': base.rotate(180, expand=False),
        'decoded_xy_rot_90.png': base.rotate(90, expand=True),
        'decoded_xy_rot_270.png': base.rotate(270, expand=True),
    }

    for name, im in variants.items():
        im.save(out / name)


if __name__ == '__main__':
    main()
