const fs = require('fs')
const { Anvil } = require('/tmp/ctf-nbt/node_modules/prismarine-provider-anvil')
const nbt = require('/tmp/ctf-nbt/node_modules/prismarine-nbt')
const world = new (Anvil('1.18.2'))('/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/MyWorld/region')
;(async () => {
  const coords=[]
  for (let cx=-1; cx<=2; cx++) for (let cz=-4; cz<=0; cz++) coords.push([cx,cz])
  for (const [cx,cz] of coords) {
    try {
      const raw = await world.loadRaw(cx,cz)
      if (!raw) continue
      const simp = nbt.simplify(raw)
      const found = new Set()
      for (const sec of simp.sections||[]) for (const p of sec.block_states?.palette||[]) found.add(p.Name||p.name)
      const arr=[...found].filter(n=>!['minecraft:air','minecraft:cave_air'].includes(n)).sort()
      console.log('CHUNK',cx,cz)
      console.log(arr.join('\n'))
      console.log('---')
    } catch (e) {}
  }
})().catch(e=>{console.error(e);process.exit(1)})
