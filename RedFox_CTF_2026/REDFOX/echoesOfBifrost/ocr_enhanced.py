#!/usr/bin/env python3
"""
OCR the enhanced images to extract text
"""
import subprocess
import sys
from PIL import Image
import numpy as np

def ocr_image(img_path, description=""):
    """Run OCR on image"""
    result = subprocess.run(
        ['tesseract', img_path, 'stdout', '--psm', '6'],
        capture_output=True,
        text=True,
        timeout=10
    )
    
    text = result.stdout.strip()
    print(f"\n{'='*60}")
    print(f"OCR Result for {description}")
    print(f"{'='*60}")
    print(f"Raw output ({len(text)} chars):")
    print(text if text else "[NO TEXT DETECTED]")
    
    if text and len(text) > 0:
        # Check confidence
        print(f"\nFirst 200 chars: {text[:200]}")
    
    return text

def analyze_equalized_image():
    """Analyze the histogram-equalized image in detail"""
    
    img = Image.open('sstv_equalized.png')
    img_arr = np.array(img)
    
    print("\n=== Equalized Image Analysis ===")
    print(f"Shape: {img_arr.shape}")
    print(f"Range: {img_arr.min()} to {img_arr.max()}")
    print(f"Mean: {img_arr.mean():.2f}")
    
    # Try different preprocessing before OCR
    for thresh in [128, 100, 150]:
        binary = (img_arr < thresh).astype(np.uint8) * 255
        binary_img = Image.fromarray(binary)
        binary_file = f'sstv_binary_{thresh}.png'
        binary_img.save(binary_file)
        
        dark_count = np.sum(img_arr < thresh)
        print(f"  Binary threshold {thresh}: {dark_count} dark pixels")

def upscale_and_ocr():
    """Upscale images for better OCR"""
    
    for factor in [2, 4, 8]:
        # Equalized
        img = Image.open('sstv_equalized.png')
        new_size = (img.width * factor, img.height * factor)
        upscaled = img.resize(new_size, Image.LANCZOS)
        upscaled_file = f'sstv_equalized_x{factor}.png'
        upscaled.save(upscaled_file)
        
        # Also try contrast version
        img2 = Image.open('sstv_contrast_3.0.png')
        upscaled2 = img2.resize(new_size, Image.LANCZOS)
        upscaled2_file = f'sstv_contrast_3.0_x{factor}.png'
        upscaled2.save(upscaled2_file)

if __name__ == '__main__':
    # First, upscale images
    print("Upscaling images for better OCR...")
    upscale_and_ocr()
    
    # Try OCR on various versions
    images_to_try = [
        ('sstv_equalized.png', 'Histogram Equalized'),
        ('sstv_contrast_3.0.png', 'Contrast Enhanced (3.0x)'),
        ('sstv_binary_100.png', 'Binary Threshold 100'),
        ('sstv_binary_128.png', 'Binary Threshold 128'),
        ('sstv_binary_150.png', 'Binary Threshold 150'),
        ('sstv_equalized_x2.png', 'Equalized 2x Upscaled'),
        ('sstv_equalized_x4.png', 'Equalized 4x Upscaled'),
        ('sstv_contrast_3.0_x2.png', 'Contrast 3.0x 2x Upscaled'),
        ('sstv_contrast_3.0_x4.png', 'Contrast 3.0x 4x Upscaled'),
    ]
    
    results = {}
    for img_path, description in images_to_try:
        try:
            text = ocr_image(img_path, description)
            results[description] = text
        except FileNotFoundError:
            print(f"File not found: {img_path}")
        except subprocess.TimeoutExpired:
            print(f"OCR timeout for {img_path}")
        except Exception as e:
            print(f"Error processing {img_path}: {e}")
    
    # Summary
    print("\n" + "="*60)
    print("SUMMARY")
    print("="*60)
    text_found = False
    for desc, text in results.items():
        if text and len(text.strip()) > 0:
            text_found = True
            print(f"\n✓ {desc}:")
            print(f"  {text[:200]}")
    
    if not text_found:
        print("No text detected in any version. Trying alternative approach...")
        analyze_equalized_image()
