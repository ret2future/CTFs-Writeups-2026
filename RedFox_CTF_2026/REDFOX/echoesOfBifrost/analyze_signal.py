#!/usr/bin/env python3
import numpy as np
import librosa
import matplotlib.pyplot as plt
from scipy import signal
from scipy.fft import fft
from PIL import Image
import warnings
warnings.filterwarnings('ignore')

def analyze_audio():
    """Analyze the audio file for hidden messages"""
    audio_path = 'Echoes_in_the_Aether.wav'
    
    # Load audio file
    y, sr = librosa.load(audio_path, sr=None)
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
    
    # Find peaks in frequency domain
    peaks, _ = signal.find_peaks(fft_result[:len(fft_result)//2], height=np.max(fft_result) * 0.1)
    peak_freqs = freqs[peaks]
    if len(peak_freqs) > 0:
        print(f"Major frequency peaks: {peak_freqs[:20]}")
    
    # 3. Check for SSTV (Slow Scan Television) - common in audio CTFs
    print("\n--- Checking for SSTV patterns ---")
    stft = librosa.stft(y)
    spectrogram = np.abs(stft)
    
    # Look for SSTV-like patterns (specific frequency ranges)
    # Typical SSTV: 1200-1800 Hz for Black, 1500-2300 Hz for pixel data
    spec_db = librosa.power_to_db(spectrogram, ref=np.max)
    
    # 4. Try to extract FSK (Frequency Shift Keying) data
    print("\n--- Attempting FSK Decoding ---")
    # Bandpass filter to isolate signal
    sos = signal.butter(5, [300, 3000], 'band', fs=sr, output='sos')
    filtered = signal.sosfilt(sos, y)
    
    # Find zero crossings to identify FSK transitions
    zero_crossings = np.where(np.diff(np.sign(filtered)))[0]
    print(f"Zero crossings detected: {len(zero_crossings)}")
    
    # 5. Check for steganography in amplitude/phase
    print("\n--- Analyzing Amplitude Modulation ---")
    # Envelope detection
    analytic_signal = signal.hilbert(filtered)
    amplitude_envelope = np.abs(analytic_signal)
    phase = np.unwrap(np.angle(analytic_signal))
    
    # 6. Look for LSB (Least Significant Bit) encoding
    print("\n--- Checking for LSB Encoding ---")
    # Convert audio to 16-bit PCM
    y_int = np.int16(y * 32767)
    lsb_bits = y_int & 1
    
    # Try to decode LSB as bytes
    lsb_bytes = []
    for i in range(0, len(lsb_bits) - 8, 8):
        byte_val = 0
        for j in range(8):
            byte_val = (byte_val << 1) | lsb_bits[i + j]
        lsb_bytes.append(byte_val)
    
    # Filter printable characters
    printable_lsb = ''.join(chr(b) for b in lsb_bytes if 32 <= b <= 126)
    if len(printable_lsb) > 10:
        print(f"Potential LSB message: {printable_lsb[:200]}")
    
    # 7. Try reverse audio
    print("\n--- Analyzing Reversed Audio ---")
    reversed_audio = y[::-1]
    
    # 8. Spectrogram visualization (saved to file)
    print("\n--- Generating spectrogram ---")
    plt.figure(figsize=(12, 4))
    librosa.display.specshow(spec_db, sr=sr, x_axis='time', y_axis='hz')
    plt.colorbar()
    plt.title('Spectrogram')
    plt.savefig('spectrogram.png', dpi=100, bbox_inches='tight')
    print("Spectrogram saved to spectrogram.png")
    
    # 9. Check mel spectrogram patterns
    print("\n--- Analyzing Mel Spectrogram ---")
    mel_spec = librosa.feature.melspectrogram(y=y, sr=sr, n_mels=128)
    mel_db = librosa.power_to_db(mel_spec, ref=np.max)
    
    # 10. Look for image data in spectrogram
    print("\n--- Attempting to extract image from spectrogram ---")
    # Normalize spectrogram for image conversion
    normalized_spec = (spec_db - np.min(spec_db)) / (np.max(spec_db) - np.min(spec_db)) * 255
    normalized_spec = normalized_spec.astype(np.uint8)
    
    # Try different resolutions
    for height in [128, 256, 512]:
        if normalized_spec.shape[0] >= height:
            # Take the first height rows
            img_data = normalized_spec[:height, :]
            img = Image.fromarray(img_data, mode='L')
            img.save(f'spectrogram_extract_{height}.png')
            print(f"Extracted spectrogram as image (height={height}): spectrogram_extract_{height}.png")

if __name__ == '__main__':
    analyze_audio()
