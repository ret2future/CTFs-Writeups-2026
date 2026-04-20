from pathlib import Path
import sys


def extract_function(source: str, name: str) -> str | None:
    marker = f"function {name}("
    start = source.find(marker)
    if start < 0:
        return None

    brace = source.find("{", start)
    depth = 0
    index = brace
    while index < len(source):
        char = source[index]
        if char == "{":
            depth += 1
        elif char == "}":
            depth -= 1
            if depth == 0:
                index += 1
                break
        index += 1
    return source[start:index]


def main() -> int:
    if len(sys.argv) < 2:
        print("usage: extract_functions.py NAME [NAME ...]", file=sys.stderr)
        return 1

    source = Path("artifacts/main.js").read_text()
    for name in sys.argv[1:]:
        body = extract_function(source, name)
        if body is None:
            print(f"==== {name} missing ====")
            continue
        print(f"==== {name} {len(body)} ====")
        print(body)
    return 0


if __name__ == "__main__":
    raise SystemExit(main())