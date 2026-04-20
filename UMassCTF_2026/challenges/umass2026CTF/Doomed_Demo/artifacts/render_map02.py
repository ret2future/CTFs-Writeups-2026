#!/usr/bin/env python3

from pathlib import Path
import struct

from PIL import Image, ImageDraw


WAD = Path("/root/umass2026CTF/Doomed_Demo/starting_files/freedoom-0.13.0/freedoom2.wad")
OUT = Path("/root/umass2026CTF/Doomed_Demo/artifacts/map02.png")

CANDIDATES = {
    "cand10": (0x074CC85, 0x03F5F1BC),
    "cand14": (0x1F050F3, 0x03DF0004),
    "cand18": (0x08FFF11, 0x04A0C0FC),
    "cand22": (0x36FFBEE, 0x032FFAD7),
}

THING_LABELS = {
    1: "start",
    5: "blue key",
    6: "yellow key",
    13: "red key",
    2002: "chaingun",
    2003: "rocket",
}

LINE_LABELS = {
    702: "open doors",
    721: "yellow switch",
    1468: "blue door",
    1473: "blue door",
    2365: "lift btn",
    2792: "exit",
}


def read_wad_directory(data: bytes):
    numlumps, infotableofs = struct.unpack_from("<II", data, 4)
    lumps = []
    for index in range(numlumps):
        offset, size, name = struct.unpack_from("<II8s", data, infotableofs + index * 16)
        lumps.append((name.rstrip(b"\0").decode("ascii"), offset, size))
    return lumps


def get_map_lumps(data: bytes, lumps, map_name: str):
    for index, (name, _, _) in enumerate(lumps):
        if name == map_name:
            out = {}
            for sub_name, off, size in lumps[index + 1 : index + 11]:
                out[sub_name] = data[off : off + size]
            return out
    raise ValueError(f"map {map_name} not found")


def parse_vertexes(blob: bytes):
    return [struct.unpack_from("<hh", blob, offset) for offset in range(0, len(blob), 4)]


def parse_linedefs(blob: bytes):
    linedefs = []
    for index, offset in enumerate(range(0, len(blob), 14)):
        v1, v2, flags, special, tag, right, left = struct.unpack_from("<hhhhhhh", blob, offset)
        linedefs.append({
            "index": index,
            "v1": v1,
            "v2": v2,
            "flags": flags,
            "special": special,
            "tag": tag,
            "right": right,
            "left": left,
        })
    return linedefs


def parse_things(blob: bytes):
    things = []
    for offset in range(0, len(blob), 10):
        x, y, angle, thing_type, flags = struct.unpack_from("<hhhhh", blob, offset)
        things.append({
            "x": x,
            "y": y,
            "angle": angle,
            "type": thing_type,
            "flags": flags,
        })
    return things


def world_to_image(x, y, min_x, max_y, scale, padding):
    return (
        padding + int((x - min_x) * scale),
        padding + int((max_y - y) * scale),
    )


def main() -> None:
    data = WAD.read_bytes()
    lumps = read_wad_directory(data)
    map_lumps = get_map_lumps(data, lumps, "MAP03")
    vertexes = parse_vertexes(map_lumps["VERTEXES"])
    linedefs = parse_linedefs(map_lumps["LINEDEFS"])
    things = parse_things(map_lumps["THINGS"])

    xs = [vertex[0] for vertex in vertexes]
    ys = [vertex[1] for vertex in vertexes]
    min_x, max_x = min(xs), max(xs)
    min_y, max_y = min(ys), max(ys)
    scale = 0.2
    padding = 40
    width = int((max_x - min_x) * scale) + padding * 2
    height = int((max_y - min_y) * scale) + padding * 2

    image = Image.new("RGB", (width, height), "white")
    draw = ImageDraw.Draw(image)

    for line in linedefs:
        x1, y1 = vertexes[line["v1"]]
        x2, y2 = vertexes[line["v2"]]
        p1 = world_to_image(x1, y1, min_x, max_y, scale, padding)
        p2 = world_to_image(x2, y2, min_x, max_y, scale, padding)
        color = "black"
        line_width = 1
        if line["special"] != 0:
            color = "red"
            line_width = 2
        draw.line((p1, p2), fill=color, width=line_width)
        if line["index"] in LINE_LABELS:
            mx = (x1 + x2) / 2
            my = (y1 + y2) / 2
            lx, ly = world_to_image(mx, my, min_x, max_y, scale, padding)
            draw.text((lx + 4, ly + 4), f"{line['index']} {LINE_LABELS[line['index']]}", fill="darkred")

    for thing in things:
        label = THING_LABELS.get(thing["type"])
        if label is None:
            continue
        px, py = world_to_image(thing["x"], thing["y"], min_x, max_y, scale, padding)
        draw.rectangle((px - 3, py - 3, px + 3, py + 3), fill="green")
        draw.text((px + 5, py - 5), label, fill="darkgreen")

    for name, (raw_x, raw_y) in CANDIDATES.items():
        signed_y = raw_y if raw_y < 0x80000000 else raw_y - 0x100000000
        signed_x = raw_x if raw_x < 0x80000000 else raw_x - 0x100000000
        x = signed_x / 65536.0
        y = signed_y / 65536.0
        px, py = world_to_image(x, y, min_x, max_y, scale, padding)
        draw.ellipse((px - 4, py - 4, px + 4, py + 4), fill="blue")
        draw.text((px + 6, py - 6), name, fill="blue")

    image.save(OUT)
    print(OUT)


if __name__ == "__main__":
    main()