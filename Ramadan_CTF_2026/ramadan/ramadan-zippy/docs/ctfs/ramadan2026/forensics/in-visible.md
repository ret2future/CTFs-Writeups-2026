---
title: "In-visible"
category: "Forensics"
difficulty: "Easy"
points: 75
emoji: "🖥️"
---

## Challenge
how harmful a picture can be, find the flag

## Solution
The provided traffic and extracted artifacts indicated a staged downloader flow, with a suspicious PNG carrying hidden data.

1. Reviewed the capture artifacts and focused on the payload image:
   - `extracted_http/i.ibb.co__0zt4quciwxs2_payload.png`
2. Used the existing helper script to extract the hidden byte stream from image pixels.
3. Decrypted the extracted bytes with XOR key `91d2f87dab32f433`.
4. Gzip-decompressed the decrypted payload to recover stage-2 PowerShell.
5. Parsed the decoded script and recovered the embedded token.

Recovered token in decoded script:
`VBD{8059d662ede0cdd27c0d218c2943248f}`

## Flag
`VBD{8059d662ede0cdd27c0d218c2943248f}`
