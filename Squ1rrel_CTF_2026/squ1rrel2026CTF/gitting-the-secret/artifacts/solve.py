#!/usr/bin/env python3

ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
PARTS = [
    "4WpKZIx9qnhWDQ7L1MTTfMgLzSL2dj",
    "BR43O1z6Oh4uZB9",
    "2kp2hO0KjST5nlsWu72RXIddAovYpsebEiUvSJgjfAX8MvwFpwz9uheyD",
]


def base62_decode(text: str) -> bytes:
    value = 0
    for char in text:
        value = value * 62 + ALPHABET.index(char)
    hex_value = f"{value:x}"
    if len(hex_value) % 2:
        hex_value = "0" + hex_value
    return bytes.fromhex(hex_value)


def main() -> int:
    decoded = [base62_decode(part).decode("utf-8") for part in PARTS]
    for index, fragment in enumerate(decoded, 1):
        print(f"part {index}: {fragment}")
    print("flag:", "".join(decoded))
    return 0


if __name__ == "__main__":
    raise SystemExit(main())