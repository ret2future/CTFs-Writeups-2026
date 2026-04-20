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


def scale(m, sx=8, sy=8):
    out = []
    for r in m:
        rr = []
        for v in r:
            rr.extend([v] * sx)
        for _ in range(sy):
            out.append(rr[:])
    return out


def transpose(m):
    h = len(m)
    w = len(m[0])
    return [[m[y][x] for y in range(h)] for x in range(w)]


w, h, m = load_pbm('rep_odd.pbm')
active_rows = [i for i, r in enumerate(m) if sum(r) > 0]
active = [m[i] for i in active_rows]

# split left/right by empirically observed transition
left = [r[:16] for r in active]
right = [r[16:] for r in active]

variants = {
    'active': active,
    'active_inv': [[0 if v else 1 for v in r] for r in active],
    'left': left,
    'left_inv': [[0 if v else 1 for v in r] for r in left],
    'right': right,
    'right_inv': [[0 if v else 1 for v in r] for r in right],
    'active_T': transpose(active),
    'active_T_inv': [[0 if v else 1 for v in r] for r in transpose(active)],
    'left_T': transpose(left),
    'left_T_inv': [[0 if v else 1 for v in r] for r in transpose(left)],
}

for name, mat in variants.items():
    for sx, sy in [(8, 8), (12, 12), (16, 16)]:
        s = scale(mat, sx=sx, sy=sy)
        fn = f'var_{name}_{sx}x{sy}.pbm'
        write_pbm(fn, s)
        out = subprocess.run([
            '/opt/homebrew/bin/tesseract', fn, 'stdout', '--psm', '6',
            '-c', 'tessedit_char_whitelist=ABCDEFGHIJKLMNOPQRSTUVWXYZ{}_0123456789'
        ], capture_output=True, text=True).stdout.strip().replace('\n', ' ')
        if out:
            print(fn, '=>', out)
