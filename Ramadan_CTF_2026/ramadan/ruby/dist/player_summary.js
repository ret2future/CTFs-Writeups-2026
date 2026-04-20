const fs = require('fs')
const zlib = require('zlib')
const nbt = require('/tmp/ctf-nbt/node_modules/prismarine-nbt')

function parse(path) {
  return nbt.parse(zlib.gunzipSync(fs.readFileSync(path))).then((p) => nbt.simplify(p.parsed))
}

;(async () => {
  const level = await parse('/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/MyWorld/level.dat')
  const player = await parse('/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/MyWorld/playerdata/92e38e65-5904-4217-8ba8-11382b9e83f1.dat')
  console.log(JSON.stringify({
    levelName: level.Data.LevelName,
    spawn: [level.Data.SpawnX, level.Data.SpawnY, level.Data.SpawnZ],
    playerPos: player.Pos,
    rotation: player.Rotation,
    dimension: player.Dimension,
    selectedSlot: player.SelectedItemSlot,
    inventory: player.Inventory,
    enderItems: player.EnderItems,
    abilities: player.abilities
  }, null, 2))
})().catch((e) => { console.error(e); process.exit(1) })
