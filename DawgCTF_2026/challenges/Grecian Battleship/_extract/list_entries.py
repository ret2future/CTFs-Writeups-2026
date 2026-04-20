import struct
from pathlib import Path

b = Path('pydata').read_bytes()
magic = b'MEI\x0c\x0b\x0a\x0b\x0e'
idx = b.rfind(magic)
_, pkglen, toc_off, toc_len, pyver, pylib = struct.unpack('!8sIIii64s', b[idx:idx + 88])

entries = []
p = toc_off
end = toc_off + toc_len
while p < end:
    entry_size = struct.unpack('!i', b[p:p + 4])[0]
    entry_pos, csz, usz, flag, typ = struct.unpack('!IIIbc', b[p + 4:p + 18])
    name = b[p + 18:p + entry_size].split(b'\0', 1)[0].decode('utf-8', 'replace')
    entries.append((typ.decode('utf-8', 'replace'), name, csz, usz, int(flag)))
    p += entry_size

print(f'entries={len(entries)} pyver={pyver} pylib={pylib.split(b"\\0")[0].decode()}')
for typ, name, csz, usz, flag in entries:
    if (
        name.startswith('tcl/')
        or name.startswith('tk/')
        or name.startswith('lib-dynload/')
        or name.startswith('lib')
        or name.endswith('.enc')
        or name.endswith('.msg')
    ):
        continue
    print(f'{typ} flag={flag} c={csz} u={usz} {name}')
