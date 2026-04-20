const fs = require('fs')
const data = JSON.parse(fs.readFileSync('/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/world_summary.json', 'utf8'))
const arr = data.importantContainers.filter(c => (c.items||[]).some(i => i.id === 'minecraft:totem_of_undying'))
const mapChar = n => (n === '0' ? ' ' : String.fromCharCode(64 + Number(n)))

function render(aKey, bKey) {
  const as = [...new Set(arr.map(c => c[aKey]))].sort((a,b)=>a-b)
  const bs = [...new Set(arr.map(c => c[bKey]))].sort((a,b)=>a-b)
  console.log(`== ${aKey} vs ${bKey} ==`)
  for (const b of bs) {
    let line = `${bKey}=${String(b).padStart(4,' ')} `
    for (const a of as) {
      const hits = arr.filter(c => c[aKey]===a && c[bKey]===b)
      if (!hits.length) { line += '.'; continue }
      const vals = [...new Set(hits.map(c => c.name))]
      line += vals.length === 1 ? mapChar(vals[0]) : '*'
    }
    console.log(line)
  }
  console.log('')
}

render('x','z')
render('x','y')
render('z','y')
