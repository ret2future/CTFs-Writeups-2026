import subprocess
import nbtlib

n = nbtlib.load('RubyRevenge_dec.schem', gzipped=False)
H = int(n['Height'])
L = int(n['Length'])
W = int(n['Width'])
b = [int(x) for x in n['BlockData']]

# odd layers only often contain signal
mat = [[1 if b[(y * L + z) * W] == 3 else 0 for z in range(0, L, 2)] for y in range(H - 1, -1, -2)]


def rotate(m):
    h = len(m)
    w = len(m[0])
    return [[m[h - 1 - r][c] for r in range(h)] for c in range(w)]


def scale(m, s):
    out = []
    for row in m:
        expanded = []
        for v in row:
            expanded.extend([v] * s)
        for _ in range(s):
            out.append(expanded[:])
    return out


def write_pbm(path, m):
    h = len(m)
    w = len(m[0])
    with open(path, 'w', encoding='ascii') as f:
        f.write('P1\n')
        f.write(f'{w} {h}\n')
        for row in m:
            f.write(' '.join(str(v) for v in row) + '\n')


variants = []
cur = mat
for r in range(4):
    variants.append((f'rot{r * 90}', cur))
    cur = rotate(cur)

for name, m in variants:
    for inv in (False, True):
        mm = [[0 if v else 1 for v in row] for row in m] if inv else m
        for s in (4, 8, 12):
            sm = scale(mm, s)
            filename = f'ocr_{name}_inv{int(inv)}_s{s}.pbm'
            write_pbm(filename, sm)
            proc = subprocess.run([
                '/opt/homebrew/bin/tesseract',
                filename,
                'stdout',
                '--psm',
                '6'
            ], capture_output=True, text=True)
            text = proc.stdout.strip().replace('\n', ' ')
            if text:
                print(filename, '=>', text)
