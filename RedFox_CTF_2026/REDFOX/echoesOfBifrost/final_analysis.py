#!/usr/bin/env python3
"""
Final attempt: Smartly extract and visualize the spectrogram data
Try to find text or patterns in the wide image
"""
import numpy as np
from PIL import Image, ImageEnhance, ImageFilter
import pytesseract

def analyze_wide_image():
    """Analyze the sstv_as_video images"""
    
    try:
        img = Image.open('sstv_as_video.png')
        img_array = np.array(img)
        
        print(f"Wide image shape: {img_array.shape}")
        
        # The image is 137260 wide x 20 tall
        # Maybe text is encoded horizontally - let's try to read it like a barcode
        
        # Get horizontal slices and look for the one with most variation (likely the text)
        row_variations = []
        for row_idx in range(img_array.shape[0]):
            row = img_array[row_idx, :]
            variation = np.std(row)
            row_variations.append(variation)
        
        print(f"Row variations: {row_variations}")
        
        # Find the rows with highest variation
        best_rows = np.argsort(row_variations)[::-1]
        print(f"Best rows: {best_rows[:5]}")
        
        # Extract text from best row
        best_row_idx = best_rows[0]
        best_row = img_array[best_row_idx, :]
        
        # Maybe it's binary - look for clear 0s and 1s
        threshold = (np.min(best_row) + np.max(best_row)) / 2
        binary_row = (best_row > threshold).astype(int)
        
        # Convert to string
        binary_str = ''.join(str(b) for b in binary_row)
        print(f"Binary from best row (first 200): {binary_str[:200]}")
        
        # Try to interpret as ASCII
        #Split into 8-bit chunks
        for chunk_size in [8, 7, 6]:
            chars = []
            for i in range(0, len(binary_row) - chunk_size, chunk_size):
                chunk = binary_row[i:i+chunk_size]
                val = int(''.join(str(c) for c in chunk), 2)
                if 32 <= val <= 126:
                    chars.append(chr(val))
                else:
                    chars.append('?')
            
            text = ''.join(chars[:200])
            if '?' not in text[:50]:
                print(f"Possible ASCII ({chunk_size}-bit): {text}")
        
        # Maybe the text is encoded in the pixel positions where value changes
        # Find where pixels transition
        diffs = np.abs(np.diff(best_row.astype(float)))
        change_points = np.where(diffs > np.std(diffs) * 2)[0]
        print(f"\nSignificant changes at: {len(change_points)} points")
        print(f"First 50 change points: {change_points[:50]}")
        
        # Try to extract segments
        if len(change_points) > 10:
            segments = np.diff(change_points)
            print(f"Segment sizes (showing first 50): {segments[:50]}")
            
            # Maybe each segment represents a character
            segment_sizes_normalized = (segments > np.median(segments)).astype(int)
            text_from_segments = ''.join(str(b) for b in segment_sizes_normalized)
            print(f"Segments as binary: {text_from_segments[:200]}")
    
    except Exception as e:
        print(f"Error in analyze_wide_image: {e}")

def smart_image_enhance():
    """Try smart image enhancement for OCR"""
    
    # Create a downsampled version that OCR can handle
    img = Image.open('sstv_as_video.png')
    
    # Downsample significantly  
    small_width = 800  # Max tesseract can handle
    aspect_ratio = img.size[1] / img.size[0]
    small_height = int(small_width * aspect_ratio)
    small_height = max(small_height, 20)
    
    print(f"\nDownsampling to {small_width}x{small_height}")
    
    img_small = img.resize((small_width, small_height), Image.Resampling.LANCZOS)
    img_small.save('sstv_small_for_ocr.png')
    
    # Try OCR
    try:
        text = pytesseract.image_to_string(img_small)
        print("OCR on downsampled image:")
        print(text[:1000])
    except Exception as e:
        print(f"OCR error: {e}")
    
    # Try with high contrast
    img_contrast = ImageEnhance.Contrast(img_small).enhance(3.0)
    img_contrast.save('sstv_small_contrast.png')
    
    try:
        text_contrast = pytesseract.image_to_string(img_contrast)
        if text_contrast.strip():
            print("\nOCR on high-contrast:")
            print(text_contrast[:1000])
    except Exception as e:
        print(f"OCR error: {e}")
    
    # Try posterize (convert to few colors/levels)
    img_levels = []
    for levels in [4, 8, 16]:
        img_post = img_small.quantize(colors=levels)
        img_post.save(f'sstv_small_levels{levels}.png')
        img_levels.append(img_post)
        
        try:
            text_post = pytesseract.image_to_string(img_post)
            if text_post.strip() and len(text_post) > 10:
                print(f"\nOCR on {levels}-level posterization:")
                print(text_post[:500])
                break
        except:
            pass

if __name__ == '__main__':
    analyze_wide_image()
    smart_image_enhance()
