const fs = require('fs')
const { Anvil } = require('/tmp/ctf-nbt/node_modules/prismarine-provider-anvil')
const nbt = require('/tmp/ctf-nbt/node_modules/prismarine-nbt')

const worldPath = '/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/MyWorld/region'
const A = Anvil('1.18.2')
const world = new A(worldPath)

;(async () => {
  const hits = []
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
          const found = new Set()
          for (const sec of simp.sections || []) {
            const pal = sec.block_states?.palette || []
            for (const p of pal) {
              const name = p.Name || p.name
              if (name && (name.includes('purple_wool') || name.includes('repeater') || name.includes('barrel') || name.includes('comparator') || name.includes('oak_sign') || name.includes('spruce_sign') || name.includes('sign'))) {
                found.add(name)
              }
            }
          }
          if (found.size) hits.push({ cx, cz, blocks: [...found].sort() })
        } catch {}
      }
    }
  }
  console.log(JSON.stringify(hits, null, 2))
})().catch((e) => { console.error(e); process.exit(1) })
