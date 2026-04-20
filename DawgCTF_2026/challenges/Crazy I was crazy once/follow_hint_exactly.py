#!/usr/bin/env python3

print("=== FOLLOWING HINT EXACTLY ===")
print("Hint: 'try substituting each leetspeek-letter back to its alphabetical equivalent first'")

# Maybe I need to be more methodical about the leetspeak substitution
# Let me start with ONLY the most basic leetspeak substitutions

# Read the file
with open('crazy.txt', 'r') as f:
    content = f.read()

print(f"Original file length: {len(content)}")

# Step 1: ONLY the most common leetspeak substitutions
basic_leet = {
    '4': 'a',
    '3': 'e', 
    '1': 'i',
    '0': 'o',
    '5': 's'
}

def basic_substitute(text):
    result = text
    for leet, alpha in basic_leet.items():
        result = result.replace(leet, alpha)
    return result

basic_decoded = basic_substitute(content)
print(f"Basic decoded length: {len(basic_decoded)}")

# Now look for the "drove me" pattern in this basic decoded version
import re

drove_patterns = re.findall(r'drove me ([^c]+?)(?: crazy|$)', basic_decoded)
print(f"Found {len(drove_patterns)} 'drove me' patterns in basic decode:")
for i, pattern in enumerate(drove_patterns):
    print(f"{i+1}: '{pattern.strip()}'")

# Step 2: Try with a few more common substitutions
extended_leet = {
    '4': 'a', '3': 'e', '1': 'i', '0': 'o', '5': 's',
    '7': 't', '9': 'p', '8': 'b', '6': 'g', '2': 'z'
}

def extended_substitute(text):
    result = text
    for leet, alpha in extended_leet.items():
        result = result.replace(leet, alpha)
    return result

extended_decoded = extended_substitute(content)
print(f"\nExtended decoded length: {len(extended_decoded)}")

drove_patterns_ext = re.findall(r'drove me ([^c]+?)(?: crazy|$)', extended_decoded)
print(f"Found {len(drove_patterns_ext)} 'drove me' patterns in extended decode:")
for i, pattern in enumerate(drove_patterns_ext):
    print(f"{i+1}: '{pattern.strip()}'")

# Step 3: Maybe I need to look for the encoded versions in the basic decoded
print(f"\n=== LOOKING FOR ENCODED VERSIONS ===")

# In the basic decoded content, look for variations of "drove me"
variations = [
    "drove me",
    "dr0ve me",  # o->0
    "drov3 me",  # e->3
    "dr0v3 me",  # o->0, e->3
    "drove m3",  # e->3
    "dr0ve m3",  # o->0, e->3
    "drov3 m3",  # e->3, e->3
    "dr0v3 m3"   # o->0, e->3, e->3
]

all_parts_basic = []
for variation in variations:
    pattern = f'{variation} ([^c]+?)(?: crazy|$)'
    matches = re.findall(pattern, basic_decoded)
    if matches:
        print(f"Found {len(matches)} matches for '{variation}' in basic decode:")
        for match in matches:
            print(f"  '{match.strip()}'")
            all_parts_basic.append(match.strip())

print(f"\nAll parts from basic decode: {all_parts_basic}")

# Step 4: Maybe the hint means I should substitute FIRST, then look for patterns
print(f"\n=== STEP 4: SUBSTITUTE FIRST, THEN PATTERN MATCH ===")

# Let me try the most comprehensive substitution first
comprehensive_leet = {
    '0': 'o', '1': 'i', '2': 'z', '3': 'e', '4': 'a', '5': 's',
    '6': 'g', '7': 't', '8': 'b', '9': 'p', '@': 'a', '$': 's',
    '!': 'i', '+': 't', '|': 'i', '(': 'c', ')': 'd', '<': 'c',
    '>': 'd', '[': 'j', ']': 'k', '{': 'f', '}': 'j', '/': 'v',
    '\\': 'v', '_': ' ', '-': 'h', '&': 'b', '%': 'x', '^': 'n',
    '*': 'm', '=': 'r'
}

def comprehensive_substitute(text):
    result = text
    for leet, alpha in comprehensive_leet.items():
        result = result.replace(leet, alpha)
    return result

fully_decoded = comprehensive_substitute(content)
print(f"Fully decoded length: {len(fully_decoded)}")

# Now look for "drove me" in the fully decoded
drove_patterns_full = re.findall(r'drove me ([^c]+?)(?: crazy|$)', fully_decoded)
print(f"Found {len(drove_patterns_full)} 'drove me' patterns in fully decoded:")
for i, pattern in enumerate(drove_patterns_full):
    print(f"{i+1}: '{pattern.strip()}'")

# Step 5: Maybe I'm overthinking this - let me just look at the most basic approach
print(f"\n=== STEP 5: BACK TO BASICS ===")

# Maybe the flag is simpler than I think
# Let me just use the original 7 parts I found and try the most straightforward flag

original_parts = ['}pl', 'eh_', 'dne', 's_e', 'sae', 'lp_', 'efi']
concatenated = ''.join(original_parts)
reversed_flag = concatenated[::-1]

print(f"Original parts: {original_parts}")
print(f"Concatenated: {concatenated}")
print(f"Reversed: {reversed_flag}")

# Maybe the flag is just this, without any complex interpretation
simple_flag = f"dawgctf{{{reversed_flag}}}"
print(f"Simple flag: {simple_flag}")

# Or maybe without the extra brace
clean_reversed = reversed_flag.rstrip('}')
clean_flag = f"dawgctf{{{clean_reversed}}}"
print(f"Clean flag: {clean_flag}")

# Step 6: Maybe the hint is telling me to avoid regex altogether
print(f"\n=== STEP 6: AVOIDING REGEX ===")

# Maybe I should just manually scan through the file
# Let me look for "drove me" manually

lines = content.split('\n')
manual_parts = []

for line in lines:
    if 'drove me' in line:
        # Extract what comes after "drove me"
        drove_pos = line.find('drove me')
        after_drove = line[drove_pos + 8:]  # +8 to skip "drove me"
        
        # Extract until we hit "crazy" or end of line
        crazy_pos = after_drove.find('crazy')
        if crazy_pos != -1:
            part = after_drove[:crazy_pos].strip()
        else:
            part = after_drove.strip()
        
        if part:
            manual_parts.append(part)
            print(f"Manual extraction: '{part}'")

print(f"Manual parts: {manual_parts}")

if len(manual_parts) >= 2:
    manual_concat = ''.join(manual_parts)
    manual_reversed = manual_concat[::-1]
    manual_flag = f"dawgctf{{{manual_reversed}}}"
    print(f"Manual flag: {manual_flag}")

# Step 7: Maybe I need to think about this completely differently
print(f"\n=== STEP 7: DIFFERENT APPROACH ===")

# The hint says "substituting each leetspeek-letter back to its alphabetical equivalent first"
# Maybe this means I should do the substitution, then look for a completely different pattern

# Let me look for any readable text in the fully decoded version
readable_words = re.findall(r'[a-zA-Z]{4,}', fully_decoded)

# Filter out the repetitive words
repetitive = {'crazy', 'once', 'they', 'locked', 'room', 'round', 'rubber', 'rats', 'wheels', 'that', 'with', 'from', 'were', 'drove'}
unique_words = [word for word in readable_words if word.lower() not in repetitive]

print(f"Unique readable words: {unique_words[:20]}")

# Maybe the flag is constructed from these
if len(unique_words) >= 2:
    flag_from_words = '_'.join(unique_words[:3])
    print(f"Flag from words: dawgctf{{{flag_from_words}}}")

# Final thought: maybe the flag is much simpler
print(f"\n=== FINAL SIMPLE APPROACH ===")
print("Maybe I'm overthinking this. Let me try the most obvious flags:")

simple_attempts = [
    "dawgctf{please_send_help}",
    "dawgctf{send_help}",
    "dawgctf{help}",
    "dawgctf{crazy_help}",
    "dawgctf{room_help}",
]

for attempt in simple_attempts:
    print(f"  {attempt}")
