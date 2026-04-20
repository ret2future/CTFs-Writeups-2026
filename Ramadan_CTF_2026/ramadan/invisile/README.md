# In-visible (Easy) — Writeup

## Challenge
**In-visible**  
Hint: *"how harmful a picture can be"*

This strongly suggests hidden data inside an image (steganography / payload embedding).

## Initial Recon
The workspace already contained:
- `capture.pcap`
- extracted HTTP artifacts (including multiple `.png` files)
- helper scripts:
  - `extract_http.py`
  - `deobf_loader.py`
  - `decode_payload.py`

The most suspicious image was:
- `extracted_http/i.ibb.co__0zt4quciwxs2_payload.png`

## Analysis Path
1. **HTTP extraction / triage**
   - Artifacts indicate a staged malware-like chain (batch/PowerShell loader + downloaded images).

2. **Decode hidden payload from PNG**
   - Ran:
     - `/Users/elenaeftimie/Desktop/CTFs/Madalina/invisile/.venv/bin/python decode_payload.py`
   - Script logic:
     - Reads first pixels to recover embedded payload length.
     - Extracts raw bytes from RGB channels across image pixels.
     - XOR-decrypts with key: `91d2f87dab32f433`
     - Gzip-decompresses recovered bytes.
   - Output written to `stage2_decoded.txt`.

3. **Flag hunt in decoded stage**
   - Decoded PowerShell content contains token assignment:
     - `$token='VBD{8059d662ede0cdd27c0d218c2943248f}'`

## Flag
`VBD{8059d662ede0cdd27c0d218c2943248f}`

## Why this works
The challenge theme is that an apparently harmless picture transported an encoded second-stage payload. The decoding pipeline (pixel extraction -> XOR -> gzip) reveals the hidden script and embedded flag token.
