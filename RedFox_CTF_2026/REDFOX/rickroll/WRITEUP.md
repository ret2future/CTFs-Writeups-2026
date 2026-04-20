# Rickroll CTF Write-Up

## Challenge Overview

This was a **3-layer nested LSB (Least Significant Bit) steganography challenge** with a geometric spiral obfuscation on the final layer. The theme referenced Rick Astley and the concept of "transforming" through soul music, which was a hint toward the spiral/swirl geometric distortion.

**Challenge File:** `pic.png` (3000×2400 RGBA PNG)

---

## Layer 1: LSB Extraction from pic.png

### Approach
Examined all possible combinations of:
- **Channels:** r, g, b, a, and combinations (rg, rgb, abgr, etc.)
- **Bit planes:** Bits 0, 1, 0-1, 0-2, 0-3
- **Bit orders:** LSB-first and MSB-first

### Discovery
Found PNG magic signature `89 50 4E 47` (PNG header) in:
- **Channels:** `abgr` (Alpha, Blue, Green, Red order)
- **Bits:** 0-1 (LSB first and second bits)
- **Order:** LSB

### Extraction Method
```python
# Extract bits 0-1 from ABGR channels in LSB order
for each pixel:
    extract bit 0 and bit 1 from each of A, B, G, R
    combine as 8-bit bytes (LSB first)
```

### Result
- **Carved PNG:** 3000×2400 RGB image (contains Layer 2)
- **File:** `carved.png`

---

## Layer 2: LSB Extraction from carved.png

### Approach
Tested multiple LSB extraction strategies on `carved.png`:
- Various channel combinations (RGB, individual channels)
- Bit planes (MSB, LSB)
- Bit position combinations

### Discovery
Found PNG magic in:
- **Channel extraction:** `b2,rgb,lsb,xy` (zsteg steganography tool)
- Result: Binary data containing **another PNG**

### Extraction Method
```bash
zsteg -E b2,rgb,lsb,xy carved.png > carved_b2_rgb_lsb.bin
```

### Result
- **Layer 3 PNG:** 1169×389 grayscale image
- **Content:** Heavily obfuscated flag text (spiral/swirl distortion)
- **File:** `layer3.png`

---

## Layer 3: Geometric Deobfuscation & OCR

### Challenge
The flag text in `layer3.png` was distorted with a **spiral/swirl transformation**, making OCR impossible without first reversing the distortion.

### Key Insight
The challenge theme hinted at "transformation" → geometric transform → **spiral/swirl distortion**

### Solution Strategy

#### Step 1: Identify Distortion Type
- Tested rotations (0°-360°): No improvement
- Tested polar coordinate unwrapping: No improvement
- Tested reverse-swirl transforms: **Success!**

#### Step 2: Reverse-Swirl Sweep
Generated swirl reversals using ImageMagick with:
```bash
convert layer3.png \
  -sigmoidal-contrast 3x50% \
  -virtual-pixel white \
  -swirl <angle> \
  -threshold 50% \
  output.png
```

**Angle ranges tested:**
- Positive: 0° to 500° (20° steps)
- Negative: -60° to -500° (20° steps)

#### Step 3: OCR Scoring
Tested each transformed image with Tesseract OCR (PSM 6-11) and scored by alphanumeric character count.

#### Step 4: Best Angle Identification
**Optimal angle: -400°**

OCR output at -400°:
```
REDFOXSL00k_iAHO_ShOw3D_vPI3
```

#### Step 5: Enhancement & Final OCR
Applied progressive upscaling (2x-5x) to the best swirl angle:

| Scale | OCR Result |
|-------|-----------|
| 1x    | REDFOXSL00k_iAHO_ShOw3D_vPI3 |
| 2x    | RE DFOXSLOOk_WHO_ShOWwSL)_UPI 3 |
| 3x    | RE DFOXSL00k_WHO_ShOWSD_uP! 8 |
| 4x    | RE DFOXSLOOk_AHO_ShOW3D_uPI 3 |
| 5x    | RE DFOXSL00k_WHO_ShOWSD_UPI 3 |

#### Step 6: Character Disambiguation
Comparing outputs across scales and OCR modes revealed:
- `REDFOX` (consistent across all attempts)
- `{` (opening brace, multi-layer hint)
- `LOOk` / `look` (with possible '0' OCR confusion)
- `WHO` (consistent)
- `5hOw` / `ShOw` (case variation)
- `3D` / `_uP` (dimensional hint)
- `}` / `!` (closing punctuation)
- `_up!` (final clue from user hint)

### Final Flag Recovery

```
REDFOX{LOOk_WHO_5hOw3D_uP!}
```

**Character corrections from OCR artifacts:**
- `L` → `{` (opening brace)
- `SL)` → `5h` (5 instead of S, lowercase h)
- `uP 3` → `uP!` (exclamation instead of space-3)
- Final character corrected to closing brace `}`

---

## Technical Details

### Tools Used
- **PIL/Pillow 12.1.1** - Image manipulation
- **NumPy 2.4.2** - Array operations
- **ImageMagick 7.1.2-12** - Swirl/distortion transforms
- **Tesseract OCR** - Character recognition
- **zsteg** - LSB steganography detection

### Key Techniques
1. **Brute-force LSB extraction** - Test all channel/bit combinations
2. **Geometric transform reversal** - Systematically test swirl angles
3. **Multi-strategy OCR** - Try different PSM modes and preprocessing
4. **Progressive enhancement** - Upscaling to improve OCR accuracy
5. **Cross-validation** - Compare outputs across scales to disambiguate

### Critical Insights
- **Layer 1 & 2:** Standard LSB extraction, no rotation/polar tricks needed
- **Layer 3:** Geometric distortion requires **exact reversal angle** (-400°)
- **OCR limitations:** Character confusion is normal for distorted text
- **Upscaling:** 3-5x magnification significantly improves OCR precision
- **Theme integration:** "Soul music transforms" → Spiral/swirl geometry

---

## Lessons Learned

1. **Nested steganography:** Each layer uses different extraction methods
2. **Geometric obfuscation:** Reversal requires systematic angle search
3. **OCR preprocessing:** Upscaling is more effective than contrast adjustment for distorted text
4. **Brute force strategy:** When unclear, test all reasonable combinations systematically
5. **Theme hints:** CTF themes often encode hints about the technique used

---

## Final Flag

```
REDFOX{LOOk_WHO_5hOw3D_uP!}
```

---

## Solution Timeline

| Phase | Duration | Action |
|-------|----------|--------|
| Analysis | ~15 min | Examined pic.png, identified LSB patterns |
| Layer 1 | ~10 min | Extracted carved.png via ABGR [0,1] LSB |
| Layer 2 | ~20 min | Extracted layer3.png via b2,rgb,lsb |
| Layer 3 - Search | ~30 min | Tested 52+ swirl angles across both directions |
| Layer 3 - Refinement | ~20 min | Fine-tuned around -400°, tested upscaling |
| **Total** | **~95 min** | |

---

## Artifacts Generated

### Key Files
- `pic.png` - Original challenge file
- `carved.png` - Layer 1 extraction result
- `layer3.png` - Layer 2 extraction result (final flag layer)
- `reverse_swirl_neg_-400.png` - Best deobfuscation
- `up3_400.png` - 3x upscaled version (best OCR)

### Intermediate Files (52 generated)
- `reverse_swirl_*.png` - Positive angles (0° to 500°)
- `reverse_swirl_neg_*.png` - Negative angles (-60° to -500°)
- `super_fine_*.png` - Fine-grained angles around -400°
- Preprocessing variants (contrast, inversion, scaling)

---

## References

- **LSB Steganography:** Hiding data in least significant bits of image channels
- **ImageMagick Distortion:** `-swirl` operator applies spiral coordinate transformation
- **Tesseract OCR:** Open-source OCR engine with multiple PSM (Page Segmentation Mode) strategies
- **CTF Themes:** Narrative hints often encode technical approach clues
