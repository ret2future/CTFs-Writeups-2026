#!/usr/bin/env python3
"""
Analyze and OCR the decoded images
"""
from PIL import Image
import pytesseract
import numpy as np
import glob

def analyze_and_ocr():
    """Analyze and try OCR on decode images"""
    
    decode_images = sorted(glob.glob('decode*.png'))
    
    print(f"Found {len(decode_images)} decode images\n")
    
    for img_file in decode_images:
        print(f"=== {img_file} ===")
        
        try:
            img = Image.open(img_file)
            print(f"Size: {img.size}")
            
            # Analyze pixel distribution
            img_array = np.array(img)
            if len(img_array.shape) == 2:  # Grayscale
                unique_vals = len(np.unique(img_array))
                print(f"Unique pixel values: {unique_vals}")
                print(f"Value range: {np.min(img_array)} to {np.max(img_array)}")
                print(f"Mean: {np.mean(img_array):.1f}, Std: {np.std(img_array):.1f}")
                
                # Try to see if there's readable content
                # Check for edge patterns
                if img.size[0] > 200 and img.size[1] > 100:
                    # Try OCR
                    print("Attempting OCR...")
                    try:
                        text = pytesseract.image_to_string(img)
                        text_clean = text[:500].strip()
                        if text_clean:
                            print(f"OCR result: {text_clean[:200]}")
                    except Exception as e:
                        print(f"OCR failed: {e}")
                
                # Try with contrast enhancement
                from PIL import ImageEnhance
                if np.std(img_array) > 20:  # Has some variation
                    enhancer = ImageEnhance.Contrast(img)
                    img_enhanced = enhancer.enhance(2.0)
                    print("Trying OCR on enhanced image...")
                    try:
                        text_enh = pytesseract.image_to_string(img_enhanced)
                        if text_enh.strip():
                            print(f"Enhanced OCR: {text_enh[:200]}")
                    except:
                        pass
        
        except Exception as e:
            print(f"Error: {e}")
        
        print()

if __name__ == '__main__':
    analyze_and_ocr()
