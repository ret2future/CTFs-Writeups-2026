#!/usr/bin/env python3

# Read the crazy.txt file
with open('crazy.txt', 'r') as f:
    content = f.read()

import re

print("=== FINAL FLAG ATTEMPT ===")

# I have two key pieces of information:
# 1. The prefix "dawgctf{" is constructed from characters at specific positions
# 2. The "drove me" parts give us: }pl, eh_, dne, s_e, sae, lp_, efi
#    When reversed: ife_please_send_help}

# Maybe the complete flag is: dawgctf{ife_please_send_help}

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

# Extract the "drove me" patterns
drove_patterns = re.findall(r'it drove me ([^c]+?)(?: crazy|$)', decoded)
parts = [p.strip() for p in drove_patterns if p.strip()]
concatenated = ''.join(parts)
reversed_content = concatenated[::-1]

print(f"Drove me parts: {parts}")
print(f"Reversed content: {reversed_content}")

# The reversed content is: ife_please_send_help}
# This looks like it should be the flag content

# Let me try the most logical flag
candidate_flag = "dawgctf{" + reversed_content
print(f"\nCandidate flag: {candidate_flag}")

# But wait, there's an extra "}" at the end
# Maybe the actual content is without the closing brace
flag_content = reversed_content.rstrip('}')
candidate_flag2 = "dawgctf{" + flag_content
print(f"Alternative flag: {candidate_flag2}")

# Let me also check if there are any other patterns I missed
print(f"\n=== DOUBLE-CHECKING FOR OTHER PATTERNS ===")

# Maybe there are more "drove me" patterns in the original content
original_drove = re.findall(r'drove me ([^c]+?)(?: cr4zy|$)', content)
original_parts = [p.strip() for p in original_drove if p.strip()]
print(f"Original 'drove me' parts: {original_parts}")

# Maybe I need to look at this differently
# Let me search for any other patterns that could contain flag content

# Look for any sequences that might be meaningful
print(f"\n=== SEARCHING FOR MEANINGFUL SEQUENCES ===")

# Search for any readable text in the corrupted sections
# The file seems to get progressively more corrupted
# Maybe there's readable text hidden in the corruption

# Split by the repetitive pattern and look at what's different
sections = content.split('crazy? i was crazy once')
for i, section in enumerate(sections[1:5]):  # Look at first 4 sections
    print(f"\nSection {i+1}:")
    # Look for any non-repetitive content
    lines = section.strip().split('\n')
    for line in lines[:3]:  # First 3 lines
        if line.strip() and len(line.strip()) > 10:
            print(f"  {line.strip()[:100]}")

# Let me also try a different approach
# Maybe the flag is spelled out across the corruption pattern
print(f"\n=== CORRUPTION PATTERN ANALYSIS ===")

# Look at the unique characters that appear as the text gets corrupted
# The file starts normal and gets progressively more corrupted
# Maybe the flag is hidden in this progression

# Find all unique character sequences that appear
unique_sequences = set()
# Look for sequences of 3-5 characters that might be meaningful
seq_pattern = re.compile(r'[a-zA-Z0-9_]{3,5}')
sequences = seq_pattern.findall(content)

for seq in sequences:
    if not seq.startswith('cra') and not seq.startswith('rou') and not seq.startswith('rub') and not seq.startswith('whe') and not seq.startswith('rat'):
        unique_sequences.add(seq)

# Show some interesting sequences
interesting = sorted(list(unique_sequences))[:20]
print(f"Interesting sequences: {interesting}")

# Let me try one more approach
# Maybe the flag content is actually the reversed "drove me" parts
# but I need to find the correct subset

print(f"\n=== TESTING DIFFERENT SUBSETS ===")

# Try different combinations of the parts
for i in range(3, len(parts) + 1):
    subset = parts[:i]
    concat = ''.join(subset)
    reversed_subset = concat[::-1]
    
    # Remove any trailing non-alphanumeric characters
    clean_reversed = re.sub(r'[^a-zA-Z0-9_]+$', '', reversed_subset)
    
    if len(clean_reversed) > 5:
        candidate = f"dawgctf{{{clean_reversed}"
        print(f"First {i} parts: {candidate}")

# Final check - maybe the flag is exactly what I think it is
print(f"\n=== FINAL ANSWER ===")
print("Based on the analysis:")
print("1. Found 'dawgctf{' constructed from character positions")
print("2. Found 'drove me' parts that reverse to 'ife_please_send_help}'")
print("3. The most logical flag would be:")
print(f"   dawgctf{{ife_please_send_help}}")

# But let me also check if there's a version without the extra brace
clean_content = "ife_please_send_help"
final_flag = f"dawgctf{{{clean_content}}}"
print(f"   dawgctf{{{clean_content}}}")

print(f"\nMost likely answer: {final_flag}")
