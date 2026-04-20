#!/usr/bin/env python3
"""
Enhanced flag extraction with aggressive preprocessing and multiple OCR strategies.
"""
import numpy as np
from PIL import Image, ImageOps, ImageFilter, ImageEnhance
import subprocess
import os
from pathlib import Path

def ocr_aggressive(img_path, psm=7):
    """Try OCR with various settings."""
    results = []
    
    # Read original
    img = Image.open(img_path).convert('L')
    
    # Try 1: Direct OCR
    try:
        cmd = ['tesseract', str(img_path), 'stdout', '--psm', str(psm), 
               '-c', 'tessedit_char_whitelist=ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789{}!_()-']
        out = subprocess.check_output(cmd, stderr=subprocess.DEVNULL, text=True).strip()
        if out:
            results.append(("direct", out))
    except:
        pass
    
    # Try 2: With upscaling
    try:
        scaled = img.resize((img.width * 2, img.height * 2), Image.LANCZOS)
        scaled_path = str(img_path).replace('.png', '_scaled.png')
        scaled.save(scaled_path)
        cmd = ['tesseract', scaled_path, 'stdout', '--psm', str(psm),
               '-c', 'tessedit_char_whitelist=ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789{}!_()-']
        out = subprocess.check_output(cmd, stderr=subprocess.DEVNULL, text=True).strip()
        if out:
            results.append(("scaled", out))
        os.remove(scaled_path)
    except:
        pass
    
    # Try 3: Morphology (erode/dilate)
    try:
        morph = img.filter(ImageFilter.MedianFilter(3))
        morph_path = str(img_path).replace('.png', '_morph.png')
        morph.save(morph_path)
        cmd = ['tesseract', morph_path, 'stdout', '--psm', str(psm),
               '-c', 'tessedit_char_whitelist=ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789{}!_()-']
        out = subprocess.check_output(cmd, stderr=subprocess.DEVNULL, text=True).strip()
        if out:
            results.append(("morph", out))
        os.remove(morph_path)
    except:
        pass
    
    # Try 4: High contrast
    try:
        enhanced = ImageEnhance.Contrast(img).enhance(3.0)
        bright = ImageEnhance.Brightness(enhanced).enhance(1.2)
        hc_path = str(img_path).replace('.png', '_hc.png')
        bright.save(hc_path)
        cmd = ['tesseract', hc_path, 'stdout', '--psm', str(psm),
               '-c', 'tessedit_char_whitelist=ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789{}!_()-']
        out = subprocess.check_output(cmd, stderr=subprocess.DEVNULL, text=True).strip()
        if out:
            results.append(("hc", out))
        os.remove(hc_path)
    except:
        pass
    
    return results

def test_best_images():
    """Test the most promising transformed images with aggressive OCR."""
    base_dir = Path("flag_extraction_tests")
    
    # Best candidates from earlier run
    best_images = [
        "edges.png",
        "inverted_t100.png",
        "swirl_i0.5.png",
        "swirl_i4.5.png",
        "inverted_t50.png",
        "inverted_t150.png",
    ]
    
    all_results = []
    
    for img_name in best_images:
        img_path = base_dir / img_name
        if not img_path.exists():
            continue
        
        print(f"\nProcessing: {img_name}")
        print("-" * 60)
        
        for psm in [6, 7, 8, 11, 13]:
            ocr_results = ocr_aggressive(img_path, psm=psm)
            
            for method, text in ocr_results:
                if text.strip():
                    # Check for flag-like patterns
                    has_flag_marker = any(x in text for x in ['redfix', 'flag', '{', '}', '_up'])
                    
                    print(f"  PSM {psm} [{method:6s}]: {text[:80]}")
                    if has_flag_marker:
                        print(f"     *** POTENTIAL FLAG COMPONENT ***")
                    
                    all_results.append({
                        'image': img_name,
                        'psm': psm,
                        'method': method,
                        'text': text,
                        'has_marker': has_flag_marker
                    })
    
    # Filter for likely flags
    print("\n" + "="*80)
    print("FLAG CANDIDATES:")
    print("="*80)
    
    candidates = [r for r in all_results if r['has_marker']]
    
    if candidates:
        for i, c in enumerate(candidates[:15], 1):
            print(f"\n{i}. {c['image']} (PSM {c['psm']}, {c['method']})")
            print(f"   {c['text']}")
    else:
        print("No clear flag markers found. Top results:")
        by_len = sorted(all_results, key=lambda x: len([ch for ch in x['text'] if ch.isalnum()]), reverse=True)
        for i, c in enumerate(by_len[:10], 1):
            print(f"\n{i}. {c['image']} (PSM {c['psm']}, {c['method']})")
            print(f"   {c['text']}")

if __name__ == "__main__":
    print("Running aggressive OCR on best candidate images...")
    test_best_images()
    print("\nDone!")
