# gitting-the-secret

## Challenge Summary
- Given: `gitting-the-secret.zip`, which contains a website project and its `.git` directory.
- Goal: recover all three hidden flag fragments and assemble the final flag.
- Constraints: the repository is intentionally incomplete at first glance and includes hidden history/object data.

## Initial Recon / Triage
- The archive includes a full `.git` directory, plus `lost-found`, `info/refs`, `refs/original`, and a suspicious hidden pack file at `.git/secret/knapsack.pack`.
- The visible repo history only showed an initial commit and a beta tag, but `git fsck` reported a dangling commit and dangling blobs.
- The homepage text includes the clue `Home base: 62`, which points to base62 decoding.

## Hypotheses & Approach
- Hypothesis 1: the missing git objects live in the hidden pack file and need to be installed into `.git/objects/pack` before the repository can be queried normally.
- Hypothesis 2: the three recovered fragments are not literal flag text, but base62-encoded chunks that must be decoded and concatenated.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
cd /root/squ1rrel2026CTF/gitting-the-secret
mkdir -p artifacts/extracted
unzip -q starting_files/gitting-the-secret.zip -d artifacts/extracted
cd artifacts/extracted/gitting-the-secret

rm -f .git/refs/.DS_Store .git/objects/.DS_Store .git/logs/.DS_Store .git/lost-found/.DS_Store
find .git -name '._*' -delete

mkdir -p .git/objects/pack .git/refs/heads .git/refs/tags
cp .git/secret/knapsack.pack .git/objects/pack/pack-34e499dda06b2d6fece2ef31f097e5350818f421.pack
git index-pack .git/objects/pack/pack-34e499dda06b2d6fece2ef31f097e5350818f421.pack

printf '569efeadc291854b0f8fe356b68eb6cd251979f2\n' > .git/refs/heads/main
printf '2ef0d8f21527e2b607dd68510567d3e0f626366f\n' > .git/refs/tags/v1.0.0-beta-internal

git fsck --full --no-reflogs
git show 9d219e026839a10ba01f792cf26c79a3a44cbd7d:flag_1.txt
git show 2ef0d8f21527e2b607dd68510567d3e0f626366f:flag_3.txt
git cat-file -p bcffeb3eb0fadbcb95c62d2abb612e4b7fef6b0c
```

Results:
- `flag_1.txt` was recovered from dangling commit `9d219e0...` (`Checkpoint 1`).
- `flag_3.txt` was recovered from tag `v1.0.0-beta-internal` (`Checkpoint 3`).
- The missing middle fragment came from dangling blob `bcffeb3...`.
- Recovered fragments:
	- `4WpKZIx9qnhWDQ7L1MTTfMgLzSL2dj`
	- `BR43O1z6Oh4uZB9`
	- `2kp2hO0KjST5nlsWu72RXIddAovYpsebEiUvSJgjfAX8MvwFpwz9uheyD`

### Stage 2
Commands:
```bash
cd /root/squ1rrel2026CTF/gitting-the-secret
python3 artifacts/solve.py
```

Results:
- Using the standard base62 alphabet `0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz`, the three fragments decode directly to:
	- `squ1rrel{d0nut_c0mM1T_`
	- `uR_s3cR3ts_`
	- `w1tH_g1T_12b7160d77d8fbd071f42e0cbccad934}`
- Concatenating them yields the final flag.
- Submitting the result to the live API returned `goodFlag`.

## Artifacts Produced
- `artifacts/extracted/` - extracted repo used for analysis.
- `artifacts/solve.py` - helper that base62-decodes the three recovered fragments.

## Flag
```text
squ1rrel{d0nut_c0mM1T_uR_s3cR3ts_w1tH_g1T_12b7160d77d8fbd071f42e0cbccad934}
```
