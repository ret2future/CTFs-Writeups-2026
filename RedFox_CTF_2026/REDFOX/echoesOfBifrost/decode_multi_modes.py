#!/usr/bin/env python3
"""
Sophisticated SSTV decoder with proper sync detection for multiple modes
"""
import numpy as np
from scipy import signal
from scipy.io import wavfile
from PIL import Image
import matplotlib.pyplot as plt

def decode_sstv_modes():
    """Try multiple SSTV modes"""
    
    # Load audio
    sample_rate, audio_data = wavfile.read('Echoes_in_the_Aether.wav')
    audio = audio_data.astype(np.float32)
    
    print(f"Sample rate: {sample_rate} Hz")
    print(f"Duration: {len(audio)/sample_rate:.1f} seconds")
    
    # Bandpass filter
    sos = signal.butter(6, [1200, 2400], 'band', fs=sample_rate, output='sos')
    filtered = signal.sosfilt(sos, audio)
    
    # Analytic signal & instantaneous frequency
    analytic = signal.hilbert(filtered)
    phase = np.unwrap(np.angle(analytic))
    freq = np.diff(phase) * sample_rate / (2 * np.pi)
    freq = np.concatenate([freq[:1], freq])
    freq_smooth = signal.savgol_filter(freq, 20, 2)
    
    print(f"\nInstantaneous frequency range: {freq_smooth.min():.0f} to {freq_smooth.max():.0f} Hz")
    
    # SSTV modes with different line durations
    modes = {
        'Robot36': {
            'lines': 120,
            'line_duration': 0.073,  # seconds
            'freq_black': 1500,
            'freq_white': 2300,
        },
        'ScottieM1': {
            'lines': 320,
            'line_duration': 0.1385,  # seconds
            'freq_black': 1500,
            'freq_white': 2300,
        },
        'MartinM1': {
            'lines': 320,
            'line_duration': 0.1465,  # seconds
            'freq_black': 1500,
            'freq_white': 2300,
        },
    }
    
    for mode_name, params in modes.items():
        print(f"\n{mode_name}: {params['lines']} lines x 320 pixels")
        
        line_samples = int(params['line_duration'] * sample_rate)
        print(f"  Line duration: {params['line_duration']:.4f}s = {line_samples} samples")
        
        # Calculate how many lines can fit in the audio
        max_lines = len(freq_smooth) // line_samples
        print(f"  Can fit {max_lines} lines in {len(audio)} samples")
        
        if max_lines < params['lines']:
            print(f"  ⚠️  Not enough data for full image ({max_lines} < {params['lines']})")
            actual_lines = max_lines
        else:
            actual_lines = params['lines']
        
        # Extract lines
        image_arr = []
        for line_idx in range(actual_lines):
            # Simple linear mapping: freq -> brightness
            start_idx = line_idx * line_samples
            end_idx = start_idx + line_samples
            
            if end_idx > len(freq_smooth):
                break
            
            line_freq = freq_smooth[start_idx:end_idx]
            
            # Resample to 320 pixels
            pixels_indices = np.linspace(0, len(line_freq)-1, 320)
            line_pixels = np.interp(pixels_indices, np.arange(len(line_freq)), line_freq)
            
            # Remap: freq->brightness (with inversion)
            line_brightness = np.clip(
                (params['freq_white'] - line_pixels) / (params['freq_white'] - params['freq_black']) * 255,
                0, 255
            )
            
            image_arr.append(line_brightness)
        
        image_arr = np.array(image_arr, dtype=np.uint8)
        
        if len(image_arr) > 0:
            print(f"  Generated: {image_arr.shape}")
            print(f"  Brightness: {image_arr.min()}-{image_arr.max()}, mean {image_arr.mean():.1f}")
            
            # Save
            img = Image.fromarray(image_arr)
            filename = f'sstv_{mode_name.lower()}_scan.png'
            img.save(filename)
            print(f"  Saved: {filename}")
            
            # Also save equalized
            from PIL import ImageOps
            img_eq = ImageOps.equalize(img)
            filename_eq = f'sstv_{mode_name.lower()}_eq.png'
            img_eq.save(filename_eq)
            print(f"  Saved: {filename_eq}")

if __name__ == '__main__':
    decode_sstv_modes()
