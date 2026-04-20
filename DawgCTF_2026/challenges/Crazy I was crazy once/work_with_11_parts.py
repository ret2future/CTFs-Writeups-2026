#!/usr/bin/env python3

import re

print("=== WORKING WITH 11 PARTS ===")

# The 11 parts I found from extensive decoding:
parts_11 = ['jpl', 'eh', 'dne', 's e', 'sae', 'lp', 'efi', 'l y', 'm f', 'o l', 'ort']

print(f"11 parts: {parts_11}")

# Clean up the parts (remove spaces)
clean_parts_11 = [part.replace(' ', '') for part in parts_11]
print(f"Clean parts: {clean_parts_11}")

# Method 1: Concatenate all 11 and reverse
method1 = ''.join(clean_parts_11)
method1_reversed = method1[::-1]
print(f"\nMethod 1 - All 11 parts:")
print(f"  Concatenated: {method1}")
print(f"  Reversed: {method1_reversed}")
print(f"  Flag: dawgctf{{{method1_reversed}}}")

# Method 2: Maybe only use the first 7 (original approach)
method2 = ''.join(clean_parts_11[:7])
method2_reversed = method2[::-1]
print(f"\nMethod 2 - First 7 parts:")
print(f"  Concatenated: {method2}")
print(f"  Reversed: {method2_reversed}")
print(f"  Flag: dawgctf{{{method2_reversed}}}")

# Method 3: Maybe use the last 7 parts
method3 = ''.join(clean_parts_11[-7:])
method3_reversed = method3[::-1]
print(f"\nMethod 3 - Last 7 parts:")
print(f"  Concatenated: {method3}")
print(f"  Reversed: {method3_reversed}")
print(f"  Flag: dawgctf{{{method3_reversed}}}")

# Method 4: Maybe use parts 2-8 (middle 7)
method4 = ''.join(clean_parts_11[1:8])
method4_reversed = method4[::-1]
print(f"\nMethod 4 - Middle 7 parts (2-8):")
print(f"  Concatenated: {method4}")
print(f"  Reversed: {method4_reversed}")
print(f"  Flag: dawgctf{{{method4_reversed}}}")

# Method 5: Maybe the additional parts change the meaning
# Let me look at what the additional parts spell out
additional_parts = clean_parts_11[7:]  # Parts 8-11
additional_concat = ''.join(additional_parts)
additional_reversed = additional_concat[::-1]
print(f"\nAdditional parts (8-11):")
print(f"  Parts: {additional_parts}")
print(f"  Concatenated: {additional_concat}")
print(f"  Reversed: {additional_reversed}")

# Method 6: Maybe the flag is constructed differently
# Let me try interpreting the parts as words or abbreviations
print(f"\nMethod 6 - Word interpretation:")

# Maybe the parts represent words:
# jpl, eh, dne, se, sae, lp, efi, ly, mf, ol, ort

# Try to make sense of these:
# jpl -> could be "jpl" or maybe it's corrupted
# eh -> "eh" (interjection)
# dne -> "end" reversed
# se -> "se" or "es" 
# sae -> "sae" or "aes" or "sea"
# lp -> "lp" or "pl"
# efi -> "ife" or "efi"
# ly -> "ly" or "yl"
# mf -> "mf" or "fm"
# ol -> "lo" or "ol"
# ort -> "tro" or "ort"

# Maybe some of these need to be reversed individually
individual_reversed = [part[::-1] for part in clean_parts_11]
print(f"  Individual parts reversed: {individual_reversed}")

# Concatenate individually reversed parts
method6 = ''.join(individual_reversed)
print(f"  Concatenated individually reversed: {method6}")
print(f"  Flag: dawgctf{{{method6}}}")

# Method 7: Maybe there's a pattern in which parts to use
print(f"\nMethod 7 - Pattern analysis:")

# Look at the original 7 parts vs the new 11 parts
original_7 = ['}pl', 'eh_', 'dne', 's_e', 'sae', 'lp_', 'efi']
new_11 = clean_parts_11

print(f"Original 7: {original_7}")
print(f"New 11: {new_11}")

# The new ones seem to be: ['l y', 'm f', 'o l', 'ort'] -> ['ly', 'mf', 'ol', 'ort']
# These might be additional words

# Maybe the complete message is longer
# Let me try all 11 again but with different processing

# Method 8: Maybe spaces need to be treated differently
print(f"\nMethod 8 - Space handling:")

# Maybe the spaces in 's e', 'l y', 'm f', 'o l' are meaningful
# Let me try with spaces preserved
with_spaces = ['jpl', 'eh', 'dne', 's e', 'sae', 'lp', 'efi', 'l y', 'm f', 'o l', 'ort']
method8 = ''.join(with_spaces)
method8_reversed = method8[::-1]
print(f"  With spaces: {method8}")
print(f"  Reversed: {method8_reversed}")

# Clean up the reversed version
clean_reversed = method8_reversed.replace(' ', '_')  # Convert spaces to underscores
print(f"  Clean reversed: {clean_reversed}")
print(f"  Flag: dawgctf{{{clean_reversed}}}")

# Method 9: Maybe only certain parts are valid
print(f"\nMethod 9 - Selective parts:")

# Maybe only parts that look like real words or abbreviations
meaningful_parts = []
for part in clean_parts_11:
    if len(part) >= 2 and part.isalpha():
        meaningful_parts.append(part)

print(f"  Meaningful parts: {meaningful_parts}")
if meaningful_parts:
    method9 = ''.join(meaningful_parts)
    method9_reversed = method9[::-1]
    print(f"  Concatenated: {method9}")
    print(f"  Reversed: {method9_reversed}")
    print(f"  Flag: dawgctf{{{method9_reversed}}}")

# Method 10: Maybe the flag needs to be constructed from a specific subset
print(f"\nMethod 10 - Systematic subset testing:")

# Try all subsets of size 7 from the 11 parts
from itertools import combinations

if len(clean_parts_11) >= 7:
    # Try a few logical combinations
    subsets_to_try = [
        (0, 7),   # First 7
        (4, 11),  # Last 7  
        (1, 8),   # Middle 7
        (0, 5),   # First 5
        (6, 11),  # Last 5
    ]
    
    for start, end in subsets_to_try:
        subset = clean_parts_11[start:end]
        concat = ''.join(subset)
        reversed_subset = concat[::-1]
        
        if len(reversed_subset) > 5:
            print(f"  Parts {start}-{end}: dawgctf{{{reversed_subset}}}")

# Final analysis - let me look at what the complete message might be
print(f"\n=== FINAL ANALYSIS ===")

# The complete 11-part reversed message is:
complete_message = method1_reversed
print(f"Complete message (all 11 parts reversed): {complete_message}")

# Look for any readable words in this
words_in_message = re.findall(r'[a-zA-Z]{3,}', complete_message)
print(f"Words found: {words_in_message}")

# Maybe the flag is one of these words or combinations
if len(words_in_message) >= 2:
    for i in range(len(words_in_message)):
        for j in range(i+1, min(i+4, len(words_in_message))):  # Try combinations of 2-4 words
            phrase = '_'.join(words_in_message[i:j])
            if len(phrase) > 5:
                print(f"  Possible phrase: dawgctf{{{phrase}}}")
