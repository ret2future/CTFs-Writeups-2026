#!/usr/bin/env python3
"""Look at frame differences to find what changes between frames."""
from PIL import Image
import numpy as np

frames = []
for i in range(12):
    img = Image.open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif')
    img.seek(i)
    frames.append(np.array(img.convert('RGB')))

# Show which pixels differ between frame i and frame i+1
print("=== Pixel differences between consecutive frames ===")
for fi in range(11):
    diff = np.any(frames[fi] != frames[fi+1], axis=2)
    changed_pixels = list(zip(*np.where(diff)))
    print(f"\nFrame {fi} -> Frame {fi+1}: {len(changed_pixels)} pixels changed")
    # Show as minimap
    for y, x in changed_pixels[:20]:
        print(f"  ({x:3d},{y:2d}): {tuple(frames[fi][y,x])} -> {tuple(frames[fi+1][y,x])}")
    if len(changed_pixels) > 20:
        print(f"  ... and {len(changed_pixels)-20} more")

# Also compare frame 0 vs frame 6 (both use index 4 yellow)
print("\n=== Frame 0 vs Frame 6 (both use idx4 yellow) ===")
diff06 = np.any(frames[0] != frames[6], axis=2)
changed_06 = list(zip(*np.where(diff06)))
print(f"{len(changed_06)} pixels differ")
for y, x in changed_06[:20]:
    print(f"  ({x:3d},{y:2d}): {tuple(frames[0][y,x])} -> {tuple(frames[6][y,x])}")

# What are ALL unique pixel sequences across all frames?
# For each (x,y), check if the pixel value changes across frames
print("\n=== Positions with UNIQUE change patterns ===")
# First find all unique position-frame patterns
unique_patterns = {}
for y in range(70):
    for x in range(100):
        pixel_seq = tuple(tuple(frames[fi][y,x]) for fi in range(12))
        if len(set(pixel_seq)) > 1:  # position that changes
            key = pixel_seq[0]  # first frame value
            # Check if it's interesting (not just water white ripples)
            first_color = pixel_seq[0]
            if not all(p in [(255,255,255), (9,32,56), (11,41,71)] for p in pixel_seq):
                unique_patterns[(x,y)] = pixel_seq

print(f"Found {len(unique_patterns)} positions with interesting changes")
for (x,y), pattern in list(unique_patterns.items())[:20]:
    print(f"  ({x:3d},{y:2d}): {[str(p) for p in pattern[:6]]}")
