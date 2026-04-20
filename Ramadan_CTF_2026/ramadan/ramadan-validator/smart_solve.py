#!/usr/bin/env python3
"""
Smarter brute-force: test common patterns and multi-char combinations
"""

import subprocess
import string
import os

VALIDATOR = "./validator"
TARGET = "Sorry, that's not it."

def test_input(candidate):
    """Test if candidate passes the validator."""
    try:
        env = os.environ.copy()
        env['CANDIDATE'] = candidate
        result = subprocess.run(
            [
                "docker", "run", "--rm", "--platform", "linux/amd64",
                "-e", "CANDIDATE",
                "-v", "/Users/elenaeftimie/Desktop/CTFs/Madalina/ramadan-validator:/work",
                "-w", "/work",
                "ubuntu:24.04",
                "bash", "-c", "printf '%s' \"$CANDIDATE\" | ./validator"
            ],
            capture_output=True,
            text=True,
            timeout=10,
            env=env
        )
        output = result.stdout + result.stderr
        is_valid = TARGET not in output
        return is_valid, output
    except Exception as e:
        return False, str(e)

def test_patterns():
    """Test common patterns first"""
    patterns = [
        "",
        # Hex numbers
        "00", "ff", "deadbeef",
        # Possible CTF keywords
        "flag{}", "ctf", "admin", "password", "12345",
        # Ramadan theme
        "ramadan", "iftar", "2024", "1446",
        # Numeric sequences
        "0", "1", "10", "255", "256", "1000",
        # Hex pairs
        "0x00", "0xff", "0xdeadbeef",
        # Base64-ish
        "AAA=", "YmY=", "AA==",
        # Just zeros/ones
        "0000", "1111", "00000000",
    ]
    
    for p in patterns:
        is_valid, output = test_input(p)
        if is_valid:
            print(f"[+] FOUND: {p!r} -> {output!r}")
            return p
        if len(p) <= 10:
            print(f"  Test {p!r}: {is_valid} ({output[:60]!r}...)")
    
    return None

def brute_pairs():
    """Brute force 2-character combinations (focused search)"""
    print("\n[*] Testing 2-character combinations...")
    chars = string.digits + string.ascii_letters  # Just digits + letters first
    
    count = 0
    for c1 in chars:
        for c2 in chars:
            candidate = c1 + c2
            is_valid, output = test_input(candidate)
            count += 1
            if count % 100 == 0:
                print(f"  Tested {count} combinations...")
            if is_valid:
                print(f"[+] FOUND: {candidate!r}")
                return candidate
    
    if count % 100 != 0:
        print(f"  Tested {count} combinations total")
    
    return None

if __name__ == "__main__":
    print("[*] Testing common patterns...")
    result = test_patterns()
    if result:
        print(f"\n[SUCCESS] Flag: {result!r}")
    else:
        print("\n[*] No patterns matched, trying 2-character combinations...")
        result = brute_pairs()
        if result:
            print(f"\n[SUCCESS] Flag: {result!r}")
        else:
            print("\n[-] No solution found")

