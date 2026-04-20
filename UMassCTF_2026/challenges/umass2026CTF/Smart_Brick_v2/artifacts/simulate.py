from kiutils.board import Board

board = Board.from_file('/root/umass2026CTF/Smart_Brick_v2/starting_files/smart-brick-v2.kicad_pcb')

# Gate pin definitions for each 74LS IC type
# (input_pins, output_pin) — multiple gates per chip
GATE_DEFS = {
    '74LS04': [  # hex inverter
        ([1], 2), ([3], 4), ([5], 6), ([9], 8), ([11], 10), ([13], 12)
    ],
    '74LS00': [  # quad 2-input NAND
        ([1,2], 3), ([4,5], 6), ([9,10], 8), ([12,13], 11)
    ],
    '74LS02': [  # quad 2-input NOR
        ([2,3], 1), ([5,6], 4), ([8,9], 10), ([11,12], 13)
    ],
    '74LS08': [  # quad 2-input AND
        ([1,2], 3), ([4,5], 6), ([9,10], 8), ([12,13], 11)
    ],
    '74LS32': [  # quad 2-input OR
        ([1,2], 3), ([4,5], 6), ([9,10], 8), ([12,13], 11)
    ],
    '74LS86': [  # quad 2-input XOR
        ([1,2], 3), ([4,5], 6), ([9,10], 8), ([12,13], 11)
    ],
    '74LS21': [  # dual 4-input AND
        ([1,2,4,5], 6), ([9,10,12,13], 8)
    ],
    '74LS20': [  # dual 4-input NAND
        ([1,2,4,5], 6), ([9,10,12,13], 8)
    ],
    '74LS27': [  # triple 3-input NOR
        ([1,2,13], 12), ([3,4,5], 6), ([9,10,11], 8)
    ],
}
GATE_TYPE = {
    '74LS04': 'NOT', '74LS00': 'NAND', '74LS02': 'NOR', '74LS08': 'AND',
    '74LS32': 'OR', '74LS86': 'XOR', '74LS21': 'AND', '74LS20': 'NAND',
    '74LS27': 'NOR',
}

# Build pad-to-net mapping
fp_pad_net = {}  # (ref, pad_num) -> net_name
for fp in board.footprints:
    ref = fp.properties.get('Reference','?')
    for pad in fp.pads:
        net = pad.net.name if pad.net else None
        fp_pad_net[(ref, pad.number)] = net

def get_net(ref, pad):
    return fp_pad_net.get((ref, str(pad)))

# Build gate list: [(gate_type, input_nets, output_net)]
gates = []
for fp in board.footprints:
    ref = fp.properties.get('Reference','?')
    val = fp.properties.get('Value','?')
    if val not in GATE_DEFS:
        continue
    gtype = GATE_TYPE[val]
    for in_pads, out_pad in GATE_DEFS[val]:
        in_nets = [get_net(ref, p) for p in in_pads]
        out_net = get_net(ref, out_pad)
        if out_net and all(n is not None for n in in_nets):
            gates.append((gtype, in_nets, out_net))

print(f"Total gates: {len(gates)}")

# Transistor gate nets (outputs we care about) in Q order
# From netlist: Q1..Q19 gates
q_nets = {}
for fp in board.footprints:
    ref = fp.properties.get('Reference','?')
    val = fp.properties.get('Value','?')
    if val == '2N7002' and ref.startswith('Q'):
        q_num = int(ref[1:])
        gate_net = get_net(ref, '1')  # gate = pin 1 for SOT-23
        q_nets[q_num] = gate_net

print("Transistor gate nets:")
for q, net in sorted(q_nets.items()):
    print(f"  Q{q:2d}: {net}")

# Simulate: evaluate all 128 input combinations
in_nets = [f'/IN{i}' for i in range(7)]

def simulate(in_vals):
    state = {}
    # Set inputs
    for i, net in enumerate(in_nets):
        state[net] = in_vals[i]
    state['GND'] = 0
    state['+5V'] = 1

    # Iterative evaluation (multiple passes for convergence)
    for _ in range(30):
        for gtype, inp_nets, out_net in gates:
            ins = [state.get(n, None) for n in inp_nets]
            if None in ins:
                continue
            if gtype == 'NOT':
                val = 1 - ins[0]
            elif gtype == 'AND':
                val = int(all(ins))
            elif gtype == 'OR':
                val = int(any(ins))
            elif gtype == 'NAND':
                val = 1 - int(all(ins))
            elif gtype == 'NOR':
                val = 1 - int(any(ins))
            elif gtype == 'XOR':
                val = ins[0] ^ ins[1]
            else:
                continue
            state[out_net] = val
    return state

# For each Q output, find which input values make it HIGH
q_active_inputs = {}
for q_num, gate_net in sorted(q_nets.items()):
    active = []
    for v in range(128):
        bits = [(v >> i) & 1 for i in range(7)]
        state = simulate(bits)
        if state.get(gate_net, 0) == 1:
            active.append(v)
    q_active_inputs[q_num] = active

print("\nTransistor activation inputs (ASCII):")
flag_chars = []
for q, vals in sorted(q_active_inputs.items()):
    chars = [chr(v) if 32 <= v < 127 else f'\\x{v:02x}' for v in vals]
    print(f"  Q{q:2d} net={q_nets[q]:8s}: {vals} = {chars}")
    if len(vals) == 1:
        flag_chars.append((q, vals[0]))

print("\nSingle-activation Qs (flag characters):")
for q, v in sorted(flag_chars):
    print(f"  Q{q:2d} = {v} = {chr(v)!r}")

flag = ''.join(chr(v) for _, v in sorted(flag_chars))
print(f"\nFlag candidate: {flag}")
