# Ramadan Validator — CTF Write-up

## Challenge Summary

The target is a Linux x86-64 PIE binary called `validator`.
It prompts for a flag, validates it with obfuscated logic, and prints either:

- Failure: `Sorry, that's not it.`
- Success: `Congratulations! You found the correct flag.`

Goal: recover the exact accepted input.

---

## Environment and Constraints

- Host OS: macOS
- Target binary: Linux ELF PIE (must be executed in Linux userspace)
- Execution approach: Docker (`ubuntu:24.04`, `linux/amd64`)

Notable blockers encountered:

1. `angr` symbolic execution repeatedly failed with VEX lifting/memory mapping errors on this sample.
2. `gdb` / `strace` workflows were limited by ptrace constraints in the environment.
3. `dlopen` of the binary as a shared object failed because it is a PIE executable, not a loadable `.so`.

---

## Recon and Static Analysis

### 1) File properties

`validator` is a non-stripped, dynamically linked, PIE ELF.

### 2) Interesting symbols

- `hIKCTDqsfNLU` at offset `0x1ac0` (top-level checker)
- `zWqDapvkXfHB` at offset `0x3440` (per-character checker/oracle)

### 3) Control-flow style

Disassembly shows heavy control-flow flattening and jump-dispatch indirection, making direct manual decompilation time-expensive.

---

## Failed Attempts (and Why)

### A) Naive brute-force script

A prefix brute script incorrectly treated `absence of failure string` as success. That produced false positives in edge cases.

### B) Symbolic solving with angr

`angr` runs failed with `SimEngineError: No bytes in memory for block starting at 0xb01000`.

### C) Direct function calling via `dlopen`

Attempting to load `./validator` as a library failed with:

`cannot dynamically load position-independent executable`

---

## Working Exploit Strategy

### Key idea

Instead of loading the PIE externally, call internal checker functions **from inside the validator process** using `LD_PRELOAD`.

### Why this works

- The process already has its PIE base loaded correctly.
- We can find that runtime base using `dl_iterate_phdr`.
- Then compute real function address as:

`runtime_addr = pie_base + function_offset`

For this challenge:

`zWqDapvkXfHB = base + 0x3440`

### Implementation details

A preload hook (`probe_hook.c`) was used to:

1. Hook `fgets` (fires when input is read)
2. Resolve PIE base at runtime
3. Build function pointer to `zWqDapvkXfHB`
4. For each index `0..79`, test printable chars (`32..126`)
5. Log all chars where `zWqDapvkXfHB(ch, idx)` returns true

This effectively turns the checker into an oracle and reconstructs the flag one position at a time.

---

## Oracle Output and Reconstruction

The probe produced one valid character per index for the flag span, reconstructing:

`VBD{I_kn0w_y0u_w0uld_us3_Opus_hehe_eafa09ad1898e0bcf9c0225076632225}`

---

## Verification

Validation command:

```bash
CAND='VBD{I_kn0w_y0u_w0uld_us3_Opus_hehe_eafa09ad1898e0bcf9c0225076632225}'
printf "%s\n" "$CAND" | docker run --rm --platform linux/amd64 -i -v "$PWD":/work -w /work ubuntu:24.04 ./validator
```

Observed output:

`Enter the secret flag: Congratulations! You found the correct flag.`

---

## Final Flag

`VBD{I_kn0w_y0u_w0uld_us3_Opus_hehe_eafa09ad1898e0bcf9c0225076632225}`

---

## Takeaways

1. In heavily obfuscated validators, extracting/using an internal oracle is often faster than full deobfuscation.
2. For PIE binaries, in-process hooks are a robust way to bypass relocation complexity.
3. Validation logic in brute tools must be strict; weak success heuristics can produce convincing false positives.
