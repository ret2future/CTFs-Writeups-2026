# Lego Clicker – Solution Notes

## Challenge
- Category: Rev / Hard
- 184 solves
- Android APK game; must "reach the top of the leaderboard"

## Approach

### 1. Decompile the APK
```
jadx -d artifacts/jadx_out starting_files/LegoClicker_umass.apk
```

Key Java files in `com/example/LegoClicker/`:
- **FCA.java** – Receives flag via intent; calls native `FCA_a()` (fake) or `FCA_b()` (also fake, same impl)
- **SessionValidator.java** – Loads `legocore` native lib; calls `syncBrickCache(score, score)` via reflection when player is #1
- **RA.java** – Leaderboard activity; if player is #1: calls `SessionValidator.a(score, score)` → `syncBrickCache`

### 2. Identify native library
`liblegocore.so` (all ABIs in APK). x86_64 used for analysis.

Exported JNI symbols:
- `FCA_a` @ 0x21b00 → returns fake flag `BHREV{fAk3_flAG_wr0ng_s3ss10n}`
- `FCA_b` @ 0x21c90 → calls same function as FCA_a (also fake)
- `FCA_c` @ 0x21d30 → check function (calls `~(score²) & 3`)
- `FlagEngine_a/b/c` @ 0x218c0/21980/21a40 → tier/score hash helpers

### 3. Find `syncBrickCache` via JNI_OnLoad
`syncBrickCache`, `refreshTileMap`, `validateBrickToken` are registered dynamically via `RegisterNatives` in `JNI_OnLoad`.

Decryption: streaming XOR cipher with key accumulation:
```python
def decrypt(offset, length, key):
    out = []
    k = key & 0xFF
    for b in data[offset:offset+length]:
        c = b ^ k
        out.append(c)
        k = (k + c) & 0xFF
    return bytes(out)
```

JNI_OnLoad registers (key=0x47):
- `syncBrickCache` (JJ)Ljava/lang/String; → function @ **0x210f0**
- `refreshTileMap` → 0x21280
- `validateBrickToken` → 0x213b0

### 4. Analyze `syncBrickCache` (0x210f0)
Anti-debug/anti-Frida checks:
- Reads `/proc/self/status` looking for `TracerPid:`
- Reads `/proc/self/maps` looking for `frida`

**If not traced/Frida** → builds real flag (function 0x21f60 state machine)  
**If traced** → calls 0x21ea0 (another fake flag)

### 5. State machine decoding (function 0x21f60)
For each of 41 bytes (i=0..40):
1. Load encoded byte from 3 interleaved tables (0x13cdc, 0x13dc6, 0x13dd4) via function 0x20310
2. Apply 5 transformations in sequence (state machine with 0xdead/0xc0de/0xcafe/0xf00d/0xaced states):
   - XOR with `key_table_58570[(i+1)%8]`
   - SUB `key_table_58570[i%8]`
   - XOR with `key_table_58568[i%8]`
   - Rotate right 3 bits
   - XOR with `key_table_58560[i%8]`
3. 4 "random" bytes at 0x585f0 computed from `clock_gettime` always evaluate to 0 (clever obfuscation that's algebraically constant: `(a | ~a + 1) XOR b XOR b = 0`)

Key tables in `.bss` (set by function 0x20370):
```python
table_58560 = [0x6c, 0x1a, 0x3f, 0x88, 0x2b, 0x5d, 0x71, 0x04]
table_58568 = [0x20, 0x67, 0xc3, 0x11, 0x95, 0x4e, 0xb2, 0x39]
table_58570 = [0x53, 0x0a, 0x7f, 0x41, 0xde, 0x28, 0x64, 0x9c]
```

### 6. Solver script
`artifacts/decode_real_flag.py` simulates the full decoding.

## Flag
`UMASS{br1ck_by_br1ck_y0u_r3ach3d_th3_t0p}`
