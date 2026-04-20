#!/usr/bin/env python3
import sys
import subprocess
import time
from itertools import permutations

def get_current_graph():
    """Get the current graph from the server"""
    try:
        result = subprocess.run(['nc', 'nc.umbccd.net', '23456'], 
                              capture_output=True, text=True, timeout=10)
        lines = result.stdout.strip().split('\n')
        n = int(lines[0].strip())
        matrix = []
        for i in range(1, n+1):
            row = list(map(int, lines[i].strip().split()))
            matrix.append(row)
        return n, matrix, result.stdout
    except Exception as e:
        print(f"Error getting graph: {e}")
        return None, None, None

def test_answer(answer):
    """Test an answer against the server"""
    try:
        result = subprocess.run(['echo', str(answer)], 
                              stdout=subprocess.PIPE)
        result = subprocess.run(['nc', 'nc.umbccd.net', '23456'], 
                              input=result.stdout.decode(), 
                              text=True, capture_output=True, timeout=10)
        return "Wrong!" not in result.stdout, result.stdout
    except Exception as e:
        print(f"Error testing answer: {e}")
        return False, ""

def solve_tsp_exact(matrix):
    """Exact TSP solution using dynamic programming"""
    n = len(matrix)
    
    # Check if graph is connected
    if not is_connected(matrix):
        print("Graph is not connected, TSP not possible")
        return None
    
    # Use Held-Karp algorithm
    return held_karp_tsp(matrix)

def is_connected(matrix):
    """Check if graph is connected"""
    n = len(matrix)
    visited = [False] * n
    stack = [0]
    visited[0] = True
    
    while stack:
        current = stack.pop()
        for neighbor in range(n):
            if not visited[neighbor] and matrix[current][neighbor] > 0:
                visited[neighbor] = True
                stack.append(neighbor)
    
    return all(visited)

def held_karp_tsp(matrix):
    """Held-Karp dynamic programming algorithm for TSP"""
    n = len(matrix)
    if n > 20:  # Too large for exact solution
        print("Graph too large for exact TSP")
        return None
    
    # dp[mask][i] = minimum distance to visit nodes in mask, ending at i
    INF = float('inf')
    dp = {}
    
    # Base case: start from city 0
    for i in range(1, n):
        mask = 1 << i
        dp[(mask, i)] = matrix[0][i]
    
    # Fill DP table
    for mask in range(1, 1 << n):
        if mask & 1 == 0:  # Skip masks that don't include meaningful nodes
            continue
            
        for u in range(1, n):
            if not (mask & (1 << u)):
                continue
                
            if (mask, u) not in dp:
                continue
                
            # Try to go to v
            for v in range(1, n):
                if mask & (1 << v):
                    continue
                    
                new_mask = mask | (1 << v)
                new_dist = dp[(mask, u)] + matrix[u][v]
                
                if (new_mask, v) not in dp or new_dist < dp[(new_mask, v)]:
                    dp[(new_mask, v)] = new_dist
    
    # Complete the tour
    full_mask = (1 << n) - 1
    min_tour = INF
    
    for u in range(1, n):
        if (full_mask, u) in dp:
            tour_dist = dp[(full_mask, u)] + matrix[u][0]
            if tour_dist < min_tour:
                min_tour = tour_dist
    
    return min_tour if min_tour != INF else None

def solve_tsp_no_return_exact(matrix):
    """Exact TSP without returning to start"""
    n = len(matrix)
    
    if not is_connected(matrix):
        return None
    
    if n > 15:  # Too large for exact solution without return
        return None
    
    min_dist = float('inf')
    
    # Try all permutations of cities 1..n-1
    for perm in permutations(range(1, n)):
        total = matrix[0][perm[0]]  # Start from 0
        for i in range(len(perm) - 1):
            total += matrix[perm[i]][perm[i+1]]
        min_dist = min(min_dist, total)
    
    return min_dist if min_dist != float('inf') else None

def solve_with_heuristics(matrix):
    """Solve using various heuristics"""
    n = len(matrix)
    results = {}
    
    # Nearest neighbor
    results["nearest_neighbor"] = nearest_neighbor_tsp(matrix)
    
    # Nearest neighbor from different starts
    for start in range(min(n, 5)):
        results[f"nearest_neighbor_from_{start}"] = nearest_neighbor_from_start(matrix, start)
    
    # 2-opt improvement
    results["nearest_neighbor_2opt"] = nearest_neighbor_with_2opt(matrix)
    
    # MST-based approximations
    results["mst"] = prim_mst(matrix)
    results["double_mst"] = 2 * prim_mst(matrix)
    
    # Greedy algorithm
    results["greedy"] = greedy_tsp(matrix)
    
    return results

def nearest_neighbor_tsp(matrix):
    """Nearest neighbor TSP starting from city 0"""
    n = len(matrix)
    visited = [False] * n
    current = 0
    visited[current] = True
    total_distance = 0
    path = [current]
    
    for _ in range(n - 1):
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
    
    # Return to start
    total_distance += matrix[current][0]
    path.append(0)
    
    return total_distance

def nearest_neighbor_from_start(matrix, start):
    """Nearest neighbor TSP from specific start"""
    n = len(matrix)
    visited = [False] * n
    current = start
    visited[current] = True
    total_distance = 0
    
    for _ in range(n - 1):
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
    
    total_distance += matrix[current][start]
    return total_distance

def nearest_neighbor_with_2opt(matrix):
    """Nearest neighbor with 2-opt improvement"""
    # Start with nearest neighbor
    n = len(matrix)
    path = [0]
    visited = [False] * n
    visited[0] = True
    current = 0
    
    for _ in range(n - 1):
        min_dist = float('inf')
        nearest = None
        for city in range(n):
            if not visited[city] and matrix[current][city] < min_dist:
                min_dist = matrix[current][city]
                nearest = city
        
        if nearest is not None:
            visited[nearest] = True
            path.append(nearest)
            current = nearest
    
    path.append(0)
    
    # Apply 2-opt
    distance = calculate_path_distance(matrix, path)
    improved = True
    
    while improved:
        improved = False
        for i in range(1, len(path) - 2):
            for j in range(i + 1, len(path) - 1):
                if j - i == 1:
                    continue
                
                new_path = path[:i] + path[i:j][::-1] + path[j:]
                new_distance = calculate_path_distance(matrix, new_path)
                
                if new_distance < distance:
                    path = new_path
                    distance = new_distance
                    improved = True
                    break
            if improved:
                break
    
    return distance

def greedy_tsp(matrix):
    """Greedy TSP algorithm"""
    n = len(matrix)
    
    # Sort all edges by weight
    edges = []
    for i in range(n):
        for j in range(i + 1, n):
            edges.append((matrix[i][j], i, j))
    
    edges.sort()
    
    # Build tour using greedy approach
    path = [0]
    visited = [False] * n
    visited[0] = True
    current = 0
    
    while len(path) < n:
        # Find shortest edge from current to unvisited
        min_dist = float('inf')
        nearest = None
        for city in range(n):
            if not visited[city] and matrix[current][city] < min_dist:
                min_dist = matrix[current][city]
                nearest = city
        
        if nearest is not None:
            visited[nearest] = True
            path.append(nearest)
            current = nearest
    
    # Return to start
    path.append(0)
    return calculate_path_distance(matrix, path)

def prim_mst(matrix):
    """Prim's algorithm for MST"""
    import heapq
    n = len(matrix)
    if n == 0:
        return 0
    
    visited = [False] * n
    min_heap = [(0, 0)]
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

def calculate_path_distance(matrix, path):
    """Calculate total distance for a path"""
    total = 0
    for i in range(len(path) - 1):
        total += matrix[path[i]][path[i + 1]]
    return total

def main():
    print("Getting current graph from server...")
    n, matrix, server_output = get_current_graph()
    
    if matrix is None:
        print("Failed to get graph from server")
        return
    
    print(f"Graph has {n} nodes")
    
    # Try exact TSP first
    print("Trying exact TSP...")
    exact_result = solve_tsp_exact(matrix)
    if exact_result is not None:
        print(f"Exact TSP result: {exact_result}")
        success, output = test_answer(exact_result)
        if success:
            print(f"SUCCESS! Exact TSP: {exact_result}")
            print(f"Server output: {output}")
            return
        else:
            print("Exact TSP was wrong")
    
    # Try TSP without return
    print("Trying exact TSP without return...")
    exact_no_return = solve_tsp_no_return_exact(matrix)
    if exact_no_return is not None:
        print(f"Exact TSP no return: {exact_no_return}")
        success, output = test_answer(exact_no_return)
        if success:
            print(f"SUCCESS! Exact TSP no return: {exact_no_return}")
            print(f"Server output: {output}")
            return
        else:
            print("Exact TSP no return was wrong")
    
    # Try heuristics
    print("Trying heuristic approaches...")
    heuristic_results = solve_with_heuristics(matrix)
    
    # Sort by value
    sorted_results = sorted(heuristic_results.items(), key=lambda x: x[1])
    
    for name, value in sorted_results:
        print(f"Testing {name}: {value}")
        success, output = test_answer(value)
        if success:
            print(f"SUCCESS! {name}: {value}")
            print(f"Server output: {output}")
            return
        else:
            print(f"  Wrong answer")
        time.sleep(0.5)
    
    print("None of the approaches worked.")

if __name__ == "__main__":
    main()
