#!/usr/bin/env python3
from PIL import Image
import subprocess
import os

im = Image.open('reverse_swirl_neg_-400.png').convert('L')

results = []
for rot in range(0, 360, 15):
    rotated = im.rotate(rot, expand=False, fillcolor=255)
    fname = f'/tmp/rot_{rot}.png'
    rotated.save(fname)
    try:
        txt = subprocess.check_output(['tesseract', fname, 'stdout', '--psm', '7'], 
                                     stderr=subprocess.DEVNULL, text=True, timeout=10)
        score = sum(c.isalnum() for c in txt)
        results.append((score, rot, txt.replace('\n', ' ')[:120]))
    except:
        pass

results.sort(reverse=True)
print("Top rotations of angle -400:")
for score, rot, txt in results[:10]:
    print(f"Rot {rot:3d}°: [{score:2d}] {txt}")
