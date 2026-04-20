#!/usr/bin/env python3
"""Try pixel-level LSB steganography across all frames."""
from PIL import Image
import numpy as np

frames = []
for i in range(12):
    img = Image.open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif')
    img.seek(i)
    frames.append(np.array(img.convert('RGB')))

def decode_lsb(bits, lsb_num=1):
    """Pack bits into bytes MSB first."""
    out = bytearray()
    mask = (1 << lsb_num) - 1
    collected = []
    for b in bits:
        for i in range(lsb_num - 1, -1, -1):
            collected.append((b >> i) & 1)
        while len(collected) >= 8:
            byte_val = 0
            for bit in collected[:8]:
                byte_val = (byte_val << 1) | bit
            out.append(byte_val)
            collected = collected[8:]
    return out

def search_flag(data, label):
    printable = ''.join(chr(b) if 32 <= b < 127 else '.' for b in data[:100])
    found = 'FOUND!' if b'UMASS' in data else ''
    print(f"  {label}: {printable[:80]} {found}")

# Try all combinations
for fi in range(12):
    f = frames[fi]
    print(f"\n=== Frame {fi} ===")
    
    # Flatten in row order
    flat_r = f[:, :, 0].flatten()
    flat_g = f[:, :, 1].flatten()
    flat_b = f[:, :, 2].flatten()
    
    for channel, flat in [('R', flat_r), ('G', flat_g), ('B', flat_b)]:
        for lsb in [1, 2]:
            decoded = decode_lsb(flat, lsb)
            if b'UMASS' in decoded:
                search_flag(decoded, f"Frame{fi} {channel} LSB{lsb}")
    
    # Interleaved R,G,B LSB
    interleaved = []
    for y in range(70):
        for x in range(100):
            interleaved.extend([f[y,x,0], f[y,x,1], f[y,x,2]])
    decoded = decode_lsb(interleaved, 1)
    if b'UMASS' in decoded:
        search_flag(decoded, f"Frame{fi} RGB interleaved LSB1")

# Combine all frames
print("\n=== All frames R LSBs combined ===")
all_r = np.concatenate([frames[fi][:,:,0].flatten() for fi in range(12)])
decoded_r = decode_lsb(all_r, 1)
search_flag(decoded_r, "All frames R LSB1")
if b'UMASS' in decoded_r:
    idx = decoded_r.find(b'UMASS')
    print(f"  Found at byte {idx}: {decoded_r[idx:idx+50]}")

print("\n=== All frames G LSBs combined ===")
all_g = np.concatenate([frames[fi][:,:,1].flatten() for fi in range(12)])
decoded_g = decode_lsb(all_g, 1)
search_flag(decoded_g, "All frames G LSB1")

print("\n=== All frames B LSBs combined ===")
all_b = np.concatenate([frames[fi][:,:,2].flatten() for fi in range(12)])
decoded_b = decode_lsb(all_b, 1)
search_flag(decoded_b, "All frames B LSB1")

# Try interleaving all frames' pixels
print("\n=== Interleaved per frame (F0p0,F1p0,...,F11p0,F0p1,...) ===")
interleaved_all = []
for pix_idx in range(100*70):
    for fi in range(12):
        r, g, b = frames[fi].flatten()[pix_idx*3:pix_idx*3+3]
        interleaved_all.append(r)
decoded = decode_lsb(interleaved_all, 1)
search_flag(decoded, "Temporal pixel interleave R LSB1")
if b'UMASS' in decoded:
    idx = decoded.find(b'UMASS')
    print(f"  Found at byte {idx}: {decoded[idx:idx+50]}")

print("\n=== Looking for UMASS in any single-frame channels ===")
for fi in range(12):
    for channel in range(3):
        data = frames[fi][:,:,channel].flatten()
        decoded = decode_lsb(data, 1)
        if b'UMASS' in decoded:
            idx = decoded.find(b'UMASS')
            print(f"  Frame {fi} channel {channel} LSB1 at byte {idx}: {decoded[idx:idx+50]}")

print("\nDone. If no FOUND!, checking first 100 bytes of all-frames-R-LSB:")
print(' '.join(f'{b:02x}' for b in decoded_r[:50]))
