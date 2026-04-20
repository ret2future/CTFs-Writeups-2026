#!/usr/bin/env python3
import sys
from itertools import permutations

def solve_tsp():
    # Read the input from stdin
    lines = sys.stdin.read().strip().split('\n')
    
    # First line is number of nodes
    n = int(lines[0].strip())
    
    # Parse adjacency matrix
    matrix = []
    for i in range(1, n+1):
        row = list(map(int, lines[i].strip().split()))
        matrix.append(row)
    
    print(f"Number of cities: {n}")
    
    # For small n, we can use brute force
    # For n=20, we need a heuristic approach
    if n <= 10:
        return brute_force_tsp(matrix)
    else:
        return nearest_neighbor_tsp(matrix)

def brute_force_tsp(matrix):
    """Brute force TSP for small n"""
    n = len(matrix)
    min_distance = float('inf')
    best_path = None
    
    # Try all permutations starting from city 0
    cities = list(range(1, n))
    
    for perm in permutations(cities):
        # Calculate total distance for this path
        distance = 0
        current = 0
        
        # Visit all cities in permutation
        for city in perm:
            distance += matrix[current][city]
            current = city
        
        # Return to starting city
        distance += matrix[current][0]
        
        if distance < min_distance:
            min_distance = distance
            best_path = [0] + list(perm) + [0]
    
    return min_distance, best_path

def nearest_neighbor_tsp(matrix):
    """Nearest neighbor heuristic for larger n"""
    n = len(matrix)
    visited = [False] * n
    path = [0]  # Start from city 0
    visited[0] = True
    current = 0
    total_distance = 0
    
    for _ in range(n - 1):
        # Find nearest unvisited city
        min_dist = float('inf')
        nearest_city = None
        
        for city in range(n):
            if not visited[city] and city != current:
                if matrix[current][city] < min_dist:
                    min_dist = matrix[current][city]
                    nearest_city = city
        
        if nearest_city is not None:
            path.append(nearest_city)
            visited[nearest_city] = True
            total_distance += min_dist
            current = nearest_city
    
    # Return to starting city
    total_distance += matrix[current][0]
    path.append(0)
    
    return total_distance, path

def two_opt_improvement(matrix, path):
    """2-opt improvement heuristic"""
    improved = True
    best_distance = calculate_path_distance(matrix, path)
    
    while improved:
        improved = False
        n = len(path) - 1  # Exclude return to start
        
        for i in range(1, n - 1):
            for j in range(i + 1, n):
                if j - i == 1:  # Skip adjacent edges
                    continue
                
                # Create new path by reversing segment
                new_path = path[:i] + path[i:j][::-1] + path[j:]
                new_distance = calculate_path_distance(matrix, new_path)
                
                if new_distance < best_distance:
                    path = new_path
                    best_distance = new_distance
                    improved = True
                    break
            if improved:
                break
    
    return path, best_distance

def calculate_path_distance(matrix, path):
    """Calculate total distance for a given path"""
    total = 0
    for i in range(len(path) - 1):
        total += matrix[path[i]][path[i + 1]]
    return total

if __name__ == "__main__":
    # Test with the new data from server
    test_data = """20
0 68 7 56 69 29 76 45 61 51 81 1 21 91 94 64 65 85 61 38
68 0 28 18 41 88 36 91 64 5 80 67 91 8 99 64 83 7 100 39
7 28 0 99 1 34 35 46 86 3 21 33 98 89 25 56 46 53 17 20
56 18 99 0 74 61 99 14 81 3 95 1 84 80 56 71 30 33 59 78
69 41 1 74 0 9 36 38 36 82 95 36 42 90 29 58 84 33 88 24
29 88 34 61 9 0 32 72 11 79 79 92 31 56 42 10 1 13 14 28
76 36 35 99 36 32 0 13 33 33 6 28 2 72 6 60 36 3 70 72
45 91 46 14 38 72 13 0 45 12 90 17 7 77 14 48 40 34 20 84
61 64 86 81 36 11 33 45 0 59 51 40 94 60 16 3 90 59 76 93
51 5 3 3 82 79 33 12 59 0 80 41 86 99 96 90 39 73 33 99
81 80 21 95 95 79 6 90 51 80 0 70 59 5 16 9 92 98 99 9
1 67 33 1 36 92 28 17 40 41 70 0 83 19 98 74 64 79 41 18
21 91 98 84 42 31 2 7 94 86 59 83 0 73 34 51 78 82 73 76
91 8 89 80 90 56 72 77 60 99 5 19 73 0 3 46 3 12 37 93
94 99 25 56 29 42 6 14 16 96 16 98 34 3 0 13 39 90 56 33
64 64 56 71 58 10 60 48 3 90 9 74 51 46 13 0 1 16 99 42
65 83 46 30 84 1 36 40 90 39 92 64 78 3 39 1 0 97 5 81
85 7 53 33 33 13 3 34 59 73 98 79 82 12 90 16 97 0 94 47
61 100 17 59 88 14 70 20 76 33 99 41 73 37 56 99 5 94 0 31
38 39 20 78 24 28 72 84 93 99 9 18 76 93 33 42 81 47 31 0"""
    
    # Parse the test data
    lines = test_data.split('\n')
    n = int(lines[0])
    matrix = []
    for i in range(1, n+1):
        row = list(map(int, lines[i].split()))
        matrix.append(row)
    
    print(f"Solving TSP for {n} cities...")
    
    # Use nearest neighbor heuristic
    distance, path = nearest_neighbor_tsp(matrix)
    print(f"Initial distance: {distance}")
    print(f"Initial path: {path}")
    
    # Improve with 2-opt
    improved_path, improved_distance = two_opt_improvement(matrix, path)
    print(f"Improved distance: {improved_distance}")
    print(f"Improved path: {improved_path}")
    
    print(f"\nFinal answer: {improved_distance}")
