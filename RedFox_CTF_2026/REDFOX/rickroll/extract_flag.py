#!/usr/bin/env python3
"""
Extract obfuscated flag from layer3.png using geometric transformations,
contrast enhancement, and OCR.
"""
import numpy as np
from PIL import Image, ImageOps, ImageFilter, ImageEnhance
import subprocess
import os
from pathlib import Path

def run_ocr(img_path, whitelist=""):
    """Run tesseract OCR on image."""
    try:
        cmd = ['tesseract', str(img_path), 'stdout', '--psm', '7']
        if whitelist:
            cmd.extend(['-c', f'tessedit_char_whitelist={whitelist}'])
        out = subprocess.check_output(cmd, stderr=subprocess.DEVNULL, text=True)
        return out.strip()
    except:
        return ""

def reverse_swirl(img_array, intensity=2.0):
    """Apply reverse swirl transformation."""
    h, w = img_array.shape[:2]
    cy, cx = h / 2, w / 2
    
    # Create meshgrid
    yy, xx = np.ogrid[:h, :w]
    yc, xc = yy - cy, xx - cx
    
    # Convert to polar
    r = np.sqrt(xc**2 + yc**2)
    theta = np.arctan2(yc, xc)
    
    # Apply reverse swirl
    theta_new = theta - intensity * r / max(h, w)
    
    # Convert back to Cartesian
    xn = r * np.cos(theta_new) + cx
    yn = r * np.sin(theta_new) + cy
    
    # Clip to valid range
    xn = np.clip(xn, 0, w - 1).astype(np.float32)
    yn = np.clip(yn, 0, h - 1).astype(np.float32)
    
    # Bilinear interpolation
    x0, x1 = np.floor(xn).astype(int), np.ceil(xn).astype(int)
    y0, y1 = np.floor(yn).astype(int), np.ceil(yn).astype(int)
    
    wx = xn - x0
    wy = yn - y0
    
    result = (img_array[y0, x0] * (1 - wx) * (1 - wy) +
             img_array[y0, x1] * wx * (1 - wy) +
             img_array[y1, x0] * (1 - wx) * wy +
             img_array[y1, x1] * wx * wy)
    
    return result.astype(np.uint8)

def polar_unwrap(img_array):
    """Convert polar coordinates to Cartesian (unwrap circles)."""
    h, w = img_array.shape[:2]
    cy, cx = h / 2, w / 2
    max_r = max(h, w) / 2
    
    # Create polar grid
    new_h, new_w = h, w
    rr, tt = np.ogrid[0:new_h, 0:new_w]
    
    # Normalize to radius and angle
    r = (rr / new_h) * max_r
    theta = (tt / new_w) * 2 * np.pi
    
    # Convert to Cartesian
    xx = r * np.cos(theta) + cx
    yy = r * np.sin(theta) + cy
    
    xx = np.clip(xx, 0, w - 1).astype(np.float32)
    yy = np.clip(yy, 0, h - 1).astype(np.float32)
    
    x0, x1 = np.floor(xx).astype(int), np.ceil(xx).astype(int)
    y0, y1 = np.floor(yy).astype(int), np.ceil(yy).astype(int)
    
    wx = xx - x0
    wy = yy - y0
    
    result = (img_array[y0, x0] * (1 - wx) * (1 - wy) +
             img_array[y0, x1] * wx * (1 - wy) +
             img_array[y1, x0] * (1 - wx) * wy +
             img_array[y1, x1] * wx * wy)
    
    return result.astype(np.uint8)

def edge_detection(img):
    """Apply Canny edge detection."""
    arr = np.array(img.convert('L'))
    # Simple edge detection via gradient
    gx = np.gradient(arr, axis=1)
    gy = np.gradient(arr, axis=0)
    magnitude = np.sqrt(gx**2 + gy**2)
    magnitude = (255 * magnitude / magnitude.max()).astype(np.uint8)
    return Image.fromarray(magnitude)

def process_image(img_path, output_dir):
    """Apply all transformations and find best OCR result."""
    base_img = Image.open(img_path).convert('L')
    arr = np.array(base_img)
    
    results = []
    os.makedirs(output_dir, exist_ok=True)
    
    # 1. Reverse swirl transformations
    print("Testing reverse swirl transformations...")
    for intensity in np.linspace(0.5, 5.0, 10):
        swirled = reverse_swirl(arr, intensity)
        img_pil = Image.fromarray(swirled)
        
        # Apply contrast enhancement
        enhancer = ImageEnhance.Contrast(img_pil)
        enhanced = enhancer.enhance(2.0)
        
        # Threshold
        bw = enhanced.point(lambda p: 0 if p < 128 else 255)
        
        fname = f"{output_dir}/swirl_i{intensity:.1f}.png"
        bw.save(fname)
        
        txt = run_ocr(fname, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789{}_()")
        score = len([c for c in txt if c.isalnum() or c in '{}_()'  ])
        if txt.strip():
            results.append((score, f"swirl_{intensity:.1f}", txt, fname))
            print(f"  swirl_i{intensity:.1f}: score={score}, text={txt[:60]}")
    
    # 2. Polar coordinate unwrapping
    print("Testing polar coordinate transforms...")
    for angle_offset in [0, 90, 180, 270]:
        # Rotate before unwrap
        rotated = base_img.rotate(angle_offset, expand=False, fillcolor=255)
        rot_arr = np.array(rotated)
        unwrapped = polar_unwrap(rot_arr)
        img_pil = Image.fromarray(unwrapped)
        
        enhancer = ImageEnhance.Contrast(img_pil)
        enhanced = enhancer.enhance(2.0)
        bw = enhanced.point(lambda p: 0 if p < 128 else 255)
        
        fname = f"{output_dir}/polar_rot{angle_offset}.png"
        bw.save(fname)
        
        txt = run_ocr(fname, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789{}_()")
        score = len([c for c in txt if c.isalnum() or c in '{}_()'  ])
        if txt.strip():
            results.append((score, f"polar_rot{angle_offset}", txt, fname))
            print(f"  polar_rot{angle_offset}: score={score}, text={txt[:60]}")
    
    # 3. Simple rotations + contrast
    print("Testing rotations with contrast...")
    for angle in [0, 45, 90, 135, 180, 225, 270, 315]:
        rotated = base_img.rotate(angle, expand=True, fillcolor=255)
        enhancer = ImageEnhance.Contrast(rotated)
        enhanced = enhancer.enhance(3.0)
        bw = enhanced.point(lambda p: 0 if p < 100 else 255)
        
        fname = f"{output_dir}/rotate_{angle}.png"
        bw.save(fname)
        
        txt = run_ocr(fname, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789{}_()")
        score = len([c for c in txt if c.isalnum() or c in '{}_()'  ])
        if txt.strip():
            results.append((score, f"rotate_{angle}", txt, fname))
            print(f"  rotate_{angle}: score={score}, text={txt[:60]}")
    
    # 4. Edge detection
    print("Testing edge detection...")
    edges = edge_detection(base_img)
    enhancer = ImageEnhance.Contrast(edges)
    enhanced = enhancer.enhance(2.0)
    bw = enhanced.point(lambda p: 0 if p < 128 else 255)
    
    fname = f"{output_dir}/edges.png"
    bw.save(fname)
    txt = run_ocr(fname, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789{}_()")
    if txt.strip():
        score = len([c for c in txt if c.isalnum() or c in '{}_()'  ])
        results.append((score, "edges", txt, fname))
        print(f"  edges: score={score}, text={txt[:60]}")
    
    # 5. Invert + various thresholds
    print("Testing inverted variations...")
    inv = ImageOps.invert(base_img.convert('L'))
    for thresh in [50, 100, 150]:
        bw = inv.point(lambda p: 0 if p < thresh else 255)
        fname = f"{output_dir}/inverted_t{thresh}.png"
        bw.save(fname)
        txt = run_ocr(fname, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789{}_()")
        if txt.strip():
            score = len([c for c in txt if c.isalnum() or c in '{}_()'  ])
            results.append((score, f"inverted_t{thresh}", txt, fname))
            print(f"  inverted_t{thresh}: score={score}, text={txt[:60]}")
    
    # Sort by score (descending)
    results.sort(reverse=True, key=lambda x: x[0])
    
    print("\n" + "="*80)
    print("TOP 10 RESULTS:")
    print("="*80)
    for i, (score, name, txt, fname) in enumerate(results[:10], 1):
        print(f"\n{i}. [{name}] (score={score})")
        print(f"   Text: {txt[:100]}")
        print(f"   Image: {fname}")
        
        # Check if it looks like a flag
        if 'redfix' in txt.lower() or 'flag' in txt.lower() or '{' in txt:
            print("   *** POTENTIAL FLAG FOUND ***")
    
    # Save summary
    with open(f"{output_dir}/../flag_results.txt", 'w') as f:
        f.write("FLAG EXTRACTION RESULTS\n")
        f.write("="*80 + "\n\n")
        for i, (score, name, txt, fname) in enumerate(results[:20], 1):
            f.write(f"{i}. [{name}] (score={score})\n")
            f.write(f"   {txt}\n")
            f.write(f"   File: {fname}\n\n")
    
    return results

if __name__ == "__main__":
    source_img = Path("00_source/layer3.png")
    if not source_img.exists():
        print(f"Error: {source_img} not found")
        exit(1)
    
    output_dir = "flag_extraction_tests"
    results = process_image(source_img, output_dir)
    
    print(f"\nAll test images saved to: {output_dir}/")
    print(f"Summary saved to: flag_results.txt")
