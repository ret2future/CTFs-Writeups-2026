#!/usr/bin/env python3
"""
OCR on the Robot 36 decoded SSTV images
"""
from PIL import Image, ImageEnhance
import pytesseract
import numpy as np

def process_robot36_images():
    """Process and OCR the decoded Robot 36 images"""
    
    for filename in ['sstv_robot36_decoded.png', 'sstv_robot36_decoded_inv.png']:
        print(f"\n=== {filename} ===")
        
        img = Image.open(filename)
        print(f"Size: {img.size}")
        
        img_array = np.array(img)
        print(f"Value range: {np.min(img_array)} to {np.max(img_array)}")
        print(f"Unique values: {len(np.unique(img_array))}")
        
        # Upscale first for better OCR
        img_scaled = img.resize((img.size[0] * 2, img.size[1] * 4), Image.Resampling.NEAREST)
        img_scaled.save(f'{filename[:-4]}_scaled.png')
        
        # Try OCR
        print("OCR on original:")
        text1 = pytesseract.image_to_string(img)
        print(f"  {text1[:300]}")
        
        print("OCR on scaled:")
        text2 = pytesseract.image_to_string(img_scaled)
        print(f"  {text2[:300]}")
        
        # Try with contrast enhancement
        print("OCR with contrast:")
        img_contrast = ImageEnhance.Contrast(img_scaled).enhance(3.0)
        text3 = pytesseract.image_to_string(img_contrast)
        print(f"  {text3[:300]}")
        
        # Invert contrast if needed
         print("OCR with inverted contrast:")
        img_inv_contrast = ImageEnhance.Contrast(img_scaled).enhance(-2.0)
        text4 = pytesseract.image_to_string(img_inv_contrast)
        print(f"  {text4[:300]}")
        
        # Save best results
        for i, text in enumerate([text1, text2, text3], 1):
            if text.strip():
                with open(f'{filename[:-4]}_ocr{i}.txt', 'w') as f:
                    f.write(text)

if __name__ == '__main__':
    process_robot36_images()
