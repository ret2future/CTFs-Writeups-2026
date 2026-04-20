# Ninja_Nerds

## Challenge Summary
- Given: `challenge.png` — 640×360 8-bit RGB PNG, LEGO Ninjago battle scene
- Goal: Find the hidden flag
- Constraints: Standard PNG, no trailing data, no EXIF steganography

## Initial Recon / Triage
- `pngcheck`: clean, well-formed PNG
- `exiftool`: no suspicious metadata
- `binwalk`: one false positive (JBOOT) at offset 0x3C386, no real embedded files
- `zsteg -a`: standard scan returned no readable hits (missed the flag — see below)
- R channel LSB distribution: nearly 50/50 (115247 ones vs 115153 zeros) — consistent with LSB steg
- R channel missing values [0,1,2,3,4,6] — minor anomaly, ultimately not the key lead
- Image content: LEGO Ninjago characters fighting, purple crystal background

## Hypotheses & Approach
- Hypothesis 1: LSB steganography in one of the RGB channels — confirmed
- Hypothesis 2: zsteg would find it — **wrong**: zsteg uses LSB-first bit packing; the payload uses MSB-first

## Execution Steps (Reproducible)

### Stage 1 — Brute-force bit-plane extraction
```python
# artifacts/lsb_scan.py
# Tries all combinations:
#   - row-major (xy) and column-major (yx) pixel ordering
#   - R, G, B channels
#   - bits 0-3
#   - MSB-first and LSB-first byte packing
# Scans each extraction for b'UMASS'
```

Result: `xy B bit0 MSB @ byte 14440` → `b'UMASS{perfectly-hidden-ready-to-strike}'`

### Stage 2 — Why zsteg missed it
zsteg's `b1,b,lsb,xy` uses LSB-first bit packing. The payload was packed **MSB-first**, so zsteg's default scan never recovers readable text. A manual extraction with MSB-first packing reveals the flag immediately.

## Artifacts Produced
- `artifacts/lsb_scan.py` — comprehensive bit-plane extractor that found the flag
- `artifacts/analyze_image.py` — earlier bitplane/lowbit amplification script
- `artifacts/zsteg.txt` — zsteg -a output (no hits)
- `artifacts/bitplanes/` — all 24 RGB bitplane images (r/g/b × bits 0-7)

## Flag
```
UMASS{perfectly-hidden-ready-to-strike}
```
