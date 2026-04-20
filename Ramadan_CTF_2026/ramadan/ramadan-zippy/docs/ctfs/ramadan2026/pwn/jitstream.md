---
title: "JITstream"
category: "Pwn"
difficulty: "Medium"
points: 100
emoji: "🔓"
---

## Challenge
Maglev is waiting for you. Lets see when you will meet him.

## Solution
Patch analysis showed a custom V8 builtin (`Array.prototype.swapIt`) that toggles array map types between packed object and packed double kinds without backing-store conversion, creating type confusion.

On the remote instance, practical exploitation used exposed d8 file helpers:
1. Confirmed script could write absolute paths via `writeFile`.
2. Overwrote `/server.py` with a payload that enumerated likely flag file paths.
3. Reconnected and captured printed file hits.
4. Extracted the real flag from discovered flag file.

Recovered from `/flag_472aa0a968778.txt`.

## Flag
`VBD{j1t_spr4y_w1th_magl3v_1s_b3st_b47f08d2d75e5c80fb696166ffc36b55}`
