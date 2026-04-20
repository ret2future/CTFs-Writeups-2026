#!/usr/bin/env python3
"""Extract flag from indices 1 vs 3 (both RGB 11,41,71) using raw LZW decoder."""

def decode_lzw(data, min_code_size):
    clear_code = 1 << min_code_size
    eoi_code = clear_code + 1
    code_size = min_code_size + 1
    code_table = {i: [i] for i in range(clear_code)}
    result = []
    prev_code = None
    bits = []
    for byte in data:
        for i in range(8):
            bits.append((byte >> i) & 1)
    pos = 0

    def read_code():
        nonlocal pos
        val = 0
        for i in range(code_size):
            if pos < len(bits):
                val |= bits[pos] << i
                pos += 1
        return val

    next_code = eoi_code + 1
    max_codes = 1 << code_size

    while pos < len(bits):
        code = read_code()
        if code == clear_code:
            code_size = min_code_size + 1
            max_codes = 1 << code_size
            code_table = {i: [i] for i in range(clear_code)}
            next_code = eoi_code + 1
            prev_code = None
            continue
        if code == eoi_code:
            break
        if code in code_table:
            entry = list(code_table[code])
        elif prev_code is not None and code == next_code:
            entry = list(code_table[prev_code]) + [code_table[prev_code][0]]
        else:
            break
        result.extend(entry)
        if prev_code is not None:
            new_entry = list(code_table[prev_code]) + [entry[0]]
            code_table[next_code] = new_entry
            next_code += 1
            if next_code >= max_codes and code_size < 12:
                code_size += 1
                max_codes = 1 << code_size
        prev_code = code
    return result

# Parse GIF and decode all frames
data = open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif', 'rb').read()
pos = 13 + 8 * 3  # after GCT
frames_indices = []

while pos < len(data):
    bid = data[pos]
    if bid == 0x3B:
        break
    elif bid == 0x21:
        pos += 2
        while True:
            sl = data[pos]; pos += 1
            if sl == 0: break
            pos += sl
    elif bid == 0x2C:
        pos += 10
        pkd = data[pos - 1]
        if (pkd >> 7) & 1:
            lct_count = 2 ** ((pkd & 7) + 1)
            pos += lct_count * 3
        min_code_size = data[pos]; pos += 1
        raw_lzw = b''
        while True:
            sl = data[pos]; pos += 1
            if sl == 0: break
            raw_lzw += data[pos:pos + sl]
            pos += sl
        indices = decode_lzw(raw_lzw, min_code_size)
        frames_indices.append(indices[:7000])
    else:
        pos += 1

print(f"Decoded {len(frames_indices)} frames\n")

# Extract bits from index 1 vs 3 pair
# Index 1 = bit 0, index 3 = bit 1
# Skip indices 0 (black), 2 (navy), 4 or 6 (yellow), 5 (white), 7 (black)

def pack_bytes_msb(bits):
    out = bytearray()
    for i in range(0, len(bits)-7, 8):
        b = 0
        for j in range(8): b = (b<<1)|bits[i+j]
        out.append(b)
    return out

def pack_bytes_lsb(bits):
    out = bytearray()
    for i in range(0, len(bits)-7, 8):
        b = 0
        for j in range(8): b |= bits[i+j] << j
        out.append(b)
    return out

# Method 1: All frames concatenated, only 1/3 pair
print("=== Method 1: Indices 1 and 3 from all frames ===")
all_bits_13 = []
for fi, frame in enumerate(frames_indices):
    frame_bits = []
    for idx in frame:
        if idx == 1:
            frame_bits.append(0)
        elif idx == 3:
            frame_bits.append(1)
    all_bits_13.extend(frame_bits)
    out = pack_bytes_msb(frame_bits)
    txt = ''.join(chr(b) if 32<=b<127 else '.' for b in out[:40])
    found = ' *** FOUND ***' if b'UMASS' in out else ''
    print(f"Frame {fi}: {len(frame_bits)} bits, MSB: {txt}{found}")

# All frames together
out_all = pack_bytes_msb(all_bits_13)
print(f"\nAll frames MSB: {''.join(chr(b) if 32<=b<127 else '.' for b in out_all[:100])}")
if b'UMASS' in out_all:
    idx = out_all.find(b'UMASS')
    print(f"FOUND at {idx}: {out_all[idx:idx+60]}")

out_all_lsb = pack_bytes_lsb(all_bits_13)
print(f"All frames LSB: {''.join(chr(b) if 32<=b<127 else '.' for b in out_all_lsb[:100])}")
if b'UMASS' in out_all_lsb:
    idx = out_all_lsb.find(b'UMASS')
    print(f"FOUND at {idx}: {out_all_lsb[idx:idx+60]}")

# Method 2: only frame 0's 1/3 bits
print("\n=== Method 2: Only frame 0, 1/3 pair ===")
frame0_bits_13 = [0 if idx==1 else 1 for idx in frames_indices[0] if idx in (1,3)]
print(f"Frame 0 has {len(frame0_bits_13)} bits from indices 1/3")
out0 = pack_bytes_msb(frame0_bits_13)
txt0 = ''.join(chr(b) if 32<=b<127 else '.' for b in out0[:100])
print(f"MSB: {txt0}")
if b'UMASS' in out0:
    print("FOUND!")

# Method 3: For each position, write index 1 or 3 as bit; skip others
# This preserves spatial ordering
print("\n=== Method 3: Positional (include NUL bits for non-1/3 pixels) ===")
for fi in range(1):
    # For frame 0: for each pixel in order, bit = idx==3 ? 1 : idx==1 ? 0 : skip
    # But also: what if we include a 0-bit for ALL non-3 pixels?
    bits_include_all = [1 if idx==3 else 0 for idx in frames_indices[0]]
    out_full = pack_bytes_msb(bits_include_all)
    txt = ''.join(chr(b) if 32<=b<127 else '.' for b in out_full[:100])
    print(f"Frame 0 (all pixels, idx3=1, rest=0) MSB: {txt}")
    if b'UMASS' in out_full:
        idx = out_full.find(b'UMASS')
        print(f"FOUND at {idx}: {out_full[idx:idx+60]}")
    
    # Same but idx1 vs all-others
    bits_1v3 = [0 if idx==1 else 1 for idx in frames_indices[0]]
    out_1v3 = pack_bytes_msb(bits_1v3)
    txt = ''.join(chr(b) if 32<=b<127 else '.' for b in out_1v3[:100])
    print(f"Frame 0 (idx1=0, rest=1) MSB: {txt}")

# Try just looking at the FIRST 52 bytes (flag-sized) from various methods
print("\n=== First 80 bytes from each method ===")
for name, bits in [
    ("frame0 1/3 only", [0 if idx==1 else 1 for idx in frames_indices[0] if idx in (1,3)]),
    ("all frames 1/3 concat", all_bits_13),
]:
    for order in ['MSB', 'LSB']:
        out = pack_bytes_msb(bits) if order=='MSB' else pack_bytes_lsb(bits)
        txt = ''.join(chr(b) if 32<=b<127 else '.' for b in out[:80])
        flag = ' FOUND!' if b'UMASS' in out else ''
        print(f"{name} {order}: {txt}{flag}")
