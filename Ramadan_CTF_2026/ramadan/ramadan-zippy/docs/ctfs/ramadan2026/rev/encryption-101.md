---
title: "Encryption 101"
category: "Reverse Engineering"
difficulty: "Easy"
points: 75
emoji: "🧩"
---

## Challenge
just classes no code

## Solution
The JAR contains an encrypted byte array and a short XOR-based decoder path.

Steps used:
1. Enumerated classes in `challenge.jar`.
2. Reversed constants and extracted encrypted bytes from the checker logic.
3. Recovered the 4-byte XOR key used by the program (`RAM!`).
4. XOR-decrypted the encrypted byte array to plaintext.

Decryption output:
`VBD{9809b485d2acb7396b328c41a0b19aa8}`

## Flag
`VBD{9809b485d2acb7396b328c41a0b19aa8}`
