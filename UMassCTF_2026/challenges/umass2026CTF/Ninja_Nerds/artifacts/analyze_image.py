#!/usr/bin/env python3

from pathlib import Path

from PIL import Image


ROOT = Path(__file__).resolve().parent
SOURCE = ROOT / "extracted" / "challenge.png"


def write_bitplanes(img: Image.Image, out_dir: Path) -> None:
    rgb = img.convert("RGB")
    pixels = rgb.load()
    width, height = rgb.size

    out_dir.mkdir(parents=True, exist_ok=True)
    for channel_index, channel_name in enumerate("rgb"):
        for bit in range(8):
            plane = Image.new("L", (width, height))
            plane_pixels = plane.load()
            for y in range(height):
                for x in range(width):
                    plane_pixels[x, y] = 255 if ((pixels[x, y][channel_index] >> bit) & 1) else 0
            plane.save(out_dir / f"{channel_name}_bit{bit}.png")

    differences = {
        "r_minus_g": lambda r, g, b: (r - g) % 256,
        "r_minus_b": lambda r, g, b: (r - b) % 256,
        "g_minus_b": lambda r, g, b: (g - b) % 256,
    }
    for label, transform in differences.items():
        derived = Image.new("L", (width, height))
        derived_pixels = derived.load()
        for y in range(height):
            for x in range(width):
                derived_pixels[x, y] = transform(*pixels[x, y])
        derived.save(out_dir / f"{label}.png")


def write_lowbit_amplifications(img: Image.Image, out_dir: Path) -> None:
    rgb = img.convert("RGB")
    pixels = rgb.load()
    width, height = rgb.size

    out_dir.mkdir(parents=True, exist_ok=True)
    for channel_index, channel_name in enumerate("rgb"):
        for bits in (1, 2, 3):
            amplified = Image.new("L", (width, height))
            amplified_pixels = amplified.load()
            mask = (1 << bits) - 1
            scale = 255 // mask
            for y in range(height):
                for x in range(width):
                    amplified_pixels[x, y] = (pixels[x, y][channel_index] & mask) * scale
            amplified.save(out_dir / f"{channel_name}_low{bits}.png")


def main() -> None:
    image = Image.open(SOURCE)
    write_bitplanes(image, ROOT / "bitplanes")
    write_lowbit_amplifications(image, ROOT / "enhanced")
    print("bitplanes:", len(list((ROOT / "bitplanes").glob("*.png"))))
    print("enhanced:", len(list((ROOT / "enhanced").glob("*.png"))))


if __name__ == "__main__":
    main()