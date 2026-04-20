#!/usr/bin/env python3
"""
Use OCR to extract text from spectrogram images
"""
import pytesseract
from PIL import Image, ImageEnhance
import os
import glob

def try_ocr_on_images():
    """Try OCR on all PNG images we generated"""
    
    # Find all PNG files
    png_files = sorted(glob.glob('*.png'))
    
    print(f"Found {len(png_files)} PNG files")
    
    # Priority: check the spectrogram files first
    priority_patterns = [
        'spec_*_sstv.png',  # Zoomed SSTV spectrograms
        'crop_*.png',       # Cropped regions
        'sstv_as_video*.png',  # Video stream interpretation
        'spectrogram_variant*.png',
    ]
    
    files_to_check = []
    for pattern in priority_patterns:
        files_to_check.extend(sorted(glob.glob(pattern)))
    
    print(f"\nTrying OCR on {len(files_to_check)} priority files:")
    
    for filename in files_to_check[:10]:  # Limit to first 10 to save time
        print(f"\n--- Processing {filename} ---")
        
        try:
            img = Image.open(filename)
            print(f"  Image size: {img.size}")
            
            # Try OCR as-is
            text = pytesseract.image_to_string(img)
            if text.strip():
                print(f"  OCR result:\n{text[:500]}")
            
            # Try with enhancements if image is small
            if img.size[0] < 2000 and img.size[1] < 500:
                # Upscale
                scale_factor = 4
                img_scaled = img.resize((img.size[0] * scale_factor, img.size[1] * scale_factor), Image.Resampling.NEAREST)
                text_scaled = pytesseract.image_to_string(img_scaled)
                if text_scaled.strip() and text_scaled != text:
                    print(f"  OCR on upscaled:\n{text_scaled[:500]}")
                
                # Try with contrast enhancement
                enhancer = ImageEnhance.Contrast(img_scaled)
                img_enhanced = enhancer.enhance(2.0)
                text_enhanced = pytesseract.image_to_string(img_enhanced)
                if text_enhanced.strip() and text_enhanced != text_scaled:
                    print(f"  OCR on upscaled+contrast:\n{text_enhanced[:500]}")
        
        except Exception as e:
            print(f"  Error: {e}")

def try_imagemagick_convert():
    """Try using ImageMagick to process images"""
    print("\n=== Trying ImageMagick conversion ===")
    
    # Try edge detection or other filters
    img_files = sorted(glob.glob('crop_*.png'))
    
    if img_files:
        for img_file in img_files[:3]:
            print(f"\nProcessing {img_file} with PIL filters:")
            img = Image.open(img_file)
            
            # Try various PIL enhancements
            # Edge enhance
            from PIL import ImageFilter
            img_edges = img.filter(ImageFilter.FIND_EDGES)
            img_edges.save(f'{img_file}_edges.png')
            
            # Sharpen with strong contrast
            img_sharp = img.filter(ImageFilter.SHARPEN)
            img_sharp = ImageEnhance.Contrast(img_sharp).enhance(3.0)
            img_sharp.save(f'{img_file}_sharp.png')
            
            # Posterize to reduce colors (might help OCR)
            img_post = ImageEnhance.Contrast(img).enhance(2.0)
            img_post = Image.Image.quantize(img_post, colors=2)  # B&W
            img_post.save(f'{img_file}_bw.png')
            
            print(f"  Saved enhanced versions")
            
            # Try OCR on enhanced
            try:
                text_bw = pytesseract.image_to_string(img_post)
                if text_bw.strip():
                    print(f"  OCR from B&W version: {text_bw[:200]}")
            except:
                pass

if __name__ == '__main__':
    try_ocr_on_images()
    try_imagemagick_convert()
