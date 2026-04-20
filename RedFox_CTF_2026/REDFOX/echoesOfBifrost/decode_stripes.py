#!/usr/bin/env python3
"""
Analyze the striped pattern as encoded data
"""
import numpy as np
from PIL import Image

def decode_stripe_pattern():
    """Try to interpret horizontal stripes as encoded data"""
    
    img_file = 'sstv_improved_sync_decode.png'
    img = Image.open(img_file)
    img_arr = np.array(img)
    
    print(f"Image shape: {img_arr.shape}")
    
    # Analyze row-by-row patterns
    print("\n=== Row Brightness Analysis ===")
    
    row_means = np.mean(img_arr, axis=1)
    
    # Quantize to binary
    threshold = np.median(row_means)
    row_binary = (row_means > threshold).astype(int)
    
    print(f"Binary row pattern: {''.join(str(b) for b in row_binary)}")
    
    # Group consecutive identical rows
    print("\n=== Run-Length Encoding ===")
    groups = []
    current_val = row_binary[0]
    current_count = 1
    
    for val in row_binary[1:]:
        if val == current_val:
            current_count += 1
        else:
            groups.append((current_val, current_count))
            current_val = val
            current_count = 1
    groups.append((current_val, current_count))
    
    print(f"Found {len(groups)} groups")
    for i, (val, count) in enumerate(groups[:50]):
        print(f"  Group {i}: {'light' if val else 'dark'} - {count} rows")
    
    # Try to interpret as morse code or binary
    morse_pattern = ''.join('-' if count > 2 else '.' for val, count in groups)
    print(f"\nAs morse-like: {morse_pattern[:100]}")
    
    # Try column analysis
    print("\n=== Column Analysis ===")
    col_means = np.mean(img_arr, axis=0)
    col_binary = (col_means > 128).astype(int)
    
    print(f"Column binary (first 100): {''.join(str(b) for b in col_binary[:100])}")
    
    # Search for repeating patterns
    print("\n=== Looking for Repeating Patterns ===")
    
    # Check for specific sequences
    pattern_search = col_binary
    
    # Try 8-bit chunks as ASCII
    for chunk_size in [7, 8]:
        text_out = []
        for i in range(0, len(pattern_search) - chunk_size, chunk_size):
            byte_bits = pattern_search[i:i+chunk_size]
            byte_val = int(''.join(str(b) for b in byte_bits), 2)
            if 32 <= byte_val <= 126:
                text_out.append(chr(byte_val))
            else:
                text_out.append(f'[{byte_val}]')
        
        result = ''.join(text_out[:100])
        if any(chr(ord('A')) <= c <= chr(ord('z')) for c in result):
            print(f"With {chunk_size}-bit decoding: {result[:150]}")

def try_qrcode_barcode():
    """Try to detect QR codes or barcodes"""
    
    import subprocess
    
    img_file = 'sstv_improved_sync_decode.png'
    
    # Try zbarimg if available
    try:
        result = subprocess.run(['which', 'zbarimg'], capture_output=True)
        if result.returncode == 0:
            print("\nTrying barcode detection with zbarimg...")
            result = subprocess.run(['zbarimg', img_file], capture_output=True, text=True)
            print(result.stdout)
    except:
        print("zbarimg not available")
    
    # Try pyzbar if available
    try:
        from pyzbar.pyzbar import decode
        print("\nTrying barcode detection with pyzbar...")
        from PIL import Image
        img = Image.open(img_file)
        codes = decode(img)
        for code in codes:
            print(f"Found: {code.data.decode('utf-8')}")
    except ImportError:
        print("pyzbar not installed")
    except Exception as e:
        print(f"Barcode error: {e}")

if __name__ == '__main__':
    decode_stripe_pattern()
    try_qrcode_barcode()
