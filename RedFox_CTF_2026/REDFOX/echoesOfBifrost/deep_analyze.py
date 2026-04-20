#!/usr/bin/env python3
"""
Analyze the decoded Robot 36 images to look for patterns
Maybe it's a QR code, barcode, or hidden message
"""
import numpy as np
from PIL import Image, ImageEnhance, ImageFilter
import pytesseract

def analyze_decoded_images():
    """Deeply analyze the decoded images"""
    
    for filepath in ['sstv_robot36_decoded.png', 'sstv_robot36_decoded_inv.png']:
        print(f"\n=== Analyzing {filepath} ===")
        
        img = Image.open(filepath)
        img_arr = np.array(img)
        
        # Histogram
        hist = np.histogram(img_arr, bins=256)[0]
        max_hist = np.argmax(hist)
        print(f"Histogram peak at value: {max_hist} (count: {hist[max_hist]})")
        print(f"Median value: {np.median(img_arr)}")
        
        # Look for patterns - rows and columns
        row_means = np.mean(img_arr, axis=1)
        col_means = np.mean(img_arr, axis=0)
        
        print(f"Row brightness range: {np.min(row_means):.1f} to {np.max(row_means):.1f}")
        print(f"Col brightness range: {np.min(col_means):.1f} to {np.max(col_means):.1f}")
        
        # Look for spatial patterns
        # Check if there are distinct regions
        unique_values = len(np.unique(img_arr))
        print(f"Unique pixel values: {unique_values}")
        
        # Try clustering pixels into 2 colors (black/white)
        threshold = np.median(img_arr)
        binary = (img_arr > threshold).astype(np.uint8) * 255
        binary_img = Image.fromarray(binary)
        binary_img.save(f'{filepath[:-4]}_binary.png')
        print(f"Saved binary version with threshold {threshold}")
        
        # Check for QR code pattern
        white_corners = [
            binary[0:10, 0:10],
            binary[0:10, -10:],
            binary[-10:, 0:10],
            binary[-10:, -10:],
        ]
        
        white_pixel_counts = [np.sum(c) / (10*10) for c in white_corners]
        print(f"Corner white percentages: {[f'{p*100:.0f}%' for p in white_pixel_counts]}")
        
        # Try different thresholds for OCR
        for thresh in [0.25, 0.5, 0.75]:
            threshold_val = int(np.min(img_arr) + thresh * (np.max(img_arr) - np.min(img_arr)))
            binary = (img_arr > threshold_val).astype(np.uint8) * 255
            binary_img = Image.fromarray(binary)
            
            # Save
            save_name = f'{filepath[:-4]}_thresh{int(thresh*100)}.png'
            binary_img.save(save_name)
            
            # Try OCR
            text = pytesseract.image_to_string(binary_img)
            if text.strip():
                print(f"Threshold {threshold_val} found text: {text[:100]}")

def try_alternative_sstv_modes():
    """Try other SSTV modes besides Robot 36"""
    import scipy.io.wavfile as wavfile
    from scipy import signal
    
    audio_path = 'Echoes_in_the_Aether.wav'
    sr, y = wavfile.read(audio_path)
    
    if len(y.shape) > 1:
        y = y[:, 0]
    
    y = np.float32(y) / np.max(np.abs(y))
    
    # Try different line times
    # Robot 36: 88ms per line + 88ms blanking = 176ms
    # Martin M1: 146.5ms per line
    # Scottie 1: 138.3ms per line
    
    modes =                          {
        'Robot36': (88, 88),           # pixel_time_ms, blanking_ms
        'MartinM1': (146.5, 0),
        'Scottie1': (138.3, 0),  }
    
    for mode_name, (pixel_ms, blank_ms) in modes.items():
        print(f"\nTrying {mode_name} mode ({pixel_ms}ms per line)...")
        
        line_duration = pixel_ms +  blank_ms
        line_samples = int(sr * line_duration / 1000)
        
        num_lines = len(y) // (line_samples * 2)  # Approximate
        print(f"  Expected lines: {num_lines}")
        
        if num_lines > 50:
            # This could be the right mode
            print(f"  Mode looks promising for {num_lines} lines")

if __name__ == '__main__':
    analyze_decoded_images()
    try_alternative_sstv_modes()
