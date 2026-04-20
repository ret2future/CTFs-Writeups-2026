#!/usr/bin/env python3

# Read the crazy.txt file
with open('crazy.txt', 'r') as f:
    content = f.read()

import re

print("=== FINDING ENCODED PATTERNS ===")

# Common leetspeak substitutions
leet_substitutions = {
    'o': ['0'],
    'i': ['1', '!'],
    'e': ['3'],
    'a': ['4', '@'],
    's': ['5', '$'],
    't': ['7'],
    'g': ['6', '9'],
    'b': ['8'],
    'p': ['9']
}

def generate_leet_variations(word):
    """Generate common leetspeak variations of a word"""
    variations = [word]
    
    # Common substitutions for "drove me"
    if word == "drove me":
        # Generate some common variations
        base_variations = [
            "drove me",  # original
            "dr0ve me",  # o->0
            "drov3 me",  # e->3
            "dr0v3 me",  # o->0, e->3
            "drove m3",  # e->3
            "dr0ve m3",  # o->0, e->3
            "drov3 m3",  # e->3, e->3
            "dr0v3 m3",  # o->0, e->3, e->3
        ]
        return base_variations
    
    return variations

print("1. SEARCHING FOR ENCODED 'drove me' VARIATIONS")

# Look for various leetspeak versions of "drove me"
drove_variations = generate_leet_variations("drove me")

all_matches = []
for variation in drove_variations:
    pattern = f'{variation} ([^c]+?)(?: cr4zy|crazy|$)'
    matches = re.findall(pattern, content)
    if matches:
        print(f"Found {len(matches)} matches for '{variation}':")
        for i, match in enumerate(matches):
            print(f"  {i+1}: '{match.strip()}'")
            all_matches.append(match.strip())

print(f"\nTotal parts found: {len(all_matches)}")
print(f"All parts: {all_matches}")

# Let me also search for the pattern more broadly
print("\n2. BROAD SEARCH FOR ANY 'drove' PATTERN")

# Look for any occurrence of "drove" followed by anything
broad_matches = re.findall(r'drove[^a-zA-Z]*me ([^c]+)', content)
print(f"Broad matches: {broad_matches}")

# Also look for "drove" with various character substitutions
print("\n3. SEARCHING FOR 'drove' WITH CHARACTER SUBSTITUTIONS")

# Common substitutions for each letter in "drove"
drove_patterns = [
    r'dr[o0]ve me ([^c]+)',  # o->0
    r'drov[e3] me ([^c]+)',   # e->3
    r'dr[o0]v[e3] me ([^c]+)', # o->0, e->3
    r'dr[o0]v[e3] m[e3] ([^c]+)', # o->0, e->3, e->3
]

for pattern in drove_patterns:
    matches = re.findall(pattern, content)
    if matches:
        print(f"Pattern '{pattern}': {matches}")

# Let me also search for the pattern I found in decoded content
print("\n4. ANALYZING WHERE ADDITIONAL PATTERNS COME FROM")

# The decoded content had 7 patterns but original only had 2
# This means some patterns are encoded and only become "drove me" after decoding

# Let me look at the file structure more carefully
# Maybe there are sections that look different but are actually the same pattern

print("\n5. SECTION-BY-SECTION ANALYSIS")

# Split the file into logical sections
# The pattern seems to be: repetitive text + "drove me" + some characters + "crazy"

sections = []
current_section = ""
lines = content.split('\n')

for line in lines:
    current_section += line + "\n"
    
    # If we find "crazy" or "cr4zy", it might be end of a section
    if "crazy" in line or "cr4zy" in line:
        # Check if this section contains "drove me"
        if "drove me" in current_section or any(leet in current_section for leet in ["dr0ve me", "drov3 me", "dr0v3 me"]):
            sections.append(current_section.strip())
            current_section = ""

# Add any remaining content
if current_section.strip():
    sections.append(current_section.strip())

print(f"Found {len(sections)} sections with 'drove me' patterns:")
for i, section in enumerate(sections):
    print(f"\nSection {i+1}:")
    # Find the "drove me" part
    drove_match = re.search(r'(drove[^a-zA-Z]*me [^c]+)', section)
    if drove_match:
        print(f"  Pattern: '{drove_match.group(1)}'")
    else:
        print("  No 'drove me' pattern found")
    print(f"  Length: {len(section)} characters")

# Let me also try a different approach
print("\n6. ALTERNATIVE APPROACH - LOOK FOR CHARACTER SEQUENCES")

# Maybe the flag parts are not after "drove me" but are hidden elsewhere
# Let me look for any 2-4 character sequences that could be flag parts

# Extract all short alphanumeric sequences
short_sequences = re.findall(r'[a-zA-Z0-9_]{2,4}', content)
print(f"Found {len(short_sequences)} short sequences")

# Filter out common words and look for interesting ones
common_words = {'the', 'and', 'was', 'were', 'room', 'rats', 'wheels', 'round', 'rubber'}
interesting_sequences = []

for seq in short_sequences:
    if seq.lower() not in common_words and len(seq) >= 2 and len(seq) <= 4:
        interesting_sequences.append(seq)

# Show unique interesting sequences
unique_sequences = list(set(interesting_sequences))[:50]
print(f"Interesting sequences: {unique_sequences}")

# Maybe the flag parts are scattered throughout
print("\n7. LOOKING FOR SCATTERED PATTERN")

# The hint mentioned not wanting to write "regex expression of eternal pain and suffering"
# This suggests the pattern might be complex

# Let me look for any 2-3 character sequences that appear in specific positions
# Maybe they're at regular intervals or specific locations

# Check if there are patterns at regular intervals
for interval in [10, 50, 100, 200, 500]:
    chars_at_interval = []
    for i in range(0, len(content), interval):
        if i < len(content):
            char = content[i]
            if char.isalnum() or char in '_{}':
                chars_at_interval.append(char)
    
    if len(chars_at_interval) > 3:
        interval_string = ''.join(chars_at_interval)
        print(f"Characters at interval {interval}: {interval_string}")
