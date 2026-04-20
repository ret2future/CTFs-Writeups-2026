const fs = require('fs')
const data = JSON.parse(fs.readFileSync('/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/east_repeater_coords.json', 'utf8'))
const xs = [4,11,18,25,32,39,46,53,60,67,74,81,88,95,102,109]
const zs = [69,70,71,72,73,74]
for (const [y, coords] of Object.entries(data)) {
  console.log('Y=' + y)
  const set = new Set(coords.map(([x,z])=>`${x},${z}`))
  for (const z of zs) {
    let line=''
    for (const x of xs) line += set.has(`${x},${z}`) ? '#' : '.'
    console.log(line)
  }
  console.log('')
}
