import nbtlib

n = nbtlib.load('RubyRevenge_dec.schem', gzipped=False)
H = int(n['Height'])
L = int(n['Length'])
W = int(n['Width'])
b = [int(x) for x in n['BlockData']]
X = len(range(0, L, 2))

# 1) repeater-only map on full Y grid
rep = [[1 if b[(y * L + z) * W] == 3 else 0 for z in range(0, L, 2)] for y in range(H - 1, -1, -1)]

# 2) odd Y rows only (compressed)
rep_odd = [[1 if b[(y * L + z) * W] == 3 else 0 for z in range(0, L, 2)] for y in range(H - 1, -1, -2)]


def write_pbm(path, mat):
    h = len(mat)
    w = len(mat[0]) if h else 0
    with open(path, 'w', encoding='ascii') as f:
        f.write('P1\n')
        f.write(f'{w} {h}\n')
        for row in mat:
            f.write(' '.join(str(v) for v in row))
            f.write('\n')


write_pbm('rep_full.pbm', rep)
write_pbm('rep_odd.pbm', rep_odd)

# 3) Inverted versions
rep_inv = [[0 if v else 1 for v in row] for row in rep]
rep_odd_inv = [[0 if v else 1 for v in row] for row in rep_odd]
write_pbm('rep_full_inv.pbm', rep_inv)
write_pbm('rep_odd_inv.pbm', rep_odd_inv)

print('Wrote PBM files:', X, 'x', H, 'and odd rows', len(rep_odd))
