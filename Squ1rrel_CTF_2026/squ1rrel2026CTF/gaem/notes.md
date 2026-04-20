# gaem

## Challenge Summary
- Given: a static x86_64 game binary `gaem` reachable at `challs.squ1rrel.dev:5001`.
- Goal: exploit the game and read the remote `flag.txt`.
- Constraints: static binary, no PIE, canary present, NX enabled, line-oriented menu interface.

## Initial Recon / Triage
- Observations:
	- The binary is non-PIE and not stripped, so the game-specific symbols are visible.
	- Pets are heap objects created by `spawn_pet`, and each pet stores a callback pointer at offset `0x20`.
	- The rename path reads `0x28` bytes into a stack buffer and then copies them into the pet name field with an unchecked string copy.
- File identification:
	- `starting_files/gaem` is a static x86_64 ELF.
- Entry points:
	- `main`
	- `spawn_pet`
	- `pet_near`
	- pet talk callbacks `cat_purr` / `dog_purr`

## Hypotheses & Approach
- Hypothesis 1: renaming a nearby pet overflows past the 32-byte pet name field and overwrites the talk callback pointer.
- Hypothesis 2: replacing that callback with `printf` gives a format-string primitive that can rewrite `main`'s saved return state and pivot into a staged ROP chain.

Both were correct.

- `spawn_pet` allocates `0x30` bytes and stores:
	- name at `+0x00`
	- callback at `+0x20`
	- x/y at `+0x28` / `+0x2c`
- The rename action reaches a nearby pet, reads `0x28` bytes into a stack buffer, and copies them into the pet object, so bytes 32-39 replace the callback pointer.
- After overwriting the callback with `printf`, talking to the pet gives a format-string write using attacker-controlled arguments embedded after a `NUL` in the same talk buffer.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
cd /root/squ1rrel2026CTF/gaem
file starting_files/gaem
checksec --file=starting_files/gaem
nm -n starting_files/gaem | egrep 'main|spawn_pet|pet_near|cat_purr|dog_purr'
objdump -d -Mintel starting_files/gaem --disassemble=main --disassemble=spawn_pet --disassemble=read_line
```

Results:
- The hero starts at `(2,2)` and `Whiskers` is reachable via move script `ssdr`.
- The rename path can replace the pet callback with `printf@0x4056b0` by sending 32 filler bytes followed by the low 3 nonzero bytes of the function pointer.
- Talking to the pet with `%7$p` leaks a stable stack pointer inside `main`'s frame.

### Stage 2
Commands:
```bash
cd /root/squ1rrel2026CTF/gaem
python3 artifacts/solve.py remote
```

Results:
- The solver renames `Whiskers` so its callback becomes `printf`.
- `%7$p` leaks the stack buffer used by the talk/rename path.
- Two short `%hn` writes patch `main`'s saved return path:
	- saved RIP -> `pop rsp ; ret`
	- next qword -> the staged talk buffer
- The final talk message begins with `A\0` so `printf` is harmless, then contains a compact ROP chain starting at byte 2:
	- `pop rdi ; ret` -> pointer to `/bin/sh`
	- `pop rsi ; pop r15 ; ret` -> `rsi = 0`
	- `pop rdx ; xor eax, eax ; pop rbx ; pop r12 ; pop r13 ; pop rbp ; ret` -> `rdx = 0`
	- `pop rax ; ret` -> `rax = 59`
	- direct `syscall`
- After sending `q` alone, the process returns through the pivot into `execve("/bin/sh", NULL, NULL)`.
- A second write sends `cat flag.txt` and `exit`, yielding the remote flag:
	- `squ1rrel{nptl_my_beloved_y0u_m4k3_th1s_g4m3_s0_ez}`

## Artifacts Produced
- `artifacts/solve.py` - final exploit script for local/remote use.

## Flag
```text
squ1rrel{nptl_my_beloved_y0u_m4k3_th1s_g4m3_s0_ez}
```
