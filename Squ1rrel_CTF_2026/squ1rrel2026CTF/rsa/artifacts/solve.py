#!/usr/bin/env python3

from __future__ import annotations

import base64
import codecs
from math import isqrt
from pathlib import Path

from Crypto.Util.number import long_to_bytes


RSA_PATH = Path(__file__).resolve().parents[1] / "starting_files" / "rsa.txt"


def load_values() -> tuple[bytes, int]:
    values: dict[str, str] = {}
    for line in RSA_PATH.read_text(encoding="utf-8").splitlines():
        if ":" not in line:
            continue
        key, value = line.split(":", 1)
        values[key.strip()] = value.strip()

    ciphertext = base64.b64decode(values["Encrypted message"])
    modulus = int(values["Public key"])
    return ciphertext, modulus


def fermat_factor(modulus: int) -> tuple[int, int]:
    a = isqrt(modulus)
    if a * a < modulus:
        a += 1

    b2 = a * a - modulus
    b = isqrt(b2)
    if b * b != b2:
        raise ValueError("modulus is not a difference of squares from the first Fermat step")

    return a - b, a + b


def caesar(text: str, shift: int) -> str:
    output: list[str] = []
    for char in text:
        if "a" <= char <= "z":
            output.append(chr((ord(char) - ord("a") - shift) % 26 + ord("a")))
        else:
            output.append(char)
    return "".join(output)


def keyboard_map(text: str, src: str, dst: str) -> str:
    table = str.maketrans(src, dst)
    return text.translate(table)


def main() -> None:
    ciphertext, modulus = load_values()
    p, q = fermat_factor(modulus)
    phi = (p - 1) * (q - 1)
    d = pow(65537, -1, phi)

    plaintext = long_to_bytes(pow(int.from_bytes(ciphertext, "big"), d, modulus), len(ciphertext))
    payload = plaintext.split(b"\x00", 2)[-1]
    text = payload.decode("utf-8")

    print(f"p = {p}")
    print(f"q = {q}")
    print(f"q - p = {q - p}")
    print(f"payload = {text}")
    print(f"rot13 = {codecs.decode(text, 'rot_13')}")
    for shift in range(26):
        shifted = caesar(text, shift)
        if shift in {0, 13} or "squ" in shifted or "flag" in shifted or "rsa" in shifted:
            print(f"caesar[{shift}] = {shifted}")

    qwerty = "qwertyuiop[]asdfghjkl;'zxcvbnm,./"
    dvorak = "',.pyfgcrl/=aoeuidhtns-;qjkxbmwvz"
    print(f"qwerty->dvorak = {keyboard_map(text, qwerty, dvorak)}")
    print(f"dvorak->qwerty = {keyboard_map(text, dvorak, qwerty)}")


if __name__ == "__main__":
    main()