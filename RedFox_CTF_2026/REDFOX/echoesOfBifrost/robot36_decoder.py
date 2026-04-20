#!/usr/bin/env python3
"""
Implement Robot 36 SSTV Decoder
Robot 36 is the most common SSTV mode
Encoding: 
- 1200Hz = sync tone
- 1500-2300Hz = pixel brightness (1500Hz=black, 2300Hz=white)
- Each scan line takes 88ms + 88ms blanking = 176ms total
- 120 lines = 21.12 seconds for full image
"""
import numpy as np
import scipy.io.wavfile as wavfile
from scipy import signal
from PIL import Image

def robot36_decode():
    """Decode Robot 36 SSTV format"""
    
    audio_path = 'Echoes_in_the_Aether.wav'
    sr, y = wavfile.read(audio_path)
    
    if len(y.shape) > 1:
        y = y[:, 0]
    
    y = np.float32(y) / np.max(np.abs(y))
    
    print("=== Robot 36 SSTV Decoder ===")
    print(f"Audio: {len(y)} samples at {sr}Hz")
    
    # Robot 36 specifications:
    # - Image resolution: 320x240 pixels
    # - Each horizontal line: 88ms of pixel data (320 pixels / 88ms = 3.636 pixels/ms)
    # - Vertical sync: 5ms at 1200Hz
    # - Blanking: 88ms
    # Total per line: 5 + 88 + 88 = 181ms
    
    # Detect sync pulses (1200Hz tone)
    # Then read pixel data between syncs
    
    # Low-pass filter to detect sync tones
    sync_freq = 1200
    pixel_freq_min = 1500
    pixel_freq_max = 2300
    
    # Design filters
    sos_sync = signal.butter(6, [1100, 1300], 'band', fs=sr, output='sos')
    y_sync = signal.sosfilt(sos_sync, y)
    
    sos_pixel = signal.butter(6, [pixel_freq_min, pixel_freq_max], 'band', fs=sr, output='sos')
    y_pixel = signal.sosfilt(sos_pixel, y)
    
    # Detect sync pulses (threshold)
    sync_envelope = np.abs(signal.hilbert(y_sync))
    sync_threshold = np.mean(sync_envelope) + 2 * np.std(sync_envelope)
    sync_detected = sync_envelope > sync_threshold
    
    # Find transitions
    sync_transitions = np.where(np.diff(sync_detected.astype(int)) != 0)[0]
    
    print(f"Detected {len(sync_transitions)} sync transitions")
    
    if len(sync_transitions) > 0:
        print(f"Sync transition spacing (should be regular):")
        transition_gaps = np.diff(sync_transitions[:50])
        print(f"  Mean: {np.mean(transition_gaps):.0f} samples")
        print(f"  Std: {np.std(transition_gaps):.0f} samples")
        print(f"  Expected for 181ms per line: {int(sr * 0.181)} samples")
    
    # Extract pixels between syncs
    pixels_data = []
    
    if len(sync_transitions) >= 4:
        # Assumes sync pattern: on, off, on, off, ...
        for i in range(1, len(sync_transitions) - 2, 2):
            sync_end = sync_transitions[i]
            next_sync_start = sync_transitions[i + 1]
            
            # Extract pixel data in this interval (should be ~88ms)
            pixel_interval = y_pixel[sync_end:next_sync_start]
            
            if len(pixel_interval) > 0:
                # Get instantaneous frequency from phase
                analytic = signal.hilbert(pixel_interval)
                phase = np.unwrap(np.angle(analytic))
                
                # Derivative gives frequency deviation
                if len(phase) > 1:
                    inst_freq_dev = np.diff(phase) * sr / (2 * np.pi)
                    
                    #Map frequency to brightness
                    # 1500Hz -> 0 (black), 2300Hz -> 255 (white)
                    brightness = (inst_freq_dev - pixel_freq_min) / (pixel_freq_max - pixel_freq_min) * 255
                    brightness = np.clip(brightness, 0, 255).astype(np.uint8)
                    
                    pixels_data.append(brightness)
    
    print(f"Extracted {len(pixels_data)} pixel arrays")
    
    # Stack into image
    if len(pixels_data) > 100:
        # Take first N complete pixel arrays
        pixel_arrays = pixels_data[:120]  # 120 lines for Robot 36
        
        # Each array should have ~320 pixels (320 pixels * 3.636 pixels/ms / sr*1000ms)
        # At 48kHz, 88ms = 4224 samples, so ~4224 / 320 = 13.2 samples/pixel
        
        # Downsample each array to 320 pixels
        processed_lines = []
        for pixels in pixel_arrays:
            if len(pixels) > 100:
                # Resample to 320 pixels
                x_old = np.linspace(0, 1, len(pixels))
                x_new = np.linspace(0, 1, 320)
                pixels_resampled = np.interp(x_new, x_old, pixels).astype(np.uint8)
                processed_lines.append(pixels_resampled)
        
        if processed_lines:
            img_data = np.array(processed_lines)
            print(f"Image shape: {img_data.shape}")
            
            img = Image.fromarray(img_data, mode='L')
            img.save('sstv_robot36_decoded.png')
            print("Saved: sstv_robot36_decoded.png")
            
            # Try inverted
            img_inv = Image.fromarray(255 - img_data, mode='L')
            img_inv.save('sstv_robot36_decoded_inv.png')
            print("Saved: sstv_robot36_decoded_inv.png")

if __name__ == '__main__':
    robot36_decode()
