#!/usr/bin/env python3

# Read the crazy.txt file
with open('crazy.txt', 'r') as f:
    content = f.read()

import re

print("=== COMPREHENSIVE REANALYSIS ===")

# Let me find ALL occurrences of "drove me" in the original content
print("1. FINDING ALL 'drove me' PATTERNS IN ORIGINAL")

# Look for "drove me" followed by anything until "cr4zy" or end
all_drove_original = re.findall(r'drove me ([^c]+?)(?: cr4zy|$)', content)
print(f"Found {len(all_drove_original)} 'drove me' patterns in original:")
for i, pattern in enumerate(all_drove_original):
    print(f"{i+1:2d}: '{pattern.strip()}'")

# Now decode with basic leetspeak
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

# Find all "drove me" patterns in decoded content
all_drove_decoded = re.findall(r'drove me ([^c]+?)(?: crazy|$)', decoded)
print(f"\nFound {len(all_drove_decoded)} 'drove me' patterns in decoded:")
for i, pattern in enumerate(all_drove_decoded):
    print(f"{i+1:2d}: '{pattern.strip()}'")

# Let me also try a more permissive pattern
print("\n2. TRYING MORE PERMISSIVE PATTERNS")

# Maybe it's not always "drove me " - let me look for variations
variations = [
    r'drove me ([^c]+?)(?: crazy|cr4zy|$)',
    r'drove me ([a-zA-Z0-9_{}]+)',
    r'drove me ([^\n]+?)(?= crazy|cr4zy|$)'
]

for i, pattern in enumerate(variations):
    matches = re.findall(pattern, content)
    print(f"Pattern {i+1}: Found {len(matches)} matches")
    for j, match in enumerate(matches[:10]):  # Show first 10
        print(f"  {j+1}: '{match.strip()}'")

# Let me also split by "drove me" and look at what comes after
print("\n3. SPLIT-BY-DRIVE-ME ANALYSIS")

sections = content.split('drove me ')
print(f"Split into {len(sections)} sections")

for i, section in enumerate(sections[1:15]):  # Look at first 14 sections
    # Get the first meaningful content
    lines = section.strip().split('\n')
    if lines:
        first_line = lines[0]
        # Extract first word/sequence
        first_word_match = re.match(r'\s*([a-zA-Z0-9_{}]+)', first_line)
        if first_word_match:
            print(f"Section {i+1}: '{first_word_match.group(1)}'")
        else:
            # Try to get any non-whitespace
            non_ws = re.match(r'\s*([^\s]+)', first_line)
            if non_ws:
                print(f"Section {i+1} (raw): '{non_ws.group(1)}'")

# Let me look at the pattern more carefully
print("\n4. DETAILED PATTERN ANALYSIS")

# Maybe the pattern changes as the file gets more corrupted
# Let me look at each "drove me" occurrence in context

drove_positions = []
start = 0
while True:
    pos = content.find('drove me', start)
    if pos == -1:
        break
    drove_positions.append(pos)
    start = pos + 1

print(f"Found 'drove me' at positions: {drove_positions[:15]}")

# Extract context around each position
for i, pos in enumerate(drove_positions[:10]):
    context_start = max(0, pos - 20)
    context_end = min(len(content), pos + 50)
    context = content[context_start:context_end]
    print(f"\nPosition {pos}: {context}")

# Let me also check if there are patterns I'm missing
print("\n5. ALTERNATIVE SEARCH APPROACHES")

# Maybe it's not "drove me" but something similar
similar_patterns = [
    r'drove [a-zA-Z]+ ([^c]+)',
    r'[a-zA-Z]+ drove me ([^c]+)',
    r'drove.*?me ([^c]+)'
]

for pattern in similar_patterns:
    matches = re.findall(pattern, content)
    if matches:
        print(f"Pattern '{pattern}': {len(matches)} matches")
        for match in matches[:5]:
            print(f"  '{match.strip()}'")

# Let me also look at the file structure differently
print("\n6. FILE STRUCTURE ANALYSIS")

# The file seems to have a repetitive structure
# Let me look at the pattern of how it changes

# Count occurrences of key phrases
key_phrases = ['drove me', 'crazy?', 'room,', 'rats,', 'wheels,']
for phrase in key_phrases:
    count = content.count(phrase)
    print(f"'{phrase}': {count} occurrences")

# Maybe the flag is constructed from a different pattern entirely
print("\n7. LOOKING FOR OTHER PATTERNS")

# Look for any sequences that might be flag-related
# Check for any bracketed content
bracketed = re.findall(r'\{[^}]*\}', content)
print(f"Bracketed content: {bracketed}")

# Look for any sequences that might decode to meaningful text
# Maybe the flag is hidden in the corruption progression

# Let me examine the corruption pattern more carefully
print("\n8. CORRUPTION PROGRESSION ANALYSIS")

# The file starts normal and gets progressively corrupted
# Let me look at how it changes

# Find the first few "crazy?" occurrences
crazy_positions = []
start = 0
for i in range(5):  # First 5 occurrences
    pos = content.find('crazy?', start)
    if pos == -1:
        break
    crazy_positions.append(pos)
    start = pos + 1

print(f"First 5 'crazy?' positions: {crazy_positions}")

# Look at the text around each occurrence
for i, pos in enumerate(crazy_positions):
    context_start = max(0, pos - 30)
    context_end = min(len(content), pos + 100)
    context = content[context_start:context_end]
    print(f"\nCrazy #{i+1} context: {context}")
