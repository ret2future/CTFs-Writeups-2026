#!/usr/bin/env python3
"""Map positions of index 1 pixels (the rare ones) to see if they form text."""

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

data = open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif', 'rb').read()
pos = 13 + 8 * 3
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
            pos += 2 ** ((pkd & 7) + 1) * 3
        min_code_size = data[pos]; pos += 1
        raw_lzw = b''
        while True:
            sl = data[pos]; pos += 1
            if sl == 0: break
            raw_lzw += data[pos:pos + sl]
            pos += sl
        frames_indices.append(decode_lzw(raw_lzw, min_code_size)[:7000])
    else:
        pos += 1

WIDTH, HEIGHT = 100, 70

# For each frame, find where index 1 pixels are
print("=== Index 1 pixel positions per frame ===")
for fi, frame in enumerate(frames_indices):
    idx1_positions = [(i % WIDTH, i // WIDTH) for i, v in enumerate(frame) if v == 1]
    print(f"\nFrame {fi}: {len(idx1_positions)} index-1 pixels")
    
    # Display as image map
    grid = [[' '] * WIDTH for _ in range(HEIGHT)]
    for x, y in idx1_positions:
        grid[y][x] = '1'
    
    # Print non-empty rows
    for y in range(HEIGHT):
        row = ''.join(grid[y])
        if '1' in row:
            print(f"  y{y:2d}: {row}")

# Also check: are the index 1 positions CONSTANT across all frames?
print("\n=== Are index 1 positions same in all frames? ===")
frame0_idx1 = set((i % WIDTH, i // WIDTH) for i, v in enumerate(frames_indices[0]) if v == 1)
for fi in range(1, len(frames_indices)):
    frame_idx1 = set((i % WIDTH, i // WIDTH) for i, v in enumerate(frames_indices[fi]) if v == 1)
    same = frame0_idx1 == frame_idx1
    diff = frame0_idx1.symmetric_difference(frame_idx1)
    print(f"Frame {fi}: same={same}, diff_count={len(diff)}")
    if diff:
        print(f"  diff: {sorted(diff)[:10]}")
