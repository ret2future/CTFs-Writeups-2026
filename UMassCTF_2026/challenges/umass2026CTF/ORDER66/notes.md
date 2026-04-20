# ORDER66

## Challenge Summary
- Given: A Flask app plus a Puppeteer admin bot in starting_files, and a live endpoint at http://order66.web.ctf.umasscybersec.org:48001/.
- Goal: Recover the admin bot's flag.
- Constraints: Only one ORDER box can be submitted at a time, and only one box per seed is rendered with the safe Jinja filter.

## Initial Recon / Triage
- Observations: app.py seeds Python's PRNG with a visible seed and computes one special box index with random.randint(1, 66). Only that box is rendered with |safe. If a payload is stored in that exact box, the seed is preserved instead of being rerolled.
- File identification: app.py contains the vulnerable render and the /admin/visit handler. app.js shows the bot sets a non-HttpOnly cookie containing FLAG, then logs browser console output back to stdout.
- Entry points: / for storing box content, /view/<uid>/<seed> for rendering a chosen grid, and /admin/visit for sending the admin bot to a supplied view URL.

## Hypotheses & Approach
- Hypothesis 1: Because the seed is exposed in the page source, the special safe-rendered box can be computed offline and targeted directly.
- Hypothesis 2: A stored XSS payload in that box can read document.cookie and exfiltrate the flag by console.log, because the bot mirrors page console messages to its own stdout.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
cd /root/umass2026CTF
challinit umass2026 ORDER66
cd /root/umass2026CTF/ORDER66/starting_files
curl -sS -L -b /tmp/umass_session.cookies 'https://ctf.umasscybersec.org/files/0749262929de4a7d77d3ccd3029a4fdf/DOWNLOADABLE_ASSETS.zip?token=eyJ1c2VyX2lkIjozMzMsInRlYW1faWQiOjkyLCJmaWxlX2lkIjozNH0.adrSIg.QfD5AmTaTXWei0E2py3IN6tmTqo' -o DOWNLOADABLE_ASSETS.zip
unzip -o DOWNLOADABLE_ASSETS.zip
```

Results:
- Retrieved the official source bundle and identified the vulnerability in starting_files/app.py and starting_files/app.js.

### Stage 2
Commands:
```bash
python3 /root/umass2026CTF/ORDER66/artifacts/solve_order66.py
```

Results:
- The solver requests /, extracts bot_uid and bot_seed from the response, computes the vulnerable ORDER index with Python's PRNG, stores <script>console.log(document.cookie)</script> into that exact box, and calls /admin/visit with http://x/view/<uid>/<seed>.
- admin/visit rewrites the host to the internal web container, the bot visits the stored-XSS page with its flag cookie set, and the page logs flag=UMASS{m@7_t53_f0rce_b$_w!th_y8u} back through Puppeteer's console bridge.

## Artifacts Produced
- artifacts/solve_order66.py

## Flag
```
UMASS{m@7_t53_f0rce_b$_w!th_y8u}
```
