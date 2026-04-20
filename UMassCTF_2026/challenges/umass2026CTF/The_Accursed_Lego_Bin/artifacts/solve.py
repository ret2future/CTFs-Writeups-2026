#!/usr/bin/env python3

import random
from pathlib import Path


def integer_nth_root(value: int, n: int) -> int:
    low, high = 0, 1
    while high**n <= value:
        high *= 2
    while low + 1 < high:
        mid = (low + high) // 2
        if mid**n <= value:
            low = mid
        else:
            high = mid
    return low


def main() -> None:
    text = "I_LOVE_RNG"
    known_plain = int.from_bytes(text.encode(), "big")

    seed_line, flag_line = Path("output.txt").read_text().splitlines()
    enc_seed = int(seed_line.split("=", 1)[1].strip())
    flag_hex = flag_line.split("=", 1)[1].strip()

    seed = integer_nth_root(enc_seed, 7)
    assert seed**7 == enc_seed
    assert seed == known_plain**7

    flag_bits = []
    for byte in bytes.fromhex(flag_hex):
        flag_bits.extend(list(bin(byte)[2:].zfill(8)))

    for round_index in range(9, -1, -1):
        rng = random.Random(seed * (round_index + 1))
        permutation = list(range(len(flag_bits)))
        rng.shuffle(permutation)

        restored = [None] * len(flag_bits)
        for shuffled_index, original_index in enumerate(permutation):
            restored[original_index] = flag_bits[shuffled_index]
        flag_bits = restored

    flag = "".join(
        chr(int("".join(flag_bits[offset:offset + 8]), 2))
        for offset in range(0, len(flag_bits), 8)
    )
    print(flag)


if __name__ == "__main__":
    main()