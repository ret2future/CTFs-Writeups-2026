#!/usr/bin/env python3
from pathlib import Path

import numpy as np
from PIL import Image


def reverse_swirl(img_array: np.ndarray, intensity: float) -> np.ndarray:
    h, w = img_array.shape[:2]
    cy, cx = h / 2.0, w / 2.0

    yy, xx = np.ogrid[:h, :w]
    yc, xc = yy - cy, xx - cx

    r = np.sqrt(xc**2 + yc**2)
    theta = np.arctan2(yc, xc)

    theta_new = theta - intensity * r / max(h, w)

    xn = r * np.cos(theta_new) + cx
    yn = r * np.sin(theta_new) + cy

    xn = np.clip(xn, 0, w - 1).astype(np.float32)
    yn = np.clip(yn, 0, h - 1).astype(np.float32)

    x0, x1 = np.floor(xn).astype(int), np.ceil(xn).astype(int)
    y0, y1 = np.floor(yn).astype(int), np.ceil(yn).astype(int)

    wx = xn - x0
    wy = yn - y0

    result = (
        img_array[y0, x0] * (1 - wx) * (1 - wy)
        + img_array[y0, x1] * wx * (1 - wy)
        + img_array[y1, x0] * (1 - wx) * wy
        + img_array[y1, x1] * wx * wy
    )
    return result.astype(np.uint8)


def main() -> None:
    root = Path(__file__).resolve().parents[1]
    src = root / "image.png"
    out_dir = root / "positive_swirl_image_png"
    out_dir.mkdir(parents=True, exist_ok=True)

    img = Image.open(src).convert("L")
    arr = np.array(img)

    for intensity in range(0, 501, 20):
        swirled = reverse_swirl(arr, float(intensity))
        out_path = out_dir / f"positive_swirl_{intensity:03d}.png"
        Image.fromarray(swirled).save(out_path)

    print(f"Source: {src}")
    print(f"Output folder: {out_dir}")
    print("Generated files: 26")


if __name__ == "__main__":
    main()
