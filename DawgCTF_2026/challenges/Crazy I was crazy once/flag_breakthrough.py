#!/usr/bin/env python3

# Read the crazy.txt file
with open('crazy.txt', 'r') as f:
    content = f.read()

import re

print("=== FLAG BREAKTHROUGH ANALYSIS ===")

# Let me go back to the original approach that worked
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

decoded = decode_basic(content)

# Extract the "drove me" patterns that I found before
drove_patterns = re.findall(r'it drove me ([^c]+?)(?: crazy|$)', decoded)
print(f"Found {len(drove_patterns)} 'drove me' patterns:")
for i, pattern in enumerate(drove_patterns):
    print(f"{i+1}: '{pattern.strip()}'")

# Extract the clean parts
parts = [p.strip() for p in drove_patterns if p.strip()]
print(f"\nClean parts: {parts}")

# Concatenate and reverse
concatenated = ''.join(parts)
reversed_flag = concatenated[::-1]
print(f"\nConcatenated: '{concatenated}'")
print(f"Reversed: '{reversed_flag}'")

# Now I need to find "dawgctf{" - maybe it's hidden in the corruption
# Let me look at the "i{F" pattern more carefully
print(f"\n=== ANALYZING i{{F}} PATTERN ===")

# The "i{F" I found might actually be "i{F}" where the closing bracket is hidden
# Let me look at the context around the i{F occurrences
if_positions = []
start = 0
while True:
    pos = content.find('i{F', start)
    if pos == -1:
        break
    if_positions.append(pos)
    start = pos + 1

print(f"Found i{{F}} at positions: {if_positions}")

for pos in if_positions:
    # Get a larger context to see if there's a closing bracket
    context_start = max(0, pos - 50)
    context_end = min(len(content), pos + 200)  # Look further ahead
    context = content[context_start:context_end]
    print(f"\nContext around position {pos}:")
    print(context)
    
    # Look for any closing bracket within this context
    closing_bracket = context.find('}', pos - context_start)
    if closing_bracket != -1:
        bracket_content = context[context.find('i{F'):closing_bracket + 1]
        print(f"Found complete bracket: {bracket_content}")
        
        # Try to decode this
        decoded_bracket = decode_basic(bracket_content)
        print(f"Decoded bracket: {decoded_bracket}")

# Maybe the flag is constructed differently
# Let me look at the pattern where "i{F" appears
print(f"\n=== DETAILED i{{F}} ANALYSIS ===")

# Get the specific section where i{F appears
for pos in if_positions:
    # Find the start of this section
    section_start = content.rfind('drove me', 0, pos)
    if section_start != -1:
        section = content[section_start:pos + 100]
        print(f"\nSection containing i{{F}}:")
        print(section)
        
        # Decode this section
        decoded_section = decode_basic(section)
        print(f"\nDecoded section:")
        print(decoded_section)

# Alternative approach: maybe "dawgctf{" is formed by combining parts
print(f"\n=== ALTERNATIVE CONSTRUCTION ===")

# Look for any characters that could form "dawgctf"
# Maybe they're scattered throughout the text

# Search for each character of "dawgctf{" in order
target = "dawgctf{"
current_pos = 0
found_positions = []

for char in target:
    # Find this character after current_pos
    pos = content.find(char, current_pos)
    if pos != -1:
        found_positions.append(pos)
        current_pos = pos + 1
        print(f"Found '{char}' at position {pos}")
    else:
        print(f"Could not find '{char}' after position {current_pos}")
        break

if len(found_positions) == len(target):
    print(f"Found all characters of 'dawgctf{{' at positions: {found_positions}")
    
    # Extract the characters at these positions
    extracted = ''.join([content[pos] for pos in found_positions])
    print(f"Extracted: {extracted}")
    
    # Maybe the flag content comes after this?
    last_pos = found_positions[-1]
    after_bracket = content[last_pos + 1:last_pos + 100]
    print(f"Content after bracket: {after_bracket}")
    
    # Look for closing bracket
    closing_pos = after_bracket.find('}')
    if closing_pos != -1:
        flag_content = after_bracket[:closing_pos + 1]
        print(f"Flag content: {flag_content}")
        
        # Decode the flag content
        decoded_flag_content = decode_basic(flag_content)
        print(f"Decoded flag content: {decoded_flag_content}")
        
        complete_flag = "dawgctf{" + decoded_flag_content[1:]  # Skip the opening bracket
        print(f"\nCOMPLETE FLAG: {complete_flag}")

# One more approach - maybe the flag is in the very specific corruption pattern
print(f"\n=== CORRUPTION PATTERN ANALYSIS ===")

# Look at the unique characters that appear in the corrupted sections
# The file seems to have a pattern where it gets progressively more corrupted
# Maybe the flag is hidden in this progression

# Extract all unique non-alphanumeric characters (except spaces and punctuation)
import string
unique_chars = set()
for char in content:
    if char not in string.ascii_letters + string.digits + string.whitespace + '.,!?;:()[]{}':
        unique_chars.add(char)

print(f"Unique special characters: {sorted(unique_chars)}")

# Maybe these special characters encode the flag
# Let me look at the sequence of special characters
special_sequence = ''.join([char for char in content if char in unique_chars])
print(f"Special character sequence: {special_sequence[:200]}...")

# Look for any patterns in this
if 'dawgctf' in special_sequence.lower():
    print("Found dawgctf in special sequence!")
