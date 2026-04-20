#!/usr/bin/env python3

# Extended leetspeak substitution mapping
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
    '+': 't',
    '|': 'i',
    '(': 'c',
    ')': 'd',
    '_': ' ',
    '/': 'v',
    '\\': 'v',
    '|': 'l',
    '[': 'j',
    ']': 'k',
    '{': 'f',
    '}': 'j',
    '<': 'c',
    '>': 'd',
    '&': 'b',
    '%': 'x',
    '^': 'n',
    '*': 'm',
    '-': 'h',
    '=': 'r'
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

# Decode with extended mapping
decoded_content = decode_leetspeak(content)

import re

# Look for ALL patterns that might contain flag pieces
print("=== COMPREHENSIVE ANALYSIS ===")

# 1. Look for any "drove me" patterns more carefully
drove_patterns = re.findall(r'drove me ([a-zA-Z0-9_{}]+)', decoded_content)
print(f"All 'drove me' patterns: {len(drove_patterns)}")
for i, pattern in enumerate(drove_patterns):
    print(f"{i+1:2d}: '{pattern}'")

# 2. Look for any unusual character sequences in the entire file
print("\n=== UNUSUAL SEQUENCES ===")
unusual = re.findall(r'[a-zA-Z0-9_{}]{8,}', decoded_content)
unique_unusual = []
for seq in unusual:
    if not seq.startswith('crazy') and not seq.startswith('room') and not seq.startswith('round') and not seq.startswith('rubber') and not seq.startswith('wheels') and not seq.startswith('rats'):
        if seq not in unique_unusual:
            unique_unusual.append(seq)

for seq in unique_unusual[:20]:
    print(f"Unusual: {seq}")

# 3. Look for specific flag patterns
print("\n=== FLAG PATTERNS ===")
flag_patterns = [
    r'dawgctf\{[^}]+\}',
    r'flag\{[^}]+\}',
    r'\{[a-zA-Z0-9_]+\}',
    r'[a-zA-Z0-9_]+\{[a-zA-Z0-9_]+\}'
]

for pattern in flag_patterns:
    matches = re.findall(pattern, decoded_content)
    if matches:
        print(f"Pattern {pattern}: {matches}")

# 4. Look at the structure more carefully - maybe there are hidden patterns
print("\n=== STRUCTURAL ANALYSIS ===")
# Split by common phrases and look at what's between
splits = ['crazy?', 'drove me', 'room,', 'rats,', 'wheels,']
for split_phrase in splits:
    parts = decoded_content.split(split_phrase)
    interesting_parts = []
    for part in parts:
        if len(part.strip()) > 5 and len(part.strip()) < 50:
            clean_part = re.sub(r'[^a-zA-Z0-9_{}]', '', part.strip())
            if clean_part and len(clean_part) > 3:
                interesting_parts.append(clean_part)
    
    if interesting_parts:
        print(f"After '{split_phrase}': {interesting_parts[:5]}")

# 5. Look at the original encoded content too
print("\n=== ORIGINAL ENCODED ANALYSIS ===")
# Maybe there are patterns in the original leetspeak
original_patterns = re.findall(r'[a-zA-Z0-9_{}]{6,}', content)
unique_original = []
for seq in original_patterns:
    if not seq.startswith('crazy') and not seq.startswith('room') and not seq.startswith('round') and seq not in unique_original:
        unique_original.append(seq)

for seq in unique_original[:15]:
    print(f"Original unusual: {seq}")

# 6. Check if there are any patterns that span multiple "drove me" sections
print("\n=== CROSS-SECTION ANALYSIS ===")
sections = decoded_content.split('drove me ')
for i, section in enumerate(sections[1:10]):  # Look at first 9 sections
    # Extract just the beginning of each section
    beginning = re.match(r'([a-zA-Z0-9_{}]{1,10})', section.strip())
    if beginning:
        print(f"Section {i+1} start: '{beginning.group(1)}'")
