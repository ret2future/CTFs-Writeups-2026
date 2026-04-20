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

# Look for all patterns that might contain flag pieces
# Let's be more thorough and look for any characters after "drove me "
all_drove_patterns = re.findall(r'drove me ([^c]+?)(?: crazy|$)', decoded_content)

print("All 'drove me' patterns:")
for i, pattern in enumerate(all_drove_patterns):
    print(f"{i+1:2d}: '{pattern.strip()}'")

# Get the first 7 parts (the clean ones)
clean_parts = [p.strip() for p in all_drove_patterns[:7]]
concatenated = ''.join(clean_parts)
reversed_flag = concatenated[::-1]

print(f"\nClean parts: {clean_parts}")
print(f"Concatenated: '{concatenated}'")
print(f"Reversed: '{reversed_flag}'")

# The flag appears to be missing the "dawgctf{" prefix
# Let's check if there are any other patterns that might contain the beginning
# Look for any occurrence of "dawgctf" in the decoded content
dawg_matches = re.findall(r'dawgctf', decoded_content)
print(f"\nFound 'dawgctf' occurrences: {len(dawg_matches)}")

# Look for any characters that might be the beginning of the flag
# Maybe there's a part before "it drove me" that contains "dawgctf{"
before_drove = re.findall(r'[^i]+? it drove me', decoded_content)
print(f"\nParts before 'it drove me': {len(before_drove)}")
for i, part in enumerate(before_drove[:3]):
    print(f"{i+1}: '{part[:50]}...'")

# Let's also check if there are any patterns with "dawgctf{" that we might have missed
flag_patterns = re.findall(r'dawgctf\{[^}]*\}', decoded_content)
print(f"\nComplete flag patterns found: {flag_patterns}")

# The most likely flag is "dawgctf{" + reversed_flag
if reversed_flag.endswith('}'):
    complete_flag = "dawgctf{" + reversed_flag
    print(f"\nMost likely flag: {complete_flag}")
else:
    print(f"\nReversed flag doesn't end with '}}', need to investigate further")
