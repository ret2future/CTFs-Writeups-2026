import nbtlib

n = nbtlib.load('RubyRevenge_dec.schem', gzipped=False)
H = int(n['Height'])
L = int(n['Length'])
W = int(n['Width'])
block_data = [int(x) for x in n['BlockData']]

x_cells = len(range(0, L, 2))
mat = [
    [1 if block_data[(y * L + z) * W] == 3 else 0 for z in range(0, L, 2)]
    for y in range(H)
]

orders = []

for y_iter, y_name in ((range(H), 'y_up'), (range(H - 1, -1, -1), 'y_down')):
    for x_iter, x_name in ((range(x_cells), 'x_up'), (range(x_cells - 1, -1, -1), 'x_down')):
        bits = []
        for y in y_iter:
            for x in x_iter:
                bits.append(mat[y][x])
        orders.append((f'row_{y_name}_{x_name}', bits))

for x_iter, x_name in ((range(x_cells), 'x_up'), (range(x_cells - 1, -1, -1), 'x_down')):
    for y_iter, y_name in ((range(H), 'y_up'), (range(H - 1, -1, -1), 'y_down')):
        bits = []
        for x in x_iter:
            for y in y_iter:
                bits.append(mat[y][x])
        orders.append((f'col_{x_name}_{y_name}', bits))


def bits_to_bytes(bits, msb=True, offset=0):
    bits = bits[offset:]
    n = (len(bits) // 8) * 8
    bits = bits[:n]
    out = []
    for i in range(0, n, 8):
        chunk = bits[i:i + 8]
        if not msb:
            chunk = chunk[::-1]
        value = 0
        for bit in chunk:
            value = (value << 1) | bit
        out.append(value)
    return bytes(out)


def score(bs):
    printable = sum(1 for c in bs if 32 <= c < 127 or c in (9, 10, 13))
    letters = sum(1 for c in bs if 65 <= c <= 90 or 97 <= c <= 122 or c in (95, 123, 125))
    return printable / len(bs), letters / len(bs)


candidates = []
for name, bits in orders:
    for msb in (True, False):
        for off in range(8):
            data = bits_to_bytes(bits, msb=msb, offset=off)
            if not data:
                continue
            printable, letters = score(data)
            text = data.decode('latin1', errors='ignore')
            if 'VBD' in text or 'flag' in text.lower() or printable > 0.88:
                candidates.append((printable, letters, name, msb, off, text[:220]))

candidates.sort(reverse=True)
for printable, letters, name, msb, off, text in candidates[:40]:
    print(f'{name} msb={msb} offset={off} printable={printable:.3f} letters={letters:.3f}')
    print(text.replace('\n', '\\n'))
    print('-' * 60)
