#!/usr/bin/env python3

from pathlib import Path
import bz2
import gzip
import io
import lzma
import re
import zlib

import numpy as np
from PIL import Image


PATH = Path("/root/umass2026CTF/knex/artifacts/lush_clean.png")
OUT_DIR = Path("/root/umass2026CTF/knex/artifacts/notcompsky_probe")
OUT_DIR.mkdir(exist_ok=True)

CHECKER = np.fromfunction(
    lambda y, x: 1 ^ ((x & 1) ^ (y & 1)),
    (8, 8),
    dtype=int,
).astype(np.uint8)


def to_cgc(arr: np.ndarray) -> np.ndarray:
    return arr ^ (arr >> 1)


def complex_blocks(plane: np.ndarray, threshold: int) -> np.ndarray:
    height, width = plane.shape
    block_rows = height // 8
    block_cols = width // 8
    trimmed = plane[: block_rows * 8, : block_cols * 8]
    blocks = trimmed.reshape(block_rows, 8, block_cols, 8).transpose(0, 2, 1, 3)
    row_changes = np.count_nonzero(blocks[:, :, 1:, :] != blocks[:, :, :-1, :], axis=(2, 3))
    col_changes = np.count_nonzero(blocks[:, :, :, 1:] != blocks[:, :, :, :-1], axis=(2, 3))
    complexity = row_changes + col_changes
    mask = complexity >= threshold
    return blocks[mask]


def extract(threshold: int) -> bytes:
    img = np.array(Image.open(PATH).convert("RGB"), dtype=np.uint8)
    cgc = to_cgc(img)
    output = bytearray()

    for channel in range(3):
        channel_bytes = cgc[:, :, channel].copy()
        for _bit in range(8):
            plane = (channel_bytes & 1).astype(np.uint8)
            channel_bytes >>= 1
            for block in complex_blocks(plane, threshold):
                if block[7, 7]:
                    block = block ^ CHECKER
                flat = block.reshape(-1)
                for byte_index in range(7):
                    value = 0
                    for bit_index in range(8):
                        value |= int(flat[8 * byte_index + bit_index]) << bit_index
                    output.append(value)

    return bytes(output)


def describe(data: bytes) -> None:
    flags = re.findall(rb"UMASS\{[^}]{1,120}\}", data)
    strings = re.findall(rb"[ -~]{8,}", data)
    print("flags:", flags[:3])
    print("strings:", strings[:10])
    for name, decoder in (
        ("zlib", zlib.decompress),
        ("gzip", gzip.decompress),
        ("bz2", bz2.decompress),
        ("lzma", lzma.decompress),
    ):
        try:
            decoded = decoder(data)
        except Exception:
            continue
        print(name, "ok", len(decoded), repr(decoded[:80]))


def main() -> None:
    for threshold in (49, 50, 51, 52):
        data = extract(threshold)
        out_path = OUT_DIR / f"thr_{threshold}.bin"
        out_path.write_bytes(data)
        print("threshold", threshold, "len", len(data), "head", data[:32].hex())
        describe(data)
        print("-" * 60)


if __name__ == "__main__":
    main()