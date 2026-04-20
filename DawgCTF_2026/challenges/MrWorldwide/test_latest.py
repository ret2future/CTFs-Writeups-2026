#!/usr/bin/env python3

# Test with the latest data from server
test_data = """20
0 68 93 61 83 34 80 34 87 21 91 49 38 75 20 35 34 79 32 27
68 0 20 91 27 79 75 69 50 34 6 41 54 98 23 53 23 31 56 73
93 20 0 11 99 33 5 43 57 21 100 77 26 88 6 40 99 22 79 92
61 91 11 0 53 29 37 29 11 26 22 90 8 83 35 4 10 24 35 24
83 27 99 53 0 79 38 47 76 98 84 94 86 95 96 52 12 77 93 65
34 79 33 29 79 0 92 71 23 46 23 63 61 9 66 90 83 76 45 21
80 75 5 37 38 92 0 38 4 56 73 76 52 76 84 87 94 74 78 17
34 69 43 29 47 71 38 0 85 57 8 41 69 1 37 16 53 18 6 87
87 50 57 11 76 23 4 85 0 19 8 33 82 57 79 96 45 45 71 51
21 34 21 26 98 46 56 57 19 0 33 80 13 8 12 7 29 50 3 76
91 6 100 22 84 23 73 8 8 33 0 38 26 67 49 67 36 99 37 44
49 41 77 90 94 63 76 41 33 80 38 0 98 32 95 68 56 79 67 72
38 54 26 8 86 61 52 69 82 13 26 98 0 22 96 25 22 12 4 74
75 98 88 83 95 9 76 1 57 8 67 32 22 0 72 50 94 67 11 58
20 23 6 35 96 66 84 37 79 12 49 95 96 72 0 34 43 60 65 60
35 53 40 4 52 90 87 16 96 7 67 68 25 50 34 0 58 44 65 85
34 23 99 10 12 83 94 53 45 29 36 56 22 94 43 58 0 57 12 24
79 31 22 24 77 76 74 18 45 50 99 79 12 67 60 44 57 0 50 95
32 56 79 35 93 45 78 6 71 3 37 67 4 11 65 65 12 50 0 50
27 73 92 24 65 21 17 87 51 76 44 72 74 58 60 85 24 95 50 0"""

import subprocess
import sys

# Write the test data to a temporary file and pipe it to our solver
process = subprocess.Popen(['python3', 'interactive_solver.py'], 
                         stdin=subprocess.PIPE, 
                         stdout=subprocess.PIPE, 
                         stderr=subprocess.PIPE,
                         text=True,
                         cwd='/Users/elenaeftimie/Desktop/CTFs/SIX-SEVEN/MrWorldwide')

stdout, stderr = process.communicate(input=test_data)

print("STDOUT:", stdout)
print("STDERR:", stderr)
