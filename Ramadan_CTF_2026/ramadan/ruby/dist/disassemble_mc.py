OPCODES = ['nop', 'hlt', 'add', 'sub', 'nor', 'and', 'xor', 'rsh', 'ldi', 'adi', 'jmp', 'brh', 'cal', 'ret', 'lod', 'str']
CONDS = ['eq', 'ne', 'ge', 'lt']
PORTS = ['pixel_x', 'pixel_y', 'draw_pixel', 'clear_pixel', 'load_pixel', 'buffer_screen', 'clear_screen_buffer',
         'write_char', 'buffer_chars', 'clear_chars_buffer', 'show_number', 'clear_number', 'signed_mode', 'unsigned_mode', 'rng', 'controller_input']
CHARS = [' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.', '!', '?']

def s8(v):
    return v - 256 if v & 0x80 else v

def s4(v):
    return v - 16 if v & 0x8 else v

def fmt_val(v):
    if 240 <= v <= 255:
        return PORTS[v - 240]
    if 0 <= v < len(CHARS):
        return repr(CHARS[v])
    return str(v)

with open('/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/decoded.mc') as f:
    lines = [line.strip() for line in f if line.strip()]

out = []
for pc, line in enumerate(lines):
    val = int(line, 2)
    op = (val >> 12) & 0xF
    a = (val >> 8) & 0xF
    b = (val >> 4) & 0xF
    c = val & 0xF
    mnem = OPCODES[op]
    if mnem in ('nop', 'hlt', 'ret'):
        asm = mnem
    elif mnem in ('add', 'sub', 'nor', 'and', 'xor'):
        asm = f'{mnem} r{a} r{b} r{c}'
    elif mnem == 'rsh':
        asm = f'{mnem} r{a} r{c}'
    elif mnem in ('ldi', 'adi'):
        imm = val & 0xFF
        asm = f'{mnem} r{a} {fmt_val(imm)} ; raw={s8(imm)}'
    elif mnem in ('jmp', 'cal'):
        asm = f'{mnem} {(val & 0x3FF)}'
    elif mnem == 'brh':
        cond = (val >> 10) & 0x3
        asm = f'brh {CONDS[cond]} {(val & 0x3FF)}'
    elif mnem in ('lod', 'str'):
        off = s4(c)
        asm = f'{mnem} r{a} r{b} {off}'
    else:
        asm = mnem
    out.append(f'{pc:04d}: {line}  {asm}')

with open('/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/decoded.asm.txt', 'w') as f:
    f.write('\n'.join(out) + '\n')

print('\n'.join(out[:80]))
