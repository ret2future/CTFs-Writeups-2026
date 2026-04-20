#!/usr/bin/env python3
"""
Try inverted and rotated versions of the images
"""
import numpy as np
from PIL import Image
import subprocess

def process_variants():
    """Create various transformations of the equalized image"""
    
    img = Image.open('sstv_equalized.png')
    img_arr = np.array(img)
    
    # Try inversion
    inv_arr = 255 - img_arr
    inv_img = Image.fromarray(inv_arr.astype(np.uint8))
    inv_img.save('sstv_equalized_inverted.png')
    
    # Try rotations
    rot_90 = img.rotate(90, expand=False)
    rot_90.save('sstv_equalized_rot90.png')
    
    rot_180 = img.rotate(180, expand=False)
    rot_180.save('sstv_equalized_rot180.png')
    
    rot_270 = img.rotate(270, expand=False)
    rot_270.save('sstv_equalized_rot270.png')
    
    # Try transposition
    trans_img = img.transpose(Image.TRANSPOSE)
    trans_img.save('sstv_equalized_transpose.png')
    
    # Try flip
    flip_h = img.transpose(Image.FLIP_LEFT_RIGHT)
    flip_h.save('sstv_equalized_flip_h.png')
    
    flip_v = img.transpose(Image.FLIP_TOP_BOTTOM)
    flip_v.save('sstv_equalized_flip_v.png')
    
    # Also invert + rotate90
    inv_rot = rot_90.copy()
    inv_rot.paste(Image.new('L', rot_90.size, color=255))  # No, wrong approach
    inv_rot_arr = 255 - np.array(rot_90)
    inv_rot_img = Image.fromarray(inv_rot_arr.astype(np.uint8))
    inv_rot_img.save('sstv_equalized_inverted_rot90.png')
    
    print("Generated variants:")
    print("  - sstv_equalized_inverted.png")
    print("  - sstv_equalized_rot90.png")
    print("  - sstv_equalized_rot180.png")
    print("  - sstv_equalized_rot270.png")
    print("  - sstv_equalized_transpose.png")
    print("  - sstv_equalized_flip_h.png")
    print("  - sstv_equalized_flip_v.png")
    print("  - sstv_equalized_inverted_rot90.png")

def ocr_variant(img_path, description):
    """Quick OCR on variant"""
    try:
        result = subprocess.run(
            ['tesseract', img_path, 'stdout', '--psm', '3'],
            capture_output=True,
            text=True,
            timeout=5
        )
        text = result.stdout.strip()
        if text and len(text) > 3:
            print(f"\n✓ {description}:")
            print(f"  {text[:150]}")
    except:
        pass

if __name__ == '__main__':
    process_variants()
    
    print("\n" + "="*60)
    print("OCR Testing Variants")
    print("="*60)
    
    variants = [
        ('sstv_equalized_inverted.png', 'Inverted'),
        ('sstv_equalized_rot90.png', 'Rotated 90°'),
        ('sstv_equalized_rot180.png', 'Rotated 180°'),
        ('sstv_equalized_rot270.png', 'Rotated 270°'),
        ('sstv_equalized_transpose.png', 'Transposed'),
        ('sstv_equalized_flip_h.png', 'Flipped Horizontal'),
        ('sstv_equalized_flip_v.png', 'Flipped Vertical'),
        ('sstv_equalized_inverted_rot90.png', 'Inverted + Rot 90°'),
    ]
    
    for img_path, desc in variants:
        ocr_variant(img_path, desc)
