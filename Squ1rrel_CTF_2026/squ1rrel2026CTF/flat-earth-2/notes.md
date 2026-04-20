# flat-earth-2

## Challenge Summary
- Given: a remote verifier at `nc challs.squ1rrel.dev 5004` plus source files `server.py` and `challenge_params.py`.
- Goal: pass 32 proof-verification rounds and recover the flag.
- Constraints: each round provides only a public output value; the server expects a Groth16-style proof JSON.

## Initial Recon / Triage
- `server.py` verifies a Groth16 proof over bn128 using fixed verification-key elements.
- `challenge_params.py` exposes not only the verification key points, but also the trapdoor scalars `GAMMA` and `DELTA`.
- That leak is fatal: with both scalars known, the public-input pairing term can be cancelled directly.

## Hypotheses & Approach
- A valid proof can be forged without the witness if the verifier trapdoor relation is reconstructible from public data.
- Choosing `A = alpha`, `B = beta`, and solving for `C` so that the extra public-input term cancels should satisfy the pairing check.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
cd /root/squ1rrel2026CTF/flat-earth-2
../.venv/bin/python - <<'PY'
import sys
sys.path.insert(0, 'starting_files')
sys.path.insert(0, 'artifacts')
from server import verify_proof
from solve import forge_proof
print(verify_proof(123456789, forge_proof(123456789)))
PY
```

Results:
- The forged proof verifies locally.
- Algebra used: if `IC(out)` is the accumulated public-input point, then set `C = -(gamma / delta) * IC(out)`.
- This makes `e(gamma_g2, IC) * e(delta_g2, C) = 1`, leaving only the valid `e(beta, alpha)` term.

### Stage 2
Commands:
```bash
cd /root/squ1rrel2026CTF/flat-earth-2
../.venv/bin/python artifacts/solve.py
```

Results:
- The script connects to the remote service, reads each `public out`, forges a proof, and submits it.
- After 32 successful rounds, the service returns the flag `squ1rrel{zksn4rk_s4ys_4rtem1s_ii_n3v3r_h4pp3n3d_3ith3r}`.

## Artifacts Produced
- `artifacts/solve.py` - proof forgery exploit.

## Flag
```text
squ1rrel{zksn4rk_s4ys_4rtem1s_ii_n3v3r_h4pp3n3d_3ith3r}
```
