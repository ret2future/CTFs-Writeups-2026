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
        
        try:
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
            if text1.strip():
                print(f"  {text1[:300]}")
            else:
                print("  [no text detected]")
            
            print("OCR on scaled:")
            text2 = pytesseract.image_to_string(img_scaled)
            if text2.strip():
                print(f"  {text2[:300]}")
            else:
                print("  [no text detected]")
            
            # Try with contrast enhancement
            print("OCR with contrast:")
            img_contrast = ImageEnhance.Contrast(img_scaled).enhance(3.0)
            text3 = pytesseract.image_to_string(img_contrast)
            if text3.strip():
                print(f"  {text3[:300]}")
            else:
                print("  [no text detected]")
            
            # Save best results
            for i, text in enumerate([text1, text2, text3], 1):
                if text.strip():
                    with open(f'{filename[:-4]}_ocr{i}.txt', 'w') as f:
                        f.write(text)
                    print(f"  Saved to {filename[:-4]}_ocr{i}.txt")
        
        except Exception as e:
            print(f"Error processing {filename}: {e}")

if __name__ == '__main__':
    process_robot36_images()
