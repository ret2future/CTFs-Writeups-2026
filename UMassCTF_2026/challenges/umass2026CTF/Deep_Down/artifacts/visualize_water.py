#!/usr/bin/env python3
"""Create a zoomed visualization of the water area and look at what it shows."""
from PIL import Image
import numpy as np

frames = []
for i in range(12):
    img = Image.open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif')
    img.seek(i)
    frames.append(np.array(img.convert('RGB')))

# Create zoomed image grid of water area (y=38-55, x=35-65)
ZOOM = 8
water_y = (35, 58)
water_x = (35, 65)
h = water_y[1] - water_y[0]
w = water_x[1] - water_x[0]

# Create a 4x3 grid of zoomed frames
grid_w = 4 * (w * ZOOM + 4)
grid_h = 3 * (h * ZOOM + 4) + 20  # extra for label
grid = Image.new('RGB', (grid_w, grid_h), (50, 50, 50))

for fi in range(12):
    row = fi // 4
    col = fi % 4
    crop = frames[fi][water_y[0]:water_y[1], water_x[0]:water_x[1]]
    small = Image.fromarray(crop.astype(np.uint8))
    zoomed = small.resize((w * ZOOM, h * ZOOM), Image.NEAREST)
    x_off = col * (w * ZOOM + 4) + 2
    y_off = row * (h * ZOOM + 4) + 2
    grid.paste(zoomed, (x_off, y_off))

grid.save('/root/umass2026CTF/Deep_Down/artifacts/water_zoom.png')
print(f"Saved water_zoom.png ({grid_w}x{grid_h})")

# Also save averaged water area zoomed
avg = np.array(frames, dtype=float).mean(axis=0)
crop = avg[water_y[0]:water_y[1], water_x[0]:water_x[1]].astype(np.uint8)
small = Image.fromarray(crop)
zoomed = small.resize((w * ZOOM * 2, h * ZOOM * 2), Image.NEAREST)
zoomed.save('/root/umass2026CTF/Deep_Down/artifacts/water_avg_zoom.png')
print(f"Saved water_avg_zoom.png")

# Also look at FULL frame zoomed
for fi in [0, 6]:
    img = Image.fromarray(frames[fi])
    img_big = img.resize((100*4, 70*4), Image.NEAREST)
    img_big.save(f'/root/umass2026CTF/Deep_Down/artifacts/frame_{fi:02d}_zoom4x.png')
    print(f"Saved frame_{fi:02d}_zoom4x.png")

# Print the water area as ASCII for each frame, looking at the FULL width
print("\n=== Full water area ASCII (y=38-56) for each frame ===")
for fi in range(12):
    print(f"\nFrame {fi}:")
    for y in range(38, 56):
        row = ''
        for x in range(100):
            r, g, b = frames[fi][y, x]
            if (r, g, b) == (255, 255, 255):
                row += '#'
            elif r > 100:
                row += '.'
            else:
                row += ' '
        if row.strip():
            print(f"  y{y:2d}: {row}")
