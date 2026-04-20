const fs = require('fs')
const data = JSON.parse(fs.readFileSync('/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/world_summary.json', 'utf8'))
const arr = data.importantContainers.filter(c => (c.items||[]).some(i => i.id === 'minecraft:totem_of_undying'))

const ys = [...new Set(arr.map(c => c.y))].sort((a,b)=>a-b)
for (const y of ys) {
  const layer = arr.filter(c => c.y === y)
  const xs = [...new Set(layer.map(c => c.x))].sort((a,b)=>a-b)
  const zs = [...new Set(layer.map(c => c.z))].sort((a,b)=>a-b)
  const map = new Map(layer.map(c => [`${c.x},${c.z}`, c.name.padStart(2,' ')]))
  console.log(`Y=${y} x:[${xs[0]},${xs[xs.length-1]}] z:[${zs[0]},${zs[zs.length-1]}] count=${layer.length}`)
  for (const z of zs) {
    let line = `z=${String(z).padStart(4,' ')} `
    for (const x of xs) {
      line += (map.get(`${x},${z}`) || ' .') + ' '
    }
    console.log(line)
  }
  console.log('')
}
