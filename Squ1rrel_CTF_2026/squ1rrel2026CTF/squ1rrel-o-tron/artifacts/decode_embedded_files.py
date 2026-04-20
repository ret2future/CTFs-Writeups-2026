from base64 import b64decode
from pathlib import Path
import re
import zlib


data = Path("artifacts/main.js").read_text()
match = re.search(r"var embedded_files = \{", data)
if not match:
    raise SystemExit("embedded_files object not found")

start = match.end()
index = start
depth = 1
while index < len(data) and depth:
    char = data[index]
    if char == "{":
        depth += 1
    elif char == "}":
        depth -= 1
    index += 1

object_text = data[start:index - 1]
out_root = Path("artifacts/embedded")
out_root.mkdir(parents=True, exist_ok=True)

for name, value in re.findall(r'"([^"\\]+)":\s*"([^"\\]*)"', object_text):
    raw = b64decode(value)
    try:
        payload = zlib.decompress(raw)
    except zlib.error:
        payload = raw
    path = out_root / name
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_bytes(payload)
    print(f"{name}\t{len(payload)}")