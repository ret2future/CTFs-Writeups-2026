# Brick_by_Brick_Hardware

## Challenge Summary
- Given: `brick-by-brick.zip` → `code.csv` — 56990 rows of `(timestamp, logic_level)` sampled at ~33377 Hz
- Goal: Decode the intercepted signal from a "custom LEGO controller"
- Constraints: Raw digital signal capture, baud rate unknown

## Initial Recon / Triage
- File: `code.csv`, 56990 samples, sample interval ~29.96 µs (~33377 Hz sample rate)
- Logic levels: binary (0/1)
- Signal idle-high, transitions to low to begin — classic UART idle state
- Hints confirmed: UART 8N1, falling-edge start bit, baud rate unknown
- Run-length GCD = 1 sample (30 µs) — no obvious base period from GCD alone
- Run length histogram: dominant lengths of 1, 2, 5, 6 samples

## Hypotheses & Approach
- Hypothesis 1: Standard baud rates (9600, 115200, etc.) — all failed, garbled output
- Hypothesis 2: Non-standard baud rate matching the sample rate itself — confirmed

## Execution Steps (Reproducible)

### Stage 1 — Baud rate sweep
```python
# artifacts/baud_sweep.py
# Tries baud rates from 300 to 115200 + non-standard candidates
# Scores each by printable ASCII ratio
```

Key finding: baud=33377 (1.000 samples/bit) → 3799 bytes, **98% printable**

### Stage 2 — Full decode
```python
# artifacts/decode_full.py
# UART 8N1 decoder: detects falling edge, samples 8 data bits (LSB-first)
# + stop bit, at baud=33377
```

Decoded output: Linux kernel boot log (`[    0.000000] Linux version 6.8.1...`)

Flag hidden in a custom kernel module log line at timestamp 0.037500:
```
[    0.037500] secretflag: 554d4153537b553452375f31355f3768335f623335372c5f72316768373f7d
```

```python
bytes.fromhex('554d4153537b553452375f31355f3768335f623335372c5f72316768373f7d').decode()
# → UMASS{U4R7_15_7h3_b357,_r1gh7?}
```

### Why standard baud rates failed
The capture sample rate (~33377 Hz) equals the baud rate — 1 sample per bit. Standard decoders expect oversampling and don't try 1:1 rates.

## Artifacts Produced
- `artifacts/analyze_signal.py` — initial signal statistics (sample rate, run lengths)
- `artifacts/gcd_analysis.py` — GCD / run-length histogram analysis
- `artifacts/baud_sweep.py` — exhaustive baud rate sweep with printable-ratio scoring
- `artifacts/decode_full.py` — final UART 8N1 decoder at 33377 baud
- `artifacts/decoded_output.txt` — full decoded Linux boot log

## Flag
```
UMASS{U4R7_15_7h3_b357,_r1gh7?}
```
