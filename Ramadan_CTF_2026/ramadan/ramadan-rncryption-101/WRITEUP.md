# Encryption 101 — Writeup

## Challenge
- **Category:** rev
- **Difficulty:** Easy
- **Goal:** Recover the flag from an obfuscated Java challenge (`challenge.jar`).

## TL;DR
The program stores the flag bytes in an encrypted byte array and XORs them with a 4-byte key. Once the key is recovered (`RAM!`), decrypting the array gives the flag directly.

## Analysis
1. Inspect the JAR:
   ```bash
   jar tf challenge.jar
   ```
   Interesting files/classes:
   - `Main.class`
   - `FlagKeeper.class`
   - hidden payload-like classes under `META-INF/.classes/...`

2. Run the challenge once to observe behavior:
   ```bash
   java -jar challenge.jar
   ```
   It exits with an invalid password path unless proper internals are satisfied.

3. Reverse the bytecode / constants and recover the encrypted flag bytes:
   ```python
   enc = [
       4,3,9,90,107,121,125,24,48,117,117,20,54,115,44,66,48,
       118,126,24,100,35,126,19,106,34,121,16,51,113,47,16,
       107,32,44,25,47
   ]
   ```

4. Recover key material from the challenge internals (including strings such as `Sup3rS3cur3_R4m4d4n2026`, `%`, `KMK323N`, and derived hashes). This leads to the 4-byte XOR key:
   ```text
   RAM!
   ```

5. Decrypt:
   ```bash
   python3 -c "enc=[4,3,9,90,107,121,125,24,48,117,117,20,54,115,44,66,48,118,126,24,100,35,126,19,106,34,121,16,51,113,47,16,107,32,44,25,47];key=b'RAM!';print(''.join(chr(v^key[i%len(key)]) for i,v in enumerate(enc)))"
   ```

## Result
```text
VBD{9809b485d2acb7396b328c41a0b19aa8}
```

## Notes
Because this is an easy reversing task, the intended solve path is likely:
- identify encrypted static data,
- recover the short XOR key,
- decode bytes to printable flag format.
