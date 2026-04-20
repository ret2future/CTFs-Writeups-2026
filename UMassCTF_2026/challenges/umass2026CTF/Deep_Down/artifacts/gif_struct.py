data = open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif', 'rb').read()

gct_end = 13 + 8*3
pos = gct_end
frame_num = 0

while pos < len(data):
    block_id = data[pos]
    if block_id == 0x3B:
        print(f"Trailer at {pos}, file size={len(data)}, remaining={len(data)-pos-1}")
        if pos + 1 < len(data):
            print(f"After trailer: {data[pos+1:]!r}")
        break
    elif block_id == 0x21:
        ext_label = data[pos+1]
        ext_name = {0xFE:'Comment',0xFF:'Application',0x01:'PlainText',0xF9:'GraphicControl'}.get(ext_label, f'0x{ext_label:02x}')
        if ext_label == 0xF9:
            block_size = data[pos+2]
            packed = data[pos+3]
            delay = int.from_bytes(data[pos+4:pos+6], 'little')
            transparent = data[pos+6]
            disposal = (packed>>2)&7
            print(f"GCE @ {pos}: delay={delay} ({delay*10}ms) disposal={disposal} transp_idx={transparent if packed&1 else 'none'}")
        elif ext_label == 0xFE:
            comment_bytes = b''
            p = pos + 2
            while data[p] != 0:
                sl = data[p]; comment_bytes += data[p+1:p+1+sl]; p += 1+sl
            print(f"Comment @ {pos}: {comment_bytes!r}")
        elif ext_label == 0xFF:
            bl = data[pos+2]
            print(f"AppExt @ {pos}: {data[pos+3:pos+3+bl]!r}")
        else:
            print(f"Ext @ {pos}: {ext_name}")
        pos += 2
        while True:
            sl = data[pos]; pos += 1
            if sl == 0: break
            pos += sl
    elif block_id == 0x2C:
        pos += 10
        if (data[pos-1] >> 7) & 1:
            lct_count = 2 ** ((data[pos-1]&7)+1)
            pos += lct_count*3
        pos += 1
        frame_num += 1
        img_start = pos
        while True:
            sl = data[pos]; pos += 1
            if sl == 0: break
            pos += sl
        print(f"Frame {frame_num} image data: {img_start}-{pos}")
    else:
        print(f"Unknown 0x{block_id:02x} @ {pos}")
        break
