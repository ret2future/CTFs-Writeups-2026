#!/usr/bin/env python3
"""
Create a visual spectrogram that might reveal the hidden message
"""
import numpy as np
import scipy.io.wavfile as wavfile
from scipy import signal
import matplotlib
matplotlib.use('Agg')  # Non-interactive backend
import matplotlib.pyplot as plt

def create_spectrograms():
    audio_path = 'Echoes_in_the_Aether.wav'
    
    sr, y = wavfile.read(audio_path)
    if len(y.shape) > 1:
        y = y[:, 0]
    y = np.float32(y) / np.max(np.abs(y))
    
    print("Creating detailed spectrograms...")
    
    # Create multiple spectrograms with different parameters
    configs = [
        (512, 256, "512_nperseg_256_overlap"),
        (2048, 1024, "2048_nperseg_1024_overlap"),
        (4096, 2048, "4096_nperseg_2048_overlap"),
    ]
    
    for nperseg, noverlap, name in configs:
        print(f"  Generating spectrogram with nperseg={nperseg}, noverlap={noverlap}")
        
        f, t, Sxx = signal.spectrogram(y, sr, nperseg=nperseg, noverlap=noverlap)
        
        plt.figure(figsize=(20, 6))
        plt.pcolormesh(t, f, 10 * np.log10(np.maximum(Sxx, 1e-10)), shading='auto', cmap='viridis')
        plt.ylabel('Frequency [Hz]')
        plt.xlabel('Time [s]')
        plt.colorbar(label='Power [dB]')
        plt.ylim([0, 3000])  # Focus on low frequencies
        plt.title('Spectrogram - Full Range')
        plt.tight_layout()
        filename = f'spec_{name}_full.png'
        plt.savefig(filename, dpi=100, bbox_inches='tight')
        plt.close()
        print(f"    Saved: {filename}")
        
        # Also zoom into SSTV range
        plt.figure(figsize=(20, 6))
        plt.pcolormesh(t, f, 10 * np.log10(np.maximum(Sxx, 1e-10)), shading='auto', cmap='viridis')
        plt.ylabel('Frequency [Hz]')
        plt.xlabel('Time [s]')
        plt.colorbar(label='Power [dB]')
        plt.ylim([1200, 2400])  # SSTV range
        plt.title('Spectrogram - SSTV Range (1200-2400Hz)')
        plt.tight_layout()
        filename_zoom = f'spec_{name}_sstv.png'
        plt.savefig(filename_zoom, dpi=100, bbox_inches='tight')
        plt.close()
        print(f"    Saved: {filename_zoom}")

if __name__ == '__main__':
    create_spectrograms()
