# hackersBOTted

## Challenge Summary
- Given: a Node/Express web app archive and a live instance at `https://hackersbotted.squ1rrel.dev`.
- Goal: recover the flag from the admin-only `/api/flag` endpoint.
- Constraints: the frontend only uploads a photo to `/api/spot`, which runs Google Vision OCR/label detection before checking whether the detected target is an admin.

## Initial Recon / Triage
- Observations: the backend had no authentication and relied entirely on DB role checks. The uploaded image text flowed into a SQL query through the Vision result.
- File identification: `starting_files/dist/backend/db.js` contained the vulnerable `isAdmin()` helper. `starting_files/dist/backend/routes.js` showed `/api/spot` calling `isAdmin()` on each OCR result and `/api/flag` returning the flag if the submitted username had role `admin`.
- Entry points: `POST /api/spot`, `POST /api/flag`, and `GET /api/rotation-timer`.

## Hypotheses & Approach
- Hypothesis 1: OCR text could be used for SQL injection because `isAdmin()` interpolated the recognized string directly into `SELECT role FROM users WHERE name = '${cleaned}'` and the sanitizer only stripped comment markers.
- Hypothesis 2: stacked statements would be simpler than racing the rotating admin username. If I could promote a known user to admin inside the injected query, `/api/flag` would accept that username immediately.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
cd /root/squ1rrel2026CTF/hackersBOTted/starting_files
unzip -o hackersbotted.zip

sed -n '1,200p' dist/backend/db.js
sed -n '1,220p' dist/backend/routes.js
```

Results:
- `dist/backend/db.js` showed:
	- `sanitize()` only removes `--`, `/*`, and `*/`.
	- `isAdmin()` builds `SELECT role FROM users WHERE name = '${cleaned}'` with raw string interpolation.
- `dist/backend/routes.js` showed uploaded images are OCR'd and every detected text fragment is passed into `isAdmin()`.
- `POST /api/flag` simply checks whether the submitted username is currently marked as admin in the `users` table.

### Stage 2
Commands:
```bash
python3 artifacts/solve.py
```

Results:
- The solver generated an image containing this OCR payload:

	`x';update users set role=chr(97)||chr(100)||chr(109)||chr(105)||chr(110) where name=chr(97)||chr(108)||chr(105)||chr(99)||chr(101);select role from users where name='x`

- When `/api/spot` processed that image, PostgreSQL executed the stacked `UPDATE`, changing `alice` from `user` to `admin`.
- The route returned `500` because the injected multi-statement query did not match the code's expected single-result shape, but the side effect had already happened.
- A follow-up `POST /api/flag` with `{"username":"alice"}` returned the real flag.

## Artifacts Produced
- `artifacts/solve.py` — end-to-end exploit script that generates the OCR payload image, sends it to `/api/spot`, and then redeems the flag through `/api/flag`.
- `artifacts/promote_alice.png` — one rendered payload image used during live testing.

## Flag
```
squ1rrel{g3t_sp0773d_b0z0_l0l}
```
