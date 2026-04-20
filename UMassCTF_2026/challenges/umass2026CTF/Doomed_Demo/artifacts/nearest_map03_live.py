#!/usr/bin/env python3

from pathlib import Path
import struct
import sys


WAD = Path("/root/umass2026CTF/Doomed_Demo/starting_files/freedoom-0.13.0/freedoom2.wad")


def signed32(value: int) -> int:
    return value if value < 0x80000000 else value - 0x100000000


def main() -> None:
    if len(sys.argv) % 3 != 1 or len(sys.argv) < 4:
        raise SystemExit("usage: nearest_map03_live.py <label> <xhex> <yhex> [<label> <xhex> <yhex> ...]")

    wad = WAD.read_bytes()
    num_lumps, dir_offset = struct.unpack_from("<II", wad, 4)
    directory = [struct.unpack_from("<II8s", wad, dir_offset + i * 16) for i in range(num_lumps)]
    names = [name.rstrip(b"\0").decode("ascii") for _, _, name in directory]
    map_index = names.index("MAP03")
    lumps = {names[i]: (directory[i][0], directory[i][1]) for i in range(map_index, map_index + 11)}

    vertex_offset, vertex_size = lumps["VERTEXES"]
    linedef_offset, linedef_size = lumps["LINEDEFS"]
    vertices = [struct.unpack_from("<hh", wad, vertex_offset + i * 4) for i in range(vertex_size // 4)]

    triples = [sys.argv[i : i + 3] for i in range(1, len(sys.argv), 3)]
    for label, raw_x_hex, raw_y_hex in triples:
        x = signed32(int(raw_x_hex, 16)) / 65536.0
        y = signed32(int(raw_y_hex, 16)) / 65536.0
        ranked = []
        for idx in range(linedef_size // 14):
            v1, v2, _flags, special, tag, _right, _left = struct.unpack_from("<hhhhhhh", wad, linedef_offset + idx * 14)
            if special == 0:
                continue
            x1, y1 = vertices[v1]
            x2, y2 = vertices[v2]
            dx = x2 - x1
            dy = y2 - y1
            if dx == 0 and dy == 0:
                proj_x, proj_y = x1, y1
            else:
                t = ((x - x1) * dx + (y - y1) * dy) / float(dx * dx + dy * dy)
                t = max(0.0, min(1.0, t))
                proj_x = x1 + t * dx
                proj_y = y1 + t * dy
            dist = ((x - proj_x) ** 2 + (y - proj_y) ** 2) ** 0.5
            ranked.append((dist, idx, special, tag, (x1, y1), (x2, y2)))
        ranked.sort()
        print(label, f"x={x:.3f}", f"y={y:.3f}")
        for item in ranked[:12]:
            print(" ", item)
        print()


if __name__ == "__main__":
    main()