import wave
import numpy as np

w = wave.open('evidence_4b.wav', 'rb')
sr = w.getframerate()
n = w.getnframes()
ch = w.getnchannels()
a = np.frombuffer(w.readframes(n), dtype=np.int16).reshape(-1, ch).astype(np.float64)
L, R = a[:, 0], a[:, 1]

win = 2048
hop = 512
window = np.hanning(win)
fl = []
fr = []
for i in range(0, len(L) - win, hop):
    sL = L[i:i + win] * window
    sR = R[i:i + win] * window
    spL = np.abs(np.fft.rfft(sL))
    spR = np.abs(np.fft.rfft(sR))
    lo = int(100 * win / sr)
    hi = int(6000 * win / sr)
    kL = lo + np.argmax(spL[lo:hi])
    kR = lo + np.argmax(spR[lo:hi])
    fl.append(kL * sr / win)
    fr.append(kR * sr / win)

fl = np.array(fl)
fr = np.array(fr)

x = np.clip(fl, np.percentile(fl, 1), np.percentile(fl, 99))
y = np.clip(fr, np.percentile(fr, 1), np.percentile(fr, 99))

x = (x - x.min()) / (x.max() - x.min() + 1e-9)
y = (y - y.min()) / (y.max() - y.min() + 1e-9)

W, H = 120, 60
hist = np.zeros((H, W), dtype=np.float64)
ix = np.clip((x * (W - 1)).astype(int), 0, W - 1)
iy = np.clip((y * (H - 1)).astype(int), 0, H - 1)
for xx, yy in zip(ix, iy):
    hist[H - 1 - yy, xx] += 1

hist = np.log1p(hist)
hist /= hist.max() if hist.max() > 0 else 1
chars = ' .:-=+*#%@'
for row in hist:
    print(''.join(chars[min(len(chars) - 1, int(v * (len(chars) - 1)))] for v in row).rstrip())

print('freqL range', fl.min(), fl.max(), 'freqR range', fr.min(), fr.max())
