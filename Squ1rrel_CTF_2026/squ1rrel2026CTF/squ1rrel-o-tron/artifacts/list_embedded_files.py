from pathlib import Path
import re


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
for name, value in re.findall(r'"([^"\\]+)":\s*"([^"\\]*)"', object_text):
    print(f"{name}\t{len(value)}")