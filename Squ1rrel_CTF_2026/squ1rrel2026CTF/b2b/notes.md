# b2b

## Challenge Summary
- Given: a 64-bit ELF binary named `b2b` and a remote service at `challs.squ1rrel.dev:5000`.
- Goal: get code execution and read the flag from the remote host.
- Constraints: NX is enabled, so this is not a shellcode challenge.

## Initial Recon / Triage
- `checksec` showed: Partial RELRO, no canary, NX enabled, no PIE.
- `back2basics()` allocates a 0x40-byte stack buffer and then calls `read(0, buf, 0x100)`, giving a simple stack overflow with RIP control at offset 72.
- The binary includes a `pop rdi; ret` gadget at `0x40117e`, enough for a basic ret2libc leak-and-return chain.

## Hypotheses & Approach
- Hypothesis 1: leak one or more libc addresses via `puts@plt(got_entry)`, return to `back2basics`, then call `system("/bin/sh")` in stage two.
- Hypothesis 2: because the binary is non-PIE and only needs `rdi` control for `puts` and `system`, the exploit can stay very small.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
cd /root/squ1rrel2026CTF/b2b/starting_files
file b2b
checksec --file=b2b
objdump -d -M intel b2b | sed -n '/<gadget>:/,/<.*>:/p;/<back2basics>:/,/<.*>:/p'
```

Results:
- The vulnerable function is:

```c
char buf[0x40];
read(0, buf, 0x100);
```

- Saved RIP is overwritten after `64 + 8 = 72` bytes.
- Key addresses used by the exploit:
	- `pop rdi; ret` at `0x40117e`
	- `ret` at `0x40101a`
	- `puts@plt` at `0x401060`
	- `back2basics` at `0x4011e8`

### Stage 2
Commands:
```bash
cd /root/squ1rrel2026CTF/b2b
python3 artifacts/solve.py exploit
```

Results:
- Stage one leaks `puts` and `read` from the remote GOT using `puts@plt`, then returns to `back2basics`.
- The observed remote offsets matched Ubuntu glibc 2.39:
	- `puts` offset `0x87be0`
	- `read` offset `0x11ba80`
- The exploit computes `libc_base = puts_leak - 0x87be0` and then sends a second payload:

```text
padding(72) + ret + pop rdi + "/bin/sh" + system
```

- After spawning a shell, the script runs `cat flag.txt` and receives:

```text
squ1rrel{pr1d3_4nd_pr3jud1ce_gr34t_g4tsby_4nd_ret2libc}
```

## Artifacts Produced
- `artifacts/solve.py` - full remote exploit with a leak stage and final ret2libc shell.

## Flag
```text
squ1rrel{pr1d3_4nd_pr3jud1ce_gr34t_g4tsby_4nd_ret2libc}
```
