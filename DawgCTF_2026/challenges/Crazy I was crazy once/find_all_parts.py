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

# Extract all occurrences of "it drove me " followed by some characters
import re

# First, let's find all the parts after "it drove me "
matches = re.findall(r'it drove me ([^c]+) crazy', decoded_content)

print(f"Found {len(matches)} parts after 'it drove me ':")
for i, match in enumerate(matches):
    print(f"{i+1:2d}: '{match}'")

# Concatenate all parts to see if they form a flag
concatenated = ''.join(matches)
print(f"\nConcatenated: '{concatenated}'")

# Try reversing it
reversed_concat = concatenated[::-1]
print(f"Reversed: '{reversed_concat}'")

# Look for flag format in the concatenated result
flag_match = re.search(r'dawgctf\{[^}]+\}', concatenated)
if flag_match:
    print(f"\nFlag found: {flag_match.group()}")

flag_match_rev = re.search(r'dawgctf\{[^}]+\}', reversed_concat)
if flag_match_rev:
    print(f"\nFlag found in reversed: {flag_match_rev.group()}")

# Let's also check if there are more parts later in the file
# Look at the entire file structure
print(f"\nTotal file length: {len(decoded_content)} characters")

# Let's examine the file more carefully - maybe there are more parts
# Look for all "drove me" occurrences
all_drove = re.findall(r'drove me ([^c]+)', decoded_content)
print(f"\nAll 'drove me' parts: {len(all_drove)}")
for i, part in enumerate(all_drove[:15]):  # Show first 15
    print(f"{i+1:2d}: '{part}'")

# Concatenate all of these too
all_concat = ''.join(all_drove)
print(f"\nAll concatenated: '{all_concat}'")
print(f"All reversed: '{all_concat[::-1]}'")

# Check for flag in all versions
for text in [all_concat, all_concat[::-1]]:
    flag_match = re.search(r'dawgctf\{[^}]+\}', text)
    if flag_match:
        print(f"\nFlag found: {flag_match.group()}")
