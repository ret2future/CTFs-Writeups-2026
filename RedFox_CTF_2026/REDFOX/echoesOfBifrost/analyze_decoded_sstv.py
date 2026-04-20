#!/usr/bin/env python3
"""
Analyze the decoded SSTV image to extract hidden message
"""
from PIL import Image, ImageEnhance, ImageFilter
import numpy as np
import pytesseract

def analyze_decoded_image():
    """Deep analysis of the decoded SSTV image"""
    
    # Load the decoded image
    img = Image.open('sstv_robot36_decoded.png')
    img_array = np.array(img)
    
    print(f"Image shape: {img_array.shape}")
    print(f"Value range: {np.min(img_array)} to {np.max(img_array)}")
    
    # The image shows horizontal stripes - could be scanlines recovery issue
    # Let me try to extract patterns row by row
    
    print("\n=== Row Analysis ===")
    row_means = np.mean(img_array, axis=1)
    print(f"Row brightness values:")
    for i in range(min(10, len(row_means))):
        print(f"  Row {i}: {row_means[i]:.1f}")
    
    # Look for transitions
    quantized_rows = (row_means > 128).astype(int)
    print(f"\nBinary row pattern: {quantized_rows}")
    
    # Try vertical analysis
    print("\n=== Column Analysis ===")
    col_means = np.mean(img_array, axis=0)
    print(f"Column brightness range: {np.min(col_means):.1f} to {np.max(col_means):.1f}")
    
    # Histogram of column means
    col_quantized = (col_means > 128).astype(int)
    
    # Find transitions (edges)
    transitions = np.diff(col_quantized)
    edge_positions = np.where(transitions != 0)[0]
    
    print(f"Found {len(edge_positions)} vertical transitions")
    if len(edge_positions) > 0:
        print(f"First 50 edge positions: {edge_positions[:50]}")
        
        # Get segment widths
        if len(edge_positions) > 1:
            widths = np.diff(edge_positions)
            print(f"First 50 segment widths: {widths[:50]}")
            
            # Normalize widths to bits (maybe proportional encoding)
            norm_widths = (widths > np.mean(widths)).astype(int)
            bit_string = ''.join(str(b) for b in norm_widths[:200])
            print(f"Binary interpretation: {bit_string}")
    
    # Try different image enhancements
    print("\n=== Trying Image Enhancements ===")
    
    enhancements = [
        ("Original", img),
        ("High Contrast", ImageEnhance.Contrast(img).enhance(3.0)),
        ("High Brightness", ImageEnhance.Brightness(img).enhance(1.5)),
        ("Sharpen", img.filter(ImageFilter.SHARPEN)),
        ("Edge Enhance", img.filter(ImageFilter.EDGE_ENHANCE_MORE)),
    ]
    
    for name, enhanced_img in enhancements:
        enhanced_img.save(f'enhanced_{name.lower().replace(" ", "_")}.png')
        
        # Try OCR
        try:
            text = pytesseract.image_to_string(enhanced_img)
            if text.strip():
                print(f"\n{name} OCR result:")
                print(f"  {text[:200]}")
        except:
            pass
    
    # Check if it's a barcode or QR code pattern
    print("\n=== Barcode/QR Analysis ===")
    
    # Black and white ratio
    black_pixels = np.sum(img_array < 128)
    white_pixels = np.sum(img_array >= 128)
    total = black_pixels + white_pixels
    
    print(f"Black pixels: {black_pixels/total*100:.1f}%")
    print(f"White pixels: {white_pixels/total*100:.1f}%")
    
    # Try to interpret as 1D barcode (read columns)
    # Each column could be a barcode stripe
    barcode_bits = (col_means > 128).astype(int)
    print(f"Barcode string (first 100): {''.join(str(b) for b in barcode_bits[:100])}")

if __name__ == '__main__':
    analyze_decoded_image()
