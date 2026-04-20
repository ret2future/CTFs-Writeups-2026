const fs = require('fs')
const zlib = require('zlib')
const nbt = require('/tmp/ctf-nbt/node_modules/prismarine-nbt')

function parseJsonText(s) {
  try { return JSON.parse(s) } catch { return s }
}
function flattenText(v) {
  if (v == null) return ''
  if (typeof v === 'string') return v
  if (Array.isArray(v)) return v.map(flattenText).join('')
  if (typeof v === 'object') return [v.text || '', flattenText(v.extra || [])].join('')
  return String(v)
}

;(async () => {
  const buf = zlib.gunzipSync(fs.readFileSync('/Users/elenaeftimie/Desktop/CTFs/Elena/ramadan/ruby/dist/challenge.schem'))
  const parsed = await nbt.parse(buf)
  const simp = nbt.simplify(parsed.parsed)
  const out = {
    keys: Object.keys(simp),
    Metadata: simp.Metadata,
    Width: simp.Width,
    Height: simp.Height,
    Length: simp.Length,
    Offset: simp.Offset,
    uniquePaletteCount: simp.Palette ? Object.keys(simp.Palette).length : 0,
    blockEntities: [],
    paletteSample: simp.Palette ? Object.entries(simp.Palette).slice(0, 40) : []
  }
  for (const be of simp.BlockEntities || []) {
    const item = { id: be.Id || be.id, Pos: be.Pos || [be.x, be.y, be.z] }
    if (be.CustomName) item.CustomName = flattenText(parseJsonText(be.CustomName))
    if (be.Text1 || be.Text2 || be.Text3 || be.Text4) item.lines = [be.Text1, be.Text2, be.Text3, be.Text4].map((s) => flattenText(parseJsonText(s)))
    if (be.Items) item.Items = be.Items
    out.blockEntities.push(item)
  }
  console.log(JSON.stringify(out, null, 2))
})().catch((e) => { console.error(e); process.exit(1) })
