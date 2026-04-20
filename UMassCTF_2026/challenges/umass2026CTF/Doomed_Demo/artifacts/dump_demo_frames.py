#!/usr/bin/env python3

from pathlib import Path
import sys


def signed(value: int) -> int:
    return value - 256 if value > 127 else value


def main() -> None:
    if len(sys.argv) != 3:
        raise SystemExit("usage: dump_demo_frames.py <header> <frame_size>")

    header = int(sys.argv[1])
    frame_size = int(sys.argv[2])
    data = Path("/root/umass2026CTF/Doomed_Demo/starting_files/demo.lmp").read_bytes()
    frames = data[header:-1]
    for index in range(0, min(len(frames), frame_size * 80), frame_size):
        chunk = frames[index : index + frame_size]
        if len(chunk) < frame_size:
            break
        print(index // frame_size, chunk.hex(), [signed(byte) for byte in chunk])


if __name__ == "__main__":
    main()