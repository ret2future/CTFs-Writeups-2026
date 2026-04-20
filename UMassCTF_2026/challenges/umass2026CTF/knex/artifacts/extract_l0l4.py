import struct

with open('/root/umass2026CTF/knex/starting_files/lush.png','rb') as f:
    data = f.read()

pos = 8
l0l4_chunks = []
while pos < len(data):
    if pos + 8 > len(data): break
    length = struct.unpack('>I', data[pos:pos+4])[0]
    ctype = data[pos+4:pos+8]
    if ctype == b'l0l4':
        l0l4_chunks.append(data[pos+8:pos+8+length])
    if ctype == b'IEND': break
    pos += 12 + length

combined = b''.join(l0l4_chunks)
print(f'Total hidden bytes: {len(combined)}')

# Check for JPEG start
if combined[:2] == b'\xff\xd8':
    print('JPEG detected')
    end = combined.rfind(b'\xff\xd9')
    if end != -1:
        jpeg_data = combined[:end+2]
        print(f'JPEG size: {len(jpeg_data)} bytes')
        outpath = '/root/umass2026CTF/knex/artifacts/hidden.jpg'
        with open(outpath, 'wb') as f:
            f.write(jpeg_data)
        print(f'Saved: {outpath}')
        leftover = combined[end+2:]
        non_null = leftover.strip(b'\x00')
        print(f'Leftover: {len(leftover)} bytes, non-null: {len(non_null)}')
        if non_null:
            print(f'Non-null leftover hex: {non_null[:64].hex()}')
            print(f'Non-null leftover ascii: {non_null[:64]}')
    else:
        print('No JPEG end marker')
else:
    print(f'First 8 bytes: {combined[:8].hex()}')
    outpath = '/root/umass2026CTF/knex/artifacts/hidden.bin'
    with open(outpath, 'wb') as f:
        f.write(combined)
    print(f'Saved: {outpath}')
