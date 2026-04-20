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

# Extract ALL "drove me" patterns
drove_patterns = re.findall(r'drove me ([a-zA-Z0-9_{}]+)', decoded_content)

print("All 'drove me' patterns:")
for i, pattern in enumerate(drove_patterns):
    print(f"{i+1:2d}: '{pattern}'")

# Concatenate all patterns
all_concatenated = ''.join(drove_patterns)
print(f"\nAll concatenated: '{all_concatenated}'")

# Reverse it
reversed_all = all_concatenated[::-1]
print(f"Reversed: '{reversed_all}'")

# Look for flag patterns
flag_match = re.search(r'dawgctf\{[^}]+\}', reversed_all)
if flag_match:
    print(f"\nFlag found: {flag_match.group()}")
else:
    # Try to find anything that looks like a flag
    possible_flag = re.search(r'\{[^}]+\}', reversed_all)
    if possible_flag:
        flag_content = possible_flag.group()
        complete_flag = "dawgctf" + flag_content
        print(f"\nPossible flag: {complete_flag}")
    else:
        print(f"\nNo clear flag pattern found in reversed string")
        
        # Maybe we need to look at it differently
        # Let's try different combinations
        print(f"\nTrying different approaches...")
        
        # Maybe it's not all patterns, maybe only some?
        for i in range(len(drove_patterns), 0, -1):
            subset = drove_patterns[:i]
            subset_concat = ''.join(subset)
            subset_reversed = subset_concat[::-1]
            
            flag_match = re.search(r'\{[^}]+\}', subset_reversed)
            if flag_match:
                flag_content = flag_match.group()
                complete_flag = "dawgctf" + flag_content
                print(f"Flag with first {i} patterns: {complete_flag}")
                break
