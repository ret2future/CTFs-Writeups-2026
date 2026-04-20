const fs = require('fs')
const { Anvil } = require('/tmp/ctf-nbt/node_modules/prismarine-provider-anvil')
const nbt = require('/tmp/ctf-nbt/node_modules/prismarine-nbt')

const worldPath = '/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/MyWorld/region'
const A = Anvil('1.18.2')
const world = new A(worldPath)
const counts = {}
const interesting = []

function walk(obj, out = []) {
  if (obj == null) return out
  if (typeof obj === 'string') out.push(obj)
  else if (Array.isArray(obj)) for (const v of obj) walk(v, out)
  else if (typeof obj === 'object') {
    for (const [k, v] of Object.entries(obj)) {
      out.push(String(k))
      walk(v, out)
    }
  }
  return out
}

;(async () => {
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
            counts[be.id] = (counts[be.id] || 0) + 1
            const txt = walk(be, []).join(' | ')
            if (/(flag|vbd\{|ruby|win|secret|code|answer|submit|click|press|goal|complete|correct)/i.test(txt) || ['minecraft:command_block', 'minecraft:chain_command_block', 'minecraft:repeating_command_block', 'minecraft:chest', 'minecraft:barrel', 'minecraft:lectern', 'minecraft:sign'].includes(be.id)) {
              interesting.push({ cx, cz, x: be.x, y: be.y, z: be.z, id: be.id, data: be })
            }
          }
        } catch (e) {
        }
      }
    }
  }

  console.log(JSON.stringify({ counts, interesting }, null, 2))
})().catch((e) => {
  console.error(e)
  process.exit(1)
})
