import angr
import claripy

BIN_PATH = './validator'
FAIL_TEXT = b"Sorry, that's not it."
PROMPT = b'Enter the secret flag:'


def solve_for_length(length: int):
    project = angr.Project(BIN_PATH, auto_load_libs=False)

    chars = [claripy.BVS(f'c{i}', 8) for i in range(length)]
    stdin_data = claripy.Concat(*chars, claripy.BVV(b'\n'))
    stdin = angr.SimFileStream(name='stdin', content=stdin_data, has_end=True)

    state = project.factory.full_init_state(args=[BIN_PATH], stdin=stdin)
    state.options.add(angr.options.ZERO_FILL_UNCONSTRAINED_MEMORY)
    state.options.add(angr.options.ZERO_FILL_UNCONSTRAINED_REGISTERS)

    for ch in chars:
        state.solver.add(ch >= 0x20)
        state.solver.add(ch <= 0x7e)

    simgr = project.factory.simgr(state, veritesting=True)

    def is_bad(s):
        return FAIL_TEXT in s.posix.dumps(1)

    def is_good(s):
        out = s.posix.dumps(1)
        return (PROMPT in out) and (FAIL_TEXT not in out)

    simgr.explore(find=is_good, avoid=is_bad, num_find=1)

    if not simgr.found:
        return None

    found = simgr.found[0]
    return ''.join(chr(found.solver.eval(c)) for c in chars)


def main():
    for length in range(1, 80):
        print(f'[*] trying length {length}', flush=True)
        result = solve_for_length(length)
        if result is not None:
            print(f'[+] FOUND: {result!r}', flush=True)
            return
    print('[-] no solution found', flush=True)


if __name__ == '__main__':
    main()
