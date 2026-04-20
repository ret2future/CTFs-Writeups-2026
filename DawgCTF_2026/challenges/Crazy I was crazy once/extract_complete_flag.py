#!/usr/bin/env python3

# Read the crazy.txt file
with open('crazy.txt', 'r') as f:
    content = f.read()

import re

print("=== EXTRACTING COMPLETE FLAG ===")

# I found the positions: [35, 43, 106, 239, 285, 317, 2034, 6179]
# The bracket is at position 6179, so let's extract everything from there to the closing bracket

bracket_pos = 6179

# Extract content starting from the bracket
from_bracket = content[bracket_pos:]
print(f"Content from bracket position: {from_bracket[:200]}...")

# Look for the closing bracket
closing_bracket_pos = from_bracket.find('}')
if closing_bracket_pos != -1:
    flag_content = from_bracket[:closing_bracket_pos + 1]
    print(f"\nFlag content: {flag_content}")
    
    # Now decode this content
    leet_to_alpha = {
        '0': 'o', '1': 'i', '2': 'z', '3': 'e', '4': 'a', '5': 's',
        '6': 'g', '7': 't', '8': 'b', '9': 'p'
    }
    
    def decode_basic(text):
        decoded = text
        for leet, alpha in leet_to_alpha.items():
            decoded = decoded.replace(leet, alpha)
        return decoded
    
    decoded_flag = decode_basic(flag_content)
    print(f"Decoded flag content: {decoded_flag}")
    
    # The complete flag should be "dawgctf" + decoded_flag
    complete_flag = "dawgctf" + decoded_flag
    print(f"\nCOMPLETE FLAG: {complete_flag}")
    
else:
    print("No closing bracket found!")
    
    # Let's try a broader search for any closing bracket
    print("Looking for any closing bracket in the rest of the file...")
    rest_of_file = content[bracket_pos:]
    print(f"Rest of file length: {len(rest_of_file)}")
    
    # Look for any '}' character
    bracket_positions = []
    start = 0
    while True:
        pos = rest_of_file.find('}', start)
        if pos == -1:
            break
        bracket_positions.append(pos)
        start = pos + 1
    
    print(f"Found closing brackets at positions: {bracket_positions[:10]}")
    
    # Try each potential closing bracket
    for i, closing_pos in enumerate(bracket_positions[:5]):
        potential_flag = rest_of_file[:closing_pos + 1]
        print(f"\nPotential flag {i+1}: {potential_flag}")
        
        # Decode it
        decoded = decode_basic(potential_flag)
        print(f"Decoded: {decoded}")
        
        # Check if it looks like a reasonable flag
        if len(decoded) > 5 and len(decoded) < 100:
            complete = "dawgctf" + decoded
            print(f"Complete flag candidate: {complete}")

# Also let me double-check by looking at the exact context around the bracket
print(f"\n=== CONTEXT ANALYSIS ===")
context_start = max(0, bracket_pos - 50)
context_end = min(len(content), bracket_pos + 200)
context = content[context_start:context_end]
print(f"Context around bracket: {context}")

# Let me also verify the "dawgctf{" construction
print(f"\n=== VERIFYING DAWGCTF{{ CONSTRUCTION ===")
target_positions = [35, 43, 106, 239, 285, 317, 2034, 6179]
target_chars = "dawgctf{"

for i, (pos, char) in enumerate(zip(target_positions, target_chars)):
    actual_char = content[pos]
    print(f"Position {pos}: expected '{char}', found '{actual_char}' - {'✓' if actual_char == char else '✗'}")

# Extract the constructed prefix
constructed_prefix = ''.join([content[pos] for pos in target_positions])
print(f"Constructed prefix: {constructed_prefix}")
