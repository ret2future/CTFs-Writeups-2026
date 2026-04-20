# The_Accursed_Lego_Bin

## Challenge Summary
- Given: `encoder.py` and `output.txt` from the challenge zip.
- Goal: recover the original flag from the shuffled bitstream in `output.txt`.
- Constraints: no remote service; the only operations are a homemade RSA-based seed derivation and ten deterministic `random.shuffle` rounds.

## Initial Recon / Triage
- Observations: `encoder.py` encrypts the known plaintext `I_LOVE_RNG` with RSA exponent `e = 7`, then uses the resulting ciphertext as the seed source for repeated shuffles of the flag bits.
- File identification: `output.txt` contains `seed = enc_seed` and `flag = <hex>`, where the hex decodes to bytes whose bit pattern is the shuffled flag bit array.
- Entry points: if the RSA reductions never wrapped modulo `n`, then `seed = m^7` and `enc_seed = seed^7 = m^49` exactly, so the seed is recoverable by an integer seventh root.

## Hypotheses & Approach
- Hypothesis 1: because `I_LOVE_RNG` is only 10 bytes, $m^{49}$ is still below the 4096-bit modulus, so the published `enc_seed` is an exact seventh power.
- Hypothesis 2: once the seed is known, each `random.shuffle` can be replayed and inverted in reverse order to restore the original flag bits.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
cd /root/umass2026CTF/The_Accursed_Lego_Bin/artifacts
python3 - <<'PY'
from pathlib import Path
print(Path('encoder.py').read_text())
print(Path('output.txt').read_text())
PY
```

Results:
- Confirmed the encoder does the following:
	- computes `seed = pow(m, 7, n)` where `m = int.from_bytes(b"I_LOVE_RNG", "big")`
	- shuffles the flag bits ten times with seeds `seed * (i + 1)` for `i = 0..9`
	- writes `enc_seed = pow(seed, 7, n)` and the shuffled bits encoded as hex bytes

### Stage 2
Commands:
```bash
cd /root/umass2026CTF/The_Accursed_Lego_Bin/artifacts
python3 solve.py
```

Results:
- Recovered `seed` as the exact integer seventh root of `enc_seed`.
- Verified `seed == int.from_bytes(b"I_LOVE_RNG", "big") ** 7`.
- Rebuilt the shuffle permutation for each round and inverted the rounds from 9 down to 0.
- Decoded the restored bit array into the final flag string.

## Artifacts Produced
- `artifacts/encoder.py`
- `artifacts/output.txt`
- `artifacts/solve.py`

## Flag
```text
UMASS{tH4Nk5_f0R_uN5CR4m8L1nG_mY_M3554g3}
```
