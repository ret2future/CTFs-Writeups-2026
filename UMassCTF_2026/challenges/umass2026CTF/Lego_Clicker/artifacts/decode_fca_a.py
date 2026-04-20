with open('/root/umass2026CTF/Lego_Clicker/artifacts/apk_extracted/lib/x86_64/liblegocore.so', 'rb') as f:
    data = f.read()

BASE1 = 0x152b0   # table1 (r10)
BASE2 = 0x152f0   # table2 (r11)
BASE3 = 0x15310   # table3 base (rdi starts here)

table1 = list(data[BASE1:BASE1+30])
table2 = list(data[BASE2:BASE2+30])
print("table1:", table1)
print("table2:", table2)

t3_window_start = 0x1530e
t3_window = data[t3_window_start : t3_window_start + 10]
print("table3 window @0x1530e:", list(t3_window))

result = []
for k in range(30):
    rcx = 2 + 3 * k
    quotient = rcx // 5
    rax_neg = quotient * 5
    eff_addr = BASE3 + 3*k - rax_neg
    
    t1 = data[BASE1 + k]
    t2 = data[BASE2 + k]
    t3 = data[eff_addr]
    
    c = t1 ^ t2 ^ t3
    result.append(c)
    print(f"k={k}: rcx={rcx}, eff_addr=0x{eff_addr:x}, t1=0x{t1:02x}, t2=0x{t2:02x}, t3=0x{t3:02x}, c={c} ({chr(c) if 32<=c<127 else '?'})")

print("\nResult:")
print("hex:", bytes(result).hex())
try:
    print("ascii:", bytes(result).decode('ascii'))
except Exception as e:
    print("not pure ascii:", e)
    print("bytes:", result)
