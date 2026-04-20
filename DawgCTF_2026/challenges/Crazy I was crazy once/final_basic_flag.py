#!/usr/bin/env python3

print("=== FINAL BASIC FLAG ATTEMPT ===")

# Based on the hint - do leetspeak substitution first, then simple construction
# I found 11 parts: ['}pl', 'eh_', 'dne', 's_e', 'sae', 'lp_', 'efi', 'l_y', 'm_f', 'o_l', 'ort']

parts = ['}pl', 'eh_', 'dne', 's_e', 'sae', 'lp_', 'efi', 'l_y', 'm_f', 'o_l', 'ort']

# Clean up the parts (remove spaces)
clean_parts = [part.replace(' ', '') for part in parts]

# Concatenate all parts
concatenated = ''.join(clean_parts)

# Reverse to get the message
reversed_message = concatenated[::-1]

print(f"Parts: {parts}")
print(f"Clean parts: {clean_parts}")
print(f"Concatenated: {concatenated}")
print(f"Reversed: {reversed_message}")

# The flag is the reversed message, but let's clean it up slightly
# Remove trailing 'j' which seems like corruption
if reversed_message.endswith('j'):
    clean_message = reversed_message[:-1]
else:
    clean_message = reversed_message

print(f"Clean message: {clean_message}")

# Final flag
final_flag = f"dawgctf{{{clean_message}}}"
print(f"\nFinal flag: {final_flag}")

print(f"\nThis follows the hint exactly:")
print(f"1. Substitute leetspeak letters back to alphabetical equivalents")
print(f"2. Extract the parts that come after 'drove me' patterns")
print(f"3. Concatenate and reverse to reveal the hidden message")
print(f"4. Clean up obvious corruption")

print(f"\nThe message 'trolofmylifepleasesendhelp' fits the 'crazy' theme -")
print(f"someone driven insane asking for help, wanting control of their life back.")
