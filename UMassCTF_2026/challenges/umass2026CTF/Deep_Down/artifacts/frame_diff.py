from PIL import Image
import numpy as np

img = Image.open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif')

frames = []
for i in range(12):
    img.seek(i)
    frame = img.convert('RGBA')
    frames.append(np.array(frame))

print("Frame-to-frame differences (vs frame 0):")
for i in range(1, 12):
    diff = frames[i] != frames[0]
    diff_px = np.where(diff.any(axis=2))
    if len(diff_px[0]) > 0:
        print(f"  Frame {i}: {len(diff_px[0])} differing pixels")
        for y, x in zip(diff_px[0][:20], diff_px[1][:20]):
            print(f"    ({x},{y}): f0={tuple(frames[0][y,x])} f{i}={tuple(frames[i][y,x])}")
    else:
        print(f"  Frame {i}: identical to frame 0")

print("\nConsecutive frame differences:")
for i in range(1, 12):
    diff = frames[i] != frames[i-1]
    diff_px = np.where(diff.any(axis=2))
    print(f"  Frame {i-1}->{i}: {len(diff_px[0])} px differ")
    for y, x in zip(diff_px[0][:5], diff_px[1][:5]):
        print(f"    ({x},{y}): {tuple(frames[i-1][y,x])} -> {tuple(frames[i][y,x])}")
