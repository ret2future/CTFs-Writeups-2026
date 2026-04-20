#!/usr/bin/env python3
"""
Search unwarp candidates for flag patterns.
The flag should match: starts with something like 'redfix{' or similar
and ends with '_up!}' or similar.
"""

import subprocess
import os
from pathlib import Path

canddir = Path('/Users/elenaeftimie/Desktop/CTFs/rickroll/06_unwarp_results/unwarp_candidates')
results = []

print("Scanning unwarp candidates for flag patterns...")
print("-" * 80)

# Sort candidates - process best scoring ones first (from unwarp_top.txt)
best_order = [
    'sw_k-6p0_r330_norm.png',
    'sw_k-2p0_r195_norm.png',
    'sw_k-2p0_r210_norm.png',
    'sw_k-1p0_r15_inv.png',
    'sw_k1p0_r180_norm.png',
    'sw_k-2p0_r135_norm.png',
    'sw_k-1p0_r135_norm.png',
]

# Then add remaining files
remaining = sorted([f for f in os.listdir(canddir) if f.endswith('.png')])
for f in best_order:
    if f in remaining:
        remaining.remove(f)

candidates = [os.path.join(canddir, f) for f in best_order] + \
             [os.path.join(canddir, f) for f in remaining[:50]]  # Limit to first 50 others

for img_path in candidates:
    img_name = os.path.basename(img_path)
    
    try:
        # Try multiple OCR modes
        for psm in [6, 7, 8, 11, 13]:
            try:
                ocr_output = subprocess.check_output(
                    ['tesseract', img_path, 'stdout', f'--psm', str(psm),
                     '-c', 'tessedit_char_whitelist=abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789{}_!-'],
                    stderr=subprocess.DEVNULL,
                    text=True,
                    timeout=5
                )
                
                text = ocr_output.strip()
                if not text:
                    continue
                
                # Look for flag-like patterns
                text_lower = text.lower()
                if any(pattern in text_lower for pattern in ['redfix', 'flag', 'ctf', 'fix{', '{', '}']):
                    results.append((img_name, psm, text))
                    print(f"\n✓ MATCH: {img_name} (PSM {psm})")
                    print(f"  OCR: {text[:150]}")
                    
            except subprocess.TimeoutExpired:
                continue
            except Exception:
                continue
                
    except Exception as e:
        pass

# Print summary
print("\n" + "=" * 80)
print("SUMMARY OF MATCHES:")
print("=" * 80)
for img_name, psm, text in results[:20]:
    print(f"\n{img_name} (PSM {psm}):")
    print(f"  {text}")

if results:
    print("\n" + "=" * 80)
    print("TOP CANDIDATE:")
    best_img, best_psm, best_text = results[0]
    print(f"Image: {best_img}")
    print(f"Text: {best_text}")
else:
    print("\nNo clear flag patterns found. Trying visual inspection approach...")
    print("Checking if any unwarp candidate looks visually readable...")
