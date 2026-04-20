#!/usr/bin/env python3
"""Raw LZW decoder to get actual palette indices for all GIF frames."""

from collections import Counter

def decode_lzw(data, min_code_size):
    """Decode LZW-compressed GIF data. Returns list of pixel indices."""
    clear_code = 1 << min_code_size
    eoi_code = clear_code + 1
    
    code_size = min_code_size + 1
    code_table = {}
    for i in range(clear_code):
        code_table[i] = [i]
    
    result = []
    prev_code = None
    
    # Build bit stream (LSB first, as per GIF spec)
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
            code_table = {}
            for i in range(clear_code):
                code_table[i] = [i]
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

# Parse the GIF and extract all frame pixel data
data = open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif', 'rb').read()
gct_end = 13 + 8 * 3  # 37
pos = gct_end
frame_num = 0
frames_indices = []

while pos < len(data):
    block_id = data[pos]
    if block_id == 0x3B:
        break
    elif block_id == 0x21:
        pos += 2
        while True:
            sl = data[pos]; pos += 1
            if sl == 0:
                break
            pos += sl
    elif block_id == 0x2C:
        pos += 10
        pkd = data[pos - 1]
        if (pkd >> 7) & 1:
            lct_count = 2 ** ((pkd & 7) + 1)
            pos += lct_count * 3
        min_code_size = data[pos]; pos += 1
        raw_lzw = b''
        while True:
            sl = data[pos]; pos += 1
            if sl == 0:
                break
            raw_lzw += data[pos:pos + sl]
            pos += sl
        indices = decode_lzw(raw_lzw, min_code_size)
        cnt = Counter(indices)
        print(f"Frame {frame_num}: {len(indices)} pixels, indices={dict(sorted(cnt.items()))}")
        frames_indices.append(indices)
        frame_num += 1
    else:
        pos += 1

print(f"\nTotal frames decoded: {len(frames_indices)}")

# Now check: does any frame contain BOTH index 6 and index 4?
print("\nYellow pair usage per frame:")
for fi, f in enumerate(frames_indices):
    c = Counter(f)
    print(f"  Frame {fi}: idx4={c.get(4,0)}, idx6={c.get(6,0)}, idx1={c.get(1,0)}, idx3={c.get(3,0)}, idx0={c.get(0,0)}, idx7={c.get(7,0)}")

# Extract bits from each pair and try to decode
def extract_bits(indices, pair_mode='all_pairs'):
    bits = []
    for idx in indices:
        if idx == 0:
            bits.append(0)
        elif idx == 7:
            bits.append(1)
        elif idx == 1:
            bits.append(0)
        elif idx == 3:
            bits.append(1)
        elif idx == 4:
            bits.append(0)
        elif idx == 6:
            bits.append(1)
        # skip 2 (navy) and 5 (white)
    return bits

# Try treating all 12 frames concatenated as a single bit stream
all_indices = []
for f in frames_indices:
    all_indices.extend(f)

print("\n--- All frames concatenated, pair bits MSB-first ---")
bits = extract_bits(all_indices)
print(f"Total bits: {len(bits)}")
out = bytearray()
for i in range(0, len(bits) - 7, 8):
    b = 0
    for j in range(8):
        b = (b << 1) | bits[i + j]
    out.append(b)
prt = ''.join(chr(b) if 32 <= b < 127 else '.' for b in out[:80])
print(f"MSB: [{prt}]")

# LSB
out2 = bytearray()
for i in range(0, len(bits) - 7, 8):
    b = 0
    for j in range(8):
        b = b | (bits[i + j] << j)
    out2.append(b)
prt2 = ''.join(chr(b) if 32 <= b < 127 else '.' for b in out2[:80])
print(f"LSB: [{prt2}]")

print("\nUMASS search:", b'UMASS' in out, b'UMASS' in out2)

# Per-frame attempt
print("\n--- Per frame, pair bits ---")
for fi, f in enumerate(frames_indices):
    bits = extract_bits(f)
    out = bytearray()
    for i in range(0, len(bits) - 7, 8):
        b = 0
        for j in range(8):
            b = (b << 1) | bits[i + j]
        out.append(b)
    prt = ''.join(chr(b) if 32 <= b < 127 else '.' for b in out[:40])
    found = ' FOUND!' if b'UMASS' in out else ''
    print(f"  Frame {fi}: {prt}{found}")
