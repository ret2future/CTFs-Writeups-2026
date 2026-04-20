from itertools import permutations
from pathlib import Path

COMMON = ['THE', 'THIS', 'THAT', 'FLAG', 'VBD', 'SECRET', 'MESSAGE', 'REVENGE', 'RUBY', 'CTF', 'CODE', 'EASY', 'USE', 'YOU', 'ARE']


def load_pbm(path):
    lines = [line.strip() for line in Path(path).read_text().splitlines() if line.strip()]
    w, h = map(int, lines[1].split())
    vals = [int(x) for line in lines[2:] for x in line.split()]
    m = [vals[i * w:(i + 1) * w] for i in range(h)]
    return w, h, m


def score(s):
    if not s:
        return -1
    printable = sum(1 for c in s if 32 <= ord(c) < 127) / len(s)
    letters = sum(1 for c in s if c.isalpha() or c in '_{}') / len(s)
    vowels = sum(1 for c in s.upper() if c in 'AEIOU') / max(1, sum(1 for c in s if c.isalpha()))
    pat = 0
    for t in COMMON:
        if t in s.upper():
            pat += 0.2
    # prefer mixed letters and not repetitive
    uniq = len(set(s)) / len(s)
    return printable * 0.25 + letters * 0.45 + (1.0 - abs(vowels - 0.4)) * 0.15 + uniq * 0.15 + pat


w, h, m = load_pbm('rep_odd.pbm')
rows = [9, 10, 11, 13, 14, 15, 17]

cands = []
for perm in permutations(rows):
    for inv in (0, 1):
        for reverse_cols in (False, True):
            xs = list(range(17, 47, 2))
            if reverse_cols:
                xs = xs[::-1]
            out = []
            for x in xs:
                v = 0
                for y in perm:
                    bit = m[y][x] ^ inv
                    v = (v << 1) | bit
                out.append(chr(v) if 32 <= v < 127 else '.')
            s = ''.join(out)
            sc = score(s)
            if sc > 0.80:
                cands.append((sc, perm, inv, reverse_cols, s))

cands.sort(reverse=True, key=lambda t: t[0])
for sc, perm, inv, rev, s in cands[:200]:
    print(f'{sc:.3f} perm={perm} inv={inv} rev={rev} :: {s}')
