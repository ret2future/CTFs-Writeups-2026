# echo

## Challenge Summary
- Given: an ARM 32-bit PIE binary and a Dockerfile that runs it under `qemu-arm` behind `socat`.
- Goal: exploit the remote service at `challs.squ1rrel.dev:5003` and recover the flag.
- Constraints: first input is limited by `fgets(..., 16, ...)`, second input is a raw `read(..., 16)`, and the target is ARM EABI.

## Initial Recon / Triage
- Observations:
	- `checksec` showed Full RELRO, no canary, PIE enabled, and NX disabled.
	- The Dockerfile runs `qemu-arm -L /usr/arm-linux-gnueabi ./echo`, so local reproduction requires the ARM cross libc.
- File identification:
	- `starting_files/echo` is a stripped 32-bit ARM ELF.
	- `starting_files/Dockerfile` documents the service runtime.
- Entry points:
	- `puts("Echo")`
	- `fgets(buf, 16, stdin)`
	- `printf(buf)`
	- `read(0, buf, 16)`

## Hypotheses & Approach
- Hypothesis 1: the first bug is a format string because user input is passed directly to `printf`.
- Hypothesis 2: the second `read` can overwrite the saved frame data because the local stack allocation is only 8 bytes.

Both were correct. Disassembly of the main worker routine showed an 8-byte stack frame followed by:

```asm
6c0: mov r1, #16
6c4: mov r0, r4
6c8: bl  fgets@plt
6cc: mov r0, r4
6d0: bl  printf@plt
6d4: mov r2, #16
6d8: mov r1, r4
6dc: mov r0, #0
6e0: bl  read@plt
6e8: add sp, sp, #8
6ec: pop {r4, pc}
```

That gives a short but enough overwrite: 8 bytes for the buffer, 4 bytes for saved `r4`, and 4 bytes for saved `pc`.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
cd /root/squ1rrel2026CTF/echo
file starting_files/echo
checksec --file=starting_files/echo
arm-linux-gnueabi-objdump -d starting_files/echo | sed -n '1,260p'
printf 'hello\n' | qemu-arm -L /usr/arm-linux-gnueabi ./starting_files/echo
```

Results:
- The service prints `Echo`, then reads a first line with `fgets` and echoes it via `printf`.
- Positional format-string probing showed `%13$p` leaks a stable stack-relative pointer.
- Local testing showed the leaked value is always `0x18c` bytes above the vulnerable stack buffer.

### Stage 2
Commands:
```bash
cd /root/squ1rrel2026CTF/echo
python3 artifacts/solve.py remote
```

Results:
- The solver uses `%13$p` to recover a stack pointer.
- It subtracts `0x18c` to recover the vulnerable buffer address.
- The second 16-byte write places a 12-byte Thumb stager plus a saved return address pointing back into that buffer with the Thumb bit set.
- The stager executes `read(0, sp, 0xff)` and `bx sp`, which pulls a full ARM payload onto the caller stack.
- The ARM payload opens `flag.txt`, reads it, and writes it back to stdout.
- The remote service returned `squ1rrel{i_l0v3_n1s@l@_h3_1s_s0_c00l}` followed by trailing binary garbage from the fixed-size write.

## Artifacts Produced
- `artifacts/solve.py` - local/remote exploit helper that reproduces the stack stager and flag readout.

## Flag
```text
squ1rrel{i_l0v3_n1s@l@_h3_1s_s0_c00l}
```
