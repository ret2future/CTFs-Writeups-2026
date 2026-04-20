#!/usr/bin/env python3
"""
Try to extract data by looking at time-varying frequency patterns
Look for binary encoding in frequency shifts
"""
import numpy as np
import scipy.io.wavfile as wavfile
from scipy import signal
from PIL import Image

def extract_by_chunks():
    """Extract image by analyzing chunks of audio"""
    audio_path = 'Echoes_in_the_Aether.wav'
    
    sr, y = wavfile.read(audio_path)
    if len(y.shape) > 1:
        y = y[:, 0]
    y = np.float32(y) / np.max(np.abs(y))
    
    print(f"Audio: {len(y)} samples at {sr}Hz, {len(y)/sr:.1f}s")
    
    # Process in chunks - analyze dominant frequency in each chunk
    chunk_duration = 0.1  # 100ms chunks
    chunk_size = int(sr * chunk_duration)
    
    dominantfreqs = []
    
    for i in range(0, len(y) - chunk_size, chunk_size):
        chunk = y[i:i+chunk_size]
        
        # FFT
        fft_result = np.abs(np.fft.fft(chunk))
        freqs = np.fft.fftfreq(len(chunk), 1/sr)
        
        # Find dominant frequency in 1500-2300Hz range
        mask = (freqs >= 1500) & (freqs <= 2300)
        if np.any(mask):
            max_idx = np.argmax(fft_result[mask])
            idx = np.where(mask)[0][max_idx]
            dom_freq = freqs[idx]
            dom_mag = fft_result[idx]
            dominantfreqs.append((dom_freq, dom_mag))
        else:
            dominantfreqs.append((0, 0))
    
    print(f"Extracted {len(dominantfreqs)} frequency samples")
    
    freqs_array = np.array([f for f, _ in dominantfreqs])
    mags_array = np.array([m for _, m in dominantfreqs])
    
    print(f"Frequency range: {np.min(freqs_array[freqs_array > 0]):.1f}Hz to {np.max(freqs_array):.1f}Hz")
    print(f"Magnitude range: {np.min(mags_array):.2f} to {np.max(mags_array):.2f}")
    
    # Normalize frequencies to 0-255
    valid_mask = freqs_array > 0
    if np.any(valid_mask):
        freq_min = np.min(freqs_array[valid_mask])
        freq_max = np.max(freqs_array[valid_mask])
        
        freq_norm = np.zeros_like(freqs_array)
        freq_norm[valid_mask] = (freqs_array[valid_mask] - freq_min) / (freq_max - freq_min) * 255
        freq_norm = freq_norm.astype(np.uint8)
        
        # Try different dimensions
        for width in [256, 512, 1024]:
            if len(freq_norm) >= width:
                height = len(freq_norm) // width
                if height > 0:
                    data = freq_norm[:width * height]
                    img = Image.fromarray(data.reshape((height, width)), mode='L')
                    filename = f'sstv_chunks_{width}x{height}.png'
                    img.save(filename)
                    print(f"Saved: {filename}")
                    
                    # Also inverted
                    img_inv = Image.fromarray(255 - data.reshape((height, width)), mode='L')
                    filename_inv = f'sstv_chunks_{width}x{height}_inv.png'
                    img_inv.save(filename_inv)
                    print(f"Saved: {filename_inv}")

def extract_as_video_stream():
    """Treat the spectrogram as a video stream - each time frame is a row"""
    audio_path = 'Echoes_in_the_Aether.wav'
    
    sr, y = wavfile.read(audio_path)
    if len(y.shape) > 1:
        y = y[:, 0]
    y = np.float32(y) / np.max(np.abs(y))
    
    # Get spectrogram with consistent frame rate
    window_size = 512
    hop_size = 256
    
    f, t, Sxx = signal.spectrogram(y, sr, nperseg=window_size, noverlap=window_size-hop_size)
    
    print(f"\nVideo stream approach:")
    print(f"Spectrogram shape: {Sxx.shape} (freqs x time)")
    
    # Extract just the 1500-2300Hz range
    f_min_idx = np.argmin(np.abs(f - 1500))
    f_max_idx = np.argmin(np.abs(f - 2300))
    
    sxx_band = Sxx[f_min_idx:f_max_idx+1, :]
    
    # Normalize
    sxx_db = 10 * np.log10(np.maximum(sxx_band, 1e-10))
    sxx_norm = (sxx_db - np.min(sxx_db)) / (np.max(sxx_db) - np.min(sxx_db)) * 255
    sxx_norm = sxx_norm.astype(np.uint8)
    
    # sxx_norm shape is (n_freqs, n_timeframes)
    # Treat as (height, width) image where time is horizontal
    img = Image.fromarray(sxx_norm, mode='L')
    
    # Upscale the width to see details
    new_size = (sxx_norm.shape[1] * 4, sxx_norm.shape[0] * 2)
    img_scaled = img.resize(new_size, Image.Resampling.NEAREST)
    img_scaled.save('sstv_as_video.png')
    print(f"Saved sstv_as_video.png ({new_size[0]}x{new_size[1]})")
    
    # Also save inver
    img_inv = Image.fromarray(255 - sxx_norm, mode='L')
    img_inv_scaled = img_inv.resize(new_size, Image.Resampling.NEAREST)
    img_inv_scaled.save('sstv_as_video_inv.png')
    print(f"Saved sstv_as_video_inv.png")
    
    # Try different crops/scalings
    for height_scale in [1, 2, 4]:
        for width_scale in [1, 2, 4, 8]:
            h = sxx_norm.shape[0] * height_scale
            w = sxx_norm.shape[1] * width_scale
            if w > 10000:  # Skip very large images
                continue
            img_var = img.resize((w, h), Image.Resampling.NEAREST)
            img_var.save(f'sstv_video_x{width_scale}y{height_scale}.png')
    
    print("Saved multiple scaled variants")

if __name__ == '__main__':
    extract_by_chunks()
    extract_as_video_stream()
