#!/usr/bin/env python3
import subprocess
import string
import os

PRINTABLE = string.printable[:-5]
TARGET = "Sorry, that's not it."

def test_input(candidate):
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
        print(f"Test '{candidate}': output={output!r}")
        print(f"  -> TARGET in output: {TARGET in output}")
        print(f"  -> is_valid (TARGET not in output): {is_valid}")
        return is_valid
    except Exception as e:
        print(f"ERROR: {e}")
        return False

# Test a few candidates
print("=== Testing empty string ===")
test_input("")

print("\n=== Testing '0' ===")
test_input("0")

print("\n=== Testing 'a' ===")
test_input("a")

print("\n=== Testing '00' ===")
test_input("00")

print("\n=== Testing 64 zeros ===")
test_input("0" * 64)
