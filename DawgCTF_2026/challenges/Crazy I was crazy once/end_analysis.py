#!/usr/bin/env python3

# Read the crazy.txt file
with open('crazy.txt', 'r') as f:
    content = f.read()

import re

print("=== END OF FILE ANALYSIS ===")

# Look at the very end of the file
last_1000 = content[-1000:]
print(f"Last 1000 chars:\n{last_1000}")

# Look for any patterns that might be additional flag parts
print("\n=== SEARCHING FOR END PATTERNS ===")

# Look for any sequences that might be flag-related
end_patterns = re.findall(r'[a-zA-Z0-9_{}i{F}]+', last_1000)
print(f"Patterns at end: {end_patterns}")

# Specifically look for the "i{F" pattern I saw
if_pattern = re.search(r'i\{F[^}]*\}', last_1000)
if if_pattern:
    print(f"\nFound i{{F}} pattern: {if_pattern.group()}")

# Look for any complete flag patterns at the end
flag_patterns = re.findall(r'dawgctf\{[^}]+\}', last_1000)
print(f"\nComplete flags at end: {flag_patterns}")

# Look for any partial flag patterns
partial_flags = re.findall(r'\{[^}]*\}', last_1000)
print(f"Partial flags at end: {partial_flags}")

# Let's also decode the end with basic leetspeak
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
    decoded = text
    for leet, alpha in leet_to_alpha_basic.items():
        decoded = decoded.replace(leet, alpha)
    return decoded

decoded_end = decode_basic(last_1000)
print(f"\nDecoded end:\n{decoded_end}")

# Look for patterns in decoded end
decoded_patterns = re.findall(r'[a-zA-Z0-9_{}]+', decoded_end)
print(f"\nPatterns in decoded end: {decoded_patterns[-20:]}")

# Look specifically for any flag-like patterns
decoded_flags = re.findall(r'dawgctf\{[^}]+\}', decoded_end)
print(f"Flags in decoded end: {decoded_flags}")

decoded_partial = re.findall(r'\{[^}]*\}', decoded_end)
print(f"Partial flags in decoded end: {decoded_partial}")

# Let me search the entire file for any "i{F" patterns
print(f"\n=== SEARCHING ENTIRE FILE FOR i{{F}} PATTERNS ===")
if_patterns_full = re.findall(r'i\{F[^}]*\}', content)
print(f"All i{{F}} patterns: {if_patterns_full}")

# Also look for any characters that might form a complete flag when combined
print(f"\n=== COMPREHENSIVE FLAG SEARCH ===")
# Get all the "drove me" parts as before
drove_patterns = re.findall(r'drove me ([^c]+?)(?: cr4zy|$)', content)
parts = [p.strip() for p in drove_patterns if p.strip()]
concatenated = ''.join(parts)
reversed_parts = concatenated[::-1]

print(f"Drove me parts reversed: {reversed_parts}")

# Now add any end patterns we found
if partial_flags:
    for flag in partial_flags:
        if flag not in reversed_parts:
            print(f"Additional pattern found: {flag}")
            # Try combining it
            combined = reversed_parts + flag
            if 'dawgctf{' in combined or combined.endswith('}'):
                print(f"Combined result: {combined}")
