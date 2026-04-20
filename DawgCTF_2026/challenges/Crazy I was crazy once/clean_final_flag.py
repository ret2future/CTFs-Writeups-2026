#!/usr/bin/env python3

print("=== CLEAN FINAL FLAG ===")

# The 11 parts I found through leetspeak substitution
parts = ['}pl', 'eh_', 'dne', 's_e', 'sae', 'lp_', 'efi', 'l_y', 'm_f', 'o_l', 'ort']

# Clean parts (remove spaces)
clean_parts = [part.replace(' ', '') for part in parts]

# Concatenate
concatenated = ''.join(clean_parts)

# Reverse
reversed_message = concatenated[::-1]

# Clean up - remove trailing brace and any extra characters
clean_message = reversed_message.rstrip('}').strip()

print(f"Reversed message: {reversed_message}")
print(f"Clean message: {clean_message}")

# Final flag
final_flag = f"dawgctf{{{clean_message}}}"
print(f"\nFinal flag: {final_flag}")

# Also try the version without underscores
no_underscores = clean_message.replace('_', '')
flag_no_underscores = f"dawgctf{{{no_underscores}}}"
print(f"Without underscores: {flag_no_underscores}")

print(f"\nBased on the hint to do leetspeak substitution first and avoid complex regex,")
print(f"the most straightforward flag is:")
print(f"dawgctf{trol_of_my_life_please_send_help}")
