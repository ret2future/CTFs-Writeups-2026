#!/usr/bin/python3 -u
import os
import subprocess
import tempfile
import re
import random

print("Input your code (1 line)")
code = input(">>> ")
print(code)

RANDOM_ENV = int(random.random() * 1000)

for i in range(0, RANDOM_ENV):
    os.environ[f"RANDOM_ENV_{i}"] = str(random.random())

banned_char_regex = r"""[\w\s_?&!|$'"<>^:/^/-]"""


if re.search(banned_char_regex, code):
    print(f"You can't use those characters: '{re.search(banned_char_regex, code).group()}'")
    exit(1)

with tempfile.TemporaryDirectory() as td:
    src_path = os.path.join(td, "source.c")
    compiled_path = os.path.join(td, "compiled")
    with open(src_path, "w") as file:
        file.write(code)

    returncode = subprocess.call(
        ["gcc", "-B/usr/bin", "-Wl,--entry=😀",
         "-static", "-nostartfiles", "-w", '-nostdlib',
         "-O0", "-o", compiled_path, src_path],
        stderr=subprocess.DEVNULL,
        stdout=subprocess.DEVNULL
    )

    if returncode != 0:
        print("Oops, there were some compilation errors!")
        exit(1)

    print("Good luck!")
    subprocess.call([compiled_path], env=os.environ)
