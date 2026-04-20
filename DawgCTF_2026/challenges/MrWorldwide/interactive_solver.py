#!/usr/bin/env python3
import sys
import random
from itertools import permutations
import math

def solve_tsp_interactive():
    """Solve TSP interactively by reading from stdin"""
    lines = sys.stdin.read().strip().split('\n')
    
    # First line is number of nodes
    n = int(lines[0].strip())
    
    # Parse adjacency matrix
    matrix = []
    for i in range(1, n+1):
        row = list(map(int, lines[i].strip().split()))
        matrix.append(row)
    
    print(f"Solving TSP for {n} cities...", file=sys.stderr)
    
    # Try multiple approaches and take the best
    best_distance = float('inf')
    best_path = None
    
    # 1. Nearest neighbor from multiple starting points
    for start in range(min(n, 5)):  # Try first 5 cities as starting points
        distance, path = nearest_neighbor_tsp(matrix, start)
        if distance < best_distance:
            best_distance = distance
            best_path = path
    
    # 2. 2-opt improvement
    if best_path:
        improved_path, improved_distance = two_opt_improvement(matrix, best_path)
        if improved_distance < best_distance:
            best_distance = improved_distance
            best_path = improved_path
    
    # 3. Simulated annealing for better results
    annealed_path, annealed_distance = simulated_annealing(matrix, best_path if best_path else list(range(n)))
    if annealed_distance < best_distance:
        best_distance = annealed_distance
        best_path = annealed_path
    
    # 4. For small n, try exact solution
    if n <= 12:
        exact_distance, exact_path = exact_tsp(matrix)
        if exact_distance < best_distance:
            best_distance = exact_distance
            best_path = exact_path
    
    print(f"Best distance found: {best_distance}", file=sys.stderr)
    print(f"Path: {best_path}", file=sys.stderr)
    
    return best_distance

def nearest_neighbor_tsp(matrix, start=0):
    """Nearest neighbor heuristic"""
    n = len(matrix)
    visited = [False] * n
    path = [start]
    visited[start] = True
    current = start
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
    total_distance += matrix[current][start]
    path.append(start)
    
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

def simulated_annealing(matrix, initial_path):
    """Simulated annealing for TSP"""
    n = len(matrix)
    current_path = initial_path[:-1]  # Remove return to start
    current_distance = calculate_path_distance(matrix, current_path + [current_path[0]])
    
    best_path = current_path[:]
    best_distance = current_distance
    
    temperature = 1000.0
    cooling_rate = 0.995
    min_temperature = 1.0
    
    while temperature > min_temperature:
        # Generate neighbor by swapping two random cities
        new_path = current_path[:]
        i, j = random.sample(range(len(new_path)), 2)
        new_path[i], new_path[j] = new_path[j], new_path[i]
        
        new_distance = calculate_path_distance(matrix, new_path + [new_path[0]])
        
        # Accept if better or with probability based on temperature
        delta = new_distance - current_distance
        if delta < 0 or random.random() < math.exp(-delta / temperature):
            current_path = new_path
            current_distance = new_distance
            
            if current_distance < best_distance:
                best_path = current_path[:]
                best_distance = current_distance
        
        temperature *= cooling_rate
    
    return best_path + [best_path[0]], best_distance

def exact_tsp(matrix):
    """Exact TSP solution using brute force for small n"""
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

def calculate_path_distance(matrix, path):
    """Calculate total distance for a given path"""
    total = 0
    for i in range(len(path) - 1):
        total += matrix[path[i]][path[i + 1]]
    return total

if __name__ == "__main__":
    distance = solve_tsp_interactive()
    print(distance)
