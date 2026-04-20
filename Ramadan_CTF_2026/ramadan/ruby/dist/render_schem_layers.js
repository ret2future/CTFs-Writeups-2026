const fs = require('fs')
const zlib = require('zlib')
const nbt = require('/tmp/ctf-nbt/node_modules/prismarine-nbt')

function ch(name) {
  if (name === 'minecraft:air') return ' '
  if (name === 'minecraft:purple_wool') return '#'
  if (name.includes('facing=east')) return '>'
  if (name.includes('facing=west')) return '<'
  if (name.includes('facing=north')) return '^'
  if (name.includes('facing=south')) return 'v'
  return '?'
}

;(async () => {
  const buf = zlib.gunzipSync(fs.readFileSync('/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/challenge.schem'))
  const parsed = await nbt.parse(buf)
  const s = nbt.simplify(parsed.parsed)
  const inv = {}
  for (const [k, v] of Object.entries(s.Palette)) inv[v] = k
  const W = s.Width, H = s.Height, L = s.Length
  const data = s.BlockData
  let i = 0
  const layers = []
  for (let y = 0; y < H; y++) {
    const grid = Array.from({ length: L }, () => Array(W).fill(' '))
    let any = false
    for (let z = 0; z < L; z++) {
      for (let x = 0; x < W; x++) {
        const name = inv[data[i++]]
        const c = ch(name)
        grid[z][x] = c
        if (c !== ' ') any = true
      }
    }
    if (!any) continue
    let minX=W,maxX=-1,minZ=L,maxZ=-1
    for (let z=0; z<L; z++) for (let x=0; x<W; x++) if (grid[z][x] !== ' ') { if (x<minX) minX=x; if (x>maxX) maxX=x; if (z<minZ) minZ=z; if (z>maxZ) maxZ=z }
    const lines=[]
    for (let z=minZ; z<=maxZ; z++) lines.push(grid[z].slice(minX,maxX+1).join(''))
    layers.push({y,minX,maxX,minZ,maxZ,lines})
  }
  for (const layer of layers) {
    console.log(`=== Y=${layer.y} x=${layer.minX}-${layer.maxX} z=${layer.minZ}-${layer.maxZ} ===`)
    for (const line of layer.lines) console.log(line)
    console.log('')
  }
})().catch(e => { console.error(e); process.exit(1) })
