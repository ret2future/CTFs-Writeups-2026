#!/usr/bin/env python3
"""
Try SSTV decoding with INVERTED frequency mapping
What if 2300Hz = DARK instead of BRIGHT?
"""
import numpy as np
from scipy import signal
from scipy.io import wavfile
import matplotlib.pyplot as plt
from PIL import Image

def decode_sstv_inverted():
    """Decode SSTV with inverted frequency mapping"""
    
    # Load audio
    sample_rate, audio_data = wavfile.read('Echoes_in_the_Aether.wav')
    audio = audio_data.astype(np.float32)
    
    print(f"Sample rate: {sample_rate} Hz")
    print(f"Duration: {len(audio) / sample_rate:.1f} seconds")
    
    # Design bandpass filter
    low_freq = 1200
    high_freq = 2400
    sos = signal.butter(6, [low_freq, high_freq], 'band', fs=sample_rate, output='sos')
    filtered = signal.sosfilt(sos, audio)
    
    # Analytic signal
    analytic = signal.hilbert(filtered)
    phase = np.unwrap(np.angle(analytic))
    
    # Instantaneous frequency
    freq = np.diff(phase) * sample_rate / (2 * np.pi)
    freq = np.concatenate([freq[:1], freq])  # Match length
    
    # Smooth with moving average
    window = 20
    freq_smooth = signal.savgol_filter(freq, window, 2)
    
    # SSTV parameters - Robot 36 mode
    # Line duration: about 0.0735 seconds (735 samples at 10kHz effective)
    # 120 lines for full image
    
    # Try different downsampling factors
    downsampling = 250  # Need more downsampling for 320x240
    downsampled_freq = freq_smooth[::downsampling]
    
    print(f"\nDownsampling factor: {downsampling}")
    print(f"Samples after downsampling: {len(downsampled_freq)}")
    
    # Frequency range and mapping (INVERTED)
    freq_min = 1500
    freq_max = 2300
    
    # INVERTED: High frequency = DARK, Low frequency = BRIGHT
    brightness_inv = np.clip(
        (freq_max - downsampled_freq) / (freq_max - freq_min) * 255,
        0, 255
    )
    
    # Reshape to image (320 pixels wide, auto height)
    pixels_per_line = 320
    num_lines = len(brightness_inv) // pixels_per_line
    
    if num_lines == 0:
        num_lines = 1
    
    # Trim or pad as needed
    total_pixels = num_lines * pixels_per_line
    if len(brightness_inv) < total_pixels:
        brightness_inv = np.pad(brightness_inv, (0, total_pixels - len(brightness_inv)))
    else:
        brightness_inv = brightness_inv[:total_pixels]
        image_arr_inv = brightness_inv.reshape(num_lines, pixels_per_line).astype(np.uint8)
    
    print(f"Image shape: {image_arr_inv.shape}")
    print(f"Brightness range: {image_arr_inv.min()} to {image_arr_inv.max()}")
    print(f"Mean brightness: {image_arr_inv.mean():.1f}")
    
    # Save
    img_inv = Image.fromarray(image_arr_inv)
    img_inv.save('sstv_inverted_freq_320x240.png')
    print(f"Saved: sstv_inverted_freq_320x240.png ({num_lines}x{pixels_per_line})")
    
    # Also try histogram equalization
    from PIL import ImageOps
    img_inv_eq = ImageOps.equalize(img_inv)
    img_inv_eq.save('sstv_inverted_freq_equalized.png')
    print(f"Saved: sstv_inverted_freq_equalized.png")
    
    # And contrast enhancement
    from PIL import ImageEnhance
    enhancer = ImageEnhance.Contrast(img_inv)
    img_inv_contrast = enhancer.enhance(3.0)
    img_inv_contrast.save('sstv_inverted_freq_contrast.png')
    print(f"Saved: sstv_inverted_freq_contrast.png")

if __name__ == '__main__':
    decode_sstv_inverted()
