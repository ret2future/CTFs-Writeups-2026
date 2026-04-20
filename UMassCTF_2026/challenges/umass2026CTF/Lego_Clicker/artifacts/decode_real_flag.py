with open('/root/umass2026CTF/Lego_Clicker/artifacts/apk_extracted/lib/x86_64/liblegocore.so', 'rb') as f:
    data = f.read()

# Tables stored by function 0x20fa0 in bss
# These are stored in little-endian order
# 0x04715d2b883f1a6c -> 6c,1a,3f,88,2b,5d,71,04
# 0x39b24e9511c36720 -> 20,67,c3,11,95,4e,b2,39
# 0x9c6428de417f0a53 -> 53,0a,7f,41,de,28,64,9c
table_58560 = [0x6c, 0x1a, 0x3f, 0x88, 0x2b, 0x5d, 0x71, 0x04]
table_58568 = [0x20, 0x67, 0xc3, 0x11, 0x95, 0x4e, 0xb2, 0x39]
table_58570 = [0x53, 0x0a, 0x7f, 0x41, 0xde, 0x28, 0x64, 0x9c]

# Three encoded-byte tables from function 0x20310
table0 = list(data[0x13cdc:0x13cdc+14])  # for i%3==0
table1 = list(data[0x13dc6:0x13dc6+14])  # for i%3==1
table2 = list(data[0x13dd4:0x13dd4+14])  # for i%3==2

def get_encoded_byte(i):
    tm = i % 3
    tr = i // 3
    if tm == 0:
        return table0[tr]
    elif tm == 1:
        return table1[tr]
    else:
        return table2[tr]

def rotr8(val, n):
    """Rotate right n bits in 8-bit space"""
    return ((val >> n) | (val << (8 - n))) & 0xFF

result = []
for i in range(41):
    b = get_encoded_byte(i)
    
    # State 0xdead: XOR with table_58570[(i+1)%8]
    b = b ^ table_58570[(i + 1) % 8]
    
    # State 0xc0de: SUB table_58570[i%8]
    b = (b - table_58570[i % 8]) & 0xFF
    
    # State 0xcafe: XOR with table_58568[i%8]
    b = b ^ table_58568[i % 8]
    
    # State 0xf00d: rotate right 3 bits
    b = rotr8(b, 3)
    
    # State 0xaced: XOR with table_58560[i%8]
    b = b ^ table_58560[i % 8]
    
    # State 0xd07e: XOR with 0 (no-op, random bytes = 0)
    # b = b ^ 0

    c = chr(b) if 32 <= b < 127 else '?'
    print(f"  i={i:2d}: enc=0x{get_encoded_byte(i):02x} -> final=0x{b:02x} ({c!r})")
    result.append(b)

print()
print("Result bytes:", bytes(result).hex())
try:
    flag = bytes(result).decode('ascii')
    print("FLAG:", flag)
except:
    print("Not pure ASCII:", result)
