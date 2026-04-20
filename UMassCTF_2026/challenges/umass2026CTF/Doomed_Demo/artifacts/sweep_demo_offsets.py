#!/usr/bin/env python3

from pathlib import Path
import argparse
import math
import re
import subprocess


SRC = Path("/root/umass2026CTF/Doomed_Demo/starting_files/demo.lmp")
TMP = Path("/root/umass2026CTF/Doomed_Demo/artifacts/tmp_candidates")
BIN = Path(
    "/root/umass2026CTF/Doomed_Demo/artifacts/candidates/chocolate-doom-3.1.1/build/src/chocolate-doom"
)
IWAD = Path("/root/umass2026CTF/Doomed_Demo/starting_files/freedoom-0.13.0/freedoom2.wad")

HEADER_109 = bytes([109, 1, 1, 3, 0, 0, 0, 0, 0, 1, 0, 0, 0])
FINALPOS_RE = re.compile(r"FINALPOS: ang=0x([0-9a-f]+) x=0x([0-9a-f]+) y=0x([0-9a-f]+)")

TARGETS = {
    "button_728": ((1216.0, -192.0), (1344.0, -192.0)),
    "button_805": ((-928.0, -384.0), (-800.0, -384.0)),
    "exit_209": ((64.0, -352.0), (64.0, -288.0)),
}


def signed32(value: int) -> int:
    return value if value < 0x80000000 else value - 0x100000000


def point_segment_distance(px: float, py: float, ax: float, ay: float, bx: float, by: float) -> float:
    abx = bx - ax
    aby = by - ay
    apx = px - ax
    apy = py - ay
    denom = abx * abx + aby * aby
    if denom == 0:
        return math.hypot(px - ax, py - ay)
    t = max(0.0, min(1.0, (apx * abx + apy * aby) / denom))
    closest_x = ax + t * abx
    closest_y = ay + t * aby
    return math.hypot(px - closest_x, py - closest_y)


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("--start", type=int, default=0)
    parser.add_argument("--end", type=int, default=64)
    args = parser.parse_args()

    TMP.mkdir(exist_ok=True)
    data = SRC.read_bytes()
    results = []

    for offset in range(args.start, args.end + 1):
        candidate = TMP / f"cand_{offset}_v109.lmp"
        candidate.write_bytes(HEADER_109 + data[offset:])
        try:
            proc = subprocess.run(
                [
                    "xvfb-run",
                    "-a",
                    str(BIN),
                    "-iwad",
                    str(IWAD),
                    "-timedemo",
                    str(candidate),
                    "-nodraw",
                    "-nosound",
                    "-nograbmouse",
                    "-window",
                ],
                stdout=subprocess.PIPE,
                stderr=subprocess.STDOUT,
                text=True,
                timeout=20,
            )
        except subprocess.TimeoutExpired:
            print(f"offset={offset:2d} timeout", flush=True)
            continue
        match = FINALPOS_RE.search(proc.stdout)
        if match is None:
            print(f"offset={offset:2d} no-finalpos", flush=True)
            continue
        ang_hex, x_hex, y_hex = match.groups()
        raw_x = int(x_hex, 16)
        raw_y = int(y_hex, 16)
        x = signed32(raw_x) / 65536.0
        y = signed32(raw_y) / 65536.0
        distances = {
            name: point_segment_distance(x, y, ax, ay, bx, by)
            for name, ((ax, ay), (bx, by)) in TARGETS.items()
        }
        results.append(
            {
                "offset": offset,
                "ang_hex": ang_hex,
                "x_hex": x_hex,
                "y_hex": y_hex,
                "x": x,
                "y": y,
                "distances": distances,
            }
        )
        nearest_name, nearest_dist = min(distances.items(), key=lambda pair: pair[1])
        print(
            f"offset={offset:2d} pos=({x:.3f},{y:.3f}) raw=({x_hex},{y_hex}) "
            f"nearest={nearest_name}:{nearest_dist:.3f} "
            f"d728={distances['button_728']:.3f} d805={distances['button_805']:.3f} "
            f"d209={distances['exit_209']:.3f}",
            flush=True,
        )

    results.sort(key=lambda item: min(item["distances"].values()))
    print("SUMMARY", flush=True)
    for item in results:
        nearest_name, nearest_dist = min(item["distances"].items(), key=lambda pair: pair[1])
        print(
            f"offset={item['offset']:2d} pos=({item['x']:.3f},{item['y']:.3f}) "
            f"raw=({item['x_hex']},{item['y_hex']}) nearest={nearest_name}:{nearest_dist:.3f} "
            f"d728={item['distances']['button_728']:.3f} d805={item['distances']['button_805']:.3f} "
            f"d209={item['distances']['exit_209']:.3f}"
        )


if __name__ == "__main__":
    main()