#!/usr/bin/env python3

# Read the crazy.txt file
with open('crazy.txt', 'r') as f:
    content = f.read()

import re

print("=== FINAL FLAG SEARCH ===")

# Let me look for the "i{F" pattern more carefully
# Search for any occurrence of "i{F" followed by anything
if_matches = re.findall(r'i\{F[^}]*\}', content)
print(f"Complete i{{F}} patterns: {if_matches}")

# Also look for incomplete patterns
if_partial = re.findall(r'i\{F[^}]*', content)
print(f"Partial i{{F}} patterns: {if_partial}")

# Let's look at the context around these patterns
for match in if_partial:
    # Find the position and get context
    pos = content.find(match)
    if pos != -1:
        start = max(0, pos - 50)
        end = min(len(content), pos + len(match) + 50)
        context = content[start:end]
        print(f"Context around '{match}': {context}")

# Maybe the flag is constructed differently - let me look for ANY bracketed content
all_brackets = re.findall(r'\{[^}]*\}', content)
print(f"\nAll bracketed content: {all_brackets}")

# Let me also look for the patterns around "drove me" again but more carefully
print("\n=== DETAILED DROVE ME ANALYSIS ===")
# Split by "drove me " and look at what comes immediately after
sections = content.split('drove me ')
for i, section in enumerate(sections[1:15]):  # Look at first 14 sections
    # Get the first few characters after "drove me "
    match = re.match(r'\s*([a-zA-Z0-9_{}]+)', section)
    if match:
        first_part = match.group(1)
        print(f"Section {i+1}: '{first_part}'")
    else:
        # Look for any non-whitespace characters
        non_ws = re.match(r'\s*([^\s]+)', section)
        if non_ws:
            first_part = non_ws.group(1)
            print(f"Section {i+1} (non-ws): '{first_part}'")

# Let me try a different approach - maybe the flag is spelled out differently
print("\n=== ALTERNATIVE FLAG CONSTRUCTION ===")

# Look at all unique character sequences that could be flag parts
unique_sequences = set()
# Find all short alphanumeric sequences
short_seqs = re.findall(r'[a-zA-Z0-9_]{1,5}', content)
for seq in short_seqs:
    if not seq.startswith('cr') and not seq.startswith('ro') and not seq.startswith('ru') and len(seq) > 1:
        unique_sequences.add(seq)

# Convert to sorted list and show interesting ones
sorted_seqs = sorted(list(unique_sequences))
print(f"Interesting short sequences: {sorted_seqs[:50]}")

# Let me specifically look for anything that could be "dawgctf"
print("\n=== DAWGCTF SEARCH ===")
dawg_variants = re.findall(r'[dD][aA][wW][gG][cC][tT][fF]', content)
print(f"DAWGCTF variants: {dawg_variants}")

# Look for any pattern that might decode to dawgctf
leet_variants = ['d4wgctf', 'dawgctf', 'd4wgc7f', 'dawgctf']
for variant in leet_variants:
    matches = re.findall(variant, content, re.IGNORECASE)
    if matches:
        print(f"Found {variant}: {matches}")

# Maybe I need to decode first, then look for the flag
print("\n=== DECODED FLAG SEARCH ===")
leet_to_alpha = {
    '0': 'o', '1': 'i', '2': 'z', '3': 'e', '4': 'a', '5': 's',
    '6': 'g', '7': 't', '8': 'b', '9': 'p'
}

def decode(text):
    decoded = text
    for leet, alpha in leet_to_alpha.items():
        decoded = decoded.replace(leet, alpha)
    return decoded

decoded = decode(content)

# Now look for flags in decoded content
decoded_flags = re.findall(r'dawgctf\{[^}]+\}', decoded, re.IGNORECASE)
print(f"Flags in decoded content: {decoded_flags}")

# Look for any bracketed content in decoded
decoded_brackets = re.findall(r'\{[^}]*\}', decoded)
print(f"Bracketed content in decoded: {decoded_brackets}")

# Let me manually construct what I think the flag should be
print("\n=== MANUAL FLAG CONSTRUCTION ===")
# From earlier analysis, I found these parts after "drove me ":
# }pl, eh_, dne, s_e, sae, lp_, efi
# When concatenated and reversed: ife_please_send_help}

parts = ['}pl', 'eh_', 'dne', 's_e', 'sae', 'lp_', 'efi']
concatenated = ''.join(parts)
reversed_flag = concatenated[::-1]

print(f"Parts: {parts}")
print(f"Concatenated: {concatenated}")
print(f"Reversed: {reversed_flag}")

# Maybe there are additional parts I missed. Let me look at the file structure again.
# The file seems to have a pattern where each section gets progressively more corrupted
# Maybe there are more parts that I need to extract differently

# Let me look at the very end again for any missed characters
very_end = content[-100:]
print(f"\nVery end of file: {very_end}")

# Extract any final characters that might be part of the flag
final_chars = re.findall(r'[a-zA-Z0-9_{}]', very_end)
print(f"Final characters: {final_chars}")

# Maybe the flag needs to be constructed with these too
if final_chars:
    final_str = ''.join(final_chars)
    print(f"Final string: {final_str}")
    
    # Try adding it to the flag
    extended_flag = reversed_flag + final_str
    print(f"Extended flag: {extended_flag}")
    
    # Look for dawgctf pattern
    if 'dawgctf' in extended_flag.lower():
        print(f"Found dawgctf in extended!")
