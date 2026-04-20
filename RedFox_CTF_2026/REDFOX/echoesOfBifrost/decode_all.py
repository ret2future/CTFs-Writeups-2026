#!/usr/bin/env python3
"""
Final comprehensive decoder - try multiple approaches simultaneously
"""
import numpy as np
import scipy.io.wavfile as wavfile
from PIL import Image
from scipy import signal

def decode_all_approaches():
    """Try all promising approaches"""
    
    audio_path = 'Echoes_in_the_Aether.wav'
    sr, y = wavfile.read(audio_path)
    
    if len(y.shape) > 1:
        y = y[:, 0]
    
    y = np.float32(y) / np.max(np.abs(y))
    y_int = np.int16(y * 32767)
    
    print("=== APPROACH 1: Direct LSB per byte ===")
    # Take every byte as-is
    y_bytes = y_int.astype(np.uint8)
    pixels_approach1 = y_bytes[::10]  # Downsample
    
    if len(pixels_approach1) > 76800:
        img_data = pixels_approach1[:76800]
        img = Image.fromarray(img_data.reshape((240, 320)), mode='L')
        img.save('decode_approach1_320x240.png')
        print("Saved: decode_approach1_320x240.png")
    
    print("\n=== APPROACH 2: High byte (MSB) ===")
    y_high = (y_int >> 8).astype(np.uint8)
    pixels_a2 = y_high[::10]
    
    if len(pixels_a2) > 76800:
        img_data = pixels_a2[:76800]
        img = Image.fromarray(img_data.reshape((240, 320)), mode='L')
        img.save('decode_approach2_msb_320x240.png')
        print("Saved: decode_approach2_msb_320x240.png")
    
    print("\n=== APPROACH 3: Magnitude spectrogram ===")
    f, t, Sxx = signal.spectrogram(y, sr, nperseg=2048)
    
    # Normalize spectrogram
    sxx_mag = np.abs(Sxx)
    sxx_norm = (sxx_mag - np.min(sxx_mag)) / (np.max(sxx_mag) - np.min(sxx_mag)) * 255
    sxx_norm = sxx_norm.astype(np.uint8)
    
    # We have shape (n_freq, n_time)
    # Try reshaping as image
    total_pixels = sxx_norm.size
    print(f"Spectrogram total pixels: {total_pixels}")
    
    # Try common resolutions
    for target_res in [76800, 102400, 153600, 307200]:  # 320x240, 320x320, 320x480, 640x480
        if total_pixels >= target_res:
            pixels_flat = sxx_norm.ravel()[:target_res]
            
            # Try different aspect ratios
            aspect_ratios = [(320, target_res//320), (target_res//256, 256), (512, target_res//512)]
            for w, h in aspect_ratios:
                if w * h == target_res:
                    img = Image.fromarray(pixels_flat.reshape((h, w)), mode='L')
                    img.save(f'decode_spectrogram_mag_{w}x{h}.png')
                    print(f"Saved: decode_spectrogram_mag_{w}x{h}.png")
                    break
    
    print("\n=== APPROACH 4: Phase spectrogram ===")
    sxx_phase = np.angle(signal.stft(y, sr, nperseg=2048)[2])
    
    # Quantize phase to 0-255
    sxx_phase_norm = ((sxx_phase + np.pi) / (2 * np.pi) * 255).astype(np.uint8)
    
    total_pixels = sxx_phase_norm.size
    if total_pixels >= 76800:
        pixels_flat = sxx_phase_norm.ravel()[:76800]
        img = Image.fromarray(pixels_flat.reshape((240, 320)), mode='L')
        img.save('decode_phase_320x240.png')
        print("Saved: decode_phase_320x240.png")
    
    print("\n=== APPROACH 5: Frequency bins as rows (video-style) ===")
    # Use spectrogram but keep it in time-frequency format
    # Time axis becomes horizontal (width), frequency becomes vertical (height)
    
    # Take subset of frequencies (e.g., 1000-3000 Hz)
    f_min_idx = np.argmin(np.abs(f - 1000))
    f_max_idx = np.argmin(np.abs(f - 3000))
    
    sxx_band = sxx_mag[f_min_idx:f_max_idx+1, :]
    
    sxx_band_norm = (sxx_band - np.min(sxx_band)) / (np.max(sxx_band) - np.min(sxx_band)) * 255
    sxx_band_norm = sxx_band_norm.astype(np.uint8)
    
    print(f"Band spectrogram shape: {sxx_band_norm.shape}")
    
    # Save at various scales
    img_band = Image.fromarray(sxx_band_norm, mode='L')
    
    # Scale up
    new_width = min(sxx_band_norm.shape[1] * 2, 2048)
    new_height = sxx_band_norm.shape[0] * 8
    img_band_scaled = img_band.resize((new_width, new_height), Image.Resampling.NEAREST)
    img_band_scaled.save('decode_band_scaled.png')
    print("Saved: decode_band_scaled.png")
    
    # Also inverted
    img_band_inv = Image.fromarray(255 - sxx_band_norm, mode='L')
    img_band_inv_scaled = img_band_inv.resize((new_width, new_height), Image.Resampling.NEAREST)
    img_band_inv_scaled.save('decode_band_inverted.png')
    print("Saved: decode_band_inverted.png")
    
    print("\nAll decoding approaches complete!")

if __name__ == '__main__':
    decode_all_approaches()
