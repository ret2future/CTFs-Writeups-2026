#!/usr/bin/env python3
"""
Process the inverted frequency images with upscaling and OCR
"""
import subprocess
from PIL import Image, ImageOps, ImageEnhance
import numpy as np

def upscale_and_ocr():
    """Upscale the inverted images and run OCR"""
    
    base_images = [
        'sstv_inverted_freq_320x240.png',
        'sstv_inverted_freq_equalized.png',
        'sstv_inverted_freq_contrast.png'
    ]
    
    for base_img in base_images:
        try:
            img = Image.open(base_img)
        except FileNotFoundError:
            print(f"File not found: {base_img}")
            continue
        
        name = base_img.replace('.png', '')
        
        # Try different upscale factors
        for factor in [2, 4, 8]:
            new_size = (img.width * factor, img.height * factor)
            upscaled = img.resize(new_size, Image.LANCZOS)
            upscaled_file = f'{name}_x{factor}.png'
            upscaled.save(upscaled_file)
            
            # Run OCR
            print(f"\nTesting {upscaled_file}...")
            result = subprocess.run(
                ['tesseract', upscaled_file, 'stdout', '--psm', '3'],
                capture_output=True,
                text=True,
                timeout=10
            )
            
            text = result.stdout.strip()
            if text and len(text) > 3:
                print(f"✓ Found text ({len(text)} chars):")
                print(f"  {text[:300]}")
            else:
                # Try alternative PSM modes
                for psm in [6, 11, 13]:
                    result = subprocess.run(
                        ['tesseract', upscaled_file, 'stdout', f'--psm', str(psm)],
                        capture_output=True,
                        text=True,
                        timeout=5
                    )
                    text = result.stdout.strip()
                    if text and len(text) > 3:
                        print(f"✓ Found with PSM {psm} ({len(text)} chars):")
                        print(f"  {text[:300]}")
                        break

def analyze_image_content():
    """Analyze what's actually in the inverted image"""
    
    img = Image.open('sstv_inverted_freq_equalized.png')
    img_arr = np.array(img)
    
    print(f"\n{'='*60}")
    print("Image Analysis: sstv_inverted_freq_equalized.png")
    print(f"{'='*60}")
    print(f"Shape: {img_arr.shape}")
    print(f"Dtype: {img_arr.dtype}")
    print(f"Range: {img_arr.min()} to {img_arr.max()}")
    print(f"Mean: {img_arr.mean():.2f}")
    print(f"Median: {np.median(img_arr):.2f}")
    print(f"Std: {img_arr.std():.2f}")
    
    # Histogram peaks
    hist, _ = np.histogram(img_arr.flatten(), bins=256, range=(0, 256))
    peaks = [(i, hist[i]) for i in range(256) if hist[i] > 100]
    print(f"\nBrightness peaks (>100 pixels):")
    for val, count in sorted(peaks, key=lambda x: x[1], reverse=True)[:5]:
        print(f"  {val}: {count} pixels ({100*count/img_arr.size:.1f}%)")
    
    # Check for text-like patterns
    print(f"\nDark regions (< 100):")
    dark_count = np.sum(img_arr < 100)
    print(f"  {dark_count} pixels ({100*dark_count/img_arr.size:.1f}%)")
    
    # Look for vertical lines (characters)
    col_sums = np.sum(img_arr < 128, axis=0)
    active_cols = np.where(col_sums > 5)[0]
    if len(active_cols) > 0:
        print(f"\nColumns with dark pixels: {active_cols[0]} to {active_cols[-1]}")

if __name__ == '__main__':
    analyze_image_content()
    upscale_and_ocr()
