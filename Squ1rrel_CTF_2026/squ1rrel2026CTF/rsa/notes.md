# rsa

## Challenge Summary
- Given: `rsa.txt` with a base64 ciphertext and a public modulus.
- Goal: Recover the flag for the crypto challenge `rsa`.
- Constraints: Flag format is `squ1rrel{...}`.

## Initial Recon / Triage
- The challenge description was `rivest shamir adleman type shi`, which strongly suggested RSA.
- The provided public key value was only the modulus `n`, so the first job was to recover the prime factors.
- The ciphertext length matched a 2048-bit RSA block.

## Hypotheses & Approach
- Because the modulus factors were extremely close, Fermat factorization was the right first attack.
- After factoring, use the standard public exponent `e = 65537`, derive `d`, and decrypt the ciphertext.
- The decrypted payload looked odd enough to invite extra decoding, but it was already the literal flag body.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
cd /root/squ1rrel2026CTF/rsa
python3 artifacts/solve.py
```

Results:
- Fermat factorization succeeded immediately.
- Recovered primes `p` and `q` with `q - p = 3320`.
- Derived the private exponent and decrypted the RSA block.

### Stage 2
Commands:
```bash
cd /root/squ1rrel2026CTF/rsa
python3 artifacts/solve.py | tail -n 8
```

Results:
- Solver output included:
	- `q - p = 3320`
	- `payload = aoei;jrgnapeicgiw,fgjpisugfr`
- Several post-processing attempts were tested, but the payload itself was the correct flag content.

## Artifacts Produced
- `artifacts/solve.py` - RSA factor/decrypt helper.
- `artifacts/solve_output.txt` - saved solver output.
- `artifacts/transform_sweep.txt` - attempted follow-up transforms.

## Flag
```text
squ1rrel{aoei;jrgnapeicgiw,fgjpisugfr}
```
