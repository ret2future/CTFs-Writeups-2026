const fs = require('fs')
const data = JSON.parse(fs.readFileSync('/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/world_summary.json', 'utf8'))

const itemKinds = {}
const nameToKinds = {}
for (const c of data.importantContainers) {
  const kinds = [...new Set((c.items || []).map((i) => i.id))].sort()
  for (const k of kinds) itemKinds[k] = (itemKinds[k] || 0) + 1
  if (!nameToKinds[c.name]) nameToKinds[c.name] = {}
  for (const k of kinds) nameToKinds[c.name][k] = (nameToKinds[c.name][k] || 0) + 1
}

const byCoord = data.importantContainers.map((c) => ({
  x: c.x, y: c.y, z: c.z, name: c.name,
  kinds: [...new Set((c.items || []).map((i) => i.id))].sort(),
  count: (c.items || []).length
}))
byCoord.sort((a, b) => a.y - b.y || a.z - b.z || a.x - b.x)

console.log(JSON.stringify({ itemKinds, nameToKinds, first200: byCoord.slice(0, 200) }, null, 2))
