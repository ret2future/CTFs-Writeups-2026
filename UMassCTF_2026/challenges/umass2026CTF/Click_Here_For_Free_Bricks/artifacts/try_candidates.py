#!/usr/bin/env python3

import subprocess
import sys


USERNAME = "zeekliviu"
PASSWORD = "umasscybersecebaza"
CHALLENGE_NAME = "Click Here For Free Bricks"

CANDIDATES = [
    ("launcher", "e7a09064fc40dd4e5dd2e14aa8dad89b328ef1b1fdb3288e4ef04b0bd497ccae"),
    ("launcher_decrypted", "e7a09064fc40dd4e5dd2e14aa8dad89b328ef1b1fdb3288e4ef04b0bd497ccae"),
    ("dump", "e7a09064fc40dd4e5dd2e14aa8dad89b328ef1b1fdb3288e4ef04b0bd497ccae"),
    ("launcher.dec", "e7a09064fc40dd4e5dd2e14aa8dad89b328ef1b1fdb3288e4ef04b0bd497ccae"),
    ("decrypted_launcher", "e7a09064fc40dd4e5dd2e14aa8dad89b328ef1b1fdb3288e4ef04b0bd497ccae"),
    ("launcher.decrypted", "e7a09064fc40dd4e5dd2e14aa8dad89b328ef1b1fdb3288e4ef04b0bd497ccae"),
    ("launcher_decrypted.bin", "e7a09064fc40dd4e5dd2e14aa8dad89b328ef1b1fdb3288e4ef04b0bd497ccae"),
    ("launcher_decrypteda", "e7a09064fc40dd4e5dd2e14aa8dad89b328ef1b1fdb3288e4ef04b0bd497ccae"),
    ("launcher-decrypted", "e7a09064fc40dd4e5dd2e14aa8dad89b328ef1b1fdb3288e4ef04b0bd497ccae"),
    ("launcher.wat", "e7a09064fc40dd4e5dd2e14aa8dad89b328ef1b1fdb3288e4ef04b0bd497ccae"),
    ("snoopy", "e7a09064fc40dd4e5dd2e14aa8dad89b328ef1b1fdb3288e4ef04b0bd497ccae"),
    ("virus.snoopy", "e7a09064fc40dd4e5dd2e14aa8dad89b328ef1b1fdb3288e4ef04b0bd497ccae"),
    ("virus_snoopy", "e7a09064fc40dd4e5dd2e14aa8dad89b328ef1b1fdb3288e4ef04b0bd497ccae"),
    ("virussnoopy", "e7a09064fc40dd4e5dd2e14aa8dad89b328ef1b1fdb3288e4ef04b0bd497ccae"),
    ("Linux.Snoopy.C", "e7a09064fc40dd4e5dd2e14aa8dad89b328ef1b1fdb3288e4ef04b0bd497ccae"),
    ("LinuxSnoopyC", "e7a09064fc40dd4e5dd2e14aa8dad89b328ef1b1fdb3288e4ef04b0bd497ccae"),
    ("Unix.Snoopy.C", "e7a09064fc40dd4e5dd2e14aa8dad89b328ef1b1fdb3288e4ef04b0bd497ccae"),
    ("UnixSnoopyC", "e7a09064fc40dd4e5dd2e14aa8dad89b328ef1b1fdb3288e4ef04b0bd497ccae"),
    ("e.bin", "695b3eeeb8a4a4d22405d78732f19c6e42527d374ae3b23ba1c4e4b757e10359"),
    ("ebin", "695b3eeeb8a4a4d22405d78732f19c6e42527d374ae3b23ba1c4e4b757e10359"),
    ("launcher", "695b3eeeb8a4a4d22405d78732f19c6e42527d374ae3b23ba1c4e4b757e10359"),
    ("fungame.jpg", "695b3eeeb8a4a4d22405d78732f19c6e42527d374ae3b23ba1c4e4b757e10359"),
    ("fungamejpg", "695b3eeeb8a4a4d22405d78732f19c6e42527d374ae3b23ba1c4e4b757e10359"),
    ("test.pcapng", "695b3eeeb8a4a4d22405d78732f19c6e42527d374ae3b23ba1c4e4b757e10359"),
    ("testpcapng", "695b3eeeb8a4a4d22405d78732f19c6e42527d374ae3b23ba1c4e4b757e10359"),
]


for name, digest in CANDIDATES:
    flag = f"UMASS{{{name}_{digest}}}"
    result = subprocess.run(
        [
            sys.executable,
            "/root/umass2026CTF/tools/submit_flag.py",
            "--username",
            USERNAME,
            "--password",
            PASSWORD,
            "--challenge-name",
            CHALLENGE_NAME,
            "--flag",
            flag,
        ],
        capture_output=True,
        text=True,
        check=False,
    )
    output = (result.stdout + result.stderr).strip()
    print(flag)
    print(output)
    if '"status": "correct"' in output or "'status': 'correct'" in output:
        print(f"FOUND {flag}")
        sys.exit(0)

print("NO_MATCH")