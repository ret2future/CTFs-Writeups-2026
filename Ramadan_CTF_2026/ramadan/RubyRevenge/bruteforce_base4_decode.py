import itertools
import nbtlib

n = nbtlib.load('RubyRevenge_dec.schem', gzipped=False)
vals = [int(x) for x in n['BlockData']]


def score(data):
    if not data:
        return 0.0, 0.0
    printable = sum(1 for c in data if 32 <= c < 127 or c in (9, 10, 13)) / len(data)
    alpha = sum(1 for c in data if 65 <= c <= 90 or 97 <= c <= 122 or c in (95, 123, 125)) / len(data)
    return printable, alpha


def decode_with_map(sequence, map2bits, reverse_symbols=False):
    bits = []
    for v in sequence:
        pair = map2bits[v]
        if reverse_symbols:
            pair = pair[::-1]
        bits.extend(pair)
    data = []
    for i in range(0, len(bits) - 7, 8):
        b = 0
        for bit in bits[i:i + 8]:
            b = (b << 1) | bit
        data.append(b)
    return bytes(data)


# all permutations assigning 2-bit pairs to values 0,1,2,3
pairs = [(0, 0), (0, 1), (1, 0), (1, 1)]
candidates = []
for perm in itertools.permutations(pairs):
    mapping = {i: perm[i] for i in range(4)}
    for reverse_seq in (False, True):
        seq = list(reversed(vals)) if reverse_seq else vals
        for reverse_symbols in (False, True):
            data = decode_with_map(seq, mapping, reverse_symbols=reverse_symbols)
            text = data.decode('latin1', errors='ignore')
            printable, alpha = score(data)
            if 'VBD' in text or 'flag' in text.lower() or printable > 0.95:
                candidates.append((printable, alpha, mapping, reverse_seq, reverse_symbols, text[:220]))

candidates.sort(reverse=True)
for printable, alpha, mapping, reverse_seq, reverse_symbols, snippet in candidates[:80]:
    print(f'map={mapping} reverse_seq={reverse_seq} reverse_symbols={reverse_symbols} printable={printable:.3f} alpha={alpha:.3f}')
    print(snippet.replace('\n', '\\n'))
    print('-' * 60)

if not candidates:
    print('No high-printability candidates found.')
