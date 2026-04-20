#!/usr/bin/env python3

import re

print("=== RE-EXAMINING THE HINT ===")
print("Hint: 'if you don't want to write the regex expression of eternal pain and suffering,")
print("try substituting each leetspeek-letter back to its alphabetical equivalent first'")

# Maybe I need to be more comprehensive about leetspeak substitution
# Let me try a more extensive mapping

extensive_leet_mapping = {
    '0': 'o', '1': 'i', '2': 'z', '3': 'e', '4': 'a', '5': 's',
    '6': 'g', '7': 't', '8': 'b', '9': 'p',
    '@': 'a', '$': 's', '!': 'i', '+': 't',
    '|': 'i', '(': 'c', ')': 'd', '<': 'c', '>': 'd',
    '[': 'j', ']': 'k', '{': 'f', '}': 'j',
    '/': 'v', '\\': 'v', '_': ' ', '-': 'h',
    '&': 'b', '%': 'x', '^': 'n', '*': 'm', '=': 'r'
}

def extensive_decode(text):
    decoded = text
    for leet, alpha in extensive_leet_mapping.items():
        decoded = decoded.replace(leet, alpha)
    return decoded

# Read the file
with open('crazy.txt', 'r') as f:
    content = f.read()

print(f"\nOriginal content length: {len(content)}")

# Apply extensive decoding
extensive_decoded = extensive_decode(content)
print(f"Extensively decoded length: {len(extensive_decoded)}")

# Now look for "drove me" patterns in the extensively decoded content
print(f"\n=== SEARCHING IN EXTENSIVELY DECODED CONTENT ===")

drove_patterns = re.findall(r'drove me ([^c]+?)(?: crazy|$)', extensive_decoded)
print(f"Found {len(drove_patterns)} 'drove me' patterns:")
for i, pattern in enumerate(drove_patterns):
    print(f"{i+1}: '{pattern.strip()}'")

# Also look for the encoded variations
all_variations = [
    "drove me", "dr0ve me", "drov3 me", "dr0v3 me",
    "drove m3", "dr0ve m3", "drov3 m3", "dr0v3 m3"
]

all_parts = []
for variation in all_variations:
    pattern = f'{variation} ([^c]+?)(?: crazy|$)'
    matches = re.findall(pattern, extensive_decoded)
    if matches:
        print(f"Found {len(matches)} matches for '{variation}' in extensive decode: {matches}")
        all_parts.extend([match.strip() for match in matches])

print(f"\nAll parts from extensive decode: {all_parts}")

# Maybe the flag format is different
print(f"\n=== CHECKING DIFFERENT FLAG FORMATS ===")

# Maybe it's not dawgctf{} but something else
# Let me look for any patterns that might indicate the flag format

# Look for common CTF flag prefixes
prefixes = ['flag', 'ctf', 'dawgctf', 'dawg_ctf', 'dawg-ctf']
for prefix in prefixes:
    if prefix in extensive_decoded.lower():
        print(f"Found '{prefix}' in decoded content")

# Maybe the flag doesn't have a prefix at all
# Let me just look for any bracketed content
bracketed = re.findall(r'\{[^}]+\}', extensive_decoded)
print(f"Bracketed content in extensive decode: {bracketed}")

# Let me also try a different approach
print(f"\n=== ALTERNATIVE APPROACH - STEP BY STEP ===")

# Maybe I need to process the file differently
# Let me look at the structure more carefully

# The file seems to have a pattern where each section gets progressively more corrupted
# Let me extract the "drove me" parts from each corruption level

# Split by "crazy?" to get sections
sections = extensive_decoded.split('crazy?')
print(f"Found {len(sections)} sections")

for i, section in enumerate(sections[1:6]):  # First 5 sections
    print(f"\nSection {i+1}:")
    # Look for "drove me" in this section
    drove_match = re.search(r'drove me ([^c]+)', section)
    if drove_match:
        part = drove_match.group(1).strip()
        print(f"  Part: '{part}'")
    else:
        print("  No 'drove me' found")

# Maybe the flag construction is different
print(f"\n=== DIFFERENT FLAG CONSTRUCTION ===")

# Maybe the parts need to be processed differently
# Let me try the original parts but with different processing

original_parts = ['}pl', 'eh_', 'dne', 's_e', 'sae', 'lp_', 'efi']

# Maybe the underscores need to be treated differently
# Maybe they represent word boundaries

# Try treating underscores as word separators
with_underscores = '_'.join(original_parts)
print(f"Parts joined with underscores: {with_underscores}")

# Try interpreting as individual characters
chars = list(''.join(original_parts))
print(f"Individual characters: {chars}")

# Maybe the flag is hidden in a different way
print(f"\n=== LOOKING FOR HIDDEN PATTERNS ===")

# Maybe the flag is spelled out using the first letters of something
# Or maybe it's hidden in the corruption pattern itself

# Let me look at the pattern of how the text changes
# The file starts normal and gets progressively more corrupted
# Maybe the flag is encoded in this progression

# Look for any patterns in the character frequencies
from collections import Counter

char_freq = Counter(content)
print(f"Most common characters: {char_freq.most_common(10)}")

# Look for patterns in the special characters
special_chars = [char for char in content if not char.isalnum() and not char.isspace()]
special_freq = Counter(special_chars)
print(f"Most common special chars: {special_freq.most_common(10)}")

# Maybe the flag is constructed from the special characters
special_sequence = ''.join([char for char in content if char in special_freq.keys()][:50])
print(f"Special char sequence: {special_sequence}")

# Final attempt - maybe I need to think about this completely differently
print(f"\n=== COMPLETELY DIFFERENT APPROACH ===")

# Maybe the flag is not constructed from the "drove me" parts at all
# Maybe it's hidden somewhere else in the file

# Let me look for any readable English words in the corrupted parts
# Maybe the flag is actually readable text hidden in the corruption

# Extract all alphabetic sequences
alpha_sequences = re.findall(r'[a-zA-Z]{4,}', extensive_decoded)

# Filter out common words from the repetitive text
common_words = {'crazy', 'once', 'they', 'locked', 'room', 'round', 'rubber', 'rats', 'wheels', 'that', 'with', 'from', 'were'}
interesting_words = [word for word in alpha_sequences if word.lower() not in common_words]

print(f"Interesting words: {interesting_words[:20]}")

# Maybe the flag is constructed from these words
if len(interesting_words) >= 3:
    flag_words = interesting_words[:3]
    potential_flag = '_'.join(flag_words)
    print(f"Potential flag from words: dawgctf{{{potential_flag}}}")

# Maybe I should just try some logical flag formats based on what I found
print(f"\n=== LOGICAL FLAG ATTEMPTS ===")

# Based on "ife_please_send_help" - maybe it's missing something
# Maybe it's "life_please_send_help" or "control_me_please_send_help"

attempts = [
    "dawgctf{life_please_send_help}",
    "dawgctf{control_me_please_send_help}",
    "dawgctf{help_me_please}",
    "dawgctf{send_help_please}",
    "dawgctf{please_help_me}",
    "dawgctf{help_please}",
    "dawgctf{ife_help_please}",
    "dawgctf{ife_send_help}",
    "dawgctf{send_help_ife}",
]

for attempt in attempts:
    print(f"  {attempt}")
