#!/usr/bin/env python3
"""
Brute-force solver: test every printable ASCII character at each position.
Builds the flag one character at a time.
"""

import subprocess
import string

VALIDATOR = "./validator"
PRINTABLE = string.printable[:-5]  # Exclude whitespace except space
TARGET = "Sorry, that's not it."

def test_input(candidate):
    """Test if candidate passes the validator via Docker."""
    try:
        import os
        env = os.environ.copy()
        env['CANDIDATE'] = candidate
        # Pass candidate via environment variable to avoid shell escaping issues
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
        # Valid if: output doesn't contain "Sorry, that's not it."
        # (input accepted either produces different message or exits cleanly)
        is_valid = TARGET not in output
        
        # Debug first few
        if len(candidate) <= 2:
            print(f"  [{candidate!r}]: output={output!r} -> {is_valid}")
        return is_valid
    except Exception as e:
        print(f"[ERROR] Testing '{candidate}': {e}")
        return False

def brutesolve(max_length=64):
    """Build flag character by character."""
    flag = ""
    
    for pos in range(max_length):
        found = False
        for char in PRINTABLE:
            candidate = flag + char
            if test_input(candidate):
                print(f"[+] Position {pos}: found '{char}' → '{candidate}'")
                flag = candidate
                found = True
                break
        
        if not found:
            print(f"[!] Position {pos}: no valid char found. Flag so far: '{flag}'")
            return flag
    
    print(f"[*] Reached max length {max_length}: '{flag}'")
    return flag

if __name__ == "__main__":
    print("[*] Starting brute-force solve...")
    flag = brutesolve(max_length=16)  # Start with shorter length for testing
    print(f"\n[RESULT] Flag: '{flag}'")
    
    # Verify
    if test_input(flag):
        print("[✓] Flag verified as pass!")
    else:
        print("[✗] Flag rejected by validator")
