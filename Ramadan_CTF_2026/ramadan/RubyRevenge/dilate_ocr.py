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


def dilate(m, rx, ry):
    h = len(m)
    w = len(m[0])
    out = [[0] * w for _ in range(h)]
    for y in range(h):
        for x in range(w):
            if m[y][x] == 0:
                continue
            for yy in range(max(0, y - ry), min(h, y + ry + 1)):
                for xx in range(max(0, x - rx), min(w, x + rx + 1)):
                    out[yy][xx] = 1
    return out


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
# keep active vertical band
m = m[9:36]

for inv in (0, 1):
    base = [[v ^ inv for v in r] for r in m]
    for rx in range(0, 5):
        for ry in range(0, 3):
            d = dilate(base, rx, ry)
            s = scale(d, s=10)
            fn = f'dil_inv{inv}_rx{rx}_ry{ry}.pbm'
            write_pbm(fn, s)
            for psm in (6, 7, 11):
                txt = subprocess.run([
                    '/opt/homebrew/bin/tesseract', fn, 'stdout', '--psm', str(psm),
                    '-c', 'tessedit_char_whitelist=ABCDEFGHIJKLMNOPQRSTUVWXYZ{}_0123456789'
                ], capture_output=True, text=True).stdout.strip().replace('\n', ' ')
                if txt and len(txt) >= 2:
                    print(fn, 'psm', psm, '=>', txt)
