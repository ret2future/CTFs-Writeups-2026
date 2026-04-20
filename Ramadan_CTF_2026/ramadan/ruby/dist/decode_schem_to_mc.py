import json
import nbtlib

schem = nbtlib.load('/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/challenge.schem')
root = schem
W = int(root['Width'])
H = int(root['Height'])
L = int(root['Length'])
palette = {int(v): k for k, v in root['Palette'].items()}
block_data = list(root['BlockData'])

# relative coords map from original schematic.py coordinates using mins x=-113, y=-33, z=-67
XOFF, YOFF, ZOFF = 113, 33, 67

def idx(x, y, z):
    return y * (L * W) + z * W + x

def block_at(x, y, z):
    if not (0 <= x < W and 0 <= y < H and 0 <= z < L):
        return 'minecraft:air'
    return palette[int(block_data[idx(x, y, z)])]

pos_list = []
mem_start_pos = [-4, -1, 2]
for i in range(2):
    for j in range(32):
        pos = mem_start_pos.copy()
        if i == 1:
            pos[0] -= 2
        pos[2] += 2 * j
        if j >= 16:
            pos[2] += 4
        for k in range(16):
            pos_list.append(pos.copy())
            if k % 2 == 0:
                pos[0] -= 7
                pos[2] += 1 if j < 16 else -1
            else:
                pos[0] -= 7
                pos[2] -= 1 if j < 16 else -1

lines = []
for address, pos in enumerate(pos_list):
    face = 'east' if address < 512 else 'west'
    x, y, z = pos
    x += XOFF; y += YOFF; z += ZOFF
    bits = []
    yy = y
    for _ in range(8):
        bits.append('1' if block_at(x, yy, z) == f'minecraft:repeater[facing={face}]' else '0')
        yy -= 2
    yy -= 2
    for _ in range(8):
        bits.append('1' if block_at(x, yy, z) == f'minecraft:repeater[facing={face}]' else '0')
        yy -= 2
    # bits currently low byte then high byte, matching schematic.py's write order
    line = ''.join(bits[8:]) + ''.join(bits[:8])
    lines.append(line)

with open('/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/decoded.mc', 'w') as f:
    f.write('\n'.join(lines) + '\n')

print('decoded', len(lines), 'lines')
print('\n'.join(lines[:32]))
