from pathlib import Path
import gzip
import re

from PIL import Image


def main() -> int:
    image_path = Path("extracted_http/i.ibb.co__0zt4quciwxs2_payload.png")
    image = Image.open(image_path).convert("RGB")

    pixel_0_0 = image.getpixel((0, 0))
    pixel_1_0 = image.getpixel((1, 0))
    size = (pixel_0_0[0] << 24) | (pixel_0_0[1] << 16) | (pixel_0_0[2] << 8) | pixel_1_0[0]

    buffer = bytearray(size)
    index = 0
    pixel_index = 2
    width, _ = image.size

    while index < size:
        x_coord = pixel_index % width
        y_coord = pixel_index // width
        red, green, blue = image.getpixel((x_coord, y_coord))

        if index < size:
            buffer[index] = red
            index += 1
        if index < size:
            buffer[index] = green
            index += 1
        if index < size:
            buffer[index] = blue
            index += 1

        pixel_index += 1

    key = b"91d2f87dab32f433"
    for byte_index in range(len(buffer)):
        buffer[byte_index] ^= key[byte_index % len(key)]

    decoded = gzip.decompress(bytes(buffer))
    Path("stage2_decoded.txt").write_bytes(decoded)

    text = decoded.decode("utf-8", errors="ignore")
    print("decoded_len", len(text))

    candidates = re.findall(r"[A-Za-z0-9_\-]*\{[^\n\r\}]{1,200}\}", text)
    print("candidates", candidates[:20])

    for marker in ["flag", "FLAG", "ctf", "CTF", "ramadan", "invis", "{", "}"]:
        if marker in text:
            print(f"contains_{marker}", True)

    print("head", text[:500])
    print("tail", text[-500:])
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
