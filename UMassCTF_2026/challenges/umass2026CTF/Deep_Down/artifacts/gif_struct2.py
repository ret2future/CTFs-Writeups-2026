#!/usr/bin/env python3
"""Parse GIF structure to find GCE disposal methods and other hidden data."""

data = open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif', 'rb').read()
print(f"GIF size: {len(data)} bytes")
print(f"Header: {data[:6]}")
print(f"Canvas: {int.from_bytes(data[6:8],'little')}x{int.from_bytes(data[8:10],'little')}")
packed = data[10]
gct_size = 2 ** ((packed & 7) + 1)
print(f"GCT size: {gct_size}, packed field: {packed:08b}")

pos = 13 + gct_size * 3
print(f"GCT ends at byte {pos}")
print()

# Netscape App Extension first?
frame_count = 0

while pos < len(data):
    bid = data[pos]
    if bid == 0x3B:
        print(f"Trailer at byte {pos}")
        break
    elif bid == 0x21:
        ext_label = data[pos+1]
        pos += 2
        if ext_label == 0xF9:  # Graphic Control Extension
            bsize = data[pos]; pos += 1
            packed_gce = data[pos]
            delay = data[pos+1] | (data[pos+2] << 8)
            trans_idx = data[pos+3]
            trans_flag = packed_gce & 1
            disposal = (packed_gce >> 3) & 7
            user_input = (packed_gce >> 1) & 1
            print(f"Frame {frame_count}: GCE packed={packed_gce:08b} disposal={disposal}, delay={delay*10}ms, trans_flag={trans_flag}, trans_idx={trans_idx}")
            pos += bsize
            # skip remaining sub-blocks
            while True:
                sl = data[pos]; pos += 1
                if sl == 0:
                    break
                pos += sl
        elif ext_label == 0xFF:  # Application Extension (Netscape)
            bsize = data[pos]; pos += 1
            app_id = data[pos:pos+bsize]
            print(f"App Ext at byte {pos-2}: ID={app_id}")
            pos += bsize
            while True:
                sl = data[pos]; pos += 1
                if sl == 0:
                    break
                pos += sl
        elif ext_label == 0xFE:  # Comment Extension
            print(f"Comment Extension at byte {pos-2}:")
            while True:
                sl = data[pos]; pos += 1
                if sl == 0:
                    break
                comment = data[pos:pos+sl]
                print(f"  Comment: {comment}")
                pos += sl
        else:
            print(f"Unknown ext 0x{ext_label:02x} at byte {pos-2}")
            while True:
                sl = data[pos]; pos += 1
                if sl == 0:
                    break
                pos += sl
    elif bid == 0x2C:
        # Image Descriptor
        left = int.from_bytes(data[pos+1:pos+3], 'little')
        top = int.from_bytes(data[pos+3:pos+5], 'little')
        width = int.from_bytes(data[pos+5:pos+7], 'little')
        height = int.from_bytes(data[pos+7:pos+9], 'little')
        packed_img = data[pos+9]
        lct_flag = (packed_img >> 7) & 1
        interlace = (packed_img >> 6) & 1
        lct_size = 2 ** ((packed_img & 7) + 1) if lct_flag else 0
        print(f"Frame {frame_count} Image at byte {pos}: ({left},{top}) {width}x{height} interlace={interlace} LCT={lct_flag}({lct_size})")
        pos += 10
        if lct_flag:
            pos += lct_size * 3
        min_code_size = data[pos]; pos += 1
        total_lzw = 0
        while True:
            sl = data[pos]; pos += 1
            if sl == 0:
                break
            total_lzw += sl
            pos += sl
        print(f"  LZW min_code={min_code_size}, data_size={total_lzw} bytes")
        frame_count += 1
    else:
        print(f"Unknown block 0x{bid:02x} at byte {pos}")
        pos += 1

print()
print(f"Total frames: {frame_count}")
print(f"Bytes after trailer: {len(data) - pos - 1}")
