const fs = require('fs')
const zlib = require('zlib')
const nbt = require('/tmp/ctf-nbt/node_modules/prismarine-nbt')

;(async () => {
  const buf = zlib.gunzipSync(fs.readFileSync('/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/challenge.schem'))
  const parsed = await nbt.parse(buf)
  const s = nbt.simplify(parsed.parsed)
  const invPalette = {}
  for (const [k, v] of Object.entries(s.Palette)) invPalette[v] = k
  const W = s.Width, H = s.Height, L = s.Length
  const data = s.BlockData
  const layers = []
  const counts = {}
  let i = 0
  for (let y = 0; y < H; y++) {
    let count = 0
    let minX = Infinity, maxX = -Infinity, minZ = Infinity, maxZ = -Infinity
    const types = {}
    for (let z = 0; z < L; z++) {
      for (let x = 0; x < W; x++) {
        const v = data[i++]
        const name = invPalette[v]
        counts[name] = (counts[name] || 0) + 1
        if (name !== 'minecraft:air') {
          count++
          if (x < minX) minX = x
          if (x > maxX) maxX = x
          if (z < minZ) minZ = z
          if (z > maxZ) maxZ = z
          types[name] = (types[name] || 0) + 1
        }
      }
    }
    if (count) layers.push({ y, count, minX, maxX, minZ, maxZ, types })
  }
  console.log(JSON.stringify({ W, H, L, counts, layers }, null, 2))
})().catch(e => { console.error(e); process.exit(1) })
