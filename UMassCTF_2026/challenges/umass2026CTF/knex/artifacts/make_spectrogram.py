import wave, numpy as np, struct

wf = wave.open('/tmp/teeny.wav', 'rb')
raw = wf.readframes(wf.getnframes())
fr = wf.getframerate()
wf.close()
samples = np.frombuffer(raw, dtype=np.int16).astype(float)

# Generate spectrogram PNG manually (no matplotlib needed)
from PIL import Image

# Parameters
fft_size = 256
hop = 64
n_frames = (len(samples) - fft_size) // hop

# Window function
window = np.hanning(fft_size)

# Compute STFT
spec = []
for i in range(n_frames):
    seg = samples[i*hop:i*hop+fft_size] * window
    fft = np.fft.rfft(seg)
    mag = np.abs(fft)
    spec.append(mag)

spec = np.array(spec).T  # shape: (fft_size//2+1, n_frames)
# Log scale
spec = np.log1p(spec)

# Normalize to 0-255
spec_min = spec.min()
spec_max = spec.max()
spec_norm = ((spec - spec_min) / (spec_max - spec_min) * 255).astype(np.uint8)

# Flip vertically (low freq at bottom)
spec_img = spec_norm[::-1, :]

# Create image
h, w = spec_img.shape
img = Image.fromarray(spec_img, mode='L')
img = img.resize((w * 2, h * 4), Image.NEAREST)
img.save('/root/umass2026CTF/knex/artifacts/spectrogram.png')
print(f'Saved spectrogram: {w*2}x{h*4}')

# Also check freq range that's actually interesting(above noise floor)
print(f'\nSpec shape: {spec.shape}')
freqs = np.fft.rfftfreq(fft_size, 1/fr)
print(f'Freq range: {freqs[0]:.0f}-{freqs[-1]:.0f} Hz')
# Find freq bins with most energy
row_energy = spec.mean(axis=1)
top_bins = np.argsort(row_energy)[-10:][::-1]
print('Top frequency bins:')
for b in top_bins:
    print(f'  {freqs[b]:.0f} Hz: avg energy {row_energy[b]:.3f}')
