from PIL import Image
import sys

img = Image.open('/root/umass2026CTF/Ninja_Nerds/artifacts/challenge.png').convert('RGB')
w, h = img.size
pixels = list(img.getdata())

def to_bytes_msb(bits):
    out = bytearray()
    for i in range(0, len(bits)-7, 8):
        b = 0
        for j in range(8): b = (b<<1) | bits[i+j]
        out.append(b)
    return bytes(out)

def to_bytes_lsb(bits):
    out = bytearray()
    for i in range(0, len(bits)-7, 8):
        b = 0
        for j in range(8): b |= bits[i+j]<<j
        out.append(b)
    return bytes(out)

px_xy = pixels
px_yx = [pixels[y*w+x] for x in range(w) for y in range(h)]

found = False
for name, px in [('xy', px_xy), ('yx', px_yx)]:
    for ci, cn in [(0,'R'),(1,'G'),(2,'B')]:
        for bit in range(4):
            bits = [(p[ci]>>bit)&1 for p in px]
            for pack, pn in [(to_bytes_msb,'MSB'),(to_bytes_lsb,'LSB')]:
                data = pack(bits)
                if b'UMASS' in data:
                    idx = data.index(b'UMASS')
                    print(f'FOUND: {name} {cn} bit{bit} {pn} @ {idx}: {repr(data[idx:idx+80])}')
                    found = True

if not found:
    print('No UMASS in single-channel single-bit extractions')

# Also try combined channel extractions (interleaved RGB)
for name, px in [('xy', px_xy), ('yx', px_yx)]:
    for order, oname in [((0,1,2),'RGB'), ((2,1,0),'BGR'), ((0,2,1),'RBG')]:
        for bit in range(4):
            bits = []
            for p in px:
                for ci in order:
                    bits.append((p[ci]>>bit)&1)
            for pack, pn in [(to_bytes_msb,'MSB'),(to_bytes_lsb,'LSB')]:
                data = pack(bits)
                if b'UMASS' in data:
                    idx = data.index(b'UMASS')
                    print(f'FOUND: {name} {oname}-interleaved bit{bit} {pn} @ {idx}: {repr(data[idx:idx+80])}')
                    found = True

if not found:
    print('No UMASS in interleaved channel extractions either')
