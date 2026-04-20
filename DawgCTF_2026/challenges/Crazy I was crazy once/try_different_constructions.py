#!/usr/bin/env python3

import re

# I found these 7 parts from the encoded "drove me" patterns:
# }pl, eh_, dne, s_e, sae, lp_, efi

parts = ['}pl', 'eh_', 'dne', 's_e', 'sae', 'lp_', 'efi']

print("=== TRYING DIFFERENT FLAG CONSTRUCTIONS ===")

print(f"Parts found: {parts}")

# Method 1: Original concatenation and reverse
method1 = ''.join(parts)
method1_reversed = method1[::-1]
print(f"\nMethod 1 - Concatenate and reverse:")
print(f"  Concatenated: {method1}")
print(f"  Reversed: {method1_reversed}")
print(f"  Flag: dawgctf{{{method1_reversed}}}")

# Method 2: Reverse order then concatenate
method2_parts = parts[::-1]
method2 = ''.join(method2_parts)
print(f"\nMethod 2 - Reverse order then concatenate:")
print(f"  Reversed parts: {method2_parts}")
print(f"  Concatenated: {method2}")
print(f"  Flag: dawgctf{{{method2}}}")

# Method 3: Remove underscores and special chars, then reverse
clean_parts = [p.replace('_', '').replace('}', '') for p in parts]
method3 = ''.join(clean_parts)
method3_reversed = method3[::-1]
print(f"\nMethod 3 - Clean parts then reverse:")
print(f"  Clean parts: {clean_parts}")
print(f"  Concatenated: {method3}")
print(f"  Reversed: {method3_reversed}")
print(f"  Flag: dawgctf{{{method3_reversed}}}")

# Method 4: Try different subsets
print(f"\nMethod 4 - Different subsets:")
for i in range(3, len(parts) + 1):
    subset = parts[:i]
    concat = ''.join(subset)
    reversed_subset = concat[::-1]
    
    # Clean up the result
    clean_result = re.sub(r'[^a-zA-Z0-9_]', '', reversed_subset)
    if len(clean_result) > 5:
        print(f"  First {i} parts: dawgctf{{{clean_result}}}")

# Method 5: Maybe the parts need to be interpreted differently
print(f"\nMethod 5 - Alternative interpretations:")

# Maybe each part needs to be reversed individually
individual_reversed = [p[::-1] for p in parts]
method5 = ''.join(individual_reversed)
print(f"  Individual parts reversed: {individual_reversed}")
print(f"  Concatenated: {method5}")
print(f"  Flag: dawgctf{{{method5}}}")

# Method 6: Maybe it's a Caesar cipher or other encoding
print(f"\nMethod 6 - Caesar cipher attempts:")

# Try Caesar shifts on the reversed string
reversed_string = method1_reversed
for shift in range(1, 26):
    caesar_result = ""
    for char in reversed_string:
        if char.isalpha():
            if char.isupper():
                caesar_result += chr((ord(char) - ord('A') - shift) % 26 + ord('A'))
            else:
                caesar_result += chr((ord(char) - ord('a') - shift) % 26 + ord('a'))
        else:
            caesar_result += char
    
    # Check if it looks like readable English
    if 'help' in caesar_result.lower() or 'please' in caesar_result.lower():
        print(f"  Shift {shift}: {caesar_result}")
        print(f"    Flag: dawgctf{{{caesar_result}}}")

# Method 7: Maybe the underscores are spaces
print(f"\nMethod 7 - Underscores as spaces:")
with_spaces = method1_reversed.replace('_', ' ')
print(f"  With spaces: {with_spaces}")
print(f"  Flag: dawgctf{{{with_spaces}}}")

# Method 8: Maybe only certain parts are used
print(f"\nMethod 8 - Selective parts:")

# Try using only parts that don't start with }
meaningful_parts = [p for p in parts if not p.startswith('}')]
method8 = ''.join(meaningful_parts)
method8_reversed = method8[::-1]
print(f"  Parts without }}: {meaningful_parts}")
print(f"  Concatenated: {method8}")
print(f"  Reversed: {method8_reversed}")
print(f"  Flag: dawgctf{{{method8_reversed}}}")

# Method 9: Maybe the } indicates the end, so we should exclude it
print(f"\nMethod 9 - Exclude closing brace:")
parts_without_end = [p.replace('}', '') for p in parts]
method9 = ''.join(parts_without_end)
method9_reversed = method9[::-1]
print(f"  Parts without }}: {parts_without_end}")
print(f"  Concatenated: {method9}")
print(f"  Reversed: {method9_reversed}")
print(f"  Flag: dawgctf{{{method9_reversed}}}")

# Method 10: Maybe the flag needs to be read differently
print(f"\nMethod 10 - Reading patterns:")

# Maybe it's not reversal but some other transformation
# Let's try reading every other character, or in some pattern

original_concat = ''.join(parts)
# Every other character starting from 0
every_other_0 = original_concat[::2]
# Every other character starting from 1  
every_other_1 = original_concat[1::2]

print(f"  Every other char (0): {every_other_0}")
print(f"  Every other char (1): {every_other_1}")

# Reverse these too
print(f"  Reversed (0): {every_other_0[::-1]}")
print(f"  Reversed (1): {every_other_1[::-1]}")

# Method 11: Maybe the parts are in the wrong order entirely
print(f"\nMethod 11 - Permutations:")

# Try a few logical orderings
# Maybe they should be ordered by when they appear in the file
# Let's check the order I found them in:
# }pl (drove me)
# eh_ (drove me) 
# dne (drov3 m3)
# s_e (drov3 m3)
# sae (dr0v3 m3)
# lp_ (dr0v3 m3)
# efi (dr0v3 m3)

# Maybe they should be grouped by the pattern type
grouped = {
    'drove me': ['}pl', 'eh_'],
    'drov3 m3': ['dne', 's_e'], 
    'dr0v3 m3': ['sae', 'lp_', 'efi']
}

print(f"  Grouped by pattern: {grouped}")

# Try concatenating by groups
grouped_order = grouped['drove me'] + grouped['drov3 m3'] + grouped['dr0v3 m3']
method11 = ''.join(grouped_order)
method11_reversed = method11[::-1]
print(f"  Grouped order: {grouped_order}")
print(f"  Concatenated: {method11}")
print(f"  Reversed: {method11_reversed}")
print(f"  Flag: dawgctf{{{method11_reversed}}}")

# Try reverse group order
reverse_group_order = grouped['dr0v3 m3'] + grouped['drov3 m3'] + grouped['drove me']
method11b = ''.join(reverse_group_order)
method11b_reversed = method11b[::-1]
print(f"  Reverse group order: {reverse_group_order}")
print(f"  Concatenated: {method11b}")
print(f"  Reversed: {method11b_reversed}")
print(f"  Flag: dawgctf{{{method11b_reversed}}}")
