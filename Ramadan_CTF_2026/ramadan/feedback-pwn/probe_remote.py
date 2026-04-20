import re
import socket
import subprocess
import time

HOST = 'ctf.vulnbydefault.com'
PORT = 30218

with socket.create_connection((HOST, PORT), timeout=10) as s:
    banner = s.recv(4096).decode('latin-1', errors='ignore')
    print(banner, end='', flush=True)
    m = re.search(r'hashcash -mb32\s+([A-Za-z0-9+/=._:-]+)', banner)
    if not m:
        raise SystemExit('PoW challenge token not found')
    token = m.group(1)
    print('[+] Solving PoW...', flush=True)
    proc = subprocess.Popen(
        ['hashcash', '-mb32', token],
        stdout=subprocess.PIPE,
        stderr=subprocess.STDOUT,
        text=True,
    )
    while proc.poll() is None:
        print('[.] working...', flush=True)
        time.sleep(2)
    out = proc.stdout.read() if proc.stdout else ''
    if proc.returncode != 0:
        raise SystemExit(f'hashcash failed: rc={proc.returncode} out={out!r}')
    sol = out.strip().splitlines()[-1].strip()
    print('[+] PoW solved', flush=True)
    s.sendall((sol + '\n').encode())

    data = s.recv(8192).decode('latin-1', errors='ignore')
    print(data, end='', flush=True)

    s.sendall(b'id\nls -la /\nls -la /dev | grep feedback\nwhoami\n')
    s.settimeout(2)
    chunks = []
    try:
        while True:
            chunks.append(s.recv(8192))
    except Exception:
        pass
    print(b''.join(chunks).decode('latin-1', errors='ignore'), flush=True)
