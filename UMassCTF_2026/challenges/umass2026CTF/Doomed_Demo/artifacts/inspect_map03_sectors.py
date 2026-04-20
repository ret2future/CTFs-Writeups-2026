#!/usr/bin/env python3

from pathlib import Path
import struct
import sys


WAD = Path("/root/umass2026CTF/Doomed_Demo/starting_files/freedoom-0.13.0/freedoom2.wad")


def clean(raw: bytes) -> str:
    return raw.rstrip(b"\0").decode("ascii", "ignore")


def main() -> None:
    targets = {int(arg) for arg in sys.argv[1:]}
    data = WAD.read_bytes()
    num_lumps, info_table_ofs = struct.unpack_from("<II", data, 4)
    directory = [struct.unpack_from("<II8s", data, info_table_ofs + index * 16) for index in range(num_lumps)]
    names = [clean(name) for _, _, name in directory]
    index = names.index("MAP03")
    lumps = {names[i]: (directory[i][0], directory[i][1]) for i in range(index, index + 11)}

    vertex_ofs, vertex_size = lumps["VERTEXES"]
    linedef_ofs, linedef_size = lumps["LINEDEFS"]
    sidedef_ofs, sidedef_size = lumps["SIDEDEFS"]
    vertices = [struct.unpack_from("<hh", data, vertex_ofs + item * 4) for item in range(vertex_size // 4)]

    sidedefs = []
    for item in range(sidedef_size // 30):
        x_off, y_off, upper, lower, middle, sector = struct.unpack_from("<hh8s8s8sh", data, sidedef_ofs + item * 30)
        sidedefs.append({
            "sector": sector,
            "upper": clean(upper),
            "lower": clean(lower),
            "middle": clean(middle),
        })

    for item in range(linedef_size // 14):
        v1, v2, flags, special, tag, right, left = struct.unpack_from("<hhhhhhh", data, linedef_ofs + item * 14)
        right_sector = sidedefs[right]["sector"] if right != -1 else None
        left_sector = sidedefs[left]["sector"] if left != -1 else None
        if targets.isdisjoint({sector for sector in (right_sector, left_sector) if sector is not None}):
            continue
        print(
            {
                "line": item,
                "special": special,
                "tag": tag,
                "v1": vertices[v1],
                "v2": vertices[v2],
                "right_sector": right_sector,
                "left_sector": left_sector,
                "right": None if right == -1 else sidedefs[right],
                "left": None if left == -1 else sidedefs[left],
            }
        )


if __name__ == "__main__":
    main()