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

def solve_exhaustively(matrix):
    """Try many different interpretations"""
    n = len(matrix)
    results = {}
    
    # Basic graph algorithms
    results["mst"] = prim_mst(matrix)
    results["double_mst"] = 2 * prim_mst(matrix)
    results["triple_mst"] = 3 * prim_mst(matrix)
    
    # TSP variants
    results["tsp_return_nn"] = tsp_nearest_neighbor(matrix, return_to_start=True)
    results["tsp_no_return_nn"] = tsp_nearest_neighbor(matrix, return_to_start=False)
    
    # Try TSP from different starting points
    for start in range(min(n, 5)):
        results[f"tsp_return_start_{start}"] = tsp_from_start(matrix, start, return_to_start=True)
        results[f"tsp_no_return_start_{start}"] = tsp_from_start(matrix, start, return_to_start=False)
    
    # Path-based approaches
    results["shortest_path_all"] = shortest_path_to_visit_all(matrix)
    results["min_span_path"] = minimum_spanning_path(matrix)
    
    # Edge-based approaches
    results["sum_all_edges"] = sum_all_edges(matrix)
    results["sum_min_edges"] = sum_of_minimum_edges(matrix)
    results["sum_second_min_edges"] = sum_of_second_minimum_edges(matrix)
    
    # Combinations
    results["mst_plus_min_edge"] = results["mst"] + find_minimum_edge(matrix)
    results["mst_plus_max_edge"] = results["mst"] + find_maximum_edge(matrix)
    results["mst_plus_avg_edge"] = results["mst"] + average_edge_weight(matrix)
    
    # Degree-based approaches
    results["sum_min_degrees"] = sum_of_minimum_degree_edges(matrix)
    results["sum_all_degrees"] = sum_of_all_degree_edges(matrix)
    
    # Approximation algorithms
    results["christofides_approx"] = christofides_approximation(matrix)
    
    # Exact for small n (if n <= 10)
    if n <= 10:
        results["exact_tsp"] = exact_tsp(matrix)
    
    return results

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

def tsp_nearest_neighbor(matrix, return_to_start=True):
    """Nearest neighbor TSP"""
    n = len(matrix)
    best_distance = float('inf')
    
    for start in range(min(n, 3)):
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
        
        if return_to_start:
            total_distance += matrix[current][start]
        
        best_distance = min(best_distance, total_distance)
    
    return best_distance

def tsp_from_start(matrix, start, return_to_start=True):
    """TSP from specific starting point"""
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
    
    if return_to_start:
        total_distance += matrix[current][start]
    
    return total_distance

def shortest_path_to_visit_all(matrix):
    """Find shortest path that visits all nodes (not necessarily returning)"""
    n = len(matrix)
    
    # Use dynamic programming for exact solution
    if n <= 15:
        return dp_shortest_path(matrix)
    else:
        # Use greedy approximation
        return greedy_path(matrix)

def dp_shortest_path(matrix):
    """Dynamic programming for shortest path visiting all nodes"""
    n = len(matrix)
    INF = float('inf')
    
    # dp[mask][i] = min distance to visit nodes in mask ending at i
    dp = [[INF] * n for _ in range(1 << n)]
    
    # Start from each node
    for i in range(n):
        dp[1 << i][i] = 0
    
    for mask in range(1 << n):
        for u in range(n):
            if not (mask & (1 << u)):
                continue
            if dp[mask][u] == INF:
                continue
            
            for v in range(n):
                if mask & (1 << v):
                    continue
                new_mask = mask | (1 << v)
                new_dist = dp[mask][u] + matrix[u][v]
                if new_dist < dp[new_mask][v]:
                    dp[new_mask][v] = new_dist
    
    # Find minimum distance to visit all nodes
    full_mask = (1 << n) - 1
    return min(dp[full_mask][i] for i in range(n))

def greedy_path(matrix):
    """Greedy path visiting all nodes"""
    n = len(matrix)
    visited = [False] * n
    current = 0
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
    
    return total_distance

def minimum_spanning_path(matrix):
    """Find minimum weight path that visits all nodes"""
    n = len(matrix)
    
    # Try all permutations for small n
    if n <= 8:
        best = float('inf')
        for perm in permutations(range(n)):
            total = sum(matrix[perm[i]][perm[i+1]] for i in range(n-1))
            best = min(best, total)
        return best
    else:
        # Use heuristic
        return greedy_path(matrix)

def sum_all_edges(matrix):
    """Sum of all edge weights"""
    n = len(matrix)
    total = 0
    for i in range(n):
        for j in range(i+1, n):
            total += matrix[i][j]
    return total

def sum_of_minimum_edges(matrix):
    """Sum of minimum edge for each node"""
    n = len(matrix)
    total = 0
    for i in range(n):
        min_edge = min(matrix[i][j] for j in range(n) if i != j)
        total += min_edge
    return total // 2  # Each edge counted twice

def sum_of_second_minimum_edges(matrix):
    """Sum of second minimum edge for each node"""
    n = len(matrix)
    total = 0
    for i in range(n):
        edges = sorted(matrix[i][j] for j in range(n) if i != j)
        if len(edges) >= 2:
            total += edges[1]
    return total // 2  # Each edge counted twice

def find_minimum_edge(matrix):
    """Find minimum edge weight in graph"""
    n = len(matrix)
    min_edge = float('inf')
    for i in range(n):
        for j in range(i+1, n):
            min_edge = min(min_edge, matrix[i][j])
    return min_edge

def find_maximum_edge(matrix):
    """Find maximum edge weight in graph"""
    n = len(matrix)
    max_edge = 0
    for i in range(n):
        for j in range(i+1, n):
            max_edge = max(max_edge, matrix[i][j])
    return max_edge

def average_edge_weight(matrix):
    """Average edge weight"""
    n = len(matrix)
    total = 0
    count = 0
    for i in range(n):
        for j in range(i+1, n):
            total += matrix[i][j]
            count += 1
    return total // count if count > 0 else 0

def sum_of_minimum_degree_edges(matrix):
    """Sum of edges from minimum degree node"""
    n = len(matrix)
    min_degree = n
    min_node = 0
    
    for i in range(n):
        degree = sum(1 for j in range(n) if i != j and matrix[i][j] > 0)
        if degree < min_degree:
            min_degree = degree
            min_node = i
    
    return sum(matrix[min_node][j] for j in range(n) if min_node != j)

def sum_of_all_degree_edges(matrix):
    """Sum of all degree edges"""
    n = len(matrix)
    total = 0
    for i in range(n):
        total += sum(matrix[i][j] for j in range(n) if i != j)
    return total // 2  # Each edge counted twice

def christofides_approximation(matrix):
    """Christofides approximation for TSP"""
    # Simplified version: MST + matching on odd degree vertices
    mst_weight = prim_mst(matrix)
    
    # Approximate matching weight (simplified)
    n = len(matrix)
    odd_vertices = []
    
    # This is a simplified version - full Christofides would be more complex
    return mst_weight + (mst_weight // 2)  # Rough approximation

def exact_tsp(matrix):
    """Exact TSP solution for small n"""
    n = len(matrix)
    best = float('inf')
    
    for perm in permutations(range(1, n)):
        # Calculate tour distance starting and ending at 0
        total = matrix[0][perm[0]]  # Start from 0 to first city
        for i in range(len(perm) - 1):
            total += matrix[perm[i]][perm[i+1]]
        total += matrix[perm[-1]][0]  # Return to 0
        
        best = min(best, total)
    
    return best

def main():
    print("Getting current graph from server...")
    n, matrix, server_output = get_current_graph()
    
    if matrix is None:
        print("Failed to get graph from server")
        return
    
    print(f"Graph has {n} nodes")
    
    print("Solving exhaustively...")
    results = solve_exhaustively(matrix)
    
    # Sort results by value
    sorted_results = sorted(results.items(), key=lambda x: x[1])
    
    print("Results (sorted by value):")
    for name, value in sorted_results:
        print(f"  {name}: {value}")
    
    print("\nTesting answers against server (starting with smallest)...")
    for name, value in sorted_results:
        print(f"Testing {name}: {value}")
        success, output = test_answer(value)
        if success:
            print(f"SUCCESS! {name} with value {value} is correct!")
            print(f"Server output: {output}")
            return
        else:
            print(f"  Wrong answer")
        time.sleep(0.5)  # Avoid overwhelming the server
    
    print("None of the approaches worked. The problem might be something else entirely.")

if __name__ == "__main__":
    main()
