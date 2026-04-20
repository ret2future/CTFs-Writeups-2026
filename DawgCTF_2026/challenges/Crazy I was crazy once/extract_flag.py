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

# Look for the parts that come after "it drove me " - these seem to contain unique characters
import re

# Extract all occurrences of "it drove me " followed by some characters
matches = re.findall(r'it drove me ([^c]+) crazy', decoded_content)

print("Extracted parts after 'it drove me ':")
for i, match in enumerate(matches):
    print(f"{i+1}: '{match}'")

# Also look for any patterns that might be flag-like
print("\nLooking for flag patterns in the extracted parts:")
for i, match in enumerate(matches):
    # Look for dawgctf{...} pattern
    flag_match = re.search(r'dawgctf\{[^}]+\}', match)
    if flag_match:
        print(f"Flag found in part {i+1}: {flag_match.group()}")
    
    # Look for any bracketed content
    bracket_match = re.search(r'\{[^}]+\}', match)
    if bracket_match:
        print(f"Brackets found in part {i+1}: {bracket_match.group()}")

# Let's also look at the raw content around these areas
print("\nExamining areas around 'it drove me':")
sections = decoded_content.split('it drove me ')
for i, section in enumerate(sections[1:6]):  # Look at first 5 sections
    print(f"Section {i+1}: {section[:50]}...")

# Look for any sequence that might be the flag by finding unusual character sequences
print("\nLooking for unusual character sequences:")
unusual_patterns = re.findall(r'[a-zA-Z0-9_{}]{10,}', decoded_content)
for pattern in unusual_patterns[:10]:  # Show first 10
    if not pattern.startswith('crazy') and not pattern.startswith('room') and not pattern.startswith('round'):
        print(f"Unusual pattern: {pattern}")
