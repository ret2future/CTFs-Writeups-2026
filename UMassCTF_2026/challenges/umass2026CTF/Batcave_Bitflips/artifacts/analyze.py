#!/usr/bin/python3

from __future__ import annotations

from collections import Counter
from pathlib import Path


BINARY_PATH = Path("/root/umass2026CTF/Batcave_Bitflips/starting_files/batcave_license_checker")
DATA_OFFSET = 0x3000


def load_constants() -> tuple[bytes, bytes, bytes, list[int]]:
    data = BINARY_PATH.read_bytes()[DATA_OFFSET : DATA_OFFSET + 0x180]
    license_key = data[0x20:0x40]
    expected = data[0x40:0x60]
    flag = data[0x60:0x80]
    sbox = list(data[0x80:0x180])
    return license_key, expected, flag, sbox


def expand_state(input_bytes: bytes) -> list[int]:
    return [(input_bytes[index % 32] ^ index) & 0xFF for index in range(64)]


def substitute(state: list[int], sbox: list[int]) -> list[int]:
    return [sbox[value] for value in state]


def mix(state: list[int]) -> list[int]:
    mixed = state[:]
    for index in range(64):
        mixed[index] = (mixed[index] ^ mixed[(index + 1) % 64] ^ mixed[63 - index]) & 0xFF
    return mixed


def rotate(state: list[int], left_shift: int, right_shift: int) -> list[int]:
    return [(((value << left_shift) & 0xFF) | (value >> right_shift)) & 0xFF for value in state]


def derive_final(state: list[int]) -> bytes:
    return bytes(((state[index] ^ state[index + 32]) & 0xFF) for index in range(32))


def hash_key(input_bytes: bytes, rounds: int, left_shift: int, right_shift: int, sbox: list[int]) -> bytes:
    state = expand_state(input_bytes)
    for _ in range(rounds):
        state = substitute(state, sbox)
        state = mix(state)
        state = rotate(state, left_shift, right_shift)
    return derive_final(state)


def main() -> None:
    license_key, expected, flag, sbox = load_constants()
    counts = Counter(sbox)
    duplicates = [value for value, count in counts.items() if count > 1]
    missing = [value for value in range(256) if value not in counts]

    print(f"license_key={license_key.decode()}")
    print(f"expected={expected.hex()}")
    print(f"flag={flag.hex()}")
    print(f"duplicate_values={[hex(value) for value in duplicates]}")
    print(f"missing_values={[hex(value) for value in missing]}")
    for duplicate in duplicates:
        positions = [index for index, value in enumerate(sbox) if value == duplicate]
        print(f"duplicate_positions_{duplicate:02x}={positions}")

    xor_plaintext = bytes(left ^ right for left, right in zip(flag, expected))
    or_plaintext = bytes(left | right for left, right in zip(flag, expected))
    and_plaintext = bytes(left & right for left, right in zip(flag, expected))
    print(f"flag_xor_expected={xor_plaintext}")
    print(f"flag_or_expected={or_plaintext}")
    print(f"flag_and_expected={and_plaintext}")


if __name__ == "__main__":
    main()