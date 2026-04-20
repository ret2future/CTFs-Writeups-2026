#!/usr/bin/env python3
"""
Check for QR code, barcode, or binary encoding in the inverted image
"""
import numpy as np
from PIL import Image
import subprocess

def check_for_qrcode():
    """Try to detect QR codes"""
    print("="*60)
    print("Checking for QR Code")
    print("="*60)
    
    # Try with pyzbar if available
    try:
        from pyzbar.pyzbar import decode as pyzbar_decode
        
        for img_file in ['sstv_inverted_freq_320x240.png', 'sstv_inverted_freq_equalized.png']:
            try:
                img = Image.open(img_file)
                codes = pyzbar_decode(img)
                if codes:
                    print(f"✓ QR Code found in {img_file}:")
                    for code in codes:
                        print(f"  Data: {code.data.decode('utf-8')}")
                else:
                    print(f"✗ No QR code in {img_file}")
            except:
                pass
    except ImportError:
        print("pyzbar not available, trying zbarimg...")
        result = subprocess.run(['which', 'zbarimg'], capture_output=True)
        if result.returncode == 0:
            for img_file in ['sstv_inverted_freq_320x240.png', 'sstv_inverted_freq_equalized.png']:
                result = subprocess.run(['zbarimg', img_file], capture_output=True, text=True)
                if result.stdout:
                    print(f"✓ Barcode in {img_file}:")
                    print(result.stdout)
                else:
                    print(f"✗ No barcode in {img_file}")

def check_binary_pattern():
    """Treat image as binary and extract as text"""
    print("\n" + "="*60)
    print("Binary Pattern Analysis")
    print("="*60)
    
    img = Image.open('sstv_inverted_freq_320x240.png')
    img_arr = np.array(img)
    
    # Binarize
    binary = (img_arr < 128).astype(int)
    
    print(f"Image shape: {binary.shape}")
    print(f"Black pixels: {np.sum(binary)}")
    print(f"White pixels: {np.sum(1-binary)}")
    
    # Try reading as bits (row by row)
    print("\nTrying row-by-row binary interpretation:")
    for row_idx in [0, 5, 10, 20, 50]:
        if row_idx < binary.shape[0]:
            row = binary[row_idx]
            # Convert to string of 0s and 1s
            bit_string = ''.join(str(b) for b in row)
            # Try 8-bit chunks
            text_out = []
            for i in range(0, len(bit_string) - 7, 8):
                byte_val = int(bit_string[i:i+8], 2)
                if 32 <= byte_val <= 126:
                    text_out.append(chr(byte_val))
                else:
                    text_out.append(f'[{byte_val}]')
            
            result = ''.join(text_out[:20])
            print(f"  Row {row_idx:3d}: {result}")
    
    # Try column-by-column
    print("\nTrying column-by-column binary interpretation:")
    for col_idx in [0, 50, 100, 150, 200, 250, 300]:
        if col_idx < binary.shape[1]:
            col = binary[:, col_idx]
            bit_string = ''.join(str(b) for b in col)
            text_out = []
            for i in range(0, len(bit_string) - 7, 8):
                byte_val = int(bit_string[i:i+8], 2)
                if 32 <= byte_val <= 126:
                    text_out.append(chr(byte_val))
                else:
                    text_out.append(f'[{byte_val}]')
            
            result = ''.join(text_out[:10])
            print(f"  Col {col_idx:3d}: {result}")

def check_pixel_intensity_as_bits():
    """Use pixel intensity to encode bits"""
    print("\n" + "="*60)
    print("Pixel Intensity as Bits")
    print("="*60)
    
    img = Image.open('sstv_inverted_freq_320x240.png')
    img_arr = np.array(img)
    
    # Use different thresholds
    for threshold in [50, 100, 128, 200]:
        binary = (img_arr < threshold).astype(int)
        black_pct = 100 * np.sum(binary) / binary.size
        print(f"\nThreshold {threshold}: {black_pct:.1f}% black pixels")
        
        # Try extracting as ASCII
        flat = binary.flatten()
        bits = ''.join(str(b) for b in flat[:2560])  # 320*8 = 2560 bits
        
        text = []
        for i in range(0, len(bits) - 7, 8):
            byte_val = int(bits[i:i+8], 2)
            if 32 <= byte_val <= 126:
                text.append(chr(byte_val))
            else:
                text.append('.')
        
        result = ''.join(text[:100])
        if any(c.isalpha() for c in result):
            print(f"  Possible text: {result}")

if __name__ == '__main__':
    check_for_qrcode()
    check_binary_pattern()
    check_pixel_intensity_as_bits()
