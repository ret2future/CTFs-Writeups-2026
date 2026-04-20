import struct
import zlib
from pathlib import Path

b = Path('pydata').read_bytes()
magic = b'MEI\x0c\x0b\x0a\x0b\x0e'
idx = b.rfind(magic)
_, pkglen, toc_off, toc_len, pyver, _ = struct.unpack('!8sIIii64s', b[idx:idx+88])

p = toc_off
end = toc_off + toc_len
while p < end:
    entry_size = struct.unpack('!i', b[p:p+4])[0]
    entry_pos, csz, usz, flag, typ = struct.unpack('!IIIbc', b[p+4:p+18])
    name = b[p+18:p+entry_size].split(b'\0', 1)[0].decode('utf-8', 'replace')
    if name == 'battleship':
        data = b[entry_pos:entry_pos+csz]
        if flag:
            data = zlib.decompress(data)
        Path('battleship_extracted.bin').write_bytes(data)
        print('extracted', len(data), 'bytes, type', typ.decode())
        break
    p += entry_size
