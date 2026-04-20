# soulmate

## Challenge Summary
- Given: A web app plus a source archive, `starting_files/soulmate.zip`, containing the FastAPI backend, frontend, model wrapper code, and the PCA artifact used for latent controls.
- Goal: Make the service classify a generated face as Tom Cruise strongly enough to pass the threshold and return the flag.
- Constraints: The live site only exposes a birthday picker in the UI, but the backend may expose more endpoints.

## Initial Recon / Triage
- Observations: The frontend only calls `/generate-random` with a date-derived seed, but the backend also exposes `/health` and a hidden `/submit-u` endpoint. `/submit-u` accepts an 8-dimensional control vector `u`, returns the Tom Cruise score directly, and leaks the flag immediately when the score crosses the threshold.
- File identification: `backend/app.py` contains the live API surface and the flag gate. `models/inference.py` exposes `TOM_SCORE_THRESHOLD = 0.15` and the PCA control dimension. `checkpoints/pca_basis_d8_tom_weighted.npz` holds the 8-D latent basis and bounds.
- Entry points: Query `/health` to obtain control bounds and threshold, then optimize the hidden `/submit-u` oracle instead of using the birthday UI.

## Hypotheses & Approach
- Hypothesis 1: The intended bug is API exposure, not model inversion through the date field. The frontend hides the powerful endpoint, but the backend does not.
- Hypothesis 2: Because the PCA artifact is explicitly Tom-weighted and the service returns `tom_score`, a simple black-box search over the 8 bounded controls should be enough to cross the low `0.15` threshold.

The useful exposed facts were:

- `GET /health` returns `control_dim = 8`, the exact lower/upper bounds for each control, and `tom_score_threshold = 0.15`.
- `POST /submit-u` returns `tom_score`, `top_predictions`, and the flag when `tom_score >= 0.15`.
- The UI path through `/generate-random` is a distraction; direct latent control is much easier.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
cd /root/squ1rrel2026CTF
python3 squ1rrel2026_live_challenges.py --stdout-json | jq '.challenges[] | select(.name=="soulmate")'

cd /root/squ1rrel2026CTF/soulmate/starting_files
unzip -o soulmate.zip -d extracted

python3 - <<'PY'
import requests
print(requests.get('https://soulmate.squ1rrel.dev/health', timeout=20).json())
PY
```

Results:
- Confirmed the source archive contains the backend, model wrapper, and PCA checkpoint.
- Confirmed the live service exposes `/health` publicly.
- Retrieved:
- `control_dim = 8`
- exact `u_lower` / `u_upper` bounds
- `tom_score_threshold = 0.15`

### Stage 2
Commands:
```bash
cd /root/squ1rrel2026CTF/soulmate
python3 artifacts/coordinate_search.py
```

Results:
- The hidden `POST /submit-u` oracle was optimized directly instead of using birthdays.
- Simple probes showed the zero vector already gave a nontrivial Tom score around `0.0691`, so the threshold was reachable.
- Coarse and then fine coordinate ascent over the 8 bounded PCA controls produced the winning vector:
- `u = [2.0, 2.0, 8.0, -3.0, 1.0, 7.0, -2.0, 0.0]`
- The accepted response was:
- `tom_score = 0.15429021418094635`
- `threshold = 0.15`
- `success = true`

The final accepted payload from the service returned:

- `flag = squ1rrel{7h3_church_c0n6r47ul4735_mr5_cru153!!}`

## Artifacts Produced
- `artifacts/probe_remote.py` — baseline oracle probes against the live service.
- `artifacts/random_search.py` — coarse bounded random search.
- `artifacts/coordinate_search.py` — the search that found the winning latent control vector.
- `artifacts/hybrid_search.py` — alternate stochastic local search.
- `artifacts/powell_search.py` — bounded SciPy optimizer prototype.
- `artifacts/coordinate_best.json` — accepted live response, winning vector, and final flag.

## Flag
```
squ1rrel{7h3_church_c0n6r47ul4735_mr5_cru153!!}
```
