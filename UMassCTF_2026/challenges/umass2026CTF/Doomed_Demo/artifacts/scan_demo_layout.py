#!/usr/bin/env python3

from pathlib import Path


def signed(value: int) -> int:
    return value - 256 if value > 127 else value


def main() -> None:
    data = Path("/root/umass2026CTF/Doomed_Demo/starting_files/demo.lmp").read_bytes()
    for header in range(7, 25):
        body = data[header:]
        if not body or body[-1] != 0x80:
            continue
        for frame_size in (4, 5, 6):
            frames = body[:-1]
            if len(frames) % frame_size:
                continue
            chunks = [frames[index : index + frame_size] for index in range(0, len(frames), frame_size)]
            count = len(chunks)
            move_ok = sum(abs(signed(chunk[0])) <= 80 and abs(signed(chunk[1])) <= 80 for chunk in chunks) / count
            event_ok = sum(chunk[frame_size - 1] < 0x80 for chunk in chunks) / count
            nonzero = sum(any(byte != 0 for byte in chunk) for chunk in chunks)
            print(
                f"header={header:2d} size={frame_size} frames={count:4d} "
                f"move_ok={move_ok:.3f} event_ok={event_ok:.3f} nonzero={nonzero}"
            )


if __name__ == "__main__":
    main()