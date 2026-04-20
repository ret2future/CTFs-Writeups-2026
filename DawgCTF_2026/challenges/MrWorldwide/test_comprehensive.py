#!/usr/bin/env python3

# Test with the latest data from server
test_data = """20
0 4 61 4 74 8 19 27 70 17 28 62 33 38 32 53 91 73 26 77
4 0 15 88 79 51 74 1 37 9 89 26 23 32 60 43 80 83 36 50
61 15 0 54 30 49 20 79 100 41 6 78 48 91 85 14 92 31 92 68
4 88 54 0 94 37 25 82 77 59 37 11 58 39 82 3 28 61 36 59
74 79 30 94 0 5 88 77 87 100 55 55 80 71 57 48 83 65 53 96
8 51 49 37 5 0 69 46 8 88 58 20 40 65 91 6 43 28 90 51
19 74 20 25 88 69 0 39 85 12 26 40 7 49 74 39 52 46 70 36
27 1 79 82 77 46 39 0 10 15 36 39 16 99 25 90 84 98 81 30
70 37 100 77 87 8 85 10 0 27 29 63 32 2 90 98 72 17 98 27
17 9 41 59 100 88 12 15 27 0 17 38 26 92 30 88 57 7 100 19
28 89 6 37 55 58 26 36 29 17 0 76 2 8 48 11 63 66 16 81
62 26 78 11 55 20 40 39 63 38 76 0 5 76 76 67 80 73 80 72
33 23 48 58 80 40 7 16 32 26 2 5 0 16 68 66 23 90 9 63
38 32 91 39 71 65 49 99 2 92 8 76 16 0 16 9 71 44 4 69
32 60 85 82 57 91 74 25 90 30 48 76 68 16 0 68 85 57 49 79
53 43 14 3 48 6 39 90 98 88 11 67 66 9 68 0 61 47 64 25
91 80 92 28 83 43 52 84 72 57 63 80 23 71 85 61 0 72 31 17
73 83 31 61 65 28 46 98 17 7 66 73 90 44 57 47 72 0 34 50
26 36 92 36 53 90 70 81 98 100 16 80 9 4 49 64 31 34 0 16
77 50 68 59 96 51 36 30 27 19 81 72 63 69 79 25 17 50 16 0"""

import subprocess
import sys

# Write the test data to a temporary file and pipe it to our solver
process = subprocess.Popen(['python3', 'comprehensive_solver.py'], 
                         stdin=subprocess.PIPE, 
                         stdout=subprocess.PIPE, 
                         stderr=subprocess.PIPE,
                         text=True,
                         cwd='/Users/elenaeftimie/Desktop/CTFs/SIX-SEVEN/MrWorldwide')

stdout, stderr = process.communicate(input=test_data)

print("STDOUT:", stdout)
print("STDERR:", stderr)
