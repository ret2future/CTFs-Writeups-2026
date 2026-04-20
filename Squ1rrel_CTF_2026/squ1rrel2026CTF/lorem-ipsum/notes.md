# lorem-ipsum

## Challenge Summary
- Given: a single PDF file, `lorem_ipsum_dolor.pdf`.
- Goal: recover the hidden flag from the manuscript.
- Constraints: no explicit service or interaction, just offline analysis of the PDF.

## Initial Recon / Triage
- The PDF looked ordinary at first glance, but `pdfid` showed two xref/trailer sections, which indicated an incremental update.
- The current file rendered as 16 pages, while the earlier embedded revision still contained 17 pages.
- The challenge description about cutting the manuscript down was literal: removed content was still recoverable from the older revision.

## Hypotheses & Approach
- Hypothesis 1: the flag was hidden in a prior version of the PDF rather than only in the visible current pages.
- Hypothesis 2: reconstructing and diffing the older revision against the current revision would expose the removed text.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
cd /root/squ1rrel2026CTF/lorem-ipsum
pdfinfo starting_files/lorem_ipsum_dolor.pdf
pdfid starting_files/lorem_ipsum_dolor.pdf
python3 - <<'PY'
from pathlib import Path
pdf = Path('starting_files/lorem_ipsum_dolor.pdf').read_bytes()
first_start = pdf.find(b'startxref')
first_eof = pdf.find(b'%%EOF', first_start)
Path('artifacts/revision1.pdf').write_bytes(pdf[:first_eof + 5])
PY
pdfinfo artifacts/revision1.pdf
```

Results:
- The live PDF had an incremental-update structure with two xref sections.
- Reconstructing the first revision produced a valid earlier PDF with 17 pages instead of 16.

### Stage 2
Commands:
```bash
cd /root/squ1rrel2026CTF/lorem-ipsum
pdftotext -layout starting_files/lorem_ipsum_dolor.pdf artifacts/full_text.txt
pdftotext -layout artifacts/revision1.pdf artifacts/revision1.txt
python3 - <<'PY'
from pathlib import Path
rev = Path('artifacts/revision1.txt').read_text(errors='ignore').splitlines()
cur = Path('artifacts/full_text.txt').read_text(errors='ignore').splitlines()
import difflib
for line in difflib.unified_diff(cur, rev, fromfile='current', tofile='revision1', n=1):
	print(line)
PY
```

Results:
- The diff showed text that had been removed from the current PDF but still existed in the older revision.
- The recovered hidden line was `squ1rrel{d4n6_17_y0u_f0und_m3!}`.

## Artifacts Produced
- `artifacts/full_text.txt` - text extracted from the current PDF.
- `artifacts/revision1.pdf` - reconstructed earlier PDF revision.
- `artifacts/revision1.txt` - text extracted from the older revision.

## Flag
```text
squ1rrel{d4n6_17_y0u_f0und_m3!}
```
