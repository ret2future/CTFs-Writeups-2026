# web-six-seven (MCTF) Write-up

## Summary
The challenge provided a single file named `67`, which is a stripped Linux ELF binary.

The binary expected one command-line argument (`./vm <flag>`), rejected most lengths, and validated input using a custom VM-like routine. The valid input turned out to be a full Rick Astley lyrics block, ending with the real CTF flag.

Final flag:

`MCTF{R1ck_R0ll3d_1s_N0t_a_Pr0blem_1s_iT?}`

---

## 1. Initial Recon

### File identification
- `file 67` -> ELF 64-bit LSB executable, stripped, dynamically linked.

### Basic runtime behavior
Running in Linux (amd64) showed:
- No args: `Usage: ./vm <flag>`
- Random arg: `Taille invalide.` or `Échec.`
- Success message present in strings: `Flag Valid` / `Flag Validé.`

Because host OS was macOS, the binary was executed inside Docker with:
- `--platform linux/amd64`

---

## 2. Length Constraint Discovery

By fuzzing argument lengths, only lengths divisible by 3 were accepted for deeper checking:
- `3, 6, 9, ...` -> `Échec.`
- other lengths -> `Taille invalide.`

This suggested processing input in 3-byte chunks (24-bit blocks).

---

## 3. Reverse Engineering the Checker

Disassembly around main checker (`.text` around `0x1200`) showed:

- A state variable initialized to `ebx = 0xDEADBEEF`.
- A bytecode stream starting at `.data + 0x20` (`VA 0x4020`, file offset `0x3020`).
- Opcodes decoded as `decoded = byte ^ 0x7A`.
- Dispatch table at `.rodata + 0x4` (`VA 0x2004`) with handlers for:
  - `j` (op 0): load next 3-byte input block
  - `k` (op 1): `x ^= x << 13`
  - `h` (op 2): `x ^= x >> 17`
  - `i` (op 3): `x ^= x << 5`
  - `n` (op 4): `x *= 0x2545F491`
  - `o` (op 5): `ebx = ecx ^ ebp` (compare/finalize relation)
  - `Z` (op 16): compare against embedded 32-bit immediate
- Stream terminator byte was `0x85`.

### Program structure extracted from bytecode
- `287` rounds total
- each round loads one 3-byte block
- each round applies exactly `1000` loop steps of `k,h,i,n`
- each round ends in `o` then `Z <imm32>` compare

Therefore required input length:
- `287 * 3 = 861` bytes

---

## 4. Why SMT Was Abandoned

A direct SMT approach with Z3 for one round returned `unknown` (timeout) even with printable-byte constraints.

Given the function is deterministic over a 24-bit block space, brute force per round was more practical in native code.

---

## 5. Native Solver Strategy

A C++ solver was built (`solve_vm67_native.cpp`) with:
- exact VM round emulation
- extraction of all `287` target constants from encoded bytecode
- multithreaded brute force over `2^24` candidates for each round
- chaining state (`prev = target_of_previous_round`)

Performance was good enough to complete all rounds and recover all 861 bytes.

Recovered output was saved to:
- `recovered_flag.txt`
- `recovered_flag.bin`

---

## 6. Validation

Passing recovered bytes back as argv in Linux (using Python subprocess to preserve exact bytes/newlines):

- length: `861`
- return code: `0`
- output: `Flag Validé.`

So recovery was correct.

---

## 7. Recovered Payload

The recovered input is a Rickroll lyrics block ending with:

`Good Job, the flag is : MCTF{R1ck_R0ll3d_1s_N0t_a_Pr0blem_1s_iT?}`

---

## 8. Files Produced During Solve

- `bench_round.c` (single-round brute-force benchmark)
- `solve_vm67.py` (initial symbolic/SMT exploration)
- `solve_vm67_native.cpp` (final full solver)
- `recovered_flag.txt` (full recovered valid argument)
- `recovered_flag.bin` (binary form)

---

## Final Flag

`MCTF{R1ck_R0ll3d_1s_N0t_a_Pr0blem_1s_iT?}`
