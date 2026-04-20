#!/usr/bin/env python3
"""Create zoomed individual frames and analyze water text."""
from PIL import Image
import numpy as np

frames = []
for i in range(12):
    img = Image.open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif')
    img.seek(i)
    frames.append(np.array(img.convert('RGB')))

# Look at JUST the water region as a 5-bit bitmap per frame
# Using x=44-53, y=41-50 (a 10x10 grid)

print("Water white pixel binary matrix per frame (y=41-50, x=42-56):")
print("Each row = y position, each col = x position")
print()

water_y = (41, 51)
water_x = (42, 57)

for fi in range(12):
    print(f"Frame {fi}:")
    binary_rows = []
    for y in range(water_y[0], water_y[1]):
        row_bits = []
        for x in range(water_x[0], water_x[1]):
            r, g, b = frames[fi][y, x]
            if (r, g, b) == (255, 255, 255):
                row_bits.append(1)
            else:
                row_bits.append(0)
        binary_rows.append(row_bits)
        row_str = ''.join('#' if b else ' ' for b in row_bits)
        print(f"  y{y}: |{row_str}|")
    print()

# Also try: render each frame's water as upscaled image
ZOOM = 12
w = water_x[1] - water_x[0]
h = water_y[1] - water_y[0]
for fi in range(12):
    crop = frames[fi][water_y[0]:water_y[1], water_x[0]:water_x[1]]
    small = Image.fromarray(crop.astype(np.uint8))
    zoomed = small.resize((w * ZOOM, h * ZOOM), Image.NEAREST)
    zoomed.save(f'/root/umass2026CTF/Deep_Down/artifacts/water_frame_{fi:02d}.png')

print("Saved individual water frame images.")

# Try the FULL frame zoomed at 4x, with marker on water area
for fi in range(12):
    img = Image.fromarray(frames[fi])
    img_big = img.resize((400, 280), Image.NEAREST)
    # Add red box around water area
    img_arr = np.array(img_big)
    wy1, wy2 = water_y[0]*4, water_y[1]*4
    wx1, wx2 = water_x[0]*4, water_x[1]*4
    img_arr[wy1:wy2, wx1, :] = [255, 0, 0]
    img_arr[wy1:wy2, wx2, :] = [255, 0, 0]
    img_arr[wy1, wx1:wx2, :] = [255, 0, 0]
    img_arr[wy2, wx1:wx2, :] = [255, 0, 0]
    Image.fromarray(img_arr).save(f'/root/umass2026CTF/Deep_Down/artifacts/full_frame_{fi:02d}.png')

print("Saved full frame images with water marker.")
