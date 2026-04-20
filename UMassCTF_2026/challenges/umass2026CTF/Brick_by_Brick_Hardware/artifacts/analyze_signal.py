import csv

rows = []
with open('/root/umass2026CTF/Brick_by_Brick_Hardware/starting_files/extracted/code.csv') as f:
    reader = csv.DictReader(f)
    for row in reader:
        rows.append((float(row['timestamp']), int(row['logic_level'])))

sample_interval = rows[1][0] - rows[0][0]
print(f"Total samples: {len(rows)}")
print(f"Sample interval: {sample_interval:.6e} s  ({1/sample_interval:.0f} Hz)")
print(f"Total duration: {rows[-1][0]:.4f} s")

# Find run lengths between transitions
runs = []
start_idx = 0
for i in range(1, len(rows)):
    if rows[i][1] != rows[i-1][1]:
        runs.append((rows[i-1][1], i - start_idx))
        start_idx = i
runs.append((rows[-1][1], len(rows) - start_idx))

print(f"\nFirst 20 runs (level, n_samples, duration_µs):")
for r in runs[:20]:
    print(f"  level={r[0]} samples={r[1]:5d} duration={r[1]*sample_interval*1e6:.1f}µs")

min_run = min(r[1] for r in runs)
print(f"\nMin run length: {min_run} samples = {min_run*sample_interval*1e6:.1f}µs")
print(f"Implied baud rate: {1/(min_run*sample_interval):.0f}")

# Common baud rates to check
for baud in [300, 1200, 2400, 4800, 9600, 19200, 38400, 57600, 115200]:
    bit_width_samples = 1/baud / sample_interval
    print(f"  baud={baud:6d}: bit_width={bit_width_samples:.2f} samples")
