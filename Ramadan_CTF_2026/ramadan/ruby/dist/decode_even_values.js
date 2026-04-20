const fs = require('fs')
const data = JSON.parse(fs.readFileSync('/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/east_repeater_coords.json', 'utf8'))
const layers = [0,2,4,6,8,10,12,14,18,20,22,24,26,28,30,32]
const xs = [4,11,18,25,32,39,46,53,60,67,74,81,88,95,102,109]
const zs = [69,70,71,72,73,74]
for (const y of layers) {
  const set = new Set((data[y] || []).map(([x,z])=>`${x},${z}`))
  const vals=[]
  for (const x of xs) {
    let v=0
    for (let i=0;i<zs.length;i++) if (set.has(`${x},${zs[i]}`)) v |= (1<<i)
    vals.push(v)
  }
  console.log(y + ': ' + vals.join(' '))
}
