#!/usr/bin/env python3
"""
Visualize SSTV images as ASCII art and analyze patterns
"""
import numpy as np
from PIL import Image

def image_to_ascii(img_path, width=80, height=30, threshold=128):
    """Convert image to ASCII art"""
    
    img = Image.open(img_path)
    # Resize to desired dimensions
    img_resized = img.resize((width, height))
    img_arr = np.array(img_resized)
    
    # ASCII characters from darkest to lightest
    ascii_chars = '@%#*+=-:. '
    
    ascii_art = []
    for row in img_arr:
        line = ''
        for pixel in row:
            # Map pixel value to ASCII character
            char_idx = int((pixel / 255) * (len(ascii_chars) - 1))
            line += ascii_chars[char_idx]
        ascii_art.append(line)
    
    return '\n'.join(ascii_art)

def print_pixel_patterns(img_path, sample_size=10):
    """Print patterns of pixel values"""
    
    img = Image.open(img_path)
    img_arr = np.array(img)
    
    print(f"Image: {img_path}")
    print(f"Shape: {img_arr.shape}")
    print(f"Range: {img_arr.min()}-{img_arr.max()}")
    
    # Sample rows
    print(f"\nSample rows (pixel patterns):")
    for row_idx in np.linspace(0, img_arr.shape[0]-1, sample_size).astype(int):
        if row_idx < img_arr.shape[0]:
            row = img_arr[row_idx]
            # Visualize as bars
            bar = ''.join(['█' if p > 200 else '▓' if p > 100 else '░' for p in row[::10]])
            vals = f"min={row.min():3d} max={row.max():3d} mean={row.mean():5.1f}"
            print(f"  Row {row_idx:3d}: {bar} | {vals}")

def find_text_regions(img_path):
    """Find regions with concentrated dark pixels that might contain text"""
    
    img = Image.open(img_path)
    img_arr = np.array(img)
    
    # Binarize
    threshold = 128
    binary = (img_arr < threshold).astype(int)
    
    # Find rows with significant dark content
    row_sums = np.sum(binary, axis=1)
    col_sums = np.sum(binary, axis=0)
    
    # Find active regions
    active_rows = np.where(row_sums > np.mean(row_sums) * 0.5)[0]
    active_cols = np.where(col_sums > np.mean(col_sums) * 0.5)[0]
    
    if len(active_rows) > 0 and len(active_cols) > 0:
        min_row, max_row = active_rows[0], active_rows[-1]
        min_col, max_col = active_cols[0], active_cols[-1]
        
        print(f"\nActive region:")
        print(f"  Rows: {min_row} to {max_row} ({max_row-min_row+1} pixels)")
        print(f"  Cols: {min_col} to {max_col} ({max_col-min_col+1} pixels)")
        
        # Extract and save
        crop = img_arr[min_row:max_row+1, min_col:max_col+1]
        crop_img = Image.fromarray(crop)
        crop_file = f'{img_path[:-4]}_crop.png'
        crop_img.save(crop_file)
        print(f"  Saved crop to {crop_file}")
        
        return crop

if __name__ == '__main__':
    images = [
        'sstv_robot36_eq.png',
        'sstv_scottiem1_eq.png', 
        'sstv_martinm1_eq.png',
    ]
    
    for img_path in images:
        try:
            print(f"\n{'='*70}")
            print(f"Processing: {img_path}")
            print(f"{'='*70}")
            
            # Pattern analysis
            print_pixel_patterns(img_path, sample_size=8)
            
            # Find text regions
            find_text_regions(img_path)
            
            # ASCII art (smaller dimensions)
            print(f"\nASCII Art Visualization:")
              print(image_to_ascii(img_path, width=40, height=15))
            
        except FileNotFoundError:
            print(f"File not found: {img_path}")
        except Exception as e:
            print(f"Error: {e}")
