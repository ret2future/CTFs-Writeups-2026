from PIL import Image
import re
import numpy as np

img = np.array(Image.open('pic.png'))
ch_names = ['r', 'g', 'b', 'a']
idx = {n: i for i, n in enumerate(ch_names)}

channel_sets = ['r', 'g', 'b', 'a', 'rg', 'rb', 'ra', 'gb', 'ga', 'ba', 'rgb', 'bgr', 'rgba', 'abgr']
bit_sets = [(0,), (1,), (0, 1), (0, 1, 2), (7,), (6, 7)]
bit_orders = ['msb', 'lsb']

patterns = [
    rb'flag\{[^\n\r]{0,120}?\}',
    rb'ctf\{[^\n\r]{0,120}?\}',
    rb'[A-Za-z0-9_\-]{2,24}\{[^\n\r]{0,120}?\}',
]
kw = re.compile(rb'flag|ctf|brook|rick|astley|onepiece|one piece|yoho', re.I)

flat = {n: img[:, :, i].reshape(-1) for n, i in idx.items()}


def bits_from(chset, bits):
    arrs = [flat[c] for c in chset]
    n = len(arrs[0])
    for p in range(n):
        for a in arrs:
            v = int(a[p])
            for b in bits:
                yield (v >> b) & 1


def pack(bit_iter, order='msb'):
    out = bytearray()
    cur = []
    for bit in bit_iter:
        cur.append(bit)
        if len(cur) == 8:
            if order == 'msb':
                v = 0
                for x in cur:
                    v = (v << 1) | x
            else:
                v = 0
                for i, x in enumerate(cur):
                    v |= (x << i)
            out.append(v)
            cur.clear()
    return bytes(out)


hits = []
for chset in channel_sets:
    for bits in bit_sets:
        for bo in bit_orders:
            data = pack(bits_from(chset, bits), bo)
            found = []
            for p in patterns:
                m = re.search(p, data, re.I)
                if m:
                    found.append(m.group(0)[:200])
            if not found:
                m = kw.search(data)
                if m:
                    s = max(0, m.start() - 50)
                    e = min(len(data), m.end() + 160)
                    found.append(data[s:e])
            if found:
                hits.append((chset, bits, bo, found[:3]))

print('total hits', len(hits))
for chset, bits, bo, found in hits:
    print('---', chset, bits, bo)
    for item in found:
        print(repr(item))
