#!/usr/bin/env python3

from math import ceil
from pathlib import Path
import re

import numpy as np
from PIL import Image


PATH = Path("/root/umass2026CTF/knex/artifacts/lush_clean.png")
OUT_PATH = Path("/root/umass2026CTF/knex/artifacts/mobeets_decoded.bin")
ALPHA = 0.45


def to_cgc(arr: np.ndarray) -> np.ndarray:
    return arr ^ (arr >> 1)


def checkerboard(h: int, w: int) -> np.ndarray:
    row_even = np.array(([0, 1] * (w // 2)) + ([0] if w % 2 else []), dtype=np.uint8)
    row_odd = 1 - row_even
    rows = [row_even if i % 2 == 0 else row_odd for i in range(h)]
    return np.stack(rows)


CHECKER = checkerboard(8, 8)


def conjugate(arr: np.ndarray) -> np.ndarray:
    return np.where(arr == 1, CHECKER, 1 - CHECKER).astype(np.uint8)


def complex_blocks(plane: np.ndarray, alpha: float) -> np.ndarray:
    height, width = plane.shape
    block_rows = height // 8
    block_cols = width // 8
    trimmed = plane[: block_rows * 8, : block_cols * 8]
    blocks = trimmed.reshape(block_rows, 8, block_cols, 8).transpose(0, 2, 1, 3)
    row_changes = np.count_nonzero(blocks[:, :, 1:, :] != blocks[:, :, :-1, :], axis=(2, 3))
    col_changes = np.count_nonzero(blocks[:, :, :, 1:] != blocks[:, :, :, :-1], axis=(2, 3))
    complexity = (row_changes + col_changes) / 112.0
    return blocks[complexity >= alpha]


def get_n_message_grids(nbits_per_map: list[int], ngrids: int) -> int:
    x, y = ngrids - 1, 1
    while not (ngrids == x + y and sum(nbits_per_map[-(y - 1) :]) < x <= sum(nbits_per_map[-y:])) and x > 0:
        x, y = x - 1, y + 1
    if x <= 0 and ngrids == 2:
        return 1
    if x <= 0:
        raise ValueError("could not separate message grids from conjugation maps")
    return x


def decode() -> bytes:
    img = np.array(Image.open(PATH).convert("RGB"), dtype=np.uint8)
    cgc = to_cgc(img)
    grids = []

    for channel in range(3):
        for bit in range(8):
            plane = ((cgc[:, :, channel] >> (7 - bit)) & 1).astype(np.uint8)
            grids.extend(complex_blocks(plane, ALPHA))

    if not grids:
        raise ValueError("no grids found")

    n_prefix = int(ceil(ALPHA * 64))
    nbits_per_map = [64 - n_prefix for _ in grids]
    n_message = get_n_message_grids(nbits_per_map, len(grids))
    message_grids = [grid.copy() for grid in grids[:n_message]]
    conj_grids = grids[n_message:]

    conj_bits = []
    for grid in conj_grids:
        conj_bits.extend(grid.reshape(-1).tolist()[-(64 - n_prefix) :])

    for index, grid in enumerate(message_grids):
        if index < len(conj_bits) and conj_bits[index]:
            message_grids[index] = conjugate(grid)

    bits = []
    for grid in message_grids:
        bits.extend(grid.reshape(-1).tolist())

    spare = len(bits) % 8
    if spare:
        bits = bits[: len(bits) - spare]
    arr = np.array(bits, dtype=np.uint8).reshape(-1, 8)
    values = np.packbits(arr, axis=1, bitorder="big").reshape(-1)
    return values.tobytes()


def main() -> None:
    data = decode()
    OUT_PATH.write_bytes(data)
    print("len", len(data), "head", data[:64].hex())
    print("flags", re.findall(rb"UMASS\{[^}]{1,120}\}", data)[:3])
    print("strings", re.findall(rb"[ -~]{8,}", data)[:20])


if __name__ == "__main__":
    main()