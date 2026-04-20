import re
from pathlib import Path

BAT = Path("extracted_http/codeberg.org__maldev_loader_raw_branch_main_packageloader.bat")
OUT_PSLINE = Path("expanded_psline.txt")
OUT_PSCMD = Path("expanded_ps_command.ps1")


def expand_batch_vars(value: str, env: dict[str, str]) -> str:
    pattern = re.compile(r"%([^%]+)%")
    return pattern.sub(lambda m: env.get(m.group(1), ""), value)


def main() -> int:
    lines = BAT.read_text(errors="ignore").splitlines()
    env: dict[str, str] = {}
    expanded_lines: list[str] = []

    for line in lines:
        expanded = expand_batch_vars(line, env)
        expanded_lines.append(expanded)

        match = re.match(r"\s*set\s+([^=\s]+)=(.*)$", expanded, re.I)
        if match:
            env[match.group(1)] = match.group(2)

    psline = None
    for line in expanded_lines:
        if "powershell.exe -ep bypass" in line.lower():
            psline = line
            break

    print("PS line found:", bool(psline))
    if not psline:
        return 1

    OUT_PSLINE.write_text(psline)
    print("Expanded line length:", len(psline))
    print("Expanded head:", psline[:220])
    print("Expanded tail:", psline[-220:])

    match = re.search(r'-c\s+"(.*)"\s*$', psline)
    if not match:
        print("Could not parse -c payload")
        return 2

    pscmd = match.group(1)
    OUT_PSCMD.write_text(pscmd)
    print("PowerShell command length:", len(pscmd))
    print("PS cmd head:", pscmd[:220])
    print("PS cmd tail:", pscmd[-220:])

    urls = re.findall(r"https?://[^'\"\s]+", pscmd)
    for url in urls:
        print("URL:", url)

    return 0


if __name__ == "__main__":
    raise SystemExit(main())
