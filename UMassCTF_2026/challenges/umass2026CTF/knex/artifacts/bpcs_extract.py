#!/usr/bin/env python3
"""
BPCS (Bit-Plane Complexity Segmentation) steganography extractor.
alpha = 0.45 (from hint). Vectorized with numpy for speed.
Uses Canonical Gray Code (CGC) representation, 8x8 blocks.
"""
import re, sys
from PIL import Image
import numpy as np

ALPHA = 0.45
BLOCK = 8
MAX_TRANS = (BLOCK * (BLOCK - 1)) * 2  # 112 for 8x8

# Checkerboard pattern for conjugation
_checker = np.empty((BLOCK, BLOCK), dtype=np.uint8)
for _i in range(BLOCK):
    for _j in range(BLOCK):
        _checker[_i, _j] = (_i + _j) % 2

def to_cgc(arr):
    return arr ^ (arr >> 1)

def get_all_block_complexities(plane):
    """Vectorized: compute complexity for all 8x8 blocks in a bit-plane."""
    H, W = plane.shape
    bh, bw = H // BLOCK, W // BLOCK
    # Trim to multiple of 8
    p = plane[:bh*BLOCK, :bw*BLOCK]
    # Reshape to (bh, BLOCK, bw, BLOCK)
    blocks = p.reshape(bh, BLOCK, bw, BLOCK)
    # Horizontal transitions: compare adjacent cols within each block
    h_trans = np.sum(blocks[:, :, :, :-1] != blocks[:, :, :, 1:], axis=(1, 3))
    # Vertical transitions: compare adjacent rows within each block
    v_trans = np.sum(blocks[:, :-1, :, :] != blocks[: ,1:, :, :], axis=(1, 3))
    return (h_trans + v_trans) / MAX_TRANS  # shape (bh, bw)

def extract_bpcs(img_path, alpha=ALPHA):
    img = Image.open(img_path).convert('RGB')
    arr = np.array(img, dtype=np.uint32)
    H, W, C = arr.shape
    bh, bw = H // BLOCK, W // BLOCK
    print(f"Image: {W}x{H}, blocks: {bh}x{bw}={bh*bw} per plane, 24 planes")

    cgc = to_cgc(arr)

    all_bits = []
    total_complex = 0

    # Order: MSB to LSB, then channel R->G->B, then row-major blocks
    for bit in range(7, -1, -1):
        for ch in range(C):
            plane = ((cgc[:, :, ch] >> bit) & 1).astype(np.uint8)
            complexities = get_all_block_complexities(plane)
            mask = complexities >= alpha  # (bh, bw) boolean
            total_complex += np.sum(mask)

            # Extract bits from complex blocks in row-major order
            p_trim = plane[:bh*BLOCK, :bw*BLOCK].reshape(bh, BLOCK, bw, BLOCK)
            for by in range(bh):
                for bx in range(bw):
                    if mask[by, bx]:
                        block = p_trim[by, :, bx, :]
                        # De-conjugate if needed: if complexity of raw block < alpha,
                        # the stored block is conjugated. We detect by checking if
                        # first bit = 1 (conjugation marker in some implementations).
                        # For now extract as-is; we'll try both orders.
                        all_bits.append(block.flatten())

    print(f"Complex blocks: {total_complex}")
    if not all_bits:
        print("No complex blocks found!")
        return None

    bits = np.concatenate(all_bits, axis=0).astype(np.uint8)
    print(f"Total bits: {len(bits)}")
    data = np.packbits(bits).tobytes()
    print(f"Bytes: {len(data)}")
    print(f"First 32 hex: {data[:32].hex()}")
    print(f"First 32 repr: {repr(data[:32])}")

    flags = re.findall(rb'UMASS\{[^}]{1,60}\}', data)
    if flags:
        print(f"\n*** FLAG FOUND: {flags} ***")

    strings = re.findall(rb'[\x20-\x7e]{8,}', data)
    print(f"\nPrintable strings (first 15):")
    for s in strings[:15]:
        print(f"  {s.decode('ascii', 'replace')}")

    return data

if __name__ == '__main__':
    img_path = '/root/umass2026CTF/knex/artifacts/lush_clean.png'
    data = extract_bpcs(img_path)
    if data:
        out = '/root/umass2026CTF/knex/artifacts/bpcs_extracted.bin'
        with open(out, 'wb') as f:
            f.write(data)
        print(f"\nSaved to {out}")
        # Also check for file magic bytes
        magic = data[:8].hex()
        print(f"File magic: {magic}")
        if data[:4] == b'\xff\xd8\xff\xe0':
            print("-> JPEG!")
        elif data[:8] == b'\x89PNG\r\n\x1a\n':
            print("-> PNG!")
        elif data[:2] == b'PK':
            print("-> ZIP!")
        elif data[:3] == b'ID3' or data[:2] == b'\xff\xfb':
            print("-> MP3!")

