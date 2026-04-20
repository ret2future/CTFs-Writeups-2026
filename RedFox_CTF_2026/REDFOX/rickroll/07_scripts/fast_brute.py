from PIL import Image
import numpy as np
import re

img = np.array(Image.open('pic.png'), dtype=np.uint8)
ch_map = {'r': 0, 'g': 1, 'b': 2, 'a': 3}
channel_sets = ['r', 'g', 'b', 'a', 'rg', 'rb', 'gb', 'rgb', 'bgr', 'rgba', 'abgr']
bit_sets = [(0,), (1,), (0, 1), (0, 1, 2)]
bit_orders = ['msb', 'lsb']

patterns = [
    re.compile(rb'flag\{[^\n\r]{0,120}?\}', re.I),
    re.compile(rb'ctf\{[^\n\r]{0,120}?\}', re.I),
    re.compile(rb'[A-Za-z0-9_\-]{2,24}\{[^\n\r]{0,120}?\}', re.I),
    re.compile(rb'flag|ctf|brook|rick|astley|yoho|onepiece|one piece', re.I),
]

flat_ch = {k: img[:, :, i].reshape(-1) for k, i in ch_map.items()}


def decode(chset, bits, bit_order):
    # Build bit stream in pixel-major then channel-major then bit-major order.
    arr = np.stack([flat_ch[c] for c in chset], axis=1)  # (N, C)
    bit_planes = [((arr >> b) & 1).astype(np.uint8) for b in bits]
    bits_arr = np.stack(bit_planes, axis=2).reshape(-1)
    usable = (bits_arr.size // 8) * 8
    bits_arr = bits_arr[:usable].reshape(-1, 8)
    if bit_order == 'msb':
        weights = np.array([128, 64, 32, 16, 8, 4, 2, 1], dtype=np.uint8)
    else:
        weights = np.array([1, 2, 4, 8, 16, 32, 64, 128], dtype=np.uint8)
    out = (bits_arr * weights).sum(axis=1).astype(np.uint8)
    return out.tobytes()

hits = []
for ch in channel_sets:
    for bs in bit_sets:
        for bo in bit_orders:
            data = decode(ch, bs, bo)
            for p in patterns:
                m = p.search(data)
                if m:
                    s = max(0, m.start() - 40)
                    e = min(len(data), m.end() + 120)
                    snippet = data[s:e]
                    hits.append((ch, bs, bo, snippet))
                    break

print('hits:', len(hits))
for ch, bs, bo, snip in hits:
    print('---', ch, bs, bo)
    print(repr(snip))
