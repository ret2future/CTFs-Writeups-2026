#!/usr/bin/env python3

from __future__ import annotations

import pathlib
import re
from urllib.parse import unquote

import requests


CHALLENGE_ID = "e7fb6214-bae7-4ca8-9da6-293592b86e42"
AGENTS_PATH = pathlib.Path("/root/squ1rrel2026CTF/AGENTS.md")
SUBMIT_URL = f"https://ctf.squ1rrel.dev/api/v1/challs/{CHALLENGE_ID}/submit"


def get_auth_token() -> str:
    text = AGENTS_PATH.read_text(encoding="utf-8")
    match = re.search(r"token=([^\s)]+)", text)
    if not match:
        raise SystemExit("unable to find team token in AGENTS.md")
    team_token = unquote(match.group(1))
    response = requests.post(
        "https://ctf.squ1rrel.dev/api/v1/auth/login",
        json={"teamToken": team_token},
        timeout=20,
    )
    response.raise_for_status()
    return response.json()["data"]["authToken"]


def main() -> None:
    auth_token = get_auth_token()
    headers = {"Authorization": f"Bearer {auth_token}"}
    candidates = [
        "0.830",
        "0.831",
        "0.829",
        "5.216",
        "5.217",
        "5.215",
        "0.355",
        "0.356",
        "0.306",
        "2.251",
        "10.025",
        "0.012",
        "830.253",
    ]
    for value in candidates:
        flag = f"squ1rrel{{{value}}}"
        response = requests.post(SUBMIT_URL, headers=headers, json={"flag": flag}, timeout=20)
        print(flag)
        print(response.status_code)
        print(response.text.strip())
        print("---")


if __name__ == "__main__":
    main()