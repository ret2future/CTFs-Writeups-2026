from PIL import Image
import numpy as np

img = Image.open('/root/umass2026CTF/Deep_Down/starting_files/CHALL.gif')

print("Full water row display (rows 38-56, all cols):")
for i in range(12):
    img.seek(i)
    arr = np.array(img.convert('RGB'))
    wm = (arr[:,:,0]==255)&(arr[:,:,1]==255)&(arr[:,:,2]==255)
    print(f"\nFrame {i}:")
    for row in range(38, 56):
        line = ''.join('#' if wm[row,col] else '.' for col in range(100))
        if '#' in line:
            print(f"  y={row}: {line}")
