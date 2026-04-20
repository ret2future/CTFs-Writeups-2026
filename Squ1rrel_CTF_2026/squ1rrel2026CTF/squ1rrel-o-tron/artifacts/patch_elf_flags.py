from pathlib import Path
import sys

if len(sys.argv) != 3:
    raise SystemExit('usage: patch_elf_flags.py IN OUT')

src = Path(sys.argv[1]).read_bytes()
data = bytearray(src)
if data[:4] != b'\x7fELF':
    raise SystemExit('not an ELF')
flags = int.from_bytes(data[0x24:0x28], 'little')
patched = flags & ~0x6
if patched == flags:
    print(f'flags unchanged: 0x{flags:x}')
else:
    data[0x24:0x28] = patched.to_bytes(4, 'little')
    print(f'patched flags 0x{flags:x} -> 0x{patched:x}')
Path(sys.argv[2]).write_bytes(data)
