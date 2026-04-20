import csv

rows = []
with open('/root/umass2026CTF/Brick_by_Brick_Hardware/starting_files/extracted/code.csv') as f:
    reader = csv.DictReader(f)
    for row in reader:
        rows.append(int(row['logic_level']))

sample_interval = 2.9960751415645503e-05
sample_rate = 1.0 / sample_interval  # ~33377 Hz

def decode_uart_8n1(signal, baud):
    bit_period = sample_rate / baud
    result = []
    n = len(signal)
    i = 0
    while i < n and signal[i] == 0:
        i += 1
    while i < n:
        if signal[i] == 1 and (i + 1) < n and signal[i + 1] == 0:
            start = i + 1
            mid_start = int(start + bit_period * 0.5)
            if mid_start >= n or signal[mid_start] != 0:
                i += 1
                continue
            byte = 0
            valid = True
            for bit_idx in range(8):
                sample_pos = int(start + (bit_idx + 1.5) * bit_period)
                if sample_pos >= n:
                    valid = False
                    break
                byte |= signal[sample_pos] << bit_idx
            if not valid:
                break
            stop_pos = int(start + 9.5 * bit_period)
            if stop_pos < n and signal[stop_pos] == 1:
                result.append(byte)
            i = int(start + 10 * bit_period)
        else:
            i += 1
    return bytes(result)

# Try a wide range including non-standard rates
candidates = [
    300, 600, 1200, 2400, 4800, 9600, 14400, 19200, 28800, 38400, 57600, 115200,
    16667, 16689, 33333, 33377, 11111, 11126, 5556, 6667, 7813, 10000, 12500, 25000, 50000,
    3333, 6250, 8333, 16500, 20000, 31250, 62500,
]

for baud in sorted(set(candidates)):
    decoded = decode_uart_8n1(rows, baud)
    if len(decoded) == 0:
        continue
    printable_count = sum(1 for b in decoded if 32 <= b < 127)
    ratio = printable_count / len(decoded) if decoded else 0
    printable = ''.join(chr(b) if 32 <= b < 127 else '.' for b in decoded[:80])
    umass = ' <-- FLAG FOUND!' if b'UMASS' in decoded else ''
    print(f"Baud {baud:7d} ({sample_rate/baud:.3f} samp/bit): {len(decoded):5d} bytes  ratio={ratio:.2f}  [{printable}]{umass}")
