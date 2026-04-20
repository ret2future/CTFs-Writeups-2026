from itertools import combinations
from pathlib import Path


def load_pbm(path):
    lines = [line.strip() for line in Path(path).read_text().splitlines() if line.strip()]
    w, h = map(int, lines[1].split())
    vals = [int(x) for line in lines[2:] for x in line.split()]
    m = [vals[i * w:(i + 1) * w] for i in range(h)]
    return w, h, m


def score_text(s):
    if not s:
        return 0.0
    printable = sum(1 for ch in s if 32 <= ord(ch) < 127) / len(s)
    letters = sum(1 for ch in s if ch.isalpha() or ch in '_{}') / len(s)
    unique = len(set(s)) / len(s)
    return printable * 0.4 + letters * 0.5 + unique * 0.1


w, h, m = load_pbm('rep_odd.pbm')
active = [i for i, r in enumerate(m) if sum(r) > 0]

x_ranges = [(0, 16), (16, 47), (0, 47)]
cands = []

# Try 7-bit and 8-bit column-wise decoding from row sets in active bands.
for rlen in (7, 8):
    for rows in combinations(active, rlen):
        # keep somewhat local bands only
        if max(rows) - min(rows) > 10:
            continue
        for reverse_rows in (False, True):
            row_order = list(rows[::-1] if reverse_rows else rows)
            for x0, x1 in x_ranges:
                for parity_mode in (0, 1, 2):
                    # 0=no xor, 1=x%2 xor, 2=(x+1)%2 xor
                    chars = []
                    for x in range(x0, x1):
                        v = 0
                        for y in row_order:
                            bit = m[y][x]
                            if parity_mode == 1:
                                bit ^= (x & 1)
                            elif parity_mode == 2:
                                bit ^= ((x + 1) & 1)
                            v = (v << 1) | bit
                        chars.append(chr(v) if 32 <= v < 127 else '.')
                    s = ''.join(chars)
                    sc = score_text(s)
                    if sc > 0.60:
                        cands.append((sc, row_order, (x0, x1), parity_mode, s))

cands.sort(reverse=True, key=lambda t: t[0])
for sc, rows, xr, pm, s in cands[:120]:
    print(f'score={sc:.3f} rows={rows} x={xr} parity={pm} :: {s}')
