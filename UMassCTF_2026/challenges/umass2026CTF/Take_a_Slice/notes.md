# Take a Slice

Flag: `UMASS{SL1C3_&_D1C3}`

## Summary

The challenge file `cake` is a binary STL 3D model of a pie-slice-shaped cake.
The flag is embossed as raised 3D text inside the model.

## Solution

Parse the binary STL (80-byte header + 4-byte triangle count + 50 bytes per triangle).
The model has 39,210 triangles. Take horizontal cross-sections (Z-slices) using the
standard plane-triangle intersection algorithm.

The embossed flag text spans multiple Z heights — different characters appear more
cleanly at different levels:
- Z=3 reveals the opening `UM` characters (red in overlay)
- Z=5 reveals the middle `ASS{SL1C3` portion (green in overlay)
- Z=7 reveals the tail `_&_D1C3}` portion (blue in overlay)

Overlaying all three slices in `artifacts/overlay_z357.png` assembles the complete flag.

Key coordinates: text region is approximately x: -1..25, y: 0.5..10 in model space.
The diagonal boundary edge (y ≈ x) of the pie slice partially occludes some characters,
which is why multiple Z levels are needed.

## Artifacts

- `overlay_z357.png` — overlaid slices at Z=3 (red), Z=5 (green), Z=7 (blue) showing full flag
- `noarc_z3/5/7.png` — individual Z-slice renders with arc boundary removed
- `slice_z12.7.png` — mid-height horizontal slice showing the pie-slice shape
