from pathlib import Path
import z3


def u32(x: int) -> int:
    return x & 0xFFFFFFFF


def extract_program_and_targets(blob: bytes):
    p = blob[0x3020:]
    pc = 0
    targets = []
    round_ops = []
    cur_ops = []

    while pc < len(p):
        d = p[pc] ^ 0x7A
        if d > 0x20:
            break
        if d <= 0x0F:
            raise RuntimeError(f"invalid opcode marker at {pc:#x}")

        op = d - 0x10
        pc += 1

        if op == 16:
            if pc + 4 > len(p):
                raise RuntimeError("truncated immediate")
            imm = (
                (p[pc + 0] ^ 0x7A)
                | ((p[pc + 1] ^ 0x7A) << 8)
                | ((p[pc + 2] ^ 0x7A) << 16)
                | ((p[pc + 3] ^ 0x7A) << 24)
            )
            pc += 4
            targets.append(imm)
            if cur_ops:
                round_ops.append(cur_ops)
                cur_ops = []
            continue

        if op == 0:
            cur_ops = []
            continue

        if op in (1, 2, 3, 4, 5):
            cur_ops.append(op)
            continue

        raise RuntimeError(f"unsupported op {op}")

    return targets, round_ops


def symbolic_step(x):
    # k: x ^= x << 13
    x = x ^ (x << 13)
    # h: x ^= x >> 17
    x = x ^ z3.LShR(x, 17)
    # i: x ^= x << 5
    x = x ^ (x << 5)
    # n: x *= 0x2545F491
    x = x * z3.BitVecVal(0x2545F491, 32)
    return x


def solve_round(prev_ebx: int, target: int, loops: int = 1000, printable_only: bool = False):
    b0 = z3.BitVec("b0", 8)
    b1 = z3.BitVec("b1", 8)
    b2 = z3.BitVec("b2", 8)

    b24 = z3.Concat(b0, b1, b2)
    seed = z3.BitVecVal(prev_ebx, 32) ^ z3.ZeroExt(8, b24)

    x = seed
    for _ in range(loops):
        x = symbolic_step(x)

    out = x ^ seed

    s = z3.Solver()
    s.set("timeout", 30000)
    s.add(out == z3.BitVecVal(target, 32))

    if printable_only:
        for b in (b0, b1, b2):
            s.add(z3.UGE(b, z3.BitVecVal(0x20, 8)))
            s.add(z3.ULE(b, z3.BitVecVal(0x7E, 8)))

    res = s.check()
    if res != z3.sat:
        return None, res

    m = s.model()
    v = m[b24].as_long()
    return v, res


def main():
    blob = Path("67").read_bytes()
    targets, round_ops = extract_program_and_targets(blob)

    print(f"targets={len(targets)} rounds={len(round_ops)}")
    if not round_ops:
        return

    # Sanity check operation profile.
    lens = [len(r) for r in round_ops]
    print(f"op_lengths(min/max)={min(lens)}/{max(lens)}")
    print(f"first_round_prefix={round_ops[0][:8]}")

    # Confirm expected pattern: khin repeated, then o.
    first = round_ops[0]
    if not first or first[-1] != 5:
        print("unexpected round tail")
    loops = (len(first) - 1) // 4
    print(f"derived_loops={loops}")

    prev = 0xDEADBEEF
    b0, r0 = solve_round(prev, targets[0], loops, printable_only=False)
    print(f"round0_unconstrained={r0}")

    if b0 is not None:
        b = bytes([(b0 >> 16) & 0xFF, (b0 >> 8) & 0xFF, b0 & 0xFF])
        print(f"round0_block={b0:#08x} bytes={b!r}")

    b1, r1 = solve_round(prev, targets[0], loops, printable_only=True)
    print(f"round0_printable={r1}")
    if b1 is not None:
        b = bytes([(b1 >> 16) & 0xFF, (b1 >> 8) & 0xFF, b1 & 0xFF])
        print(f"round0_printable_block={b1:#08x} bytes={b!r}")


if __name__ == "__main__":
    main()
