#!/usr/bin/env python3
"""
Try different threshold levels and contrast adjustments to reveal hidden content
"""
import numpy as np
from PIL import Image, ImageEnhance

def analyze_with_different_thresholds():
    """Try extracting content at different brightness thresholds"""
    
    img_file = 'sstv_improved_sync_decode.png'
    img = Image.open(img_file)
    img_arr = np.array(img, dtype=np.uint8)
    
    print(f"Image range: {img_arr.min()} to {img_arr.max()}")
    print(f"Mean brightness: {img_arr.mean():.2f}")
    print(f"Std deviation: {img_arr.std():.2f}")
    
    # Histogram analysis
    print("\n=== Brightness Distribution ===")
    hist, bins = np.histogram(img_arr.flatten(), bins=256, range=(0, 256))
    
    # Find peaks
    peaks = []
    for i in range(1, 255):
        if hist[i] > hist[i-1] and hist[i] > hist[i+1]:
            if hist[i] > 100:  # Significant peak
                peaks.append((i, hist[i]))
    
    for val, count in sorted(peaks, key=lambda x: x[1], reverse=True)[:10]:
        print(f"  Peak at {val}: {count} pixels ({100*count/img_arr.size:.1f}%)")
    
    # Print value ranges
    for threshold in [100, 150, 200, 220, 240]:
        dark_pixels = np.sum(img_arr < threshold)
        print(f"  Pixels < {threshold}: {dark_pixels} ({100*dark_pixels/img_arr.size:.1f}%)")
    
    # Try inverting
    print("\n=== Inverted Image Analysis ===")
    inv_img_arr = 255 - img_arr
    print(f"Inverted range: {inv_img_arr.min()} to {inv_img_arr.max()}")
    
    # Try different contrast enhancements
    print("\n=== Testing Contrast Adjustments ===")
    
    for contrast_factor in [1.5, 2.0, 3.0, 5.0]:
        enhancer = ImageEnhance.Contrast(img)
        enhanced = enhancer.enhance(contrast_factor)
        
        out_file = f'sstv_contrast_{contrast_factor}.png'
        enhanced.save(out_file)
        
        enhanced_arr = np.array(enhanced, dtype=np.uint8)
        dark = np.sum(enhanced_arr < 128)
        print(f"  Contrast {contrast_factor}: saved to {out_file}, dark pixels: {dark}")
    
    # Try equalization
    print("\n=== Histogram Equalization ===")
    from PIL import ImageOps
    eq_img = ImageOps.equalize(img)
    eq_img.save('sstv_equalized.png')
    
    eq_arr = np.array(eq_img, dtype=np.uint8)
    print(f"Equalized range: {eq_arr.min()} to {eq_arr.max()}")
    print(f"Equalized mean: {eq_arr.mean():.2f}")
    
    # Try adaptive histogram equalization
    print("\n=== Examining Actual Data Values ===")
    unique_vals = np.unique(img_arr)
    print(f"Unique values in image: {len(unique_vals)}")
    print(f"Values: {unique_vals[:20]}...{unique_vals[-20:]}")
    
    # Check if there's actual variation in rows
    print("\n=== Per-Row Value Distribution ===")
    for row_idx in [0, 50, 100, 150, 200, 239]:
        row = img_arr[row_idx]
        print(f"  Row {row_idx}: min={row.min()}, max={row.max()}, mean={row.mean():.1f}, unique={len(np.unique(row))}")
    
    # Look for the dark regions specifically
    print("\n=== Dark Pixel Locations ===")
    dark_mask = img_arr < 200
    dark_rows = np.any(dark_mask, axis=1)
    dark_row_indices = np.where(dark_rows)[0]
    
    if len(dark_row_indices) > 0:
        print(f"Rows with pixels < 200: {len(dark_row_indices)}")
        print(f"Row ranges: {dark_row_indices[0]} to {dark_row_indices[-1]}")
        print(f"Gaps between: {np.diff(dark_row_indices)[:20]}")
        
        # Crop to region with actual content
        min_row = dark_row_indices[0]
        max_row = dark_row_indices[-1] + 1
        
        crop = img_arr[min_row:max_row, :]
        print(f"\nCrop shape: {crop.shape}")
        crop_img = Image.fromarray(crop)
        crop_img.save('sstv_cropped_content.png')
        print("Saved cropped region to sstv_cropped_content.png")

if __name__ == '__main__':
    analyze_with_different_thresholds()
