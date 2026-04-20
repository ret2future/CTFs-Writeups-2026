#!/usr/bin/env python3
"""
Extract text from the improved decoded SSTV images
"""
from PIL import Image, ImageEnhance
import pytesseract
import subprocess
import numpy as np

def ocr_and_analyze():
    """Try OCR on the improved decoded images"""
    
    images_to_try = [
        'sstv_direct_freqmap_320x240_down114.png',
        'sstv_direct_freqmap_320x240_down114_inv.png',
        'sstv_improved_sync_decode.png',
        'sstv_improved_sync_decode_inv.png',
    ]
    
    for img_file in images_to_try:
        print(f"\n=== {img_file} ===")
        
        try:
            img = Image.open(img_file)
            print(f"Size: {img.size}")
            
            # Basic OCR
            text = pytesseract.image_to_string(img)
            if text.strip():
                print("OCR Result:")
                print(text[:500])
                
                # Save to file
                with open(f'{img_file[:-4]}.txt', 'w') as f:
                    f.write(text)
            else:
                print("[No text detected]")
            
            # Try with preprocessing
            # High contrast
            img_contrast = ImageEnhance.Contrast(img).enhance(2.0)
            text_c = pytesseract.image_to_string(img_contrast)
            if text_c.strip() and text_c != text:
                print("OCR with contrast:")
                print(text_c[:300])
            
            # Upscale first
            img_scaled = img.resize((img.size[0] * 2, img.size[1] * 2), Image.Resampling.LANCZOS)
            text_scaled = pytesseract.image_to_string(img_scaled)
            if text_scaled.strip() and text_scaled != text:
                print("OCR on 2x scaled:")
                print(text_scaled[:300])
        
        except Exception as e:
            print(f"Error: {e}")

def analyze_image_patterns():
    """Look for patterns in the images"""
    
    for img_file in ['sstv_direct_freqmap_320x240_down114.png', 'sstv_improved_sync_decode.png']:
        print(f"\n=== Pattern Analysis: {img_file} ===")
        
        img = Image.open(img_file)
        img_arr = np.array(img)
        
        # Histogram
        hist, bins = np.histogram(img_arr, bins=256)
        peak_val = bins[np.argmax(hist)]
        print(f"Histogram peak at value: {peak_val}")
        print(f"Dark pixels (<128): {np.sum(img_arr < 128)}")
        print(f"Light pixels (>=128): {np.sum(img_arr >= 128)}")
        
        # Row analysis
        row_means = np.mean(img_arr, axis=1)
        row_variation = np.std(row_means)
        print(f"Row brightness variation: {row_variation:.2f}")
        
        # Column analysis
        col_means = np.mean(img_arr, axis=0)
        col_variation = np.std(col_means)
        print(f"Column brightness variation: {col_variation:.2f}")
        
        # Look for text patterns (should have high local contrast)
        if col_variation > 10:
            print("Detected significant column variation - could contain text")
        
        # Try edge detection
        from PIL import ImageFilter
        edges = img.filter(ImageFilter.FIND_EDGES)
        edge_text = pytesseract.image_to_string(edges)
        if edge_text.strip():
            print("OCR on edge-detected image:")
            print(edge_text[:200])

if __name__ == '__main__':
    ocr_and_analyze()
    analyze_image_patterns()
