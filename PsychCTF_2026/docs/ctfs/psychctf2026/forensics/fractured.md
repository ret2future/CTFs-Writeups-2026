---
title: "Fractured"
category: "Forensics"
difficulty: "Medium"
points: 250
emoji: "🖥️"
---

## Challenge
A single image hid multiple nested layers. The objective was to recover three fragments and assemble the final flag.

Provided/derived artifacts in the solve directory:

- `fractured/hidden.zip` embedded after PNG `IEND`
- `fractured/hidden_extracted/intake_form.pdf`
- nested ZIP inside the PDF tail
- `fractured/pdf_tail_extracted/final_transmission.txt`

## Solution
I used `fractured/solve_extract.py` to automate extraction and decoding.

### 1) Recover trailer data from PNG
The script locates PNG end marker (`IEND`) and writes trailing bytes to `trailer.bin`, then unzips it.

### 2) Decode Fragment 2 from PDF
Inside the PDF text, token:

`U0VOVFpSQUdfMjpndTNfejFhcTpSQVE=`

was base64-decoded and then Caesar-shifted by 13 to get:

`FRAGMENT_2:th3_m1nd:END`

### 3) Decode Fragment 3 from nested archive
From `final_transmission.txt`, hex blob:

`1c081b1d171f140e0569603c286e3d3769342e29601f141e`

was decoded with XOR key `0x5A`, yielding:

`FRAGMENT_3:fr4gm3nts:END`

### 4) Recover Fragment 1 from image bit planes
The candidate scan found a clear hit in LSB extraction:

`FRAGMENT_1:p0ly_gl0t:END`

### 5) Assemble final flag
Combine the three fragment values in order:

- fragment1 = `p0ly_gl0t`
- fragment2 = `th3_m1nd`
- fragment3 = `fr4gm3nts`

Final flag format from the transmission note:

`psych{fragment1_fragment2_fragment3}`

## Flag
`psych{fragment1_fragment2_fragment3}`
