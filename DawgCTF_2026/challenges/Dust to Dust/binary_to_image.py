#!/usr/bin/env python3

from PIL import Image
import sys

def binary_to_image(input_file, output_file):
    # Read binary data
    with open(input_file, 'r') as f:
        lines = [line.strip() for line in f.readlines() if line.strip()]
    
    if not lines:
        print("No data found in input file")
        return
    
    width = len(lines[0])
    height = len(lines)
    
    print(f"Image dimensions: {height}x{width}")
    
    # Create image
    img = Image.new('RGB', (width, height))
    pixels = img.load()
    
    # Convert binary to pixels (1=white, 0=black)
    for y, line in enumerate(lines):
        for x, bit in enumerate(line):
            if bit == '1':
                pixels[x, y] = (255, 255, 255)  # White
            else:
                pixels[x, y] = (0, 0, 0)        # Black
    
    # Save image
    img.save(output_file)
    print(f"Image saved to {output_file}")

if __name__ == "__main__":
    binary_to_image("original.txt", "flag.png")
