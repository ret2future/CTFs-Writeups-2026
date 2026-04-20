# squ1rrel-o-tron

## Challenge Summary
- Given: a PDF in `starting_files/linux.pdf` and a Python server in `starting_files/server.py`.
- Goal: implement `F(nonce)` well enough to answer 5 challenge rounds from `nc challs.squ1rrel.dev 5002`.
- Constraint: the server only gives 5 seconds per round, so the approach had to be direct and reproducible.

## Initial Recon / Triage
- The provided server made the interface clear: each round sends a 32-byte nonce and expects a 16-byte response as 32 hex characters.
- The PDF was not just a document. It embedded a JavaScript-based VM and a RISC-V Linux guest.
- The main useful extracted files ended up being under `artifacts/`, especially:
  - `main.js`
  - `run_vm.js`
  - `extract_functions.py`
  - `patch_elf_flags.py`
  - the extracted guest root under `artifacts/embedded/`

## Hypotheses & Approach
- First idea: use the PDF/VM itself as an oracle and feed it challenge inputs directly.
- That turned out to be unreliable because booting the embedded guest consistently enough to use as a solver was messy.
- The successful path was to recover the real transform from the embedded challenge binary and reimplement it locally.

## Execution Steps (Reproducible)
### Stage 1
Commands:
```bash
cd /root/squ1rrel2026CTF/squ1rrel-o-tron
node artifacts/run_vm.js
```

Results:
- Confirmed the PDF JavaScript was actually driving a VM.
- Extracted the embedded filesystem and identified the RISC-V challenge binary inside `artifacts/embedded/root/files/0000000000000004`.

### Stage 2
Commands:
```bash
cd /root/squ1rrel2026CTF/squ1rrel-o-tron
python3 artifacts/patch_elf_flags.py
qemu-riscv32 /tmp/chall-soft
```

Results:
- Patched the guest ELF flags so the extracted binaries could at least run far enough under user-mode QEMU to hit the interesting logic.
- Confirmed the challenge binary dies on custom RISC-V instructions rather than on normal environment issues.

### Stage 3
Commands:
```bash
cd /root/squ1rrel2026CTF/squ1rrel-o-tron
python3 artifacts/extract_functions.py artifacts/main.js ci
llvm-objdump-18 -d --no-show-raw-insn /tmp/chall-soft
```

Results:
- Located the real asm.js CPU interpreter in `main.js`.
- Recovered the semantics of the custom instructions from the interpreter instead of trying to emulate them in QEMU.
- Determined that the transform keeps hidden state between word updates, uses a 256-byte substitution table from the VM memory image, runs 4096 rounds, and returns the first 16 bytes of the final 32-byte state.

### Stage 4
Commands:
```bash
cd /root/squ1rrel2026CTF/squ1rrel-o-tron
python3 solve.py
```

Results:
- Reimplemented the recovered transform locally and used it against the remote service.
- One important client bug during solving was that the remote service does not send per-round success lines; it immediately prints the next round. Fixing that protocol handling was necessary for a clean solve.
- Final remote solve returned the flag below.

## Artifacts Produced
- `artifacts/main.js`: extracted JavaScript VM payload used for reverse engineering.
- `artifacts/run_vm.js`: helper harness used to exercise the PDF VM and inspect embedded files.
- `artifacts/extract_functions.py`: helper to isolate minified functions from `main.js`.
- `artifacts/patch_elf_flags.py`: helper used while making the extracted guest binaries runnable under QEMU.
- `artifacts/embedded/`: extracted guest filesystem and binaries.

## Flag
- `squ1rrel{why_run_l1nux_0n_4_pr1nt3r_wh3n_y0u_c4n_run_l1nux_0n_4_pdf}`