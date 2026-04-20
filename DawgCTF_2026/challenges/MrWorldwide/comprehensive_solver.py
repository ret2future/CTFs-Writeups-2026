#!/usr/bin/env python3
import sys
import heapq
from itertools import permutations

def solve_comprehensive():
    """Try different interpretations of the problem"""
    lines = sys.stdin.read().strip().split('\n')
    
    # First line is number of nodes
    n = int(lines[0].strip())
    
    # Parse adjacency matrix
    matrix = []
    for i in range(1, n+1):
        row = list(map(int, lines[i].strip().split()))
        matrix.append(row)
    
    print(f"Analyzing graph with {n} nodes...", file=sys.stderr)
    
    results = {}
    
    # 1. Traditional TSP (return to start)
    tsp_distance = solve_tsp_no_return(matrix)
    results["TSP (no return)"] = tsp_distance
    
    # 2. TSP with return to start
    tsp_return_distance = solve_tsp_with_return(matrix)
    results["TSP (with return)"] = tsp_return_distance
    
    # 3. Minimum Spanning Tree weight
    mst_distance = prim_mst(matrix)
    results["MST weight"] = mst_distance
    
    # 4. Shortest path to visit all nodes (greedy, no return)
    greedy_distance = greedy_tour(matrix)
    results["Greedy tour"] = greedy_distance
    
    # 5. Sum of shortest paths from one node to all others
    shortest_paths_sum = sum_shortest_paths(matrix)
    results["Sum of shortest paths"] = shortest_paths_sum
    
    print("Results:", file=sys.stderr)
    for name, value in results.items():
        print(f"  {name}: {value}", file=sys.stderr)
    
    # Return the most likely answer (start with TSP variants)
    return min(results["TSP (no return)"], results["TSP (with return)"], results["Greedy tour"])

def solve_tsp_no_return(matrix):
    """TSP without returning to start"""
    n = len(matrix)
    best_distance = float('inf')
    
    # Try multiple starting points
    for start in range(min(n, 3)):
        visited = [False] * n
        current = start
        visited[current] = True
        total_distance = 0
        path = [current]
        
        for _ in range(n - 1):
            # Find nearest unvisited
            min_dist = float('inf')
            nearest = None
            for city in range(n):
                if not visited[city] and matrix[current][city] < min_dist:
                    min_dist = matrix[current][city]
                    nearest = city
            
            if nearest is not None:
                visited[nearest] = True
                total_distance += min_dist
                current = nearest
                path.append(current)
        
        if total_distance < best_distance:
            best_distance = total_distance
    
    return best_distance

def solve_tsp_with_return(matrix):
    """TSP with return to start"""
    n = len(matrix)
    best_distance = float('inf')
    
    # Try multiple starting points
    for start in range(min(n, 3)):
        visited = [False] * n
        current = start
        visited[current] = True
        total_distance = 0
        path = [current]
        
        for _ in range(n - 1):
            # Find nearest unvisited
            min_dist = float('inf')
            nearest = None
            for city in range(n):
                if not visited[city] and matrix[current][city] < min_dist:
                    min_dist = matrix[current][city]
                    nearest = city
            
            if nearest is not None:
                visited[nearest] = True
                total_distance += min_dist
                current = nearest
                path.append(current)
        
        # Add return to start
        total_distance += matrix[current][start]
        
        if total_distance < best_distance:
            best_distance = total_distance
    
    return best_distance

def prim_mst(matrix):
    """Calculate MST weight using Prim's algorithm"""
    n = len(matrix)
    if n == 0:
        return 0
    
    visited = [False] * n
    min_heap = [(0, 0)]  # (weight, node)
    total_weight = 0
    edges_used = 0
    
    while min_heap and edges_used < n:
        weight, u = heapq.heappop(min_heap)
        
        if visited[u]:
            continue
        
        visited[u] = True
        total_weight += weight
        edges_used += 1
        
        for v in range(n):
            if not visited[v] and matrix[u][v] > 0:
                heapq.heappush(min_heap, (matrix[u][v], v))
    
    return total_weight

def greedy_tour(matrix):
    """Simple greedy tour without return"""
    n = len(matrix)
    if n == 0:
        return 0
    
    visited = [False] * n
    current = 0
    visited[current] = True
    total_distance = 0
    
    for _ in range(n - 1):
        # Find nearest unvisited
        min_dist = float('inf')
        nearest = None
        for city in range(n):
            if not visited[city] and matrix[current][city] < min_dist:
                min_dist = matrix[current][city]
                nearest = city
        
        if nearest is not None:
            visited[nearest] = True
            total_distance += min_dist
            current = nearest
    
    return total_distance

def sum_shortest_paths(matrix):
    """Sum of shortest paths from node 0 to all other nodes"""
    n = len(matrix)
    if n == 0:
        return 0
    
    # Dijkstra's algorithm
    distances = [float('inf')] * n
    distances[0] = 0
    visited = [False] * n
    
    for _ in range(n):
        # Find unvisited node with minimum distance
        min_dist = float('inf')
        current = -1
        for i in range(n):
            if not visited[i] and distances[i] < min_dist:
                min_dist = distances[i]
                current = i
        
        if current == -1:
            break
        
        visited[current] = True
        
        # Update distances to neighbors
        for neighbor in range(n):
            if not visited[neighbor]:
                new_dist = distances[current] + matrix[current][neighbor]
                if new_dist < distances[neighbor]:
                    distances[neighbor] = new_dist
    
    return sum(distances)

if __name__ == "__main__":
    distance = solve_comprehensive()
    print(distance)
