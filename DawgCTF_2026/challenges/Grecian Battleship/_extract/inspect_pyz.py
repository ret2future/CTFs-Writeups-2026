import marshal
import struct
from pathlib import Path

p = Path('PYZ-00.pyz')
with p.open('rb') as f:
    header = f.read(4)
    pyc_magic = f.read(4)
    toc_pos = struct.unpack('!i', f.read(4))[0]
    f.seek(toc_pos)
    toc = marshal.load(f)

print('header', header)
print('pyc_magic', pyc_magic)
print('toc_pos', toc_pos)
print('module_count', len(toc))

print('toc_type', type(toc).__name__)
if isinstance(toc, dict):
    names = sorted(toc.keys())
elif isinstance(toc, list):
    names = sorted([item[0] for item in toc if isinstance(item, tuple) and item])
else:
    names = []

for name in names:
    print(name)
