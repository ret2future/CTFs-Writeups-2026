import wave
import numpy as np
import struct

# Load WAV
wf = wave.open('/tmp/teeny.wav', 'rb')
n_frames = wf.getnframes()
raw = wf.readframes(n_frames)
framerate = wf.getframerate()
wf.close()

samples = np.frombuffer(raw, dtype=np.int16).astype(float)
duration = len(samples) / framerate
print(f'Duration: {duration:.2f}s at {framerate}Hz')

# Check amplitude envelope over time (100ms windows)
window = int(framerate * 0.1)
energies = []
for i in range(0, len(samples) - window, window // 2):
    seg = samples[i:i+window]
    energies.append(np.sqrt(np.mean(seg**2)))

print(f'Energy over time (RMS per 100ms):')
for i, e in enumerate(energies):
    t = i * 0.05
    bar = '#' * int(e / max(energies) * 40)
    threshold = '|' if e > max(energies) * 0.1 else ' '
    print(f'  {t:5.2f}s: {threshold}{bar}')

# Detect DTMF tones
dtmf_freqs_row = [697, 770, 852, 941]
dtmf_freqs_col = [1209, 1336, 1477, 1633]
dtmf_map = {
    (697, 1209): '1', (697, 1336): '2', (697, 1477): '3', (697, 1633): 'A',
    (770, 1209): '4', (770, 1336): '5', (770, 1477): '6', (770, 1633): 'B',
    (852, 1209): '7', (852, 1336): '8', (852, 1477): '9', (852, 1633): 'C',
    (941, 1209): '*', (941, 1336): '0', (941, 1477): '#', (941, 1633): 'D',
}

# Decode Morse code - check for beep patterns
# High energy = dash/dot, low = gap
threshold = max(energies) * 0.15
is_high = [e > threshold for e in energies]
print('\nHigh/low energy pattern:')
pattern = ''.join(['H' if h else 'L' for h in is_high])
print(pattern)

# Look for Morse: run-length encode the pattern
runs = []
cur = is_high[0]
cnt = 0
for h in is_high:
    if h == cur:
        cnt += 1
    else:
        runs.append((cur, cnt * 0.05))
        cur = h
        cnt = 1
runs.append((cur, cnt * 0.05))

print('\nRun-length encoded:')
for is_h, dur in runs:
    print(f'  {"HIGH" if is_h else " low"}: {dur:.2f}s')
