#!/usr/bin/env python3

import re

print("=== FINAL ATTEMPTS ===")

# Try the clean version first
clean_flag = "dawgctf{ifepleasesendhelp}"
print(f"Clean version attempt: {clean_flag}")

# Let me also check if there are any other patterns I missed
# Maybe there are more "drove me" patterns that I didn't find

# Read the file again
with open('crazy.txt', 'r') as f:
    content = f.read()

# Look for ANY pattern that might be "drove me" in any encoding
print("\n=== COMPREHENSIVE PATTERN SEARCH ===")

# Generate all possible leetspeak combinations for "drove me"
def generate_all_variations():
    variations = []
    
    # Basic variations
    base = "drove me"
    variations.append(base)
    
    # Single substitutions
    substitutions = {
        'o': '0',
        'e': '3', 
        'm': 'm'  # m doesn't usually get substituted
    }
    
    # Generate combinations
    # o->0
    variations.append("dr0ve me")
    # e->3  
    variations.append("drov3 me")
    # o->0, e->3
    variations.append("dr0v3 me")
    
    # Also try variations for "me"
    # m->m, e->3
    variations.extend([
        "drove m3",
        "dr0ve m3", 
        "drov3 m3",
        "dr0v3 m3"
    ])
    
    return variations

all_variations = generate_all_variations()
print(f"Trying {len(all_variations)} variations of 'drove me'")

all_parts = []
for variation in all_variations:
    pattern = f'{variation} ([^c]+?)(?: cr4zy|crazy|$)'
    matches = re.findall(pattern, content)
    if matches:
        print(f"Found {len(matches)} matches for '{variation}': {matches}")
        all_parts.extend([match.strip() for match in matches])

print(f"\nAll parts found: {all_parts}")
print(f"Total parts: {len(all_parts)}")

# Remove duplicates while preserving order
seen = set()
unique_parts = []
for part in all_parts:
    if part not in seen:
        seen.add(part)
        unique_parts.append(part)

print(f"Unique parts: {unique_parts}")

# Try different constructions with these parts
print(f"\n=== TRYING CONSTRUCTIONS WITH ALL PARTS ===")

if len(unique_parts) >= 7:
    # Method 1: Original approach
    concat1 = ''.join(unique_parts[:7])
    reversed1 = concat1[::-1]
    clean1 = re.sub(r'[^a-zA-Z0-9_]', '', reversed1)
    print(f"Method 1: dawgctf{{{clean1}}}")
    
    # Method 2: Clean first, then concatenate
    clean_parts = [re.sub(r'[^a-zA-Z0-9_]', '', part) for part in unique_parts[:7]]
    concat2 = ''.join(clean_parts)
    reversed2 = concat2[::-1]
    print(f"Method 2: dawgctf{{{reversed2}}}")
    
    # Method 3: Remove all non-alphanumeric completely
    alpha_parts = [re.sub(r'[^a-zA-Z]', '', part) for part in unique_parts[:7]]
    concat3 = ''.join(alpha_parts)
    reversed3 = concat3[::-1]
    print(f"Method 3: dawgctf{{{reversed3}}}")

# Let me also check if there are patterns at the very end of the file
print(f"\n=== END OF FILE ANALYSIS ===")

last_500 = content[-500:]
print(f"Last 500 chars: {last_500}")

# Look for any short sequences at the end that might be flag-related
end_sequences = re.findall(r'[a-zA-Z0-9_]{3,8}', last_500)
print(f"End sequences: {end_sequences}")

# Maybe the flag is constructed from the end
if len(end_sequences) >= 3:
    end_concat = ''.join(end_sequences[-3:])
    print(f"Last 3 sequences concatenated: {end_concat}")
    print(f"Reversed: {end_concat[::-1]}")

# Also check if there are any patterns I missed in the corruption
print(f"\n=== CORRUPTION PATTERN ANALYSIS ===")

# The file gets progressively more corrupted
# Maybe the flag is hidden in how it changes

# Look for unique character sequences that appear only in corrupted sections
# Split the file into sections and look for differences

# Find all "crazy?" positions to identify sections
crazy_positions = []
start = 0
while True:
    pos = content.find('crazy?', start)
    if pos == -1:
        break
    crazy_positions.append(pos)
    start = pos + 1

print(f"Found 'crazy?' at positions: {crazy_positions}")

# Look at the character patterns around each position
for i, pos in enumerate(crazy_positions[:3]):  # First 3
    context_start = max(0, pos - 100)
    context_end = min(len(content), pos + 200)
    context = content[context_start:context_end]
    
    # Look for any short alphanumeric sequences
    sequences = re.findall(r'[a-zA-Z0-9_]{2,6}', context)
    unique_seqs = [seq for seq in sequences if seq not in ['crazy', 'once', 'they', 'locked', 'room', 'round', 'rubber', 'rats', 'wheels']]
    if unique_seqs:
        print(f"Section {i+1} unique sequences: {unique_seqs[:5]}")

# Final attempt - maybe the flag is simpler than I think
print(f"\n=== SIMPLER APPROACH ===")

# Maybe the flag is just "please_send_help" or similar
# Let me look for these words in the parts

all_text = ''.join(unique_parts)
print(f"All parts text: {all_text}")

# Look for common words
words = ['please', 'send', 'help', 'ife', 'life', 'control', 'me']
for word in words:
    if word in all_text.lower():
        print(f"Found '{word}' in parts")

# Maybe the flag is just "help" or "please_help"
simple_flags = [
    "dawgctf{help}",
    "dawgctf{please_help}", 
    "dawgctf{send_help}",
    "dawgctf{please_send_help}",
    "dawgctf{ife_please_send_help}",
    "dawgctf{life_please_send_help}",
    "dawgctf{control_me_please_send_help}"
]

print(f"\nSimple flag attempts:")
for flag in simple_flags:
    print(f"  {flag}")

print(f"\nMy best guess is still: {clean_flag}")
print(f"Alternative: dawgctf{ife_please_send_help}")
