from pathlib import Path


def load_pbm(path):
    lines = [line.strip() for line in Path(path).read_text().splitlines() if line.strip()]
    assert lines[0] == 'P1'
    w, h = map(int, lines[1].split())
    vals = [int(x) for line in lines[2:] for x in line.split()]
    assert len(vals) == w * h
    m = [vals[i * w:(i + 1) * w] for i in range(h)]
    return w, h, m


def runs(indices):
    if not indices:
        return []
    out = []
    s = indices[0]
    p = indices[0]
    for x in indices[1:]:
        if x == p + 1:
            p = x
            continue
        out.append((s, p, p - s + 1))
        s = p = x
    out.append((s, p, p - s + 1))
    return out


w, h, m = load_pbm('rep_odd.pbm')

row_sums = [sum(r) for r in m]
col_sums = [sum(m[y][x] for y in range(h)) for x in range(w)]
active_rows = [i for i, s in enumerate(row_sums) if s > 0]
active_cols = [i for i, s in enumerate(col_sums) if s > 0]

print('size', w, h)
print('active rows', active_rows)
print('active row runs', runs(active_rows))
print('active cols', active_cols)
print('active col runs', runs(active_cols))

print('\nRows (index: sum: bitmap)')
for i, r in enumerate(m):
    if row_sums[i] == 0:
        continue
    s = ''.join('#' if v else '.' for v in r)
    print(f'{i:02d}: {row_sums[i]:02d}: {s}')

print('\nColumn sums:')
for i, s in enumerate(col_sums):
    if s:
        print(i, s)

# Test split hypotheses by blank rows/cols
print('\nCandidate vertical segmentation widths')
for width in range(3, 13):
    if w % width == 0:
        chunks = w // width
        entropy = []
        for c in range(chunks):
            x0 = c * width
            x1 = x0 + width
            ones = sum(m[y][x] for y in range(h) for x in range(x0, x1))
            entropy.append(ones)
        print('width', width, 'chunks', chunks, 'ones-per-chunk', entropy)
