import wave
from pathlib import Path

import numpy as np
from PIL import Image, ImageFilter, ImageOps


def build_density(xs, ys, width=2600, height=1000, clip_lo=1, clip_hi=99, step=2):
    x = xs[::step]
    y = ys[::step]

    x = np.clip(x, np.percentile(x, clip_lo), np.percentile(x, clip_hi))
    y = np.clip(y, np.percentile(y, clip_lo), np.percentile(y, clip_hi))

    x = (x - x.min()) / (x.max() - x.min() + 1e-9)
    y = (y - y.min()) / (y.max() - y.min() + 1e-9)

    ix = np.clip((x * (width - 1)).astype(np.int32), 0, width - 1)
    iy = np.clip(((1.0 - y) * (height - 1)).astype(np.int32), 0, height - 1)

    hist = np.zeros((height, width), dtype=np.float32)
    np.add.at(hist, (iy, ix), 1)

    hist = np.log1p(hist)
    hist = hist / (hist.max() + 1e-9)
    arr = (hist * 255).astype(np.uint8)
    return Image.fromarray(arr, mode='L')


def save_variants(base, stem):
    out = {}
    out[f'{stem}_raw.png'] = base
    out[f'{stem}_flip_h.png'] = ImageOps.mirror(base)
    out[f'{stem}_flip_v.png'] = ImageOps.flip(base)
    out[f'{stem}_rot_180.png'] = base.rotate(180, expand=False)

    for name, img in list(out.items()):
        enhanced = img.filter(ImageFilter.GaussianBlur(0.5))
        enhanced = ImageOps.autocontrast(enhanced, cutoff=1)
        thr = enhanced.point(lambda p: 255 if p > 28 else 0)
        out[name.replace('.png', '_thr.png')] = thr

    for name, img in out.items():
        img.save(name)


def main():
    p = Path('evidence_4b.wav')
    with wave.open(str(p), 'rb') as w:
        n = w.getnframes()
        ch = w.getnchannels()
        data = np.frombuffer(w.readframes(n), dtype=np.int16).reshape(-1, ch)

    L = data[:, 0].astype(np.float64)
    R = data[:, 1].astype(np.float64)

    base = build_density(L, R)
    save_variants(base, 'dens_xy')

    # Also try frequency-track XY projection.
    sr = w.getframerate() if False else 44100
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

    fimg = build_density(np.array(fl), np.array(fr), width=2200, height=1200, step=1)
    save_variants(fimg, 'dens_freqxy')


if __name__ == '__main__':
    main()
