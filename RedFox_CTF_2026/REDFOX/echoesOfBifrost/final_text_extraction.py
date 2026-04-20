#!/usr/bin/env python3
"""
Final attempt: Extract direct text/message from audio
Try multiple low-level approaches
"""
import numpy as np
import scipy.io.wavfile as wavfile
from collections import Counter

def extract_every_byte_as_text():
    """Try every possible byte extraction method"""
    
    audio_path = 'Echoes_in_the_Aether.wav'
    sr, y = wavfile.read(audio_path)
    
    if len(y.shape) > 1:
        y = y[:, 0]
    
    y_int16 = np.int16(y / np.max(np.abs(y)) * 32767)
    y_uint16 = y_int16.astype(np.uint16)
    y_bytes = y_int16.astype(np.uint8)
    
    print("=== Trying every byte extraction method ===\n")
    
    methods = [
        ("LSB (every low byte)", (y_int16 & 0xFF).astype(np.uint8)),
        ("MSB (every high byte)", ((y_int16 >> 8) & 0xFF).astype(np.uint8)),
        ("Alternating LSB", (y_int16[::2] & 0xFF).astype(np.uint8)),
        ("Alternating MSB", ((y_int16[::2] >> 8) & 0xFF).astype(np.uint8)),
        ("Every 10th byte LSB", (y_int16[::10] & 0xFF).astype(np.uint8)),
        ("Every 10th byte MSB", ((y_int16[::10] >> 8) & 0xFF).astype(np.uint8)),
        ("Direct y_bytes", y_bytes),
    ]
    
    for method_name, byte_array in methods:
        # Look for printable text
        text_chars = [chr(b) if 32 <= b <= 126 else '.' for b in byte_array[:1000]]
        text_str = ''.join(text_chars)
        
        # Count printable characters
        printable_count = sum(1 for b in byte_array if 32 <= b <= 126)
        printable_pct = printable_count / len(byte_array) * 100
        
        print(f"{method_name}: {printable_pct:.1f}% printable")
        
        if printable_pct > 50:  # Significant amount of printable text
            print(f"  First 100 chars: {text_str[:100]}")
            
            # Try to find coherent words/patterns
            words = ''.join([chr(b) if 32 <= b <= 126 else '\n' for b in byte_array[:2000]]).split()
            for word in words[:20]:
                if len(word) > 3:
                    print(f"    Word: {word}")

def look_for_specific_strings():
    """Look for known patterns"""
    
    audio_path = 'Echoes_in_the_Aether.wav'
    sr, y = wavfile.read(audio_path)
    
    if len(y.shape) > 1:
        y = y[:, 0]
    
    y_int16 = np.int16(y / np.max(np.abs(y)) * 32767)
    
    print("\n=== Looking for specific patterns ===\n")
    
    # Try different byte interpretations
    interpretations = [
        ("Big-endian 16-bit", [(int(y_int16[i]) << 8 | int(y_int16[i+1])) & 0xFF for i in range(0, len(y_int16)-1, 2)]),
        ("Little-endian 16-bit", [(int(y_int16[i]) | (int(y_int16[i+1]) << 8)) & 0xFF for i in range(0, len(y_int16)-1, 2)]),
        ("Alternating bits", [int(y_int16[i]) & 1 for i in range(len(y_int16))]),
    ]
    
    for interp_name, byte_array in interpretations[:2]:  # Skip the alternating bits one
        try:
            # Try to decode as ASCII
            text = ''.join([chr(b) if 32 <= b <= 126 else '.' for b in byte_array[:500]])
            print(f"{interp_name}: {text[:200]}")
        except:
            pass

def find_message_in_segments():
    """Split audio into segments and look for messages"""
    
    audio_path = 'Echoes_in_the_Aether.wav'
    sr, y = wavfile.read(audio_path)
    
    if len(y.shape) > 1:
        y = y[:, 0]
    
    y_int16 = np.int16(y / np.max(np.abs(y)) * 32767)
    
    print("\n=== Searching segments ===\n")
    
    # Divide into 100 segments
    segment_size = len(y_int16) // 100
    
    for seg in range(20):  # Check first 20 segments
        start = seg * segment_size
        end = (seg + 1) * segment_size
        
        segment = y_int16[start:end]
        
        # Get bytes
        lsb_bytes = (segment & 0xFF).astype(np.uint8)
        msb_bytes = ((segment >> 8) & 0xFF).astype(np.uint8)
        
        # Check each for text
        for byte_arr, label in [(lsb_bytes, "LSB"), (msb_bytes, "MSB")]:
            printable = sum(1 for b in byte_arr if 32 <= b <= 126)
            if printable > len(byte_arr) * 0.3:
                text = ''.join([chr(b) if 32 <= b <= 126 else '.' for b in byte_arr[:100]])
                print(f"Segment {seg} {label}: {text[:80]}")

if __name__ == '__main__':
    extract_every_byte_as_text()
    look_for_specific_strings()
    find_message_in_segments()
