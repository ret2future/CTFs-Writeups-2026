from PIL import Image
import struct

# Parse raw GIF pixel indices (before color lookup)
data = open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif', 'rb').read()

# GCT:
# index 2 = background (9,32,56)
# index 4 = yellow1 (221,193,64) -> bit 1
# index 6 = yellow2 (220,193,64) -> bit 0

# Use PIL to get per-frame raw PIL palette index arrays
img = Image.open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif')
w, h = img.size
print(f"Size: {w}x{h}, frames: ", end='')

frames_raw = []
try:
    for i in range(100):
        img.seek(i)
        # 'P' mode = palette indexed
        frame_p = img.convert('P')
        # Actually for GIF, use getdata() on 'P' mode original
        img.seek(i)
        # Get palette indices directly
        raw = list(img.getdata())
        frames_raw.append(raw)
except EOFError:
    pass
print(len(frames_raw))

# Show palette index distribution per frame
for fi, fr in enumerate(frames_raw):
    from collections import Counter
    cnt = Counter(fr)
    print(f"Frame {fi}: {dict(sorted(cnt.items()))}")

# Find pixels that are yellow (index 4 or 6) in any frame
yellow_pixels = set()
for fr in frames_raw:
    for idx, val in enumerate(fr):
        if val in (4, 6):
            yellow_pixels.add(idx)

print(f"\nTotal yellow pixel positions: {len(yellow_pixels)}")

# For each frame, collect bits from yellow pixels (sorted by position)
sorted_yellows = sorted(yellow_pixels)
print(f"Yellow positions (first 20): {sorted_yellows[:20]}")

# Try reading bits from frame 0 only first
bits_f0 = []
for pos in sorted_yellows:
    v = frames_raw[0][pos]
    if v == 4:
        bits_f0.append(1)
    elif v == 6:
        bits_f0.append(0)
    else:
        bits_f0.append(None)  # not yellow in this frame

print(f"\nFrame 0 bits at yellow positions ({len(bits_f0)} total):")
print(bits_f0[:80])

# Pack bits to bytes (skip Nones, only yellow pixels)
yellow_bits_f0 = [b for b in bits_f0 if b is not None]
print(f"\nJust yellow bits in frame 0: {len(yellow_bits_f0)} bits = {len(yellow_bits_f0)//8} bytes")
print(yellow_bits_f0[:80])

def pack_bits_msb(bits):
    out = bytearray()
    for i in range(0, len(bits)-7, 8):
        b = 0
        for j in range(8): b = (b << 1) | bits[i+j]
        out.append(b)
    return bytes(out)

def pack_bits_lsb(bits):
    out = bytearray()
    for i in range(0, len(bits)-7, 8):
        b = 0
        for j in range(8): b |= bits[i+j] << j
        out.append(b)
    return bytes(out)

# Try all frames combined - concatenate bits across frames
all_bits = []
for fi, fr in enumerate(frames_raw):
    for pos in sorted_yellows:
        v = fr[pos]
        if v == 4:
            all_bits.append(1)
        elif v == 6:
            all_bits.append(0)

print(f"\nAll frames combined: {len(all_bits)} bits = {len(all_bits)//8} bytes")

for pack_fn, pname in [(pack_bits_msb, 'MSB'), (pack_bits_lsb, 'LSB')]:
    d = pack_fn(all_bits)
    printable = ''.join(chr(b) if 32<=b<127 else '.' for b in d[:100])
    flag = ' FLAG!' if b'UMASS' in d else ''
    print(f"  {pname}: [{printable}]{flag}")

# Also try just the bits from all frames at each position (not per-frame, but per-pixel across time)
print("\nPer-pixel-across-time bits:")
time_bits = []
for pos in sorted_yellows:
    for fi, fr in enumerate(frames_raw):
        v = fr[pos]
        if v == 4:
            time_bits.append(1)
        elif v == 6:
            time_bits.append(0)

print(f"Time-ordered bits: {len(time_bits)}")
for pack_fn, pname in [(pack_bits_msb, 'MSB'), (pack_bits_lsb, 'LSB')]:
    d = pack_fn(time_bits)
    printable = ''.join(chr(b) if 32<=b<127 else '.' for b in d[:100])
    flag = ' FLAG!' if b'UMASS' in d else ''
    print(f"  {pname}: [{printable}]{flag}")
