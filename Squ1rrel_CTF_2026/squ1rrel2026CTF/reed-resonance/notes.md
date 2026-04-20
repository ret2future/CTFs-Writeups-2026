# reed-resonance

## Challenge Summary
- Given: A single image, `starting_files/circuit.png`, showing a two-stage active filter built from ideal op-amp blocks, resistors, and capacitors.
- Goal: Determine the circuit's resonant frequency and report it truncated to three decimal places, with no rounding.
- Constraints: The flag format is `squ1rrel{[DIGITS_BEFORE_DECIMAL].XXX}`.

## Initial Recon / Triage
- Observations: The image is not a reverse-engineering binary challenge in practice; it is a circuit-analysis problem. The first stage is a non-inverting Sallen-Key low-pass filter. The second stage is a non-inverting Sallen-Key high-pass filter. Cascading them yields a band-pass response with a single magnitude peak.
- File identification: `starting_files/circuit.png` is a 920x320 PNG containing the full circuit and all component values.
- Entry points: Read the component values from the schematic, derive the transfer function of each stage, multiply them, then maximize `|H(j\omega)|`.

## Hypotheses & Approach
- Hypothesis 1: The left block is a Sallen-Key low-pass stage because the op-amp is configured non-inverting and the RC network feeds the non-inverting input with a shunt capacitor to ground.
- Hypothesis 2: The right block is a Sallen-Key high-pass stage because the input path is capacitive and the lower node is resistively returned to ground.

Using ideal op-amp assumptions, the overall transfer function is:

`H(s) = 22084650000 s^2 / (3282547198203 s^4 + 1439549899502470 s^3 + 17211285679391000 s^2 + 38134621487500000 s + 82025000000000)`

The overall magnitude `|H(j\omega)|` has a single peak.

Important detail: the challenge accepted the resonant frequency expressed in angular frequency (`rad/s`), not in `Hz`.

For reference, the same peak occurs at:

- `f_peak = 0.830244770956505 Hz`
- `\omega_peak = 2\pi f_peak = 5.216581746236590 rad/s`

Truncating without rounding gives `5.216`.

The earlier Hz interpretation is mathematically reasonable for a filter problem, but the live endpoint rejects it.

Using ideal op-amp assumptions, the two stages can also be viewed as:

`H1(s) = K1 / (1 + a1 s + b1 s^2)`

with:

- `K1 = 1 + 100k / 17M`
- `a1 = (R1 + R2) C2 + R1 C1 (1 - K1)`
- `b1 = R1 R2 C1 C2`

for `R1 = 91k`, `R2 = 7.9k`, `C1 = 47uF`, `C2 = 4.7mF`

and:

`H2(s) = K2 * (b2 s^2) / (1 + a2 s + b2 s^2)`

with:

- `K2 = 1 + 1.2M / 19.3M`
- `a2 = R3 C3 + R4 C3 + R4 C4 (1 - K2)`
- `b2 = R3 R4 C3 C4`

for `R3 = 10k`, `R4 = 900`, `C3 = 7uF`, `C4 = 4uF`

The full transfer function is `H(s) = H1(s) * H2(s)`. The resonant frequency is the `f > 0` that maximizes `|H(j 2\pi f)|`.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
cd /root/squ1rrel2026CTF/reed-resonance/starting_files
file circuit.png
```

Results:
- Confirmed the provided artifact is a PNG schematic image.
- Transcribed the component values from the drawing:
- `R1 = 91k`, `R2 = 7.9k`, `C1 = 47uF`, `C2 = 4.7mF`, gain stage `K1 = 1 + 100k/17M`
- `R3 = 10k`, `R4 = 900`, `C3 = 7uF`, `C4 = 4uF`, gain stage `K2 = 1 + 1.2M/19.3M`

### Stage 2
Commands:
```bash
cd /root/squ1rrel2026CTF/reed-resonance
python3 artifacts/solve_reed_resonance.py
python3 artifacts/solve_nodal.py
python3 artifacts/exact_symbolic.py
```

Results:
- The direct nodal-analysis and exact symbolic transfer-function solvers agree on the same overall peak in hertz.
- The exact symbolic solver gives:
- `H(s) = 22084650000 s^2 / (3282547198203 s^4 + 1439549899502470 s^3 + 17211285679391000 s^2 + 38134621487500000 s + 82025000000000)`
- Numerical peak results:
- `peak_hz = 0.830244770956505`
- `peak_rad_s = 5.216581746236590`
- Truncation without rounding gives:
- `0.830` in hertz
- `5.216` in rad/s

### Stage 3
Commands:
```bash
cd /root/squ1rrel2026CTF
python3 - <<'PY'
import re, pathlib, urllib.parse as up, requests
text = pathlib.Path('AGENTS.md').read_text()
team_token = up.unquote(re.search(r'token=([^\s)]+)', text).group(1))
auth = requests.post('https://ctf.squ1rrel.dev/api/v1/auth/login', json={'teamToken': team_token}, timeout=20).json()['data']['authToken']
headers = {'Authorization': f'Bearer {auth}'}
flag = 'squ1rrel{5.216}'
resp = requests.post('https://ctf.squ1rrel.dev/api/v1/challs/e7fb6214-bae7-4ca8-9da6-293592b86e42/submit', headers=headers, json={'flag': flag}, timeout=20)
print(resp.status_code)
print(resp.text)
PY
```

Results:
- The live API accepted `squ1rrel{5.216}`.
- This confirms the challenge wanted angular frequency instead of hertz.

## Artifacts Produced
- `artifacts/solve_reed_resonance.py` — reproducible solver for the circuit transfer function.
- `artifacts/solve_nodal.py` — direct nodal-analysis solver for the exact schematic.
- `artifacts/exact_symbolic.py` — exact symbolic derivation of the full transfer function.
- `artifacts/analyze_transfer.py` — response-shape helper for peak and `-3 dB` inspection.
- `artifacts/solver_output.txt` — validated resonance results and final accepted flag.

## Flag
```
squ1rrel{5.216}
```
