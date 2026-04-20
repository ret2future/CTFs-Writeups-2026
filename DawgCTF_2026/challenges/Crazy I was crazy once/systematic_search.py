#!/usr/bin/env python3

# Read the crazy.txt file
with open('crazy.txt', 'r') as f:
    content = f.read()

import re

print("=== SYSTEMATIC FLAG SEARCH ===")

# Let me approach this completely differently
# Maybe the flag is constructed from specific positions or patterns

# 1. Look for any occurrence of characters that could form "dawgctf{" when decoded
print("1. SEARCHING FOR DAWGCTF PATTERNS")

# Basic leetspeak mapping
leet_to_alpha = {
    '0': 'o', '1': 'i', '2': 'z', '3': 'e', '4': 'a', '5': 's',
    '6': 'g', '7': 't', '8': 'b', '9': 'p'
}

def decode_basic(text):
    decoded = text
    for leet, alpha in leet_to_alpha.items():
        decoded = decoded.replace(leet, alpha)
    return decoded

# Look for patterns that could be "dawgctf" in various leetspeak forms
dawg_patterns = [
    r'dawgctf',
    r'd4wgctf', 
    r'dawgctf',
    r'd4wgc7f',
    r'dawgctf',
    r'd4wgctf',
    r'dawgctf'
]

for pattern in dawg_patterns:
    matches = re.findall(pattern, content, re.IGNORECASE)
    if matches:
        print(f"Found {pattern}: {matches}")

# 2. Look for the pattern in decoded content
decoded = decode_basic(content)
for pattern in dawg_patterns:
    matches = re.findall(pattern, decoded, re.IGNORECASE)
    if matches:
        print(f"Found {pattern} in decoded: {matches}")

# 3. Maybe the flag is hidden in the corruption pattern
print("\n2. ANALYZING CORRUPTION PATTERN")

# The file seems to get progressively more corrupted
# Let me look at the first occurrence of each pattern
sections = content.split('drove me ')
print(f"Number of 'drove me' sections: {len(sections) - 1}")

# Extract the first few characters from each section
section_starts = []
for i, section in enumerate(sections[1:]):
    # Get the first meaningful characters
    match = re.match(r'\s*([^a-zA-Z]*)([a-zA-Z0-9_{}]+)', section)
    if match:
        section_starts.append(match.group(2))
        print(f"Section {i+1}: '{match.group(2)}'")
    else:
        # Try to get any non-whitespace
        non_ws = re.match(r'\s*([^\s]+)', section)
        if non_ws:
            section_starts.append(non_ws.group(1))
            print(f"Section {i+1} (raw): '{non_ws.group(1)}'")

# 4. Try different concatenation orders
print(f"\n3. TRYING DIFFERENT CONSTRUCTIONS")

if len(section_starts) >= 7:
    # Original order
    original = ''.join(section_starts[:7])
    print(f"Original order: {original}")
    print(f"Reversed: {original[::-1]}")
    
    # Maybe it's not the first 7, but a different subset
    for i in range(5, min(12, len(section_starts))):
        subset = section_starts[:i]
        concat = ''.join(subset)
        reversed_concat = concat[::-1]
        
        # Look for flag-like patterns
        if '{' in reversed_concat and '}' in reversed_concat:
            print(f"First {i} sections reversed: {reversed_concat}")
            
            # Try to extract flag content
            flag_match = re.search(r'\{[^}]*\}', reversed_concat)
            if flag_match:
                flag_content = flag_match.group()
                potential_flag = "dawgctf" + flag_content
                print(f"Potential flag: {potential_flag}")

# 5. Look at the specific "i{F" pattern more carefully
print(f"\n4. ANALYZING i{{F}} PATTERN")

# Find all occurrences of "i{F"
if_positions = []
start = 0
while True:
    pos = content.find('i{F', start)
    if pos == -1:
        break
    if_positions.append(pos)
    start = pos + 1

print(f"Found 'i{{F}}' at positions: {if_positions}")

# Extract context around each occurrence
for pos in if_positions:
    context_start = max(0, pos - 100)
    context_end = min(len(content), pos + 100)
    context = content[context_start:context_end]
    print(f"Context around position {pos}: {context}")

# 6. Maybe the flag is constructed from the END of sections
print(f"\n5. LOOKING AT SECTION ENDINGS")

section_endings = []
for i, section in enumerate(sections[1:]):
    # Look at the end of each section (before the next "drove me" or end)
    next_drove = section.find('drove me')
    if next_drove != -1:
        end_part = section[:next_drove]
    else:
        end_part = section
    
    # Get the last meaningful characters
    lines = end_part.strip().split('\n')
    if lines:
        last_line = lines[-1]
        # Extract alphanumeric characters from the end
        match = re.search(r'([a-zA-Z0-9_{}]+)$', last_line)
        if match:
            section_endings.append(match.group(1))
            print(f"Section {i+1} ending: '{match.group(1)}'")

# 7. Try constructing from endings
if section_endings:
    endings_concat = ''.join(section_endings)
    print(f"\nSection endings concatenated: {endings_concat}")
    print(f"Section endings reversed: {endings_concat[::-1]}")

# 8. Final attempt - maybe I need to look at the problem differently
print(f"\n6. FINAL ATTEMPT - COMPREHENSIVE SEARCH")

# Let me look for ANY sequence that could be a flag
# Search for any pattern that has brackets and reasonable length
all_possible = re.findall(r'\{[a-zA-Z0-9_]{5,30}\}', content)
print(f"All bracketed patterns: {all_possible}")

# Also in decoded content
decoded_possible = re.findall(r'\{[a-zA-Z0-9_]{5,30}\}', decoded)
print(f"All bracketed patterns in decoded: {decoded_possible}")

# Maybe the flag doesn't use brackets - let me look for "dawgctf" followed by anything
dawg_followed = re.findall(r'dawgctf[a-zA-Z0-9_{}]*', decoded, re.IGNORECASE)
print(f"DAWGCTF followed by anything: {dawg_followed}")

# Manual check based on what I found so far
print(f"\n7. MANUAL RECONSTRUCTION")
# I know the parts are: }pl, eh_, dne, s_e, sae, lp_, efi
# When reversed: ife_please_send_help}
# But this doesn't include "dawgctf{" prefix

# Maybe "dawgctf{" is hidden somewhere else
# Let me look at the very beginning of the file
beginning = content[:500]
print(f"Beginning of file: {beginning}")

# And decode it
decoded_beginning = decode_basic(beginning)
print(f"Decoded beginning: {decoded_beginning}")

# Look for any dawgctf pattern in the beginning
for pattern in dawg_patterns:
    matches = re.findall(pattern, decoded_beginning, re.IGNORECASE)
    if matches:
        print(f"Found {pattern} at beginning: {matches}")
