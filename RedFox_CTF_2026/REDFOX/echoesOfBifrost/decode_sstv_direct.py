#!/usr/bin/env python3
"""
More direct frequency-to-image SSTV decoder
Map frequency values directly to pixel intensity
"""
import numpy as np
import scipy.io.wavfile as wavfile
from scipy import signal
from PIL import Image

def decode_sstv_direct():
    """Decode by mapping frequency to brightness"""
    audio_path = 'Echoes_in_the_Aether.wav'
    
    sr, y = wavfile.read(audio_path)
    if len(y.shape) > 1:
        y = y[:, 0]
    y = np.float32(y) / np.max(np.abs(y))
    
    # Bandpass to isolate image data frequencies (1500-2300 Hz)
    sos = signal.butter(8, [1500, 2300], 'band', fs=sr, output='sos')
    y_filtered = signal.sosfilt(sos, y)
    
    print("Filtered audio to 1500-2300Hz band")
    
    # Instantaneous frequency using phase
    analytic = signal.hilbert(y_filtered)
    phase = np.unwrap(np.angle(analytic))
    # Derivative of phase = frequency deviation
    inst_freq = np.diff(phase) * sr / (2 * np.pi)
    
    # Normalize to 0-255
    freq_data = inst_freq.copy()
    freq_data = np.clip(freq_data, 1500, 2300)
    freq_norm = (freq_data - 1500) / (2300 - 1500) * 255
    freq_norm = freq_norm.astype(np.uint8)
    
    print(f"Extracted {len(freq_norm)} frequency samples")
    print(f"Value range: {np.min(freq_norm)} to {np.max(freq_norm)}")
    
    # Downsample to get image - one pixel per audio frame
    # At 48kHz, with ~2000 pixels, we need 48000 * 183 / 2000 ~ 4430 samples per pixel
    frame_size = 4096
    pixels = []
    for i in range(0, len(freq_norm) - frame_size, frame_size):
        frame = freq_norm[i:i+frame_size]
        pixel_val = np.mean(frame)
        pixels.append(int(pixel_val))
    
    pixels = np.array(pixels, dtype=np.uint8)
    print(f"Extracted {len(pixels)} pixels")
    
    # Try different image dimensions
    test_configs = [
        (256, len(pixels) // 256),  # Use computed height
        (320, len(pixels) // 320),
        (512, len(pixels) // 512),
        (len(pixels) // 256, 256),   # Swap - long horizontal
        (len(pixels) // 192, 192),
    ]
    
    for width, height in test_configs:
        if width > 0 and height > 0 and width * height <= len(pixels):
            try:
                img_data = pixels[:width * height]
                img_2d = img_data.reshape((height, width))
                
                # Save original
                img = Image.fromarray(img_2d, mode='L')
                filename = f'sstv_freqmap_{width}x{height}.png'
                img.save(filename)
                print(f"Saved: {filename}")
                
                # Also save transposed
                img_t = Image.fromarray(np.transpose(img_2d), mode='L')
                filename_t = f'sstv_freqmap_{height}x{width}_transposed.png'
                img_t.save(filename_t)
                print(f"Saved: {filename_t}")
                
                # Save inverted
                img_inv = Image.fromarray(255 - img_2d, mode='L')
                filename_inv = f'sstv_freqmap_{width}x{height}_inverted.png'
                img_inv.save(filename_inv)
                print(f"Saved: {filename_inv}")
                
            except Exception as e:
                print(f"Error with {width}x{height}: {e}")
    
    # Also save the raw pixel stream visualization
    # Reshape to long thin image - see the data structure
    raw_width = min(2048, len(pixels))
    raw_height = (len(pixels) + raw_width - 1) // raw_width
    pixels_padded = np.concatenate([pixels, np.zeros(raw_width * raw_height - len(pixels), dtype=np.uint8)])
    img_raw = Image.fromarray(pixels_padded.reshape((raw_height, raw_width)), mode='L')
    img_raw.save('sstv_raw_stream.png')
    print(f"Saved raw stream: sstv_raw_stream.png ({raw_width}x{raw_height})")

if __name__ == '__main__':
    decode_sstv_direct()
