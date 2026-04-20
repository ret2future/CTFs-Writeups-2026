# Lost_and_Found

## Challenge Summary
- Given: a VM image in `starting_files/ctf-vm.ova` and an Alpine Linux filesystem where `/home` had been recursively XOR-encrypted, including a git repository.
- Goal: recover the hidden `UMASS{...}` flag.
- Constraints: the visible `decrypt_home.py` key was incomplete, many decrypted paths were red herrings, and the large git objects could not be decompressed until enough key bytes were recovered.

## Initial Recon / Triage
- The mounted VM contents under `artifacts/mnt/` showed an encrypted `/home` tree and an unencrypted `/root/root/.ash_history`.
- `.ash_history` showed `cargo install xor`, use of the Rust `xor` crate, `git init .`, an initial commit, creation of `nonsuspiciousatall.txt`, and finally `git stash` activity.
- The encrypted `/home` contained a git repository with reflogs and loose objects. Small objects and config/description files gave enough known plaintext to start recovering the XOR key.

## Hypotheses & Approach
- Hypothesis 1: the provided 25-byte key in `artifacts/decrypt_home.py` was only a prefix of the real XOR key.
- Hypothesis 2: enough known plaintext existed in git metadata (`config`, reflogs, commit structure) to recover a much longer non-repeating key prefix and decrypt the stash commit directly.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
cat artifacts/mnt/root/root/.ash_history
python3 - <<'PY'
cfg = open('artifacts/mnt/root/home/5457501C/195F570E1C11', 'rb').read()
plain = b'[core]\n\trepositoryformatversion = 0\n\tfilemode = true\n\tbare = false\n\tlogallrefupdates = true\n'
print(bytes(c ^ p for c, p in zip(cfg, plain)).decode())
PY
```

Results:
- Confirmed the host used the Rust `xor` crate to encrypt the home directory recursively.
- Recovered the first 92 key bytes from the encrypted git config, extending the earlier partial key.

### Stage 2
Commands:
```bash
python3 artifacts/recover_flag.py
```

Results:
- Brute-forced the initial commit timestamp from the known commit SHA1 `8fadc480a8f31d2dac1099cfbb03a8bfb5de569f`.
- Reconstructed HEAD reflog line 1 exactly, which recovered key bytes `0..170`.
- Decrypted the 165-byte stash helper commit `6098e011...`, revealing stash timestamp `1774732415` and the `git stash <git@stash>` identity.
- Reconstructed HEAD reflog line 2 exactly, recovering key bytes `0..313`.
- Decrypted stash commit `55a10e0874b6d37a8b9c2d70468d91f5b8c78cf5` and read the commit message:
	`On master: You found me! UMASS{h3r35_7h3_c4rg0_vr00m}`

## Artifacts Produced
- `artifacts/recover_flag.py` - reproducible recovery script for the final flag path.
- `artifacts/key_recovery.txt` - concise record of the recovered timestamps, reflog plaintext, and stash commit details.
- Existing intermediate files in `artifacts/` were preserved: mounted VM contents, partial decryptions, and helper scripts used during analysis.

## Flag
```
UMASS{h3r35_7h3_c4rg0_vr00m}
```
