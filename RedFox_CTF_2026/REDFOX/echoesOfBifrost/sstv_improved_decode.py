#!/usr/bin/env python3
"""
Alternative SSTV decoding - map frequency directly to grayscale without sync extraction
"""
import numpy as np
import scipy.io.wavfile as wavfile
from scipy import signal
from PIL import Image

def direct_frequency_to_image():
    """Map instantaneous frequency directly to image intensity"""
    
    audio_path = 'Echoes_in_the_Aether.wav'
    sr, y = wavfile.read(audio_path)
    
    if len(y.shape) > 1:
        y = y[:, 0]
    
    y = np.float32(y) / np.max(np.abs(y))
    
    print("=== Direct Frequency-to-Image Mapping ===")
    
    # Bandpass filter to SSTV range
    sos = signal.butter(8, [1500, 2300], 'band', fs=sr, output='sos')
    y_filtered = signal.sosfilt(sos, y)
    
    # Get instantaneous frequency using phase
    analytic = signal.hilbert(y_filtered)
    phase = np.unwrap(np.angle(analytic))
    
    # Instantaneous frequency = derivative of phase
    inst_freq = np.diff(phase) * sr / (2 * np.pi)
    
    # Map frequency to brightness
    # 1500Hz = black (0), 2300Hz = white (255)
    brightness = np.clip((inst_freq - 1500) / (2300 - 1500) * 255, 0, 255)
    brightness = brightness.astype(np.uint8)
    
    print(f"Total brightness samples: {len(brightness)}")
    print(f"Brightness range: {np.min(brightness)} to {np.max(brightness)}")
    
    # Downsample to create image
    # For 183 seconds, 48kHz = 8,784,000 samples
    # 320x240 image = 76,800 pixels
    # So downsampling factor ≈ 8,784,000 / 76,800 ≈ 114 samples per pixel
    
    for down_factor in [100, 110, 114, 120, 130, 140, 160]:
        pixels = brightness[::down_factor]
        
        # Reshape to image
        # Height can be computed from total pixels
        height = len(pixels) // 320
        if height > 50:
            width = 320
            
            # Crop to exact size
            pixels_trimmed = pixels[:width * height]
            
            img_data = pixels_trimmed.reshape((height, width))
            img = Image.fromarray(img_data, mode='L')
            
            filename = f'sstv_direct_freqmap_{width}x{height}_down{down_factor}.png'
            img.save(filename)
            print(f"Saved: {filename}")
            
            # Also try inverted
            img_inv = Image.fromarray(255 - img_data, mode='L')
            img_inv.save(f'sstv_direct_freqmap_{width}x{height}_down{down_factor}_inv.png')

def sync_based_decode_improved():
    """Improved sync-based decoding"""
    
    audio_path = 'Echoes_in_the_Aether.wav'
    sr, y = wavfile.read(audio_path)
    
    if len(y.shape) > 1:
        y = y[:, 0]
    
    y = np.float32(y) / np.max(np.abs(y))
    
    print("\n=== Improved Sync-Based Decoding ===")
    
    # Separate sync and pixel channels
    sos_sync = signal.butter(6, [1100, 1300], 'band', fs=sr, output='sos')
    sos_pixel = signal.butter(6, [1500, 2300], 'band', fs=sr, output='sos')
    
    y_sync = signal.sosfilt(sos_sync, y)
    y_pixel = signal.sosfilt(sos_pixel, y)
    
    # Detect sync pulses with better threshold
    sync_envelope = np.abs(signal.hilbert(y_sync))
    
    # Use adaptive threshold
    sync_threshold = np.median(sync_envelope) + np.std(sync_envelope)
    sync_detected = sync_envelope > sync_threshold
    
    # Find sync pulse intervals
    sync_starts = np.where(np.diff(sync_detected.astype(int)) == 1)[0]
    sync_ends = np.where(np.diff(sync_detected.astype(int)) == -1)[0]
    
    print(f"Detected {len(sync_starts)} sync starts")
    
    if len(sync_ends) > len(sync_starts):
        sync_ends = sync_ends[:len(sync_starts)]
    
    # Extract pixel data between syncs
    lines = []
    for i in range(min(len(sync_starts), len(sync_ends)) - 1):
        sync_end = sync_ends[i]
        next_sync_start = sync_starts[i + 1]
        
        # Get pixel data in this interval
        pixel_interval = y_pixel[sync_end:next_sync_start]
        
        if len(pixel_interval) > 100:
            # Frequency to brightness
            analytic = signal.hilbert(pixel_interval)
            phase = np.unwrap(np.angle(analytic))
            inst_freq = np.diff(phase) * sr / (2 * np.pi)
            
            brightness = np.clip((inst_freq - 1500) / (2300 - 1500) * 255, 0, 255).astype(np.uint8)
            
            # Resample to 320 pixels
            if len(brightness) > 50:
                x_old = np.linspace(0, 1, len(brightness))
                x_new = np.linspace(0, 1, 320)
                line = np.interp(x_new, x_old, brightness).astype(np.uint8)
                lines.append(line)
    
    print(f"Extracted {len(lines)} lines")
    
    if len(lines) > 100:
        img_data = np.array(lines[:240])  # 240 lines for full image
        img = Image.fromarray(img_data, mode='L')
        img.save('sstv_improved_sync_decode.png')
        print("Saved: sstv_improved_sync_decode.png")
        
        # Inverted
        img_inv = Image.fromarray(255 - img_data, mode='L')
        img_inv.save('sstv_improved_sync_decode_inv.png')
        print("Saved: sstv_improved_sync_decode_inv.png")

if __name__ == '__main__':
    direct_frequency_to_image()
    sync_based_decode_improved()
