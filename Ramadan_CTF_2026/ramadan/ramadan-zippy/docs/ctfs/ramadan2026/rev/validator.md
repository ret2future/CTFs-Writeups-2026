---
title: "Validator"
category: "Reverse Engineering"
difficulty: "Medium"
points: 100
emoji: "🧩"
---

## Challenge
Trust Me, It’s Just a Simple Check

## Solution
`validator` is a Linux PIE binary with obfuscated validation flow. Instead of full deobfuscation, the solve used an in-process oracle.

Approach:
1. Identified internal checker symbol offsets in the binary.
2. Used `LD_PRELOAD` hook to run inside the target process (so PIE base is already mapped correctly).
3. Resolved runtime base with `dl_iterate_phdr`.
4. Called the per-character checker function (`base + 0x3440`) for each position and printable byte.
5. Reconstructed the full accepted string from oracle positives.
6. Verified by piping candidate to the binary and confirming success message.

Recovered flag:
`VBD{I_kn0w_y0u_w0uld_us3_Opus_hehe_eafa09ad1898e0bcf9c0225076632225}`

## Flag
`VBD{I_kn0w_y0u_w0uld_us3_Opus_hehe_eafa09ad1898e0bcf9c0225076632225}`
