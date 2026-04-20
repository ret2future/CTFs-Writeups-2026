1. Challenge Summary

- Given: an unstripped ELF64 binary named batcave_license_checker.
- Goal: recover the valid flag from a debug build that has been corrupted by a small number of bitflips.

2. Initial Recon / Triage

- The challenge provided three free hints, which were used because they cost 0 points:
- BatAI estimates there are 3 bugs.
- Rotation rotation rotation!
- Something about that SBOX seems off...
- strings plus objdump showed named functions for hash construction, verification, and decrypt_flag.
- The .data section exposed four important globals:
- LICENSE_KEY at 0x4020
- EXPECTED at 0x4040
- FLAG at 0x4060
- SBOX at 0x4080

3. Hypotheses & Approach

- Because the binary is unstripped and the encrypted flag plus expected hash are both embedded, the fastest route is to analyze the broken decryption path directly instead of fully repairing the license checker.
- The SBOX is definitely corrupted because it contains 255 unique values, not 256.
- The decrypt_flag routine also looks suspect: it uses OR when combining the encrypted flag bytes with the derived key, while a keyed byte-wise decrypt routine would normally use XOR.

4. Execution Steps (Reproducible)

Stage 1: extract the embedded constants.

```bash
/usr/bin/python3 /root/umass2026CTF/Batcave_Bitflips/artifacts/analyze.py
```

Relevant output:

- license_key=!_batman-robin-alfred_((67||67))
- expected=3b54751a2406af05778047c5e483d348cb8730de1a9145ab15c79b2204022bee
- flag=6e193449777df05a07b433a68ce6e617fbe96fae2ee526c370e3c47d277f2b00
- duplicate_values=['0x43']
- missing_values=['0x44']

Stage 2: test the obvious direct decrypt candidates.

The helper computes XOR, OR, and AND between FLAG and EXPECTED. XOR produces readable plaintext immediately:

- UMASS{__p4tche5_0n_p4tche$__#}\x00\xee

The trailing \x00 terminates the C string, so the printable flag is:

- UMASS{__p4tche5_0n_p4tche$__#}

This matches the disassembly: decrypt_flag currently uses OR, so the clean recovery path is to apply XOR directly to the embedded ciphertext with the expected 32-byte verification digest.

5. Artifacts Produced

- artifacts/analyze.py: extracts constants, checks SBOX integrity, and demonstrates the direct XOR recovery.
- artifacts/extract_flag.py: prints the recovered flag directly from the ELF.

6. Flag

- UMASS{__p4tche5_0n_p4tche$__#}
