import csv
from math import gcd
from functools import reduce
from collections import Counter

rows = []
with open('/root/umass2026CTF/Brick_by_Brick_Hardware/starting_files/extracted/code.csv') as f:
    reader = csv.DictReader(f)
    for row in reader:
        rows.append((float(row['timestamp']), int(row['logic_level'])))

sample_interval = rows[1][0] - rows[0][0]

# Find run lengths
runs = []
start_idx = 0
for i in range(1, len(rows)):
    if rows[i][1] != rows[i-1][1]:
        runs.append((rows[i-1][1], i - start_idx))
        start_idx = i
runs.append((rows[-1][1], len(rows) - start_idx))

lengths = [r[1] for r in runs]
g = reduce(gcd, lengths)
print(f"GCD of all run lengths: {g} sample(s) = {g*sample_interval*1e6:.1f}us")
print(f"Unit period: {g*sample_interval*1e6:.1f}us -> {1/(g*sample_interval):.0f} baud")

hist = Counter(lengths)
print("\nRun length histogram (samples: count):")
for k in sorted(hist.keys())[:20]:
    print(f"  {k:4d} samples ({k*sample_interval*1e6:.1f}us): {hist[k]} occurrences")

print("\nFirst 40 runs (level, n_units):")
print([(r[0], r[1]//g) for r in runs[:40]])
