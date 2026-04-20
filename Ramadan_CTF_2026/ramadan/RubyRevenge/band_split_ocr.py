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
    w = len(m[0])
    with open(path, 'w', encoding='ascii') as f:
        f.write('P1\n')
        f.write(f'{w} {h}\n')
        for r in m:
            f.write(' '.join(str(v) for v in r) + '\n')


def scale(m, sx=12, sy=12):
    out = []
    for r in m:
        rr = []
        for v in r:
            rr.extend([v] * sx)
        for _ in range(sy):
            out.append(rr[:])
    return out


w, h, m = load_pbm('rep_odd.pbm')
bands = [(9, 11), (13, 18), (21, 24), (26, 27), (29, 35)]

for b0, b1 in bands:
    band = m[b0:b1 + 1]
    col_sum = [sum(r[x] for r in band) for x in range(w)]
    nz = [i for i, s in enumerate(col_sum) if s > 0]
    if nz:
        x0, x1 = min(nz), max(nz)
    else:
        x0, x1 = 0, w - 1
    cropped = [r[x0:x1 + 1] for r in band]
    print(f'band {b0}-{b1} x={x0}-{x1} w={x1-x0+1} h={len(cropped)}')

    for inv in (0, 1):
        mm = [[v ^ inv for v in r] for r in cropped]
        for sx, sy in [(20, 20), (30, 20), (20, 30)]:
            s = scale(mm, sx, sy)
            fn = f'band_{b0}_{b1}_inv{inv}_{sx}x{sy}.pbm'
            write_pbm(fn, s)
            txt = subprocess.run([
                '/opt/homebrew/bin/tesseract', fn, 'stdout', '--psm', '6',
                '-c', 'tessedit_char_whitelist=ABCDEFGHIJKLMNOPQRSTUVWXYZ{}_0123456789'
            ], capture_output=True, text=True).stdout.strip().replace('\n', ' ')
            if txt:
                print(' ', fn, '=>', txt)
