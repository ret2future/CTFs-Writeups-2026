#!/usr/bin/env python3
"""Compare actual water reflection vs ideal (undistorted) moon reflection.
The difference encodes the steganographic data."""
from PIL import Image
import numpy as np

frames = []
for i in range(12):
    img = Image.open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif')
    img.seek(i)
    frames.append(np.array(img.convert('RGB')))

# Moon pixels (from analysis, absolute positions):
moon_pixels = set()
for x in range(46, 50): moon_pixels.add((x, 14))  # 4
for x in range(45, 51): moon_pixels.add((x, 15))  # 6
for x in range(44, 52): moon_pixels.add((x, 16))  # 8
for x in range(44, 52): moon_pixels.add((x, 17))  # 8
for x in range(44, 52): moon_pixels.add((x, 18))  # 8
for x in range(44, 52): moon_pixels.add((x, 19))  # 8
for x in range(45, 51): moon_pixels.add((x, 20))  # 6
for x in range(46, 50): moon_pixels.add((x, 21))  # 4
print(f"Moon pixels: {len(moon_pixels)}")  # Should be 52

# Ideal reflection: flip around horizon y=31
HORIZON = 31
ideal_reflection = {}
for (x, y) in moon_pixels:
    reflected_y = HORIZON + (HORIZON - y)
    ideal_reflection[(x, reflected_y)] = (x, y)  # maps to original moon pixel

print(f"Ideal reflection pixels: {len(ideal_reflection)}")
print(f"Ideal reflection y range: {min(p[1] for p in ideal_reflection.keys())} - {max(p[1] for p in ideal_reflection.keys())}")

# Sort ideal pixels by position for consistent bit extraction
ideal_sorted = sorted(ideal_reflection.keys(), key=lambda p: (p[1], p[0]))
print(f"\nIdeal reflection positions (y,x):")
for pos in ideal_sorted:
    moon_src = ideal_reflection[pos]
    print(f"  water({pos[0]},{pos[1]}) <-- moon({moon_src[0]},{moon_src[1]})")

# For each frame, compare actual white pixels to ideal reflection positions
print("\n=== Per-frame comparison: ideal bit pattern ===")
print("1 = pixel present (matches ideal), 0 = pixel absent")
all_bits = []
for fi in range(len(frames)):
    frame = frames[fi]
    bits = []
    for (x, y) in ideal_sorted:
        is_white = tuple(frame[y, x]) == (255, 255, 255)
        bits.append(1 if is_white else 0)
    all_bits.append(bits)
    
    # Pack into bytes
    out = bytearray()
    for i in range(0, len(bits) - 7, 8):
        b = 0
        for j in range(8):
            b = (b << 1) | bits[i + j]
        out.append(b)
    text = ''.join(chr(b) if 32 <= b < 127 else '.' for b in out)
    print(f"Frame {fi:2d}: {''.join(str(b) for b in bits)} | {text}")

# Combine all frames' bits sequentially
print("\n=== All frames concatenated ===")
combined = []
for fi in range(len(frames)):
    combined.extend(all_bits[fi])

# Pack MSB
out_msb = bytearray()
for i in range(0, len(combined) - 7, 8):
    b = 0
    for j in range(8):
        b = (b << 1) | combined[i + j]
    out_msb.append(b)
text = ''.join(chr(b) if 32 <= b < 127 else '.' for b in out_msb)
print(f"MSB: {text}")
if b'UMASS' in out_msb:
    print("FOUND!")
    idx = out_msb.find(b'UMASS')
    print(out_msb[idx:idx+50])

# Pack LSB
out_lsb = bytearray()
for i in range(0, len(combined) - 7, 8):
    b = 0
    for j in range(8):
        b = b | (combined[i + j] << j)
    out_lsb.append(b)
text = ''.join(chr(b) if 32 <= b < 127 else '.' for b in out_lsb)
print(f"LSB: {text}")

# Also try: look at just the extra pixels (not in ideal reflection)
print("\n=== Extra white pixels NOT in ideal reflection ===")
for fi in range(len(frames)):
    frame = frames[fi]
    extra = []
    for y in range(35, 55):
        for x in range(40, 60):
            if tuple(frame[y, x]) == (255, 255, 255) and (x, y) not in ideal_reflection:
                extra.append((x, y))
    print(f"Frame {fi}: {len(extra)} extra white pixels: {extra}")

# Also show which ideal pixels are missing in each frame
print("\n=== Missing ideal pixels per frame ===")
for fi in range(len(frames)):
    frame = frames[fi]
    missing = []
    for (x, y) in ideal_sorted:
        if tuple(frame[y, x]) != (255, 255, 255):
            missing.append((x, y))
    print(f"Frame {fi}: {len(missing)} missing: {missing}")
