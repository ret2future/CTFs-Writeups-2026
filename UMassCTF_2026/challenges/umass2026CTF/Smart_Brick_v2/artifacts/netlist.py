from kiutils.board import Board

board = Board.from_file('/root/umass2026CTF/Smart_Brick_v2/starting_files/smart-brick-v2.kicad_pcb')

net_map = {}

for fp in board.footprints:
    ref = fp.properties.get('Reference','?')
    val = fp.properties.get('Value','?')
    for pad in fp.pads:
        net = pad.net.name if pad.net else 'NONET'
        net_map.setdefault(net, []).append(f"{ref}.{pad.number}({val})")

# Show connector pads
for fp in board.footprints:
    ref = fp.properties.get('Reference','?')
    if ref.startswith('J'):
        print(f"\n{ref} ({fp.properties.get('Value','?')}) pads:")
        for pad in fp.pads:
            net = pad.net.name if pad.net else 'NONET'
            print(f"  Pin {pad.number}: net={net}")

print("\n\nAll NON-POWER signal nets:")
for net, pins in sorted(net_map.items()):
    if net not in ('NONET', '+5V', 'GND', ''):
        print(f"  {net:30s}: {pins}")
