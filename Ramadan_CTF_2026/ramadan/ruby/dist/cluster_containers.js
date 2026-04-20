const fs = require('fs')
const data = JSON.parse(fs.readFileSync('/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/world_summary.json', 'utf8'))

function rng(arr, key) {
  const vals = arr.map((x) => x[key])
  return { min: Math.min(...vals), max: Math.max(...vals) }
}

const groups = {}
for (const c of data.importantContainers) {
  const kinds = [...new Set((c.items || []).map((i) => i.id))].sort().join(',')
  const key = `${c.name}|${kinds}`
  ;(groups[key] ||= []).push(c)
}

const out = Object.entries(groups).map(([key, arr]) => ({
  key,
  count: arr.length,
  x: rng(arr, 'x'),
  y: rng(arr, 'y'),
  z: rng(arr, 'z'),
  sample: arr.slice(0, 10).map(({x,y,z,name}) => ({x,y,z,name}))
})).sort((a,b)=>a.y.min-b.y.min||a.z.min-b.z.min||a.x.min-b.x.min)

console.log(JSON.stringify(out, null, 2))
