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

def exact_tsp_dynamic_programming(matrix):
    """Exact TSP using dynamic programming (Held-Karp)"""
    n = len(matrix)
    if n > 20:  # Too large for exact DP
        return None
    
    # dp[mask][i] = minimum distance to visit nodes in mask ending at i
    INF = float('inf')
    dp = {}
    
    # Initialize: start from city 0
    for i in range(1, n):
        dp[(1 << i, i)] = matrix[0][i]
    
    # Fill DP table
    for mask in range(1, 1 << n):
        if not (mask & 1):  # Must include city 0 in full mask
            continue
            
        for u in range(1, n):
            if not (mask & (1 << u)):
                continue
                
            if (mask, u) not in dp:
                continue
                
            for v in range(1, n):
                if mask & (1 << v):
                    continue
                    
                new_mask = mask | (1 << v)
                new_dist = dp[(mask, u)] + matrix[u][v]
                
                if (new_mask, v) not in dp or new_dist < dp[(new_mask, v)]:
                    dp[(new_mask, v)] = new_dist
    
    # Complete the tour by returning to city 0
    full_mask = (1 << n) - 1
    min_tour = INF
    
    for u in range(1, n):
        if (full_mask, u) in dp:
            tour_dist = dp[(full_mask, u)] + matrix[u][0]
            min_tour = min(min_tour, tour_dist)
    
    return min_tour

def solve_with_various_interpretations(matrix):
    """Try various interpretations of the problem"""
    n = len(matrix)
    results = {}
    
    # 1. Exact TSP (if feasible)
    exact_result = exact_tsp_dynamic_programming(matrix)
    if exact_result is not None:
        results["exact_tsp"] = exact_result
    
    # 2. TSP without return (exact if feasible)
    exact_no_return = exact_tsp_no_return(matrix)
    if exact_no_return is not None:
        results["exact_tsp_no_return"] = exact_no_return
    
    # 3. Try different starting cities for exact TSP
    for start in range(min(n, 3)):
        exact_from_start = exact_tsp_from_start(matrix, start)
        if exact_from_start is not None:
            results[f"exact_tsp_from_{start}"] = exact_from_start
    
    # 4. Try some heuristic approaches with better optimization
    results["tsp_2opt"] = tsp_with_2opt(matrix)
    results["tsp_3opt"] = tsp_with_3opt(matrix)
    
    # 5. Try branch and bound for smaller instances
    if n <= 15:
        results["branch_and_bound"] = tsp_branch_and_bound(matrix)
    
    # 6. Try different graph metrics
    results["minimum_spanning_tree"] = prim_mst(matrix)
    results["minimum_spanning_tree_x2"] = 2 * prim_mst(matrix)
    
    # 7. Try shortest path approaches
    results["shortest_path_tree"] = shortest_path_tree_weight(matrix)
    
    return results

def exact_tsp_no_return(matrix):
    """Exact TSP without returning to start"""
    n = len(matrix)
    if n > 15:  # Too large for exact
        return None
    
    min_dist = float('inf')
    
    for perm in permutations(range(1, n)):
        total = matrix[0][perm[0]]  # Start from 0
        for i in range(len(perm) - 1):
            total += matrix[perm[i]][perm[i+1]]
        min_dist = min(min_dist, total)
    
    return min_dist

def exact_tsp_from_start(matrix, start):
    """Exact TSP starting from specific city"""
    n = len(matrix)
    if n > 15:  # Too large for exact
        return None
    
    other_cities = [i for i in range(n) if i != start]
    min_dist = float('inf')
    
    for perm in permutations(other_cities):
        total = matrix[start][perm[0]]  # Start from specified city
        for i in range(len(perm) - 1):
            total += matrix[perm[i]][perm[i+1]]
        total += matrix[perm[-1]][start]  # Return to start
        min_dist = min(min_dist, total)
    
    return min_dist

def tsp_with_2opt(matrix):
    """TSP with 2-opt improvement"""
    n = len(matrix)
    
    # Start with nearest neighbor
    path = nearest_neighbor_path(matrix)
    distance = calculate_path_distance(matrix, path)
    
    # Apply 2-opt
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

def tsp_with_3opt(matrix):
    """TSP with 3-opt improvement (simplified)"""
    # For now, just return 2-opt result
    return tsp_with_2opt(matrix)

def tsp_branch_and_bound(matrix):
    """Branch and bound TSP for small instances"""
    n = len(matrix)
    if n > 12:
        return None
    
    # Simple branch and bound
    best = float('inf')
    current_path = [0]
    visited = [False] * n
    visited[0] = True
    
    def branch_and_bound_recursive(current_path, visited, current_distance):
        nonlocal best
        
        if len(current_path) == n:
            # Complete the tour
            total_distance = current_distance + matrix[current_path[-1]][0]
            best = min(best, total_distance)
            return
        
        # Bound: if current distance + minimum possible remaining > best, prune
        if current_distance >= best:
            return
        
        current_city = current_path[-1]
        
        # Try next cities in order of increasing distance
        next_cities = [(matrix[current_city][j], j) for j in range(n) if not visited[j]]
        next_cities.sort()
        
        for _, next_city in next_cities:
            visited[next_city] = True
            current_path.append(next_city)
            
            branch_and_bound_recursive(current_path, visited, 
                                      current_distance + matrix[current_city][next_city])
            
            current_path.pop()
            visited[next_city] = False
    
    branch_and_bound_recursive(current_path, visited, 0)
    return best

def nearest_neighbor_path(matrix):
    """Generate nearest neighbor path"""
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
    
    path.append(0)  # Return to start
    return path

def calculate_path_distance(matrix, path):
    """Calculate total distance for a path"""
    total = 0
    for i in range(len(path) - 1):
        total += matrix[path[i]][path[i + 1]]
    return total

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

def shortest_path_tree_weight(matrix):
    """Weight of shortest path tree from node 0"""
    n = len(matrix)
    
    # Dijkstra's algorithm
    distances = [float('inf')] * n
    distances[0] = 0
    visited = [False] * n
    
    for _ in range(n):
        min_dist = float('inf')
        current = -1
        for i in range(n):
            if not visited[i] and distances[i] < min_dist:
                min_dist = distances[i]
                current = i
        
        if current == -1:
            break
        
        visited[current] = True
        
        for neighbor in range(n):
            if not visited[neighbor]:
                new_dist = distances[current] + matrix[current][neighbor]
                if new_dist < distances[neighbor]:
                    distances[neighbor] = new_dist
    
    return sum(distances)

def main():
    print("Getting current graph from server...")
    n, matrix, server_output = get_current_graph()
    
    if matrix is None:
        print("Failed to get graph from server")
        return
    
    print(f"Graph has {n} nodes")
    
    print("Solving with various interpretations...")
    results = solve_with_various_interpretations(matrix)
    
    # Sort results by value
    sorted_results = sorted(results.items(), key=lambda x: x[1])
    
    print("Results (sorted by value):")
    for name, value in sorted_results:
        print(f"  {name}: {value}")
    
    print("\nTesting answers against server...")
    for name, value in sorted_results:
        print(f"Testing {name}: {value}")
        success, output = test_answer(value)
        if success:
            print(f"SUCCESS! {name} with value {value} is correct!")
            print(f"Server output: {output}")
            return
        else:
            print(f"  Wrong answer")
        time.sleep(0.5)
    
    print("None of the approaches worked. The problem might require a different interpretation.")

if __name__ == "__main__":
    main()
