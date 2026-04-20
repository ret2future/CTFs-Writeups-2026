#!/usr/bin/env python3

from pathlib import Path
import struct


WAD = Path("/root/umass2026CTF/Doomed_Demo/starting_files/freedoom-0.13.0/freedoom2.wad")


def clean_name(raw: bytes) -> str:
    return raw.rstrip(b"\0").decode("ascii", "ignore")


def read_wad_directory(data: bytes):
    num_lumps, info_table_ofs = struct.unpack_from("<II", data, 4)
    return [
        struct.unpack_from("<II8s", data, info_table_ofs + index * 16)
        for index in range(num_lumps)
    ]


def get_map_lumps(data: bytes, directory, map_name: str):
    names = [clean_name(name) for _, _, name in directory]
    index = names.index(map_name)
    out = {}
    for lump_ofs, lump_size, lump_name in directory[index + 1 : index + 11]:
        out[clean_name(lump_name)] = data[lump_ofs : lump_ofs + lump_size]
    return out


def parse_vertexes(blob: bytes):
    return [struct.unpack_from("<hh", blob, offset) for offset in range(0, len(blob), 4)]


def parse_sidedefs(blob: bytes):
    sides = []
    for offset in range(0, len(blob), 30):
        x_offset, y_offset, upper, lower, middle, sector = struct.unpack_from(
            "<hh8s8s8sh", blob, offset
        )
        sides.append(
            {
                "x_offset": x_offset,
                "y_offset": y_offset,
                "upper": clean_name(upper),
                "lower": clean_name(lower),
                "middle": clean_name(middle),
                "sector": sector,
            }
        )
    return sides


def parse_linedefs(blob: bytes):
    lines = []
    for index, offset in enumerate(range(0, len(blob), 14)):
        v1, v2, flags, special, tag, right, left = struct.unpack_from("<hhhhhhh", blob, offset)
        lines.append(
            {
                "index": index,
                "v1": v1,
                "v2": v2,
                "flags": flags,
                "special": special,
                "tag": tag,
                "right": right,
                "left": left,
            }
        )
    return lines


def parse_sectors(blob: bytes):
    sectors = []
    for offset in range(0, len(blob), 26):
        floor, ceiling, floor_flat, ceiling_flat, light, special, tag = struct.unpack_from(
            "<hh8s8shhh", blob, offset
        )
        sectors.append(
            {
                "floor": floor,
                "ceiling": ceiling,
                "floor_flat": clean_name(floor_flat),
                "ceiling_flat": clean_name(ceiling_flat),
                "light": light,
                "special": special,
                "tag": tag,
            }
        )
    return sectors


def parse_things(blob: bytes):
    things = []
    for offset in range(0, len(blob), 10):
        x, y, angle, thing_type, flags = struct.unpack_from("<hhhhh", blob, offset)
        things.append(
            {
                "x": x,
                "y": y,
                "angle": angle,
                "type": thing_type,
                "flags": flags,
            }
        )
    return things


def side_summary(sides, side_index: int):
    if side_index == -1:
        return None
    side = sides[side_index]
    return {
        "sector": side["sector"],
        "upper": side["upper"],
        "lower": side["lower"],
        "middle": side["middle"],
    }


def line_textures(side):
    if side is None:
        return []
    return [texture for texture in (side["upper"], side["lower"], side["middle"]) if texture != "-"]


def main() -> None:
    data = WAD.read_bytes()
    directory = read_wad_directory(data)
    lumps = get_map_lumps(data, directory, "MAP03")
    vertexes = parse_vertexes(lumps["VERTEXES"])
    sidedefs = parse_sidedefs(lumps["SIDEDEFS"])
    linedefs = parse_linedefs(lumps["LINEDEFS"])
    sectors = parse_sectors(lumps["SECTORS"])
    things = parse_things(lumps["THINGS"])

    interesting_types = {
        1: "player1_start",
        5: "blue_keycard",
        6: "yellow_keycard",
        13: "red_keycard",
        2001: "shotgun",
        2002: "chaingun",
        2003: "rocket_launcher",
        2007: "ammo_clip",
        2008: "shotgun_shells",
        2012: "medikit",
        2018: "green_armor",
        3001: "imp",
        3002: "demon",
        3004: "zombieman",
        9: "shotgun_guy",
    }

    print("Non-zero linedefs:")
    for line in linedefs:
        if line["special"] == 0:
            continue
        front = side_summary(sidedefs, line["right"])
        back = side_summary(sidedefs, line["left"])
        textures = sorted(set(line_textures(front) + line_textures(back)))
        print(
            f"line={line['index']:3d} special={line['special']:3d} tag={line['tag']:3d} "
            f"v1={vertexes[line['v1']]} v2={vertexes[line['v2']]} textures={textures} "
            f"front={front} back={back}"
        )

    print("\nTagged sectors:")
    for index, sector in enumerate(sectors):
        if sector["tag"] != 0 or sector["special"] != 0:
            print(f"sector={index:3d} {sector}")

    print("\nInteresting things:")
    for thing in things:
        name = interesting_types.get(thing["type"])
        if name is not None:
            print(f"thing={name:15s} x={thing['x']:5d} y={thing['y']:5d} angle={thing['angle']:3d} flags={thing['flags']}")


if __name__ == "__main__":
    main()