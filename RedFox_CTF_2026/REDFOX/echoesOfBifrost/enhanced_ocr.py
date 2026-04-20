#!/opt/homebrew/bin/python3
"""
Enhanced OCR attempts on the band images
Try multiple image processing techniques
"""
from PIL import Image, ImageEnhance, ImageFilter, ImageOps
import pytesseract
import numpy as np
from scipy import ndimage

def enhance_and_ocr(input_file, output_prefix):
    """Try various enhancements and OCR"""
    
    img = Image.open(input_file)
    img_array = np.array(img)
    
    print(f"\n=== Processing {input_file} ===")
    results = []
    
    # 1. Direct OCR
    try:
        text = pytesseract.image_to_string(img)
        results.append(("Original", text.strip()))
    except:
        pass
    
    # 2. Contrast
    for contrast_level in [1.5, 2.0, 3.0]:
        enhanced = ImageEnhance.Contrast(img).enhance(contrast_level)
        try:
            text = pytesseract.image_to_string(enhanced)
            if text.strip():
                results.append((f"Contrast {contrast_level}", text.strip()))
        except:
            pass
    
    # 3. Brightness
    for brightness_level in [0.5, 1.5, 2.0]:
        enhanced = ImageEnhance.Brightness(img).enhance(brightness_level)
        try:
            text = pytesseract.image_to_string(enhanced)
            if text.strip():
                results.append((f"Brightness {brightness_level}", text.strip()))
        except:
            pass
    
    # 4. Sharpen
    for sharpness in [1.5, 2.0]:
        enhanced = ImageEnhance.Sharpness(img).enhance(sharpness)
        try:
            text = pytesseract.image_to_string(enhanced)
            if text.strip():
                results.append((f"Sharpness {sharpness}", text.strip()))
        except:
            pass
    
    # 5. Invert
    inverted = ImageOps.invert(img.convert('L'))
    try:
        text = pytesseract.image_to_string(inverted)
        if text.strip():
            results.append(("Inverted", text.strip()))
    except:
        pass
    
    # 6. Filters
    filters_to_try = [
        ("SHARPEN", ImageFilter.SHARPEN),
        ("DETAIL", ImageFilter.DETAIL),
        ("EDGE_ENHANCE", ImageFilter.EDGE_ENHANCE),
        ("EDGE_ENHANCE_MORE", ImageFilter.EDGE_ENHANCE_MORE),
    ]
    
    for fname, filt in filters_to_try:
        try:
            filtered = img.filter(filt)
            text = pytesseract.image_to_string(filtered)
            if text.strip():
                results.append((f"Filter {fname}", text.strip()))
        except:
            pass
    
    # 7. Threshold
    # Convert to binary at different thresholds
    img_arr = np.array(img)
    for threshold_val in [50, 100, 150, 200]:
        binary = (img_arr > threshold_val).astype(np.uint8) * 255
        binary_img = Image.fromarray(binary)
        try:
            text = pytesseract.image_to_string(binary_img)
            if text.strip():
                results.append((f"Threshold {threshold_val}", text.strip()))
        except:
            pass
    
    # 8. Dilation/Erosion to clean up
    try:
        binary = (img_arr > np.median(img_arr)).astype(np.uint8) * 255
        dilated = ndimage.binary_dilation(binary, iterations=2).astype(np.uint8) * 255
        dilated_img = Image.fromarray(dilated)
        text = pytesseract.image_to_string(dilated_img)
        if text.strip():
            results.append(("Dilated", text.strip()))
    except:
        pass
    
    # Print results
    for method, text in results:
        print(f"\n{method}:")
        print(f"  {text[:300]}")
        
        # Save promising results
        if len(text) > 50 and text.count(' ') > 5:
            with open(f'{output_prefix}_{method.replace(" ", "_")}.txt', 'w') as f:
                f.write(text)
            print(f"  [Saved to file]")

if __name__ == '__main__':
    # Try on the band images since those showed promise
    for img_name in ['decode_band_scaled.png', 'decode_band_inverted.png']:
        if __import__('os').path.exists(img_name):
            enhance_and_ocr(img_name, f'ocr_result_{img_name.split(".")[0]}')
