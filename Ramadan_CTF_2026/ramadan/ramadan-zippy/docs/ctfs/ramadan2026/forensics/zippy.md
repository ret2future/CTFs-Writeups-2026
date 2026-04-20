---
title: "Zippy"
category: "Forensics"
difficulty: "Easy"
points: 75
emoji: "🖥️"
---
## Challenge Info

- CTF: Ramadan CTF 2026
- Organizer: Vuln By Default
- Challenge Name: Zippy
- Type: Standard
- Category: Forensics
- Difficulty: Easy
- Points: 75
- Author: VBD
- Solves: 84

## Challenge Statement

How much data is lost during compression? dont keep the lock and key at the same place.

## Files Provided

- `LOCKED_FILES.RAR` (357 bytes)

## Recon

The archive looked very small, so the intended trick was likely metadata or hidden streams rather than heavy brute force.

```bash
unrar vta locked_files.rar
7z l -slt locked_files.rar
```

From listing, we found:

- `locked_files.zip` containing `flag.txt` (encrypted)
- A service stream entry related to `:forgotpassword`

This confirms the hint: the lock (encrypted zip) and key (password) are stored separately.

## Solution

On this macOS setup, standard extraction did not directly recover the service stream payload. The workaround was to patch the RAR5 service block into a regular file block, then extract it.

### 1. Patch RAR block type (SERVICE -> FILE)

```python
from pathlib import Path
import zlib

src = Path("locked_files.rar").read_bytes()
out = bytearray(src)

# Service block header location identified during analysis
start = 266
end = 310

# Block type: 3 (SERVICE) -> 2 (FILE)
out[start + 5] = 2

# Recompute header CRC32
crc = zlib.crc32(bytes(out[start + 4:end])) & 0xffffffff
out[start:start + 4] = crc.to_bytes(4, "little")

Path("locked_files_patched.rar").write_bytes(out)
```

### 2. Extract patched archive

```bash
unrar x -o+ locked_files_patched.rar patched_out/
cat patched_out/STM
```

Recovered password:
`8d364896e034aabe3fc9fd2e05fb1cbe`

### 3. Decrypt zip and read flag

```bash
unzip -P '8d364896e034aabe3fc9fd2e05fb1cbe' -p locked_files.zip flag.txt
```

Flag recovered:
`VBD{c99a11a53a3748269e3f86d7ac38df11}`

## Result

- Challenge status: Solved
- Platform points shown for flag: 75

## Takeaways

- Easy forensics challenges often hide secrets in archive metadata.
- RAR5 service records can store key material away from encrypted payload files.
- When extraction tools fail, format-aware patching can expose hidden content safely.
