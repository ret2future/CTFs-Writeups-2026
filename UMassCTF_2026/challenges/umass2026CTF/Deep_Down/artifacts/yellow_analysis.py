#!/usr/bin/env python3
"""Analyze yellow pixel positions across all frames."""
from PIL import Image
import numpy as np

frames_pil = []
for i in range(12):
    img = Image.open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif')
    img.seek(i)
    frames_pil.append(img.convert('RGB'))

frames = [np.array(f) for f in frames_pil]

# Find yellow pixels per frame
# Frame 0 uses (221,193,64), frames 1-11 use (220,193,64)
def get_yellow_positions(frame_arr):
    yellow1 = ((frame_arr[:,:,0] == 221) & (frame_arr[:,:,1] == 193) & (frame_arr[:,:,2] == 64))
    yellow2 = ((frame_arr[:,:,0] == 220) & (frame_arr[:,:,1] == 193) & (frame_arr[:,:,2] == 64))
    return np.where(yellow1 | yellow2)

print("Yellow pixel positions per frame:")
for fi, f in enumerate(frames):
    ys, xs = get_yellow_positions(f)
    positions = sorted(zip(ys.tolist(), xs.tolist()))
    print(f"Frame {fi}: {len(positions)} yellows")
    # Show as a mini map
    grid = [[' ']*100 for _ in range(70)]
    for y, x in positions:
        grid[y][x] = '*'
    # Print non-empty rows
    for y in range(70):
        row = ''.join(grid[y])
        if row.strip():
            print(f"  y{y:2d}: {row}")

# UNION of all yellow positions across all frames
print("\n=== UNION of all yellow positions ===")
all_yellows = set()
for fi, f in enumerate(frames):
    ys, xs = get_yellow_positions(f)
    for y, x in zip(ys.tolist(), xs.tolist()):
        all_yellows.add((y, x))

grid = [[' ']*100 for _ in range(70)]
for y, x in all_yellows:
    grid[y][x] = '*'
print("Non-empty rows in union:")
for y in range(70):
    row = ''.join(grid[y])
    if row.strip():
        print(f"  y{y:2d}: {row}")
