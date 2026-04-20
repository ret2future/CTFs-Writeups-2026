from itertools import combinations
from pathlib import Path
import math


def load_pbm(path):
    lines = [line.strip() for line in Path(path).read_text().splitlines() if line.strip()]
    w, h = map(int, lines[1].split())
    vals = [int(x) for line in lines[2:] for x in line.split()]
    m = [vals[i * w:(i + 1) * w] for i in range(h)]
    return w, h, m


def entropy(s):
    if not s:
        return 0
    from collections import Counter
    c = Counter(s)
    n = len(s)
    return -sum((v / n) * math.log2(v / n) for v in c.values())


def looks_text(s):
    if not s:
        return -1
    printable = sum(1 for ch in s if 32 <= ord(ch) < 127) / len(s)
    letters = sum(1 for ch in s if ch.isalpha() or ch in '_{}') / len(s)
    vowels = sum(1 for ch in s.lower() if ch in 'aeiou') / max(1, sum(1 for ch in s if ch.isalpha()))
    e = entropy(s)
    # penalize repetitive patterns like abababa or same char
    unique = len(set(s)) / len(s)
    rep_penalty = 0
    if len(s) >= 6 and s[:2] * (len(s) // 2) in s + s:
        rep_penalty += 0.2
    return printable * 0.35 + letters * 0.35 + min(e / 4.5, 1.0) * 0.2 + unique * 0.1 - rep_penalty


w, h, m = load_pbm('rep_odd.pbm')
active = [i for i, r in enumerate(m) if sum(r) > 0]

cands = []
for rlen in (7, 8):
    for rows in combinations(active, rlen):
        if max(rows) - min(rows) > 12:
            continue
        for rev in (False, True):
            row_order = list(rows[::-1] if rev else rows)
            for step in (1, 2):
                for start in range(0, 2):
                    for x0, x1 in ((0, w), (16, w), (0, 20), (0, 16), (5, 30)):
                        chars = []
                        for x in range(x0 + start, x1, step):
                            v = 0
                            for y in row_order:
                                bit = m[y][x]
                                v = (v << 1) | bit
                            chars.append(chr(v) if 32 <= v < 127 else '.')
                        s = ''.join(chars)
                        sc = looks_text(s)
                        if sc > 0.62 and len(s) >= 8:
                            cands.append((sc, row_order, step, start, (x0, x1), s))

cands.sort(reverse=True, key=lambda t: t[0])
for sc, rows, step, start, xr, s in cands[:200]:
    print(f'{sc:.3f} rows={rows} step={step} start={start} x={xr} :: {s}')
