#!/usr/bin/env python3

print("=== SIMPLE SUBSTITUTE AND READ ===")
print("Following hint: substitute leetspeak letters first, then read")

# Read the file
with open('crazy.txt', 'r') as f:
    content = f.read()

print(f"Original file has {len(content)} characters")

# Step 1: Substitute leetspeak letters back to alphabetical
# Let me be very thorough but systematic

# Common leetspeak substitutions
leet_map = {
    '4': 'a', '3': 'e', '1': 'i', '0': 'o', '5': 's',
    '7': 't', '9': 'p', '8': 'b', '6': 'g', '2': 'z',
    '@': 'a', '$': 's', '!': 'i', '+': 't'
}

# Apply substitutions
decoded = content
for leet, alpha in leet_map.items():
    decoded = decoded.replace(leet, alpha)

print(f"After basic leetspeak substitution: {len(decoded)} characters")

# Step 2: Now just read through and look for anything that looks like a flag
print(f"\n=== LOOKING FOR FLAGS IN DECODED TEXT ===")

# Look for "dawgctf" in any form
import re

# Case insensitive search for dawgctf
dawg_matches = re.findall(r'dawgctf[^a-zA-Z]*\{[^}]*\}', decoded, re.IGNORECASE)
print(f"Found dawgctf patterns: {dawg_matches}")

# Look for any bracketed content
bracketed = re.findall(r'\{[^}]*\}', decoded)
print(f"Found bracketed content: {bracketed}")

# Look for "drove me" patterns simply
drove_matches = re.findall(r'drove me ([a-zA-Z0-9_{}]+)', decoded)
print(f"Found 'drove me' patterns: {drove_matches}")

# Step 3: Maybe I need to look at this completely differently
print(f"\n=== MANUAL APPROACH ===")

# Let me just scan through the decoded text visually
# Split into smaller chunks and look for interesting patterns

lines = decoded.split('\n')
interesting_lines = []

for i, line in enumerate(lines):
    # Look for lines that contain "drove me"
    if 'drove me' in line:
        interesting_lines.append((i, line.strip()))
    
    # Look for lines that have bracketed content
    if '{' in line and '}' in line:
        interesting_lines.append((i, line.strip()))
    
    # Look for lines that might contain flag-like content
    if any(word in line.lower() for word in ['flag', 'dawg', 'help', 'please', 'send']):
        interesting_lines.append((i, line.strip()))

print(f"Interesting lines found:")
for line_num, line in interesting_lines[:10]:  # First 10
    print(f"Line {line_num}: {line[:100]}...")

# Step 4: Let me try the most basic approach possible
print(f"\n=== MOST BASIC APPROACH ===")

# Maybe the flag is just constructed from the "drove me" parts I found
# Let me extract them manually without complex regex

manual_parts = []
for line in lines:
    if 'drove me' in line:
        # Find "drove me" position
        drove_pos = line.find('drove me')
        if drove_pos != -1:
            # Get everything after "drove me"
            after_drove = line[drove_pos + 8:]  # Skip "drove me"
            
            # Find next "crazy" or end of line
            crazy_pos = after_drove.find('crazy')
            if crazy_pos != -1:
                part = after_drove[:crazy_pos].strip()
            else:
                part = after_drove.strip()
            
            if part and len(part) < 20:  # Reasonable length
                manual_parts.append(part)

print(f"Manual extraction found: {manual_parts}")

# Step 5: Try the simplest flag construction
print(f"\n=== SIMPLEST FLAG CONSTRUCTION ===")

if len(manual_parts) >= 2:
    # Just concatenate and reverse
    simple_concat = ''.join(manual_parts)
    simple_reversed = simple_concat[::-1]
    
    print(f"Simple concatenation: {simple_concat}")
    print(f"Simple reversed: {simple_reversed}")
    
    # Clean it up
    clean_reversed = re.sub(r'[^a-zA-Z0-9_]', '', simple_reversed)
    print(f"Clean reversed: {clean_reversed}")
    
    simple_flag = f"dawgctf{{{clean_reversed}}}"
    print(f"Simple flag: {simple_flag}")

# Step 6: Maybe I need to think about this differently
print(f"\n=== ALTERNATE THINKING ===")

# The hint says "substituting each leetspeek-letter back to its alphabetical equivalent first"
# Maybe I should do this substitution and then look for a completely different pattern

# Let me look for any readable English words or phrases in the decoded text
words = re.findall(r'[a-zA-Z]{4,}', decoded)
print(f"Found {len(words)} words")

# Remove common repetitive words
common_words = {'crazy', 'once', 'they', 'locked', 'room', 'round', 'rubber', 'rats', 'wheels', 'that', 'with', 'from', 'were', 'drove', 'me'}
unique_words = [word for word in words if word.lower() not in common_words]

print(f"Unique words: {unique_words[:20]}")

# Maybe the flag is constructed from these unique words
if len(unique_words) >= 2:
    word_flag = '_'.join(unique_words[:3])
    print(f"Word-based flag: dawgctf{{{word_flag}}}")

# Step 7: Final attempt - maybe the answer is very simple
print(f"\n=== FINAL SIMPLE ATTEMPTS ===")

# Based on the theme, maybe the flag is just asking for help
simple_flags = [
    "dawgctf{help}",
    "dawgctf{please_help}",
    "dawgctf{send_help}",
    "dawgctf{help_me}",
    "dawgctf{crazy_help}",
    "dawgctf{room_help}",
]

print("Simple theme-based flags:")
for flag in simple_flags:
    print(f"  {flag}")

print(f"\nThe hint suggests avoiding complex regex and doing simple substitution first.")
print(f"Maybe the answer is much simpler than all my complex analysis.")
