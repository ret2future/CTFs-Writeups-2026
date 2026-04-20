#!/usr/bin/env python3
"""Try average as baseline and compare each frame's deviation."""
from PIL import Image
import numpy as np

frames = []
for i in range(12):
    img = Image.open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif')
    img.seek(i)
    frames.append(np.array(img.convert('RGB')))

nframes = len(frames)

# Focus on water area
wy0, wy1 = 41, 49
wx0, wx1 = 44, 52

# White pixel map: 1=white, 0=not white
is_white = np.zeros((nframes, wy1-wy0, wx1-wx0), dtype=int)
for fi in range(nframes):
    for y in range(wy0, wy1):
        for x in range(wx0, wx1):
            r, g, b = frames[fi][y, x]
            is_white[fi, y-wy0, x-wx0] = 1 if (r,g,b)==(255,255,255) else 0

print("White pixel map per frame (y=41-48, x=44-51):")
for fi in range(nframes):
    rows = []
    for y_idx in range(wy1-wy0):
        row = ''.join('#' if is_white[fi, y_idx, x_idx] else '.' for x_idx in range(wx1-wx0))
        rows.append(row)
    print(f"F{fi:02d}: {' '.join(rows)}")

# Average: which pixels are white in MORE than half the frames
avg = is_white.mean(axis=0)
print("\nAverage presence per pixel:")
for y_idx in range(wy1-wy0):
    row = ''.join(f'{avg[y_idx,x_idx]:.0f}' for x_idx in range(wx1-wx0))
    print(f"  y{wy0+y_idx}: {row}")

print("\nAverage threshold >= 0.5:")
baseline = (avg >= 0.5).astype(int)
for y_idx in range(wy1-wy0):
    row = ''.join('#' if baseline[y_idx,x_idx] else '.' for x_idx in range(wx1-wx0))
    print(f"  y{wy0+y_idx}: |{row}|")

# Deviation from average for each frame
print("\nDeviation bits per frame (1=above average, 0=below):")
all_bits = []
bit_positions = [(y_idx, x_idx) for y_idx in range(wy1-wy0) for x_idx in range(wx1-wx0)]

for fi in range(nframes):
    bits = [is_white[fi, y_idx, x_idx] for y_idx, x_idx in bit_positions]
    all_bits.append(bits)
    
    # Decode as MSB
    out = bytearray()
    for i in range(0, len(bits)-7, 8):
        b = 0
        for j in range(8):
            b = (b<<1) | bits[i+j]
        out.append(b)
    text = ''.join(chr(b) if 32<=b<127 else '.' for b in out)
    found = ' FOUND!' if b'UMASS' in out else ''
    print(f"F{fi}: {''.join(str(b) for b in bits)} = {text}{found}")

# Scan ALL possible orderings/offsets across concatenated frames
print("\nScanning for UMASS in all-frame bitstream...")
combined = []
for fi in range(nframes):
    combined.extend(all_bits[fi])

print(f"Total bits: {len(combined)}")

# Try different orderings
def pack_bits_msb(bits):
    out = bytearray()
    for i in range(0, len(bits)-7, 8):
        b = 0
        for j in range(8):
            b = (b<<1)|bits[i+j]
        out.append(b)
    return out

def pack_bits_lsb(bits):
    out = bytearray()
    for i in range(0, len(bits)-7, 8):
        b = 0
        for j in range(8):
            b |= bits[i+j] << j
        out.append(b)
    return out

# Try: read bits column by column (x-major order across grid)
col_major = [(y_idx, x_idx) for x_idx in range(wx1-wx0) for y_idx in range(wy1-wy0)]
for fi in range(nframes):
    bits = [is_white[fi, y_idx, x_idx] for y_idx, x_idx in col_major]
    decoded = pack_bits_msb(bits)
    if b'UMASS' in decoded:
        print(f"FOUND in frame {fi} col-major!")
        print(decoded)

# Try temporal ordering: for each pixel position, its value across all 12 frames
temporal_order = []
for y_idx, x_idx in bit_positions:
    for fi in range(nframes):
        temporal_order.append(is_white[fi, y_idx, x_idx])

decoded_temporal = pack_bits_msb(temporal_order)
text_t = ''.join(chr(b) if 32<=b<127 else '.' for b in decoded_temporal[:80])
print(f"\nTemporal MSB: {text_t}")
if b'UMASS' in decoded_temporal:
    print("FOUND in temporal order!")

# Try: interleave frames (F0_b0, F1_b0, F2_b0, ..., F11_b0, F0_b1, ...)
interleaved = []
n_bits = len(bit_positions)
for bit_idx in range(n_bits):
    for fi in range(nframes):
        y_idx, x_idx = bit_positions[bit_idx]
        interleaved.append(is_white[fi, y_idx, x_idx])

decoded_i = pack_bits_msb(interleaved)
text_i = ''.join(chr(b) if 32<=b<127 else '.' for b in decoded_i[:80])
print(f"Interleaved MSB: {text_i}")
if b'UMASS' in decoded_i:
    print("FOUND in interleaved order!")
