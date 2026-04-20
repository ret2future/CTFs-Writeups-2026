#!/usr/bin/env python3
"""
SSTV (Slow Scan Television) Decoder
This decodes SSTV signals transmitted over audio
"""
import numpy as np
import scipy.io.wavfile as wavfile
from scipy import signal
from PIL import Image
import struct

def decode_sstv():
    """Decode SSTV from WAV file"""
    audio_path = 'Echoes_in_the_Aether.wav'
    
    sr, y = wavfile.read(audio_path)
    if len(y.shape) > 1:
        y = y[:, 0]
    y = y.astype(float) / np.max(np.abs(y))
    
    print(f"Loaded {len(y)} samples at {sr}Hz")
    
    # Downsample if needed for easier processing
    downsample_factor = 4
    y_down = y[::downsample_factor]
    sr_down = sr // downsample_factor
    
    print(f"Downsampled to {len(y_down)} samples at {sr_down}Hz")
    
    # Use spectrogram to visualize the scan lines
    # SSTV transmits image line by line
    # Each line is transmitted as varying frequency
    
    # Parameters for SSTV decoding
    f_min = 1500  # Black
    f_max = 2300  # White
    
    # Get spectrogram with fine time resolution
    # Keep high frequency resolution
    window_size = 2048
    f, t, Sxx = signal.spectrogram(y_down, sr_down, nperseg=window_size, noverlap=window_size-512)
    
    print(f"Spectrogram shape: {Sxx.shape}")
    print(f"Frequency bins: {len(f)}, Time bins: {len(t)}")
    print(f"Frequency range: {f[0]:.1f}Hz to {f[-1]:.1f}Hz")
    
    # Extract the image data from spectrogram
    # Find the 1500-2300Hz range
    f_idx_min = np.argmin(np.abs(f - f_min))
    f_idx_max = np.argmin(np.abs(f - f_max))
    
    print(f"Frequency indices: {f_idx_min} to {f_idx_max}")
    
    # Sum power across the frequency band for each time point
    # This gives us the image data (intensity at each horizontal position)
    image_data_raw = np.sum(Sxx[f_idx_min:f_idx_max+1, :], axis=0)
    
    print(f"Image data raw shape: {image_data_raw.shape}")
    print(f"Image data min: {np.min(image_data_raw)}, max: {np.max(image_data_raw)}")
    
    # Normalize to 0-255
    image_data_norm = (image_data_raw - np.min(image_data_raw)) / (np.max(image_data_raw) - np.min(image_data_raw)) * 255
    image_data_norm = image_data_norm.astype(np.uint8)
    
    # Try different aspect ratios (typical SSTV is 320x240 or 256x192)
    aspect_ratios = [
        (320, 240),  # 4:3
        (256, 192),
        (256, 256),
        (512, 384),
        (640, 480),
    ]
    
    for width, height in aspect_ratios:
        if len(image_data_norm) >= width * height:
            # Reshape and save
            try:
                img_data = image_data_norm[:width*height]
                img_2d = img_data.reshape((height, width))
                img = Image.fromarray(img_2d, mode='L')
                filename = f'sstv_decoded_{width}x{height}.png'
                img.save(filename)
                print(f"Saved: {filename}")
                
                # Also try rotating
                img_rot = img.rotate(90)
                filename_rot = f'sstv_decoded_{width}x{height}_rot90.png'
                img_rot.save(filename_rot)
                print(f"Saved (rotated): {filename_rot}")
            except Exception as e:
                print(f"Error processing {width}x{height}: {e}")
    
    # Also try a simpler approach - slice the spectrogram differently
    print("\n--- Alternative extraction method ---")
    
    # Take the frequency band and create an image
    sxx_band = Sxx[f_idx_min:f_idx_max+1, :]
    sxx_db = 10 * np.log10(np.maximum(sxx_band, 1e-10))
    sxx_norm = (sxx_db - np.min(sxx_db)) / (np.max(sxx_db) - np.min(sxx_db)) * 255
    sxx_norm = sxx_norm.astype(np.uint8)
    
    print(f"Spectrogram band shape: {sxx_norm.shape}")
    
    # Save as-is
    img_band = Image.fromarray(sxx_norm, mode='L')
    img_band.save('sstv_band_raw.png')
    print("Saved: sstv_band_raw.png")
    
    # Try flipping
    img_band_flip = Image.fromarray(np.flipud(sxx_norm), mode='L')
    img_band_flip.save('sstv_band_flipped.png')
    print("Saved: sstv_band_flipped.png")
    
    # Try with full spectrogram normalized
    print("\n--- Full spectrogram as image ---")
    sxx_full_db = 10 * np.log10(np.maximum(Sxx, 1e-10))
    sxx_full_norm = (sxx_full_db - np.min(sxx_full_db)) / (np.max(sxx_full_db) - np.min(sxx_full_db)) * 255
    sxx_full_norm = sxx_full_norm.astype(np.uint8)
    
    # Resize to more manageable size
    img_full = Image.fromarray(sxx_full_norm, mode='L')
    
    # Save original
    img_full.save('sstv_full_spectrogram.png')
    print("Saved: sstv_full_spectrogram.png")
    
    # Try different scaling
    for scale in [2, 4, 8]:
        height, width = sxx_full_norm.shape
        new_height = height * scale
        new_width = min(width * scale // 4, 2048)  # Limit width
        img_scaled = img_full.resize((new_width, new_height), Image.Resampling.LANCZOS)
        img_scaled.save(f'sstv_full_scaled_x{scale}.png')
        print(f"Saved: sstv_full_scaled_x{scale}.png")

if __name__ == '__main__':
    decode_sstv()
