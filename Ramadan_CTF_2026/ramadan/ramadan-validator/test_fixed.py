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
        result = subprocess.run([
            "docker", "run", "--rm", "--platform", "linux/amd64",
            "-e", "CANDIDATE",
            "-v", "/Users/elenaeftimie/Desktop/CTFs/Madalina/ramadan-validator:/work",
            "-w", "/work",
            "ubuntu:24.04",
            "bash", "-c", "printf '%s' \"$CANDIDATE\" | ./validator"
        ], capture_output=True, text=True, timeout=10, env=env)
        
        output = result.stdout + result.stderr
        is_valid = (TARGET not in output) and (output != "Enter the secret flag: ")
        print(f"Test {candidate!r}: {output!r} -> {is_valid}")
        return is_valid
    except Exception as e:
        print(f"ERROR: {e}")
        return False

# Test a few
print("Testing with FIXED logic:")
test_input("")
test_input("0")
test_input("a")
test_input(" ")
