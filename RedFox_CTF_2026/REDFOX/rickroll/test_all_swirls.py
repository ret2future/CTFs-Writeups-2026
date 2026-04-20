#!/usr/bin/env python3
import glob
import subprocess
from PIL import Image

results = []

# Test both negative and positive swirl images
for f in sorted(glob.glob('reverse_swirl_*.png')) + sorted(glob.glob('reverse_swirl_neg_*.png')):
    try:
        # Try multiple PSM modes
        best_txt = ""
        best_score = 0
        
        for psm in [6, 7, 8, 11]:
            out = subprocess.check_output(['tesseract', f, 'stdout', '--psm', str(psm)], 
                                         stderr=subprocess.DEVNULL, text=True)
            txt = out.replace('\n', ' ')[:200].strip()
            score = sum(c.isalnum() for c in txt)
            if score > best_score:
                best_score = score
                best_txt = txt
        
        if best_score > 0:
            results.append((best_score, f, best_txt))
    except Exception as e:
        pass

# Sort by score
results.sort(reverse=True)

# Show top 20
print("=" * 130)
print("TOP SWIRL ANGLES:")
print("=" * 130)
for i, (score, fname, txt) in enumerate(results[:20], 1):
    angle = fname.replace('reverse_swirl_neg_', 'NEG_').replace('reverse_swirl_', 'POS_').replace('.png', '')
    print(f"{i:2d}. [{score:3d}] {angle:15s} | {txt[:90]}")
