import itertools
import nbtlib

n = nbtlib.load('RubyRevenge_dec.schem', gzipped=False)
vals = [int(x) for x in n['BlockData']]


def bits_to_bytes(bits, msb=True, offset=0):
    bits = bits[offset:]
    size = (len(bits) // 8) * 8
    bits = bits[:size]
    out = []
    for i in range(0, size, 8):
        chunk = bits[i:i + 8]
        if not msb:
            chunk = list(reversed(chunk))
        value = 0
        for bit in chunk:
            value = (value << 1) | bit
        out.append(value)
    return bytes(out)


def score(data):
    if not data:
        return 0.0, 0.0
    printable = sum(1 for c in data if 32 <= c < 127 or c in (9, 10, 13)) / len(data)
    alpha = sum(1 for c in data if 65 <= c <= 90 or 97 <= c <= 122 or c in (95, 123, 125)) / len(data)
    return printable, alpha


candidates = []
value_set = [0, 1, 2, 3]
for mask in range(1, 15):
    one_values = {value_set[i] for i in range(4) if (mask >> i) & 1}
    for reverse in (False, True):
        seq = list(reversed(vals)) if reverse else vals
        bits = [1 if v in one_values else 0 for v in seq]
        for msb in (True, False):
            for offset in range(8):
                data = bits_to_bytes(bits, msb=msb, offset=offset)
                text = data.decode('latin1', errors='ignore')
                printable, alpha = score(data)
                if 'VBD' in text or 'flag' in text.lower() or printable > 0.95:
                    candidates.append((printable, alpha, one_values, reverse, msb, offset, text[:220]))

candidates.sort(reverse=True)
for printable, alpha, one_values, reverse, msb, offset, snippet in candidates[:80]:
    print(f'ones={sorted(one_values)} reverse={reverse} msb={msb} offset={offset} printable={printable:.3f} alpha={alpha:.3f}')
    print(snippet.replace('\n', '\\n'))
    print('-' * 60)

if not candidates:
    print('No high-printability candidates found.')
