# Rickroll CTF Challenge - File Organization

This directory organizes the stego extraction and OCR processing files by category.

## Folder Structure

```
00_source/
├── Original challenge image and extracted layers
├── pic.png          - Original stego image (source)
├── layer3.bin       - Final binary payload before PNG conversion
└── carved.png, layer3.png - Extracted intermediate PNG layers

01_lsb_channels/
├── LSB bitplane extractions from original pic.png
├── R_lsb.png, G_lsb.png, B_lsb.png, A_lsb.png
└── Processed variants (*_up_bw.png, *_inv.png)

02_carved_variants/
├── Transformations of carved.png (Layer 2)
├── Threshold variants (carved_thr_*.png)
├── Channel differences (r_minus_g, etc.)
├── LSB layers of carved image
└── Binary data extracted from carved.png

03_layer3_processing/
├── OCR preprocessing attempts on layer3.png (Layer 3)
├── Threshold sweeps (layer3_thr_*.png)
├── Autocontrast variants (layer3_ac_t*.png)
├── layer3_crop.png - Bounding box of text
└── layer3_ascii.txt - ASCII visualization attempts

04_ocr_preprocessing/
├── LARGE FOLDER: ~1800 OCR preparation images
├── Various scaling (up1-up4), blur, threshold combos
├── Morphological filters (min/max filters)
├── Inverted variants (*_inv.png)
├── Pattern: ocrprep_[ID]_up[SCALE]_b[BLUR]_t[THRESHOLD]_[FILTER].png
└── Test candidates for final flag extraction

05_preprocessing/
├── Simplified preprocessing attempts
├── prep_t*.png - Threshold variants with median filter
└── prep_t*_inv.png - Inverted versions

06_unwarp_results/
├── Geometric deobfuscation experiments
├── unwarp_search.py - Main deobfuscation script
├── unwarp_top.txt - Top ranked results from deobfuscation
└── unwarp_candidates/ - Generated test images from geometric transforms

07_scripts/
├── Python processing scripts
├── brutelsb.py - LSB brute-force decoder (early attempt)
├── fast_brute.py - Optimized LSB search
└── Other utility scripts

08_binary_data/
├── Extracted binary payloads and intermediate data
├── carved_from_abgr01lsb.bin - Layer 2 (became carved.png)
├── carved_b2_rgb_lsb.bin - Layer 3 binary
├── extracted_*.bin - Other channel extractions
└── Re-encoded format: bits packed into bytes

09_text_output/
├── Text results and logs
├── *.txt - OCR output, analysis results
├── *.out - Process logs and debug output
└── layer3_ascii.txt, unwarp_top.txt - Key results

```

## Challenge Layers

```
Layer 1: pic.png 
  ↓ [Extract abgr bits 0-1, LSB order]
Layer 2: carved.png (3000×2400 RGB)
  ↓ [Extract b2,rgb,lsb channel]
Layer 3: layer3.png (1169×389 grayscale)
  ↓ [OCR + Deobfuscation]
FLAG
```

## How to Find New Files

- **New LSB extractions?** → Check `01_lsb_channels/`
- **New carved image variants?** → Check `02_carved_variants/`
- **New layer3 attempts?** → Check `03_layer3_processing/`
- **Testing new OCR approaches?** → Add to `04_ocr_preprocessing/`
- **New preprocessing ideas?** → Check `05_preprocessing/`
- **Geometric unwarp attempts?** → Check `06_unwarp_results/`
- **Running new scripts?** → Store in `07_scripts/`
- **Raw binary analysis?** → Check `08_binary_data/`
- **Results & logs?** → Check `09_text_output/`

## Key Files to Review

1. **Source**: `00_source/pic.png` - Start here
2. **Layer 2**: `00_source/carved.png` - After first LSB decode
3. **Layer 3**: `00_source/layer3.png` - Final obfuscated text
4. **Deobfuscation**: `06_unwarp_results/unwarp_top.txt` - Ranked candidates
5. **Results**: `09_text_output/` - All extracted text attempts

## Folder Sizes (Typical)

- `04_ocr_preprocessing/` - Very large (~1800 test images)
- `02_carved_variants/` - Medium (~20-30 files)
- Others - Small to medium

---
*Updated: March 21, 2026*
