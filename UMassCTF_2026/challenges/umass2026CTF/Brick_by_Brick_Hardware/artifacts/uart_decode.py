import csv

rows = []
with open('/root/umass2026CTF/Brick_by_Brick_Hardware/starting_files/extracted/code.csv') as f:
    reader = csv.DictReader(f)
    for row in reader:
        rows.append(int(row['logic_level']))

sample_interval = 2.9960751415645503e-05
sample_rate = 1.0 / sample_interval  # ~33377 Hz

def decode_uart_8n1(signal, baud, sample_rate):
    bit_period = sample_rate / baud  # samples per bit
    result = []
    n = len(signal)
    i = 0

    # Skip initial LOW or wait for idle HIGH
    while i < n and signal[i] == 0:
        i += 1

    while i < n:
        # Look for falling edge (start bit)
        if signal[i] == 1 and (i + 1) < n and signal[i + 1] == 0:
            start = i + 1  # start bit begins here

            # Confirm start bit in middle
            mid_start = int(start + bit_period * 0.5)
            if mid_start >= n or signal[mid_start] != 0:
                i += 1
                continue

            # Sample 8 data bits (LSB first for UART)
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

            # Check stop bit
            stop_pos = int(start + 9.5 * bit_period)
            if stop_pos < n and signal[stop_pos] == 1:
                result.append(byte)
            
            # Advance past this frame
            i = int(start + 10 * bit_period)
        else:
            i += 1

    return bytes(result)


print(f"Sample rate: {sample_rate:.0f} Hz, interval: {sample_interval*1e6:.2f} µs\n")

for baud in [300, 600, 1200, 2400, 4800, 9600, 14400, 19200, 28800, 38400, 57600, 115200]:
    decoded = decode_uart_8n1(rows, baud, sample_rate)
    printable = ''.join(chr(b) if 32 <= b < 127 else '.' for b in decoded[:80])
    umass = 'FLAG FOUND' if b'UMASS' in decoded else ''
    print(f"Baud {baud:6d}: {len(decoded):4d} bytes  {umass}")
    print(f"  [{printable}]")
