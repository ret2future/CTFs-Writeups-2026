#!/usr/bin/env python3
"""
Try to extract the actual message from various encodings
Look for: morse code, binary FSK, base64, ASCII art
"""
import numpy as np
import scipy.io.wavfile as wavfile
from scipy import signal
import struct

def try_morse_decode():
    """Try to decode morse code from the audio"""
    audio_path = 'Echoes_in_the_Aether.wav'
    
    sr, y = wavfile.read(audio_path)
    if len(y.shape) > 1:
        y = y[:, 0]
    y = np.float32(y) / np.max(np.abs(y))
    
    print("=== Trying Morse Code Decoding ===")
    
    # Filter to SSTV band
    sos = signal.butter(8, [1500, 2300], 'band', fs=sr, output='sos')
    y_filtered = signal.sosfilt(sos, y)
    
    # Envelope detection - morse would show clear on/off pattern
    analytic = signal.hilbert(y_filtered)
    envelope = np.abs(analytic)
    
    # Threshold to detect on/offline
    threshold = np.mean(envelope) + np.std(envelope) * 0.5
    signal_on = (envelope > threshold).astype(int)
    
    # Downsample to reasonable bit rate (e.g., 1000bps)
    downsample_factor = sr // 1000
    signal_down = signal_on[::downsample_factor]
    
    print(f"Signal on/off pattern length: {len(signal_down)} samples at 1000Hz")
    
    # Look for morse-like patterns (dit=short, dah=long)
    # Find transitions
    transitions = np.diff(signal_down)
    on_to_off = np.where(transitions == -1)[0]
    off_to_on = np.where(transitions == 1)[0]
    
    print(f"Transitions: {len(on_to_off)} on->off, {len(off_to_on)} off->on")
    
    # Look for repeating patterns
    on_durations = np.diff(on_to_off + off_to_on)
    if len(on_durations) > 0:
        print(f"On duration range: {np.min(on_durations)} to {np.max(on_durations)}")

def try_binary_extraction():
    """Try to extract binary data from frequency shifts"""
    audio_path = 'Echoes_in_the_Aether.wav'
    
    sr, y = wavfile.read(audio_path)
    if len(y.shape) > 1:
        y = y[:, 0]
    y = np.float32(y) / np.max(np.abs(y))
    
    print("\n=== Trying Binary FSK Decoding ===")
    
    # FSK: frequency shift keying - two frequencies represent 0 and 1
    # Typical: 1200bps with tones at 1200/2200Hz (Bell 202) or 1500/2300Hz
    
    # Divide audio into short segments
    segment_duration = 0.002  # 2ms per bit at 500bps
    segment_size = int(sr * segment_duration)
    
    bits = []
    for i in range(0, len(y) - segment_size, segment_size):
        segment = y[i:i+segment_size]
        
        # Get FFT
        fft_result = np.abs(np.fft.fft(segment))
        freqs = np.fft.fftfreq(len(segment), 1/sr)
        
        # Check energy at 1500Hz vs 2300Hz (typical SSTV markers)
        f1_idx = np.argmin(np.abs(freqs - 1500))
        f2_idx = np.argmin(np.abs(freqs - 2300))
        
        energy_1500 = fft_result[f1_idx]
        energy_2300 = fft_result[f2_idx]
        
        if energy_2300 > energy_1500:
            bits.append('1')
        else:
            bits.append('0')
    
    print(f"Extracted {len(bits)} bits")
    
    # Try to decode as ASCII
    bit_string = ''.join(bits[:8*50])  # First 50 bytes
    
    print(f"First 400 bits: {bit_string}")
    
    # Try different groupings
    for group_size in [7, 8]:
        text_out = []
        for i in range(0, len(bits) - group_size, group_size):
            byte_str = ''.join(bits[i:i+group_size])
            byte_val = int(byte_str, 2)
            if 32 <= byte_val <= 126:
                text_out.append(chr(byte_val))
            else:
                text_out.append('?')
        
        result_text = ''.join(text_out[:200])
        if '?' not in result_text[:20]:  #  Good match
            print(f"\nPossible text ({group_size}-bit groups):")
            print(result_text)


def try_raw_extraction():
    """Try raw data extraction from LSB and MSB"""
    audio_path = 'Echoes_in_the_Aether.wav'
    
    sr, y = wavfile.read(audio_path)
    if len(y.shape) > 1:
        y = y[:, 0]
    
    print("\n=== Trying Raw Data Extraction ===")
    
    y_int16 = np.int16(y / np.max(np.abs(y)) * 32767)
    
    # Extract LSBs
    lsb_bits = (y_int16 & 1).astype(int)
    
    # Try as bytes
    bytes_lsb = []
    for i in range(0, len(lsb_bits) - 8, 8):
        byte_val = 0
        for j in range(8):
            byte_val = (byte_val << 1) | lsb_bits[i + j]
        bytes_lsb.append(byte_val)
    
    # Check for patterns
    text_lsb = ''.join(chr(b) if 32 <= b <= 126 else '.' for b in bytes_lsb[:500])
    print(f"LSB extraction (first 500): {text_lsb}")
    
    # Try bits 1-3
    for bit_start in [1, 2, 4, 8]:
        bits_shifted = ((y_int16 >> bit_start) & 1).astype(int)
        bytes_shifted = []
        for i in range(0, len(bits_shifted) - 8, 8):
            byte_val = 0
            for j in range(8):
                byte_val = (byte_val << 1) | bits_shifted[i + j]
            bytes_shifted.append(byte_val)
        
        text_shifted = ''.join(chr(b) if 32 <= b <= 126 else '.' for b in bytes_shifted[:200])
        if '.' not in text_shifted[:20]:
            print(f"Bit {bit_start} extraction looks promising: {text_shifted}")

if __name__ == '__main__':
    try_morse_decode()
    try_binary_extraction()
    try_raw_extraction()
