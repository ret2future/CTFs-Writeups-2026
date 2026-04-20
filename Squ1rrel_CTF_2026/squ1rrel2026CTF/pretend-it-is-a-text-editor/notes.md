# pretend-it-is-a-text-editor

## Challenge Summary
- Given: a live SecureNotes-style web app at `https://pretend.squ1rrel.dev` and no source files.
- Goal: recover the flag from the service.
- Constraints: only the live HTTP surface was available, so the solve had to come from behavioral analysis of the frontend and API.

## Initial Recon / Triage
- Observations: the frontend exposed session auth, note creation, note listing, and an "embed preview API" path: `GET /api/notes/:id/embed?width=400`.
- File identification: the live `app.js` showed these routes: `/api/login`, `/api/register`, `/api/me`, `/api/logout`, `/api/notes`, and `/api/notes/:id/embed`.
- Entry points: the note embed endpoint was the interesting one because it was a server-side rendering helper instead of plain CRUD.

## Hypotheses & Approach
- Hypothesis 1: `GET /api/notes/:id/embed` might have weaker authorization than the normal note view route.
- Hypothesis 2: even if embed only returned layout metadata, that metadata might still be enough to reconstruct note content if each character could be forced onto its own line.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
python3 - <<'PY'
import requests

s = requests.Session()
s.post('https://pretend.squ1rrel.dev/api/login', json={
	'username': 'qa_test_1',
	'password': 'pw123456',
}, timeout=20)

for path in [
	'/api/notes/1',
	'/api/notes/1/embed?width=400',
	'/api/notes/1/embed?width=1',
]:
	r = s.get('https://pretend.squ1rrel.dev' + path, timeout=20)
	print(path, r.status_code, r.text[:300])
PY
```

Results:
- `/api/notes/1` returned `403 {"error":"Access denied"}`.
- `/api/notes/1/embed?...` still returned `200` with JSON layout metadata, so the embed endpoint had an IDOR even though the normal note view route was protected.
- With `width=1`, the server returned one line per rendered glyph and exposed each line width.

### Stage 2
Commands:
```bash
python3 artifacts/solve.py
```

Results:
- The solver registers a throwaway user, creates a calibration note containing printable ASCII, and queries its own embed with `width=1` to build a glyph-width table.
- It then walks note IDs and decodes any note whose width sequence maps cleanly to text. Note `1` decodes to the flag:

	`squ1rrel{pr3t3xt_i5_sup35fUn_i5_It_n0T}`

## Artifacts Produced
- `artifacts/app.js` and `artifacts/layout.js` — downloaded frontend assets used to map the route surface.
- `artifacts/solve.py` — automated exploit that calibrates printable character widths and reconstructs the flag note from the vulnerable embed endpoint.

## Flag
```
squ1rrel{pr3t3xt_i5_sup35fUn_i5_It_n0T}
```
