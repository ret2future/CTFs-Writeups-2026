# Zippy CTF Challenge Writeup

## Challenge Overview

**Title:** Zippy  
**Difficulty:** Easy  
**Points:** 75  
**Solves:** 66  

**Description:**
> How much data is lost during compression? don't keep the lock and key at the same place

**Challenge File:** `LOCKED_FILES.RAR` (357 bytes)

---

## Solution Summary

The challenge exploits the principle **"don't keep the lock and key at the same place"** by hiding the encryption key in an NTFS alternate data stream (ADS) nested within a RAR archive, separate from the encrypted ZIP file it protects.

**Password:** `8d364896e034aabe3fc9fd2e05fb1cbe` (32-character hex string)  
**Flag:** `VBD{c99a11a53a3748269e3f86d7ac38df11}`

---

## Technical Approach

### 1. Archive Structure Analysis

Extract and inspect the RAR archive:

```bash
unrar vta locked_files.rar
```

**Output reveals:**
- `locked_files.zip` (203 bytes) – AES-encrypted file containing `flag.txt`
- Service record `STM` (32 bytes) – NTFS alternate data stream named `:forgotpassword`

The ZIP is AES-encrypted and we need the password. The STM entry is the hidden key.

### 2. NTFS Alternate Data Stream Extraction Challenge

**Problem:** Standard extraction tools (unrar, 7z, unar, bsdtar) on macOS do not directly expose NTFS alternate stream content—they only list it in archive metadata but fail to extract the payload.

**Solution:** Patch the RAR archive to convert the service block type into a regular file block, allowing standard extraction.

### 3. RAR5 Block Type Manipulation

RAR 5.x uses block headers with the following structure:

```
[0:4]   Header CRC32
[4:5]   Block Type (3 = SERVICE, 2 = FILE)
[5:44]  Rest of header
[44:310] Compressed data
```

**Patching Process:**

```python
from pathlib import Path
import zlib

# Read original archive
src = Path('locked_files.rar').read_bytes()
out = bytearray(src)

# Locate service block header at offset 266
start = 266
end = 310

# Change block type from 3 (SERVICE) to 2 (FILE)
out[start + 5] = 2

# Recalculate CRC32 over modified header
crc = zlib.crc32(bytes(out[start + 4:end])) & 0xffffffff
out[start:start + 4] = crc.to_bytes(4, 'little')

# Write patched archive
Path('locked_files_patched.rar').write_bytes(out)
```

### 4. Extract the Hidden Key

```bash
unrar x -o+ locked_files_patched.rar
```

**Result:** `STM` file (32 bytes) containing:
```
8d364896e034aabe3fc9fd2e05fb1cbe
```

### 5. Decrypt the ZIP

The extracted value **is the password itself** (not a hash to crack)—use it directly as a string:

```python
import pyzipper

password = b'8d364896e034aabe3fc9fd2e05fb1cbe'  # As hex string, not decoded bytes
with pyzipper.AESZipFile('locked_files.zip') as zf:
    zf.setpassword(password)
    flag = zf.read('flag.txt')
    print(flag.decode())
    # Output: VBD{c99a11a53a3748269e3f86d7ac38df11}
```

**OR via command line:**

```bash
unzip -P '8d364896e034aabe3fc9fd2e05fb1cbe' -p locked_files.zip flag.txt
```

---

## Key Insights

1. **Dual-Layer Obfuscation:**
   - Encrypted ZIP (first barrier)
   - Hidden key in archive metadata (second barrier)

2. **ADS as Steganography:**
   - NTFS streams are not directly visible in standard file listings
   - Stored inside archive metadata, separated from the locked file
   - Requires understanding of archive formats to discover

3. **"Don't Keep Lock and Key Together":**
   - The ZIP file and its password are in **different archive structures**
   - Password is in a service record, not alongside the ZIP
   - Demonstrates physical separation of secrets

4. **Cross-Platform Challenges:**
   - RAR5 service blocks are archive-format specific
   - macOS/Unix tools lack NTFS stream awareness
   - Requires archive manipulation to extract hidden records

---

## Tools Used

- `unrar` / `rar` – RAR archive inspection and extraction
- `7z` – Archive listing with technical details
- `python3` – CRC32 calculation, archive patching
- `pyzipper` – AES-encrypted ZIP decryption
- `unzip` – Alternative ZIP extraction method

---

## Files Reference

- `locked_files.rar` – Original challenge archive
- `locked_files.zip` – Encrypted inner archive (extracted)
- `locked_files_patched.rar` – Patched RAR with service block converted to file block
- `patched_out/STM` – Extracted 32-byte password file

---

## Lessons Learned

✓ NTFS alternate data streams can hide secrets in archive metadata  
✓ Service records in RAR5 are distinct from regular files  
✓ CRC validation prevents casual tampering but can be recalculated  
✓ Archive format knowledge is critical for forensic analysis  
✓ "Separation of secrets" principle: lock ≠ location of key  

---

## Alternative Approaches

### Direct unrar Service Extraction
Some versions of unrar support ADS extraction:
```bash
rar p -inul ':forgotpassword' locked_files.rar
```
(Unsuccessful on this macOS environment, but architecturally valid)

### 7-Zip NTFS Stream Support
```bash
7z x -sns locked_files.rar
```
(Also unsupported due to RAR5 service block handling limits on macOS)

### Hexdump + Manual Extraction
```bash
xxd locked_files.rar | grep -A 5 "forgotpassword"
# Manually extract bytes from offset and decode
```

---

## Summary Timeline

| Step | Action | Result |
|------|--------|--------|
| 1 | Extract RAR metadata | Discover SERVICE block `:forgotpassword` |
| 2 | Attempt standard extraction | Fails—macOS tools don't expose ADS |
| 3 | Analyze RAR5 block structure | Identify block type field at offset +5 |
| 4 | Patch block type 3→2 | Convert SERVICE to FILE block |
| 5 | Recalculate CRC32 header | Validate modified archive |
| 6 | Extract patched archive | Recover `STM` file (32 bytes) |
| 7 | Use password `8d36...1cbe` | Decrypt ZIP and read flag |
| 8 | Flag obtained | `VBD{c99a11a53a3748269e3f86d7ac38df11}` |

---

**Status:** ✅ SOLVED  
**Approach:** Archive forensics + RAR5 block manipulation  
**Difficulty Assessment:** Medium-Easy (requires format knowledge)
