---
title: "Broken Terminal"
category: "Pwn"
difficulty: "Easy"
points: 100
emoji: "🔓"
---

## Challenge
We were given a 64-bit Linux ELF (`pwn_chal`) that asks for a patient name. The binary looked like a classic stack-overflow warmup challenge.

## Solution
Decompilation (`broken_terminal/pwn_chal_decompiled.c`) shows:

- A vulnerable `gets()` call inside `vulnerable()`.
- A hidden `win()` function that prints the flag.
- Buffer layout: `char s[64]` + saved RBP (`8`) => overwrite offset to RIP is `72` bytes.
- No canary and no PIE, so the `win()` address is stable.

Exploit idea:

1. Send `72` padding bytes.
2. Overwrite return address with `win()`.

Minimal payload shape:

```python
payload = b"A" * 72 + p64(0x4011d6)  # win()
```

When execution returns from `vulnerable()`, it jumps directly to `win()`.

## Flag
`psych{0v3rfl0w_0p3n3d_th3_c3ll}`
