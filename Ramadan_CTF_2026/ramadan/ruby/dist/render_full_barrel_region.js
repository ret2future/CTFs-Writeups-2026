const fs = require('fs')
const { Anvil } = require('/tmp/ctf-nbt/node_modules/prismarine-provider-anvil')
const nbt = require('/tmp/ctf-nbt/node_modules/prismarine-nbt')

const worldPath = '/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/MyWorld/region'
const A = Anvil('1.18.2')
const world = new A(worldPath)

function parseName(n) {
  const s = n?.text || n?.value || n
  if (!s) return null
  if (typeof s === 'string') {
    try {
      const j = JSON.parse(s)
      if (j.text) return j.text
      if (j.extra && j.extra[0] && j.extra[0].text) return j.extra[0].text
    } catch {}
    return s
  }
  return null
}

;(async () => {
  const out=[]
  for (const [cx,cz] of [[2,-7],[2,-6],[2,-5]]) {
    const raw = await world.loadRaw(cx,cz)
    const simp = nbt.simplify(raw)
    for (const be of simp.block_entities || []) {
      if (be.id === 'minecraft:barrel' && be.x >= 30 && be.x <= 46 && be.y >= 213 && be.y <= 219 && be.z >= -97 && be.z <= -69) {
        const name = parseName(be.CustomName)
        out.push({x:be.x,y:be.y,z:be.z,name:name || '1'})
      }
    }
  }
  out.sort((a,b)=>a.y-b.y||a.z-b.z||a.x-b.x)
  console.log(JSON.stringify(out,null,2))
})().catch(e=>{console.error(e);process.exit(1)})
