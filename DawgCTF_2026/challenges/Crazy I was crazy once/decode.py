#!/usr/bin/env python3

# Leetspeak substitution mapping
leet_to_alpha = {
    '0': 'o',
    '1': 'i', 
    '2': 'z',
    '3': 'e',
    '4': 'a',
    '5': 's',
    '6': 'g',
    '7': 't',
    '8': 'b',
    '9': 'p',
    '@': 'a',
    '$': 's',
    '!': 'i',
    '+': 't'
}

def decode_leetspeak(text):
    """Replace leetspeak characters with their alphabetic equivalents"""
    decoded = text
    for leet, alpha in leet_to_alpha.items():
        decoded = decoded.replace(leet, alpha)
    return decoded

# Read the crazy.txt file
with open('crazy.txt', 'r') as f:
    content = f.read()

# Decode the content
decoded_content = decode_leetspeak(content)

print("Decoded content:")
print("=" * 50)
print(decoded_content)
print("=" * 50)

# Look for potential flag patterns
import re

# Common flag patterns
flag_patterns = [
    r'dawgctf\{[^}]+\}',
    r'flag\{[^}]+\}', 
    r'DAWGCTF\{[^}]+\}',
    r'FLAG\{[^}]+\}'
]

print("\nSearching for flags:")
for pattern in flag_patterns:
    matches = re.findall(pattern, decoded_content)
    if matches:
        print(f"Found with pattern {pattern}: {matches}")

# Also look for any text that stands out (non-repetitive parts)
print("\nLooking for unique patterns...")
# Split by the repetitive "crazy" text and look for unique parts
parts = decoded_content.split('crazy?')
unique_parts = []
for i, part in enumerate(parts):
    if part.strip() and len(part.strip()) > 10:
        unique_parts.append(part.strip())

for i, part in enumerate(unique_parts[:5]):  # Show first 5 unique parts
    print(f"Unique part {i+1}: {part[:100]}...")
