from pathlib import Path
import subprocess
import nbtlib

n = nbtlib.load('RubyRevenge_dec.schem', gzipped=False)
H = int(n['Height'])
L = int(n['Length'])
W = int(n['Width'])
vals = [int(x) for x in n['BlockData']]

# map y top->bottom, z left->right
mat = []
for y in range(H - 1, -1, -1):
    row = []
    for z in range(L):
        v = vals[(y * L + z) * W]
        row.append(v)
    mat.append(row)

# printable dump
sym = {0: '.', 1: 'W', 2: 'M', 3: 'R'}
rows = [''.join(sym.get(v, '?') for v in r) for r in mat]
Path('full_symbols.txt').write_text('\n'.join(rows), encoding='ascii')
print('wrote full_symbols.txt')

# Build grayscale PBM-like variants (thresholded by symbol classes)
# class variants keep more information than repeater-only
variants = {
    'non_air': [[1 if v != 0 else 0 for v in r] for r in mat],
    'wm_only': [[1 if v in (1, 2) else 0 for v in r] for r in mat],
    'white_only': [[1 if v == 1 else 0 for v in r] for r in mat],
    'magenta_only': [[1 if v == 2 else 0 for v in r] for r in mat],
    'repeater_only': [[1 if v == 3 else 0 for v in r] for r in mat],
    'not_magenta': [[1 if v != 2 else 0 for v in r] for r in mat],
    'not_white': [[1 if v != 1 else 0 for v in r] for r in mat],
}


def scale(m, s=6):
    out = []
    for r in m:
        rr = []
        for v in r:
            rr.extend([v] * s)
        for _ in range(s):
            out.append(rr[:])
    return out


def write_pbm(path, m):
    h = len(m)
    w = len(m[0])
    with open(path, 'w', encoding='ascii') as f:
        f.write('P1\n')
        f.write(f'{w} {h}\n')
        for r in m:
            f.write(' '.join(str(v) for v in r) + '\n')


for name, m in variants.items():
    for inv in (False, True):
        mm = [[0 if v else 1 for v in r] for r in m] if inv else m
        fn = f'{name}_{"inv" if inv else "norm"}.pbm'
        write_pbm(fn, scale(mm, s=8))
        out = subprocess.run([
            '/opt/homebrew/bin/tesseract', fn, 'stdout', '--psm', '6',
            '-c', 'tessedit_char_whitelist=ABCDEFGHIJKLMNOPQRSTUVWXYZ{}_0123456789'
        ], capture_output=True, text=True).stdout.strip().replace('\n', ' ')
        if out:
            print(fn, '=>', out)
