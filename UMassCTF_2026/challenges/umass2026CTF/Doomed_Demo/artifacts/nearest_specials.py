#!/usr/bin/env python3

from pathlib import Path
import math
import struct


WAD = Path("/usr/share/games/doom/freedoom2.wad")

CANDIDATES = {
    "cand10": (0x2A85FDD, 0xFD80024B),
    "cand14": (0x1900442, 0xFEBCAD9E),
    "cand18": (0x1900004, 0xFE6E28EB),
    "cand22": (0x1900038, 0xFE69D6CE),
}


def read_wad_directory(data: bytes):
    numlumps, infotableofs = struct.unpack_from("<II", data, 4)
    entries = []
    for index in range(numlumps):
        offset, size, name = struct.unpack_from("<II8s", data, infotableofs + index * 16)
        entries.append((name.rstrip(b"\0").decode("ascii"), offset, size))
    return entries


def get_map_lumps(data: bytes, lumps, map_name: str):
    for index, (name, _, _) in enumerate(lumps):
        if name == map_name:
            out = {}
            for sub_name, off, size in lumps[index + 1 : index + 11]:
                out[sub_name] = data[off : off + size]
            return out
    raise ValueError(map_name)


def parse_vertexes(blob: bytes):
    return [struct.unpack_from("<hh", blob, offset) for offset in range(0, len(blob), 4)]


def parse_linedefs(blob: bytes):
    out = []
    for offset in range(0, len(blob), 14):
        v1, v2, flags, special, tag, right, left = struct.unpack_from("<hhhhhhh", blob, offset)
        out.append((v1, v2, flags, special, tag, right, left))
    return out


def signed32(value: int) -> int:
    return value if value < 0x80000000 else value - 0x100000000


def point_line_distance(px, py, x1, y1, x2, y2):
    dx = x2 - x1
    dy = y2 - y1
    if dx == 0 and dy == 0:
        return math.hypot(px - x1, py - y1)
    t = ((px - x1) * dx + (py - y1) * dy) / float(dx * dx + dy * dy)
    t = max(0.0, min(1.0, t))
    projx = x1 + t * dx
    projy = y1 + t * dy
    return math.hypot(px - projx, py - projy)


def main() -> None:
    data = WAD.read_bytes()
    lumps = read_wad_directory(data)
    map_lumps = get_map_lumps(data, lumps, "MAP02")
    verts = parse_vertexes(map_lumps["VERTEXES"])
    lines = parse_linedefs(map_lumps["LINEDEFS"])

    specials = []
    for index, (v1, v2, flags, special, tag, right, left) in enumerate(lines):
        if special == 0:
            continue
        x1, y1 = verts[v1]
        x2, y2 = verts[v2]
        specials.append((index, special, tag, x1, y1, x2, y2))

    for name, (raw_x, raw_y) in CANDIDATES.items():
        x = signed32(raw_x) / 65536.0
        y = signed32(raw_y) / 65536.0
        print(name, 'pos', x, y)
        ranked = []
        for index, special, tag, x1, y1, x2, y2 in specials:
            dist = point_line_distance(x, y, x1, y1, x2, y2)
            ranked.append((dist, index, special, tag, x1, y1, x2, y2))
        ranked.sort()
        for item in ranked[:10]:
            print(' ', item)
        print()


if __name__ == "__main__":
    main()