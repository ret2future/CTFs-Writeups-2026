#!/usr/bin/env python3

from pathlib import Path


SRC = Path("/root/umass2026CTF/Doomed_Demo/starting_files/demo.lmp")
OUT = Path("/root/umass2026CTF/Doomed_Demo/artifacts/candidates")
OUT.mkdir(exist_ok=True)


def main() -> None:
    data = SRC.read_bytes()
    header_109 = bytes([109, 1, 1, 3, 0, 0, 0, 0, 0, 1, 0, 0, 0])
    header_111 = bytes([111, 1, 1, 3, 0, 0, 0, 0, 0, 1, 0, 0, 0])
    for offset in (10, 14, 18, 22):
        body = data[offset:]
        (OUT / f"cand_{offset}_v109.lmp").write_bytes(header_109 + body)
        (OUT / f"cand_{offset}_v111.lmp").write_bytes(header_111 + body)


if __name__ == "__main__":
    main()