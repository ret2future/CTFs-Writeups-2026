#!/usr/bin/env python3
"""
Enhanced OCR and analysis of multi-mode SSTV images
"""
import subprocess
from PIL import Image, ImageEnhance
import numpy as np

def enhance_and_ocr():
    """Upscale and OCR the images from each mode"""
    
    base_images = [
        ('sstv_robot36_eq.png', 'Robot36 Equalized'),
        ('sstv_scottiem1_eq.png', 'Scottie M1 Equalized'),
        ('sstv_martinm1_eq.png', 'Martin M1 Equalized'),
    ]
    
    for img_file, description in base_images:
        print(f"\n{'='*60}")
        print(f"Processing: {description}")
        print(f"{'='*60}")
        
        try:
            img = Image.open(img_file)
        except FileNotFoundError:
            print(f"File not found: {img_file}")
            continue
        
        img_arr = np.array(img)
        print(f"Image shape: {img_arr.shape}")
        print(f"Brightness: {img_arr.min()}-{img_arr.max()}, mean {img_arr.mean():.1f}")
        
        # Try multiple enhancements
        for upscale_factor in [2, 4]:
            new_size = (img.width * upscale_factor, img.height * upscale_factor)
            upscaled = img.resize(new_size, Image.LANCZOS)
            upscaled_file = f'{img_file[:-4]}_x{upscale_factor}.png'
            upscaled.save(upscaled_file)
            
            # Try different PSM modes
            for psm in [3, 6, 8, 11, 13]:
                result = subprocess.run(
                    ['tesseract', upscaled_file, 'stdout', f'--psm', str(psm)],
                    capture_output=True,
                    text=True,
                    timeout=10
                )
                
                text = result.stdout.strip()
                if text and len(text) > 5:
                    print(f"\n  x{upscale_factor} PSM {psm}: Found text ({len(text)} chars)")
                    lines = text.split('\n')
                    for line in lines[:10]:
                        print(f"    {line[:80]}")
                    if len(lines) > 10:
                        print(f"    ... ({len(lines)} lines total)")
                    break
        
        # Also try contrast adjustment
        print(f"\n  Testing contrast enhancement...")
        for enhancement_factor in [2.0, 3.0, 5.0]:
            enhancer = ImageEnhance.Contrast(img)
            enhanced = enhancer.enhance(enhancement_factor)
            enhanced_file = f'{img_file[:-4]}_contrast{enhancement_factor}.png'
            enhanced.save(enhanced_file)
            
            result = subprocess.run(
                ['tesseract', enhanced_file, 'stdout', '--psm', '6'],
                capture_output=True,
                text=True,
                timeout=5
            )
            
            text = result.stdout.strip()
            if text and len(text) > 5:
                print(f"  Contrast {enhancement_factor}: Found text ({len(text)} chars)")
                print(f"    {text[:200]}")
                break

if __name__ == '__main__':
    enhance_and_ocr()
