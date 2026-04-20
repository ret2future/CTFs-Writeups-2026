# knex

## Challenge Summary
- Given: `starting_files/lush.png`
- Goal: recover the hidden flag from the nested stego layers.
- Constraints: free hint gives `alpha = 0.45`, and the challenge title/description point toward BPCS.

## Initial Recon / Triage
- Observations:
	- `lush.png` contains 64 custom PNG chunks of type `l0l4`.
	- Concatenating those chunks yields a JPEG payload.
	- The visible PNG data itself still contains a separate BPCS-based layer.
- File identification:
	- `lush.png` -> hidden `hidden.jpg` -> `teeny.mp3` / `teeny.wav`
	- `teeny.wav` looks like a steghide carrier but is a distraction for the actual solve path.
- Entry points:
	- strip the `l0l4` chunks to rebuild the clean PNG image data.
	- decode BPCS from the clean image using `alpha = 0.45`.

## Hypotheses & Approach
- Hypothesis 1: the custom `l0l4` chunks are one stego layer and may hide a clue or secondary carrier.
- Hypothesis 2: the visible image data uses BPCS in CGC space, and the hint `alpha = 0.45` is the correct threshold.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
python artifacts/extract_l0l4.py
steghide extract -sf artifacts/hidden.jpg -p ''
```

Results:
- `hidden.jpg` was recovered from concatenated `l0l4` chunks.
- `hidden.jpg` contains a steghide payload with an empty passphrase, which extracts `teeny.mp3`.
- Converting/listening to the audio confirms it is Rick Roll audio and not the final flag.

### Stage 2
Commands:
```bash
python artifacts/mobeets_probe.py
python - <<'PY'
from pathlib import Path
import re, base91

payload = Path('artifacts/mobeets_decoded.bin').read_bytes()
strings = re.findall(rb'[ -~]{16,}', payload)
for s in strings:
		try:
				decoded = bytes(base91.decode(s.decode()))
		except Exception:
				continue
		flags = re.findall(rb'UMASS\{[^}]{1,120}\}', decoded)
		if flags:
				print(flags)
				break
PY
```

Results:
- Rebuilt `lush_clean.png` by stripping custom `l0l4` chunks from the original PNG.
- Decoded the BPCS layer from the clean image using the mobeets-style traversal/order and `alpha = 0.45`.
- The resulting stream contains long printable runs that decode cleanly as Base91.
- Base91 decoding yields the flag `UMASS{0N3_D4Y_Y0U_W1LL_83_3MPL0Y3D}`.

## Artifacts Produced
- `artifacts/`
- `artifacts/hidden.jpg`
- `artifacts/lush_clean.png`
- `artifacts/mobeets_decoded.bin`
- `artifacts/mobeets_probe.py`

## Flag
```
UMASS{0N3_D4Y_Y0U_W1LL_83_3MPL0Y3D}
```
