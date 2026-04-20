#!/usr/bin/env python3
from PIL import Image
import numpy as np

frames = []
for i in range(12):
    img = Image.open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif')
    img.seek(i)
    frames.append(np.array(img.convert('RGB')))

white = np.array([255, 255, 255])
is_white = np.array([np.all(f == white, axis=2) for f in frames], dtype=int)

# Average all frames and look for text
avg = np.array(frames, dtype=float).mean(axis=0)
print('Averaged water area:')
for y in range(38, 55):
    row = ''
    for x in range(100):
        pix = avg[y, x, 0]
        if pix > 150:
            row += '#'
        elif pix > 50:
            row += '.'
        else:
            row += ' '
    print(f'y{y:2d}: |{row}|')

# Check temporal pattern of each pixel
print()
print('Temporal patterns (which frames have white at each position):')
for y in range(40, 52):
    for x in range(100):
        pattern = tuple(is_white[:, y, x])
        if sum(pattern) >= 3:
            print(f'  ({x:3d},{y}): {pattern}')

# ALL 12 frames x-positions with white pixels
print()
print('Per-frame white pixel columns in water rows 41-50:')
for fi in range(12):
    positions = []
    for y in range(41, 51):
        for x in range(100):
            if is_white[fi, y, x]:
                positions.append((x, y))
    print(f'Frame {fi}: {sorted(set(p[0] for p in positions))}')
