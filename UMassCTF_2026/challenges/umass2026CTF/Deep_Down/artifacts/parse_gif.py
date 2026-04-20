data = open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif', 'rb').read()
print(f"File size: {len(data)} bytes")
print(f"Header: {data[:6]}")

w = int.from_bytes(data[6:8], 'little')
h = int.from_bytes(data[8:10], 'little')
packed = data[10]
has_gct = (packed >> 7) & 1
gct_size = packed & 0x7
gct_count = 2 ** (gct_size + 1)
print(f"Canvas: {w}x{h}, has_GCT={has_gct}, gct_size=2^{gct_size+1}={gct_count} colors")

gct_start = 13
gct_end = gct_start + gct_count * 3
gct = data[gct_start:gct_end]
print("Global Color Table:")
for i in range(gct_count):
    r,g,b = gct[i*3], gct[i*3+1], gct[i*3+2]
    print(f"  [{i}] R={r:3d} G={g:3d} B={b:3d}  LSBs={r&1}{g&1}{b&1}")

pos = gct_end
frame_num = 0
while pos < len(data):
    block_id = data[pos]
    if block_id == 0x3B:
        print(f"\nTrailer at {pos}")
        break
    elif block_id == 0x2C:
        x_off = int.from_bytes(data[pos+1:pos+3], 'little')
        y_off = int.from_bytes(data[pos+3:pos+5], 'little')
        w_f   = int.from_bytes(data[pos+5:pos+7], 'little')
        h_f   = int.from_bytes(data[pos+7:pos+9], 'little')
        packed_f = data[pos+9]
        has_lct = (packed_f >> 7) & 1
        lct_size = packed_f & 0x7
        lct_count = 2 ** (lct_size + 1)
        pos += 10
        if has_lct:
            lct = data[pos:pos + lct_count*3]
            print(f"\nFrame {frame_num} LCT ({lct_count} colors):")
            for i in range(lct_count):
                r,g,b = lct[i*3], lct[i*3+1], lct[i*3+2]
                print(f"  [{i}] R={r:3d} G={g:3d} B={b:3d}  LSBs={r&1}{g&1}{b&1}")
            pos += lct_count * 3
        else:
            print(f"\nFrame {frame_num}: uses Global CT")
        lzw_min = data[pos]; pos += 1
        while True:
            sub_len = data[pos]; pos += 1
            if sub_len == 0: break
            pos += sub_len
        frame_num += 1
    elif block_id == 0x21:
        ext_label = data[pos+1]
        pos += 2
        while True:
            sub_len = data[pos]; pos += 1
            if sub_len == 0: break
            pos += sub_len
    else:
        print(f"Unknown block 0x{block_id:02x} at {pos}")
        pos += 1
