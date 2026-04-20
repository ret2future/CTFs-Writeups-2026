import csv

rows = []
with open('/root/umass2026CTF/Brick_by_Brick_Hardware/starting_files/extracted/code.csv') as f:
    reader = csv.DictReader(f)
    for row in reader:
        rows.append(int(row['logic_level']))

sample_interval = 2.9960751415645503e-05
sample_rate = 1.0 / sample_interval

def decode_uart_8n1(signal, baud):
    bit_period = sample_rate / baud
    result = []
    n = len(signal)
    i = 0
    while i < n and signal[i] == 0:
        i += 1
    while i < n:
        if signal[i] == 1 and (i+1) < n and signal[i+1] == 0:
            start = i + 1
            mid_start = int(start + bit_period * 0.5)
            if mid_start >= n or signal[mid_start] != 0:
                i += 1
                continue
            byte = 0
            valid = True
            for bit_idx in range(8):
                sp = int(start + (bit_idx + 1.5) * bit_period)
                if sp >= n:
                    valid = False
                    break
                byte |= signal[sp] << bit_idx
            if not valid:
                break
            stop_pos = int(start + 9.5 * bit_period)
            if stop_pos < n and signal[stop_pos] == 1:
                result.append(byte)
            i = int(start + 10 * bit_period)
        else:
            i += 1
    return bytes(result)

decoded = decode_uart_8n1(rows, 33377)
text = decoded.decode('latin-1')
print(text)
