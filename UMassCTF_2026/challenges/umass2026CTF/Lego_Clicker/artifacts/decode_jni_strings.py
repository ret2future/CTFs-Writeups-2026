with open('/root/umass2026CTF/Lego_Clicker/artifacts/apk_extracted/lib/x86_64/liblegocore.so', 'rb') as f:
    data = f.read()

def decrypt(offset, length, key):
    enc = data[offset:offset+length]
    out = []
    k = key & 0xFF
    for b in enc:
        c = b ^ k
        out.append(c)
        k = (k + c) & 0xFF
    return bytes(out)

# From JNI_OnLoad disassembly - all use key=0x47
regions = [
    (0x13d30, 0x10, 0x47, "class_name"),
    (0x13d40, 0x28, 0x47, "method_1_name"),
    (0x13d68, 0x0e, 0x47, "method_1_sig"),
    (0x13d76, 0x0e, 0x47, "method_2_name"),
    (0x13d90, 0x12, 0x47, "method_2_sig"),
    (0x13db0, 0x16, 0x47, "method_3_sig_or_name"),
]

for (offset, length, key, name) in regions:
    result = decrypt(offset, length, key)
    print(f"{name} @0x{offset:x} len={length}: {result.decode('ascii', errors='replace')!r}")
