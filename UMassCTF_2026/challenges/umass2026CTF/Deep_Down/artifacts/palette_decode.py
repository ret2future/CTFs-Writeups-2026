from PIL import Image

img = Image.open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif')
img.seek(0)
raw = list(img.getdata())

# GCT pairs: (0,7)=black, (1,3)=same dark blue, (4,6)=similar yellow
# Skip non-carrier: 2 (unique dark), 5 (unique white)
bits = []
for idx in raw:
    if idx in (0, 7):
        bits.append(idx & 1)
    elif idx in (1, 3):
        bits.append(0 if idx == 1 else 1)
    elif idx in (4, 6):
        bits.append(0 if idx == 4 else 1)
    # skip 2, 5

print(f'Carrier bits: {len(bits)} = {len(bits)//8} bytes')
print(f'First 80 bits: {bits[:80]}')

def to_bytes_msb(bits):
    out = bytearray()
    for i in range(0, len(bits)-7, 8):
        b = 0
        for j in range(8):
            b = (b<<1) | bits[i+j]
        out.append(b)
    return bytes(out)

def to_bytes_lsb(bits):
    out = bytearray()
    for i in range(0, len(bits)-7, 8):
        b = 0
        for j in range(8):
            b |= bits[i+j] << j
        out.append(b)
    return bytes(out)

for fn, name in [(to_bytes_msb, 'MSB'), (to_bytes_lsb, 'LSB')]:
    bd = fn(bits)
    printable = ''.join(chr(b) if 32 <= b < 127 else '.' for b in bd[:120])
    flag = ' FOUND!' if b'UMASS' in bd else ''
    print(f'{name}: [{printable}]{flag}')

# Also try: LSB of ALL pixel indices (no skipping)
bits_all = [idx & 1 for idx in raw]
print(f'\nAll-pixel LSB bits: {len(bits_all)}')
for fn, name in [(to_bytes_msb, 'MSB'), (to_bytes_lsb, 'LSB')]:
    bd = fn(bits_all)
    printable = ''.join(chr(b) if 32 <= b < 127 else '.' for b in bd[:120])
    flag = ' FOUND!' if b'UMASS' in bd else ''
    print(f'  {name}: [{printable}]{flag}')
