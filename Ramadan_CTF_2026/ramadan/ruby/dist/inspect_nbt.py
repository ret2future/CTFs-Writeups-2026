import nbtlib

paths = [
    'MyWorld/level.dat',
    'MyWorld/playerdata/92e38e65-5904-4217-8ba8-11382b9e83f1.dat',
    'challenge.schem',
]

for path in paths:
    print(f'--- {path} ---')
    n = nbtlib.load(path)
    root = n.root
    print('keys:', list(root.keys())[:20])

    if path.endswith('level.dat'):
        d = root['Data']
        for k in ['LevelName', 'DataVersion', 'SpawnX', 'SpawnY', 'SpawnZ', 'GameType', 'hardcore', 'Difficulty', 'allowCommands']:
            if k in d:
                print(f'{k}:', d[k])
        print('Version:', d.get('Version'))
        print('Player Pos:', d['Player'].get('Pos'))
        print('Player Dimension:', d['Player'].get('Dimension'))

    elif 'playerdata' in path:
        for k in ['Pos', 'Rotation', 'Dimension', 'playerGameType', 'SelectedItemSlot']:
            if k in root:
                print(f'{k}:', root[k])
        inv = root.get('Inventory', [])
        print('Inventory count:', len(inv))
        for item in inv[:20]:
            print(' item:', {kk: item[kk] for kk in item.keys() if kk in ['Slot', 'id', 'Count', 'tag']})

    else:
        print('root keys:', list(root.keys()))
        meta = root.get('Metadata')
        if meta:
            print('Metadata keys:', list(meta.keys()))
            for k in ['Name', 'Author', 'Date', 'RequiredMods']:
                if k in meta:
                    print(f'{k}:', meta[k])
        for k in ['Width', 'Height', 'Length', 'Version', 'DataVersion', 'Offset']:
            if k in root:
                print(f'{k}:', root[k])
