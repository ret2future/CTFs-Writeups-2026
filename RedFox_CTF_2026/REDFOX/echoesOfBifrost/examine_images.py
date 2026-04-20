#!/usr/bin/env python3
"""
Examine the wide image data to see if it contains readable text
"""
import numpy as np
from PIL import Image

# Load the widest image we created
img = Image.open('sstv_as_video.png')
img_array = np.array(img)

print(f"Image shape: {img_array.shape}")
print(f"Image size: {img.size}")

# Sample the image data
print(f"Sample pixels from start:")
print(img_array[:, :100])

print(f"\nSample pixels from middle:")
mid = img_array.shape[1] // 2
print(img_array[:, mid:mid+100])

# Look for patterns - maybe text can be recognized
# Try to find where pixels transition from dark to light (possible letter boundaries)

# Get the average pixel value across vertical axis (for each column)
avg_per_col = np.mean(img_array, axis=0)

# Find where there are significant changes
diffs = np.abs(np.diff(avg_per_col))
peaks = np.where(diffs > np.std(diffs) * 3)[0]

print(f"\nFound {len(peaks)} potential column transitions")
if len(peaks) > 0:
    print(f"First few: {peaks[:20]}")

# Try the inverted version
img_inv = Image.open('sstv_as_video_inv.png')
img_inv_array = np.array(img_inv)

print(f"\nInverted image shape: {img_inv_array.shape}")

# Let's try to find if there's any text-like pattern
# Crop to a portion and save for manual inspection
crops_to_try = [
    (0, 4000),          # Start
    (30000, 34000),     # Middle
    (60000, 64000),     # Later middle
    (100000, 104000),   # Near end
]

for start, end in crops_to_try:
    if end < img_array.shape[1]:
        # Crop original
        crop = img_array[:, start:end]
        crop_img = Image.fromarray(crop, mode='L')
        crop_img_scaled = crop_img.resize((crop.shape[1] * 10, crop.shape[0] * 20), Image.Resampling.NEAREST)
        filename = f'crop_{start}_{end}.png'
        crop_img_scaled.save(filename)
        print(f"Saved crop: {filename}")

# Maybe the data needs different interpretation
# Try treating as if it needs vertical scanning
print("\n--- Trying transposed interpretation ---")
img_t = Image.fromarray(img_array.T, mode='L')
img_t_small = img_t.resize((img_t.size[0]//10, img_t.size[1]//10), Image.Resampling.LANCZOS)
img_t_small.save('sstv_transposed_small.png')
print("Saved transposed version")
