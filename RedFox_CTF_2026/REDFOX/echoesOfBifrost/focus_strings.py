#!/usr/bin/env python3
"""
Focus on the repeating strings - they might be the SSTV encoded data
"""
import scipy.io.wavfile as wavfile
import numpy as np
from collections import Counter

def extract_repeating_strings():
    """Extract and analyze repeating string patterns"""
    
    audio_path = 'Echoes_in_the_Aether.wav'
    sr, y = wavfile.read(audio_path)
    
    if len(y.shape) > 1:
        y = y[:, 0]
    
    # Get the raw bytes
    y_bytes = y.tobytes()
    
    print(f"Total audio bytes: {len(y_bytes)}")
    
    # Extract all strings
    strings = []
    current_str = b''
    for byte in y_bytes:
        if 32 <= byte <= 126:  # Printable
            current_str += bytes([byte])
        else:
            if len(current_str) >= 5:  # At least 5 characters
                strings.append(current_str)
            current_str = b''
    
    print(f"Found {len(strings)} strings total")
    
    # Count most common ones
    string_counts = Counter(strings)
    most_common = string_counts.most_common(50)
    
    print("\nMost common strings:")
    for string, count in most_common:
        try:
            decoded = string.decode('ascii')
            print(f"  '{decoded}' - {count} times")
        except:
            pass
    
    # Extract unique patterns
    unique_strings = sorted(set(strings), key=lambda x: -string_counts[x])
    
    print(f"\n\n===  Looking for patterns in unique strings ===")
    
    for s in unique_strings[:100]:
        try:
            decoded = s.decode('ascii')
            if '{' in decoded or '}' in decoded or 'flag' in decoded.lower() or 'ctf' in decoded.lower():
                print(f"  INTERESTING: '{decoded}'")
        except:
            pass
    
    # Maybe the strings themselves encode image pixels
    # Each unique character might represent a pixel value
    print("\n=== Analyzing string patterns ===")
    
    # Take top strings and see if they form an image
    top_strings = [s for s, c in most_common if c > 100]
    print(f"Found {len(top_strings)} strings with >100 occurrences")
    
    # Map each string to a character code
    string_to_char = {}
    for i, s in enumerate(top_strings[:26]):  # Max 26 letters
        char = chr(ord('A') + i)
        string_to_char[s] = char
    
    # Reconstruct  "image" using these mappings
    image_data = []
    current_str = b''
    for byte in y_bytes:
        if 32 <= byte <= 126:
            current_str += bytes([byte])
        else:
            if current_str in string_to_char:
                image_data.append(string_to_char[current_str])
            elif len(current_str) >= 5:
                image_data.append('?')
            current_str = b''
    
    reconstructed = ''.join(image_data[:5000])
    print(f"Reconstructed (first 5000): {reconstructed}")
    
    # Look for repeating patterns
    if len(reconstructed) > 100:
        # Find smallest repeating unit
        for period in range(1, min(100, len(reconstructed)//10)):
            segment = reconstructed[:period]
            # Check if this repeats
            matches = 0
            for i in range(0, len(reconstructed) - period, period):
                if reconstructed[i:i+period] == segment:
                    matches += 1
            
            if matches > len(reconstructed) / (period * 2):  # If repeats significantly
                print(f"\nFound repeating pattern (period {period}):")
                print(f"  '{segment}'")
                print(f"  Repeats {matches} times")

def extract_pixel_values():
    """Try to map string positions to image pixels"""
    
    audio_path = 'Echoes_in_the_Aether.wav'
    sr, y = wavfile.read(audio_path)
    
    if len(y.shape) > 1:
        y = y[:, 0]
    
    y_int = np.int16(y / np.max(np.abs(y)) * 32767)
    
    # Every Nth sample might encode pixel data
    # For SSTV in 183 seconds at 48kHz: 183*48000 = 8784000 samples
    # If it's an image like 320x240 = 76800 pixels
    # Then each pixel is 8784000 / 76800 = 114 samples
    
    print("\n=== Pixel extraction ===")
    
    pixels_per_sample = [114, 128, 256, 512, 1024]
    
    for pps in pixels_per_sample:
        pixels = []
        for i in range(0, len(y_int) - pps, pps):
            segment = y_int[i:i+pps]
            # Average, take dominant bit pattern, or peak
            pixel_val = np.mean(np.abs(segment))
            pixel_val = int((pixel_val / 32767) * 255)
            pixels.append(pixel_val)
        
        print(f"\nWith {pps} samples per pixel: {len(pixels)} total pixels")
        print(f"  Values: {pixels[:50]}")
        
        # Check if there are distinct values (might indicate structured data)
        unique_vals = len(set(pixels))
        print(f"  Unique values: {unique_vals}")

if __name__ == '__main__':
    extract_repeating_strings()
    extract_pixel_values()
