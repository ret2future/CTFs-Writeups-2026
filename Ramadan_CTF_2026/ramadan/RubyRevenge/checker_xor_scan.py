from pathlib import Path
import subprocess


def load_pbm(path):
    lines = [line.strip() for line in Path(path).read_text().splitlines() if line.strip()]
    w, h = map(int, lines[1].split())
    vals = [int(x) for line in lines[2:] for x in line.split()]
    m = [vals[i * w:(i + 1) * w] for i in range(h)]
    return w, h, m


def write_pbm(path, m):
    h = len(m)
    w = len(m[0]) if h else 0
    with open(path, 'w', encoding='ascii') as f:
        f.write('P1\n')
        f.write(f'{w} {h}\n')
        for r in m:
            f.write(' '.join(str(v) for v in r) + '\n')


def scale(m, s=12):
    out = []
    for r in m:
        rr = []
        for v in r:
            rr.extend([v] * s)
        for _ in range(s):
            out.append(rr[:])
    return out


w, h, m = load_pbm('rep_odd.pbm')
active_rows = [i for i, r in enumerate(m) if sum(r) > 0]
mat = [m[i] for i in active_rows]

# Try XOR against checkerboards with different phase and optional crop
cases = []
for x0 in [0, 8, 12, 16]:
    sub = [r[x0:] for r in mat]
    for phase in [0, 1]:
        xored = []
        for y, r in enumerate(sub):
            row = []
            for x, v in enumerate(r):
                c = (x + y + phase) & 1
                row.append(v ^ c)
            xored.append(row)
        cases.append((f'x{x0}_p{phase}', sub, xored))

for name, sub, xored in cases:
    for label, mm in [('sub', sub), ('xored', xored), ('xored_inv', [[0 if v else 1 for v in r] for r in xored])]:
        fn = f'ch_{name}_{label}.pbm'
        write_pbm(fn, scale(mm, s=10))
        out = subprocess.run([
            '/opt/homebrew/bin/tesseract', fn, 'stdout', '--psm', '6',
            '-c', 'tessedit_char_whitelist=ABCDEFGHIJKLMNOPQRSTUVWXYZ{}_0123456789'
        ], capture_output=True, text=True).stdout.strip().replace('\n', ' ')
        if out:
            print(fn, '=>', out)
