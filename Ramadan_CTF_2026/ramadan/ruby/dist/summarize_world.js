const fs = require('fs')
const { Anvil } = require('/tmp/ctf-nbt/node_modules/prismarine-provider-anvil')
const nbt = require('/tmp/ctf-nbt/node_modules/prismarine-nbt')

const worldPath = '/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/MyWorld/region'
const A = Anvil('1.18.2')
const world = new A(worldPath)

function parseJsonText(s) {
  try {
    return JSON.parse(s)
  } catch {
    return s
  }
}

function flattenText(v) {
  if (v == null) return ''
  if (typeof v === 'string') return v
  if (Array.isArray(v)) return v.map(flattenText).join('')
  if (typeof v === 'object') return [v.text || '', flattenText(v.extra || [])].join('')
  return String(v)
}

;(async () => {
  const out = { signs: [], barrelNames: {}, otherNames: {}, importantContainers: [] }
  const regionFiles = fs.readdirSync(worldPath).filter((f) => f.endsWith('.mca'))
  for (const rf of regionFiles) {
    const m = rf.match(/^r\.(-?\d+)\.(-?\d+)\.mca$/)
    if (!m) continue
    const rx = Number(m[1])
    const rz = Number(m[2])
    for (let lx = 0; lx < 32; lx++) {
      for (let lz = 0; lz < 32; lz++) {
        const cx = rx * 32 + lx
        const cz = rz * 32 + lz
        try {
          const raw = await world.loadRaw(cx, cz)
          if (!raw) continue
          const simp = nbt.simplify(raw)
          for (const be of simp.block_entities || []) {
            if (be.id === 'minecraft:sign') {
              const lines = [be.Text1, be.Text2, be.Text3, be.Text4].map((s) => flattenText(parseJsonText(s)))
              out.signs.push({ x: be.x, y: be.y, z: be.z, lines })
            }
            if (be.CustomName) {
              const name = flattenText(parseJsonText(be.CustomName))
              if (be.id === 'minecraft:barrel') out.barrelNames[name] = (out.barrelNames[name] || 0) + 1
              else out.otherNames[`${be.id}:${name}`] = (out.otherNames[`${be.id}:${name}`] || 0) + 1
              if (name && name !== '1') {
                out.importantContainers.push({ x: be.x, y: be.y, z: be.z, id: be.id, name, items: be.Items || [] })
              }
            }
          }
        } catch {}
      }
    }
  }
  out.signs.sort((a, b) => a.y - b.y || a.z - b.z || a.x - b.x)
  console.log(JSON.stringify(out, null, 2))
})().catch((e) => {
  console.error(e)
  process.exit(1)
})
