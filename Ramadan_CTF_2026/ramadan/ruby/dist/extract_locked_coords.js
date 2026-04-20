const fs = require('fs')
const zlib = require('zlib')
const nbt = require('/tmp/ctf-nbt/node_modules/prismarine-nbt')

;(async () => {
  const buf = zlib.gunzipSync(fs.readFileSync('/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/challenge.schem'))
  const parsed = await nbt.parse(buf)
  const s = nbt.simplify(parsed.parsed)
  const inv = {}
  for (const [k, v] of Object.entries(s.Palette)) inv[v] = k
  const W=s.Width,H=s.Height,L=s.Length
  let i=0
  const out={}
  for (let y=0;y<H;y++) {
    for (let z=0;z<L;z++) for (let x=0;x<W;x++) {
      const name=inv[s.BlockData[i++]]
      if (name && name.includes('locked=true')) {
        ;(out[y] ||= []).push([x,z,name.includes('north')?'^':name.includes('south')?'v':name.includes('east')?'>':'<'])
      }
    }
  }
  console.log(JSON.stringify(out,null,2))
})().catch(e=>{console.error(e);process.exit(1)})
