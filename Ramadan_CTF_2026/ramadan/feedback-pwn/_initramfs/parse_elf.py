from elftools.elf.elffile import ELFFile
import struct

path = 'root/feedback.ko'
with open(path, 'rb') as f:
    elf = ELFFile(f)

    print('Sections with file offsets:')
    for s in elf.iter_sections():
        if s.name in ('.text', '.data', '.rodata', '.bss', '.rela.data', '.rela.text', '.symtab'):
            print(s.name, 'off', hex(s['sh_offset']), 'size', hex(s['sh_size']), 'addr', hex(s['sh_addr']))

    data = elf.get_section_by_name('.data').data()
    print('\n.data qwords (non-zero):')
    for i in range(0, len(data), 8):
        q = struct.unpack_from('<Q', data, i)[0]
        if q:
            print(hex(i), hex(q))

    ro = elf.get_section_by_name('.rodata').data()
    print('\n.rodata strings around offsets:')
    for off in [0, 0x77]:
        end = ro.find(b'\x00', off)
        print(hex(off), ro[off:end])

    symtab = elf.get_section_by_name('.symtab')
    def symname(idx):
        return symtab.get_symbol(idx).name

    rela_data = elf.get_section_by_name('.rela.data')
    print('\n.rela.data entries:')
    for r in rela_data.iter_relocations():
        add = r['r_addend'] & ((1 << 64) - 1)
        print(hex(r['r_offset']), symname(r['r_info_sym']), hex(add))
