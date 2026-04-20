#!/usr/bin/env python3

# Let me manually count some samples to check my math
samples = [
    # First 10 samples from the log
    ("minor", "minor"),      # sample 001
    ("major", "major"),      # sample 002  
    ("major", "major"),      # sample 003
    ("major", "major"),      # sample 004
    ("major", "medium"),     # sample 005
    ("major", "major"),      # sample 006
    ("major", "medium"),     # sample 007
    ("medium", "minor"),     # sample 008
    ("major", "major"),      # sample 009
    ("minor", "minor"),      # sample 010
]

print("Manual check of first 10 samples:")
print("Sample | Known | Detected | Minor FP/FN | Medium FP/FN | Major FP/FN")
print("-" * 70)

minor_fp = minor_fn = 0
medium_fp = medium_fn = 0  
major_fp = major_fn = 0

for i, (known, detected) in enumerate(samples, 1):
    print(f"#{i:03d}   | {known:6} | {detected:8} |", end=" ")
    
    # Check for minor classification
    if known == "minor" and detected != "minor":
        minor_fn += 1
        print(f"FN={minor_fn}    | ", end="")
    elif known != "minor" and detected == "minor":
        minor_fp += 1
        print(f"FP={minor_fp}    | ", end="")
    else:
        print(f"           | ", end="")
        
    # Check for medium classification  
    if known == "medium" and detected != "medium":
        medium_fn += 1
        print(f"FN={medium_fn}    | ", end="")
    elif known != "medium" and detected == "medium":
        medium_fp += 1
        print(f"FP={medium_fp}    | ", end="")
    else:
        print(f"           | ", end="")
        
    # Check for major classification
    if known == "major" and detected != "major":
        major_fn += 1
        print(f"FN={major_fn}")
    elif known != "major" and detected == "major":
        major_fp += 1
        print(f"FP={major_fp}")
    else:
        print()

print(f"\nCounts after 10 samples:")
print(f"Minor: FP={minor_fp}, FN={minor_fn}")
print(f"Medium: FP={medium_fp}, FN={medium_fn}")
print(f"Major: FP={major_fp}, FN={major_fn}")
