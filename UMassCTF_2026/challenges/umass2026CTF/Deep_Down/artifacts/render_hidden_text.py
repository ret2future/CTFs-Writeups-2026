#!/usr/bin/env python3
"""Render the index-1 pixel text from the GIF."""
from PIL import Image
import numpy as np

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

# Use frame 0 (all frames have same index-1 positions)
frame = frames_indices[0]

# Create index-1 highlight image
idx1_image = np.zeros((HEIGHT, WIDTH, 3), dtype=np.uint8)
for pix_idx, idx in enumerate(frame):
    x = pix_idx % WIDTH
    y = pix_idx // WIDTH
    if idx == 1:
        idx1_image[y, x] = [255, 255, 255]  # white
    elif idx == 3:
        idx1_image[y, x] = [30, 30, 30]  # dark gray
    elif idx == 0:
        idx1_image[y, x] = [0, 0, 0]  # black
    elif idx == 2:
        idx1_image[y, x] = [20, 20, 20]  # nearly black
    elif idx in (4, 6):
        idx1_image[y, x] = [200, 180, 50]  # yellow
    elif idx == 5:
        idx1_image[y, x] = [200, 200, 200]  # light gray

# Save zoomed versions
ZOOM = 10
img = Image.fromarray(idx1_image)
img_big = img.resize((WIDTH * ZOOM, HEIGHT * ZOOM), Image.NEAREST)
img_big.save('/root/umass2026CTF/Deep_Down/artifacts/idx1_highlight.png')
print(f"Saved idx1_highlight.png ({WIDTH*ZOOM}x{HEIGHT*ZOOM})")

# Just the text region y=52-68
text_region = idx1_image[52:68, :]
text_img = Image.fromarray(text_region)
text_big = text_img.resize((WIDTH * ZOOM, 16 * ZOOM), Image.NEAREST)
text_big.save('/root/umass2026CTF/Deep_Down/artifacts/idx1_text_region.png')
print(f"Saved idx1_text_region.png")

# Print ASCII art of JUST the index 1 text area
print("\n=== Index-1 text region (y=52-68, full width) ===")
for y in range(52, 68):
    row = ''
    for x in range(WIDTH):
        if frame[y * WIDTH + x] == 1:
            row += '#'
        else:
            row += ' '
    if row.strip():
        print(f"y{y:2d}: |{row}|")

print("\nDone!")
