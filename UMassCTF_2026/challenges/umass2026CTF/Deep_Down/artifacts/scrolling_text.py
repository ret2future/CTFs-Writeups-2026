#!/usr/bin/env python3
"""Try to read the water area as scrolling text or frame-encoded letters."""
from PIL import Image
import numpy as np

frames = []
for i in range(12):
    img = Image.open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif')
    img.seek(i)
    frames.append(np.array(img.convert('RGB')))

water_y = (41, 51)
water_x = (42, 57)
nframes = 12
h = water_y[1] - water_y[0]
w = water_x[1] - water_x[0]

# Build the 3D binary cube: cube[y][x][frame]
cube = np.zeros((h, w, nframes), dtype=int)
for fi in range(nframes):
    for y_idx in range(h):
        for x_idx in range(w):
            y = water_y[0] + y_idx
            x = water_x[0] + x_idx
            r, g, b = frames[fi][y, x]
            cube[y_idx, x_idx, fi] = 1 if (r, g, b) == (255, 255, 255) else 0

# View 1: temporal profile per row (time = horizontal, row = each row)
print("=== Temporal profiles per ROW (x=frame index, y=row, 1=white) ===")
print("This shows how each row evolves across 12 frames")
for y_idx in range(h):
    y = water_y[0] + y_idx
    row = ''.join('#' if cube[y_idx, :, fi].any() else ' ' for fi in range(nframes))
    # more detailed: show full row across frames
    print(f"y{y}: ", end='')
    for fi in range(nframes):
        has_white = cube[y_idx, :, fi].any()
        count = cube[y_idx, :, fi].sum()
        print(f"{count:2d}", end='')
    print()

# View 2: temporal profile per COLUMN (time = horizontal)
print("\n=== Temporal profiles per COLUMN ===")
for x_idx in range(w):
    x = water_x[0] + x_idx
    has_white_per_frame = [cube[:, x_idx, fi].any() for fi in range(nframes)]
    row = ''.join('#' if h else ' ' for h in has_white_per_frame)
    print(f"x{x:3d}: |{row}|")

# View 3: "Vertical slice" through time - for each frame, what column range has white?
print("\n=== Column ranges with white per frame ===")
for fi in range(nframes):
    frame_has_white = [cube[:, x_idx, fi].any() for x_idx in range(w)]
    cols = [water_x[0] + x_idx for x_idx, h in enumerate(frame_has_white) if h]
    print(f"Frame {fi:2d}: cols {cols}")

# View 4: Flatten frames into a wide image (place all 12 frames side by side)
# This is like a "scrolling LED display"
flat_width = w * nframes
flat_image = np.zeros((h, flat_width), dtype=np.uint8)
for fi in range(nframes):
    for y_idx in range(h):
        for x_idx in range(w):
            flat_image[y_idx, fi * w + x_idx] = 255 if cube[y_idx, x_idx, fi] else 0

# Save as image
pil = Image.fromarray(flat_image).resize((flat_width * 8, h * 8), Image.NEAREST)
pil.save('/root/umass2026CTF/Deep_Down/artifacts/water_scrolling.png')
print(f"\nSaved scrolling strip image ({flat_width}x{h} -> {flat_width*8}x{h*8})")

# Also flip the strip vertically (for reflected text)
pil_flipped = pil.transpose(Image.FLIP_TOP_BOTTOM)
pil_flipped.save('/root/umass2026CTF/Deep_Down/artifacts/water_scrolling_flipped.png')
print("Saved flipped version")

# Print ASCII version of scrolling strip
print("\n=== Scrolling strip ASCII (frame0 | frame1 | ...) ===")
print("Each character block is one frame's water area")
for y_idx in range(h):
    y = water_y[0] + y_idx
    row = ''
    for fi in range(nframes):
        row += ''.join('#' if cube[y_idx, x_idx, fi] else ' ' for x_idx in range(w))
        row += '|'
    print(f"y{y}: |{row}")
