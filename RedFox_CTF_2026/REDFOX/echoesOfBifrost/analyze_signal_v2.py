#!/usr/bin/env python3
import numpy as np
import scipy.io.wavfile as wavfile
from scipy import signal
from scipy.fft import fft
from PIL import Image
import warnings
warnings.filterwarnings('ignore')

def analyze_audio():
    """Analyze the audio file for hidden messages"""
    audio_path = 'Echoes_in_the_Aether.wav'
    
    # Load audio file
    sr, y = wavfile.read(audio_path)
    if len(y.shape) > 1:
        y = y[:, 0]  # Take first channel if stereo
    y = y.astype(float) / np.max(np.abs(y))  # Normalize
    
    print(f"Audio loaded: {len(y)} samples at {sr}Hz")
    print(f"Duration: {len(y)/sr:.2f} seconds")
    
    # 1. Check for basic statistics
    print(f"\nAudio Statistics:")
    print(f"  Min: {np.min(y):.4f}")
    print(f"  Max: {np.max(y):.4f}")
    print(f"  Mean: {np.mean(y):.4f}")
    print(f"  Std: {np.std(y):.4f}")
    
    # 2. Perform FFT for frequency analysis
    print("\n--- Frequency Analysis ---")
    fft_result = np.abs(fft(y))
    freqs = np.fft.fftfreq(len(y), 1/sr)
    
    # Get positive frequencies only
    positive_freqs = freqs[:len(freqs)//2]
    positive_fft = fft_result[:len(fft_result)//2]
    
    # Find peaks in frequency domain
    peaks, heights = signal.find_peaks(positive_fft, height=np.max(positive_fft) * 0.05)
    peak_freqs = positive_freqs[peaks]
    peak_heights = heights['peak_heights']
    
    # Sort by strength
    sorted_idx = np.argsort(peak_heights)[::-1]
    top_peaks = sorted_idx[:20]
    
    print(f"Top frequency peaks:")
    for idx in top_peaks:
        print(f"  {positive_freqs[peaks[idx]]:.1f}Hz - Magnitude: {heights['peak_heights'][idx]:.2f}")
    
    # 3. Try LSB encoding
    print("\n--- Checking for LSB Encoding ---")
    y_int = np.int16(y * 32767)
    lsb_bits = y_int & 1
    
    lsb_bytes = []
    for i in range(0, len(lsb_bits) - 8, 8):
        byte_val = 0
        for j in range(8):
            byte_val = (byte_val << 1) | lsb_bits[i + j]
        lsb_bytes.append(byte_val)
    
    # Try different interpretations
    printable_lsb = ''.join(chr(b) if 32 <= b <= 126 else '?' for b in lsb_bytes)
    print(f"LSB decoded (first 300 chars):\n{printable_lsb[:300]}")
    
    # 4. Spectrogram analysis
    print("\n--- Creating Spectrogram ---")
    f, t, Sxx = signal.spectrogram(y, sr)
    
    # Normalize for image
    Sxx_db = 10 * np.log10(np.maximum(Sxx, 1e-10))
    Sxx_normalized = (Sxx_db - np.min(Sxx_db)) / (np.max(Sxx_db) - np.min(Sxx_db)) * 255
    Sxx_normalized = Sxx_normalized.astype(np.uint8)
    
    print(f"Spectrogram shape: {Sxx_normalized.shape}")
    
    # Save spectrogram as image
    img = Image.fromarray(Sxx_normalized, mode='L')
    img.save('spectrogram.png')
    print("Spectrogram saved to spectrogram.png")
    
    # 5. Try MSB analysis
    print("\n--- Checking MSB pattern ---")
    msb_bits = (y_int >> 8) & 0xFF
    msb_bytes = []
    for i in range(0, len(msb_bits) - 8, 8):
        byte_val = 0
        for j in range(8):
            byte_val = (byte_val << 1) | ((msb_bits[i + j] >> (7-j)) & 1)
        msb_bytes.append(byte_val)
    
    printable_msb = ''.join(chr(b) if 32 <= b <= 126 else '?' for b in msb_bytes)
    print(f"MSB decoded (first 300 chars):\n{printable_msb[:300]}")
    
    # 6. Check for specific audio steganography techniques
    print("\n--- Advanced Analysis ---")
    # Envelope detection
    analytic_signal = signal.hilbert(y)
    amplitude = np.abs(analytic_signal)
    phase = np.unwrap(np.angle(analytic_signal))
    
    print(f"Amplitude envelope - Min: {np.min(amplitude):.4f}, Max: {np.max(amplitude):.4f}")
    print(f"Phase range: {np.min(phase):.2f} to {np.max(phase):.2f}")
    
    # 7. Check if audio is actually an image in spectral form
    print("\n--- Attempting SSTV Decoding ---")
    # SSTV typically uses specific sync tones
    # Check for 1200Hz and 2300Hz (common SSTV markers)
    print("Checking for typical SSTV markers (1200Hz, 2300Hz)...")
    
    # Bandpass around 1200Hz
    sos_1200 = signal.butter(4, [1000, 1400], 'band', fs=sr, output='sos')
    filtered_1200 = signal.sosfilt(sos_1200, y)
    energy_1200 = np.sum(filtered_1200 ** 2)
    print(f"Energy at 1200Hz range: {energy_1200:.6f}")
    
    # Bandpass around 2300Hz
    sos_2300 = signal.butter(4, [2000, 2600], 'band', fs=sr, output='sos')
    filtered_2300 = signal.sosfilt(sos_2300, y)
    energy_2300 = np.sum(filtered_2300 ** 2)
    print(f"Energy at 2300Hz range: {energy_2300:.6f}")
    
    # 8. Try to decode as steganographic image in various ways
    print("\n--- Extracting data variants ---")
    
    # Get more samples from spectrogram
    for scale in [1, 2, 4]:
        height = min(Sxx_normalized.shape[0] // scale, 256)
        width = min(Sxx_normalized.shape[1], 512)
        cropped = Sxx_normalized[:height, :width]
        if cropped.size > 0:
            img_variant = Image.fromarray(cropped, mode='L')
            variant_name = f'spectrogram_variant_h{height}_w{width}.png'
            img_variant.save(variant_name)
            print(f"Saved: {variant_name}")

if __name__ == '__main__':
    analyze_audio()
