#!/usr/bin/env python3

# Basic leetspeak substitution mapping only
leet_to_alpha_basic = {
    '0': 'o',
    '1': 'i', 
    '2': 'z',
    '3': 'e',
    '4': 'a',
    '5': 's',
    '6': 'g',
    '7': 't',
    '8': 'b',
    '9': 'p'
}

def decode_basic(text):
    """Replace basic leetspeak characters only"""
    decoded = text
    for leet, alpha in leet_to_alpha_basic.items():
        decoded = decoded.replace(leet, alpha)
    return decoded

# Read the crazy.txt file
with open('crazy.txt', 'r') as f:
    content = f.read()

# Decode with basic mapping only
decoded_basic = decode_basic(content)

import re

print("=== BASIC DECODING ANALYSIS ===")

# Extract "drove me" patterns from basic decoding
drove_basic = re.findall(r'drove me ([^c]+?)(?: crazy|$)', decoded_basic)
print(f"Basic 'drove me' patterns: {len(drove_basic)}")
for i, pattern in enumerate(drove_basic):
    print(f"{i+1:2d}: '{pattern.strip()}'")

# Concatenate basic patterns
basic_parts = [p.strip() for p in drove_basic if p.strip()]
basic_concat = ''.join(basic_parts)
basic_reversed = basic_concat[::-1]

print(f"\nBasic concatenated: '{basic_concat}'")
print(f"Basic reversed: '{basic_reversed}'")

# Now let's look at the original content more carefully
print("\n=== ORIGINAL CONTENT ANALYSIS ===")

# Look for patterns in the original that might be the flag
original_drove = re.findall(r'drove me ([^c]+?)(?: cr4zy|$)', content)
print(f"Original 'drove me' patterns: {len(original_drove)}")
for i, pattern in enumerate(original_drove):
    print(f"{i+1:2d}: '{pattern.strip()}'")

# Extract and concatenate original patterns
orig_parts = [p.strip() for p in original_drove if p.strip()]
orig_concat = ''.join(orig_parts)
print(f"\nOriginal concatenated: '{orig_concat}'")
print(f"Original reversed: '{orig_concat[::-1]}'")

# Let's also look at what comes AFTER the last "drove me" pattern
print("\n=== END OF FILE ANALYSIS ===")
last_drove = content.rfind('drove me')
if last_drove != -1:
    after_last = content[last_drove:]
    print(f"Content after last 'drove me': {after_last[:200]}...")

# Look for any patterns that might be missed
print("\n=== ALTERNATIVE PATTERN SEARCH ===")
# Maybe the flag is built differently - let's look at first characters
# of each section after "drove me"
sections = content.split('drove me ')
first_chars = []
for i, section in enumerate(sections[1:]):
    # Get first non-whitespace character(s)
    match = re.match(r'\s*([^a-zA-Z]*)([a-zA-Z0-9_{}]+)', section)
    if match:
        first_chars.append(match.group(2))
        print(f"Section {i+1}: '{match.group(2)}'")

if first_chars:
    chars_concat = ''.join(first_chars)
    print(f"\nFirst chars concatenated: '{chars_concat}'")
    print(f"First chars reversed: '{chars_concat[::-1]}'")

# Let's also try looking at the very end of the file
print("\n=== FILE END ANALYSIS ===")
file_end = content[-500:]  # Last 500 chars
print(f"End of file: {file_end}")

# Look for any characters that stand out at the end
end_patterns = re.findall(r'[a-zA-Z0-9_{}]+', file_end)
print(f"Patterns at end: {end_patterns[-10:]}")
