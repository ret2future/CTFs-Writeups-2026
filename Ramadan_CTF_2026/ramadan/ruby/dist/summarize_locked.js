const fs = require('fs')
const data = JSON.parse(fs.readFileSync('/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/locked_coords.json','utf8'))
for (const y of Object.keys(data).map(Number).sort((a,b)=>a-b)) {
  const arr=data[y]
  const xs=[...new Set(arr.map(t=>t[0]))].sort((a,b)=>a-b)
  const zs=[...new Set(arr.map(t=>t[1]))].sort((a,b)=>a-b)
  const dirs={}
  for (const [, ,d] of arr) dirs[d]=(dirs[d]||0)+1
  console.log(JSON.stringify({y,count:arr.length,xs,zs,dirs}))
}
