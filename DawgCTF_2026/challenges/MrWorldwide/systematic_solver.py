#!/usr/bin/env python3
import sys
import subprocess
import time

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

def solve_all_interpretations(matrix):
    """Try all possible interpretations of the problem"""
    n = len(matrix)
    results = {}
    
    # 1. Traditional TSP (return to start)
    results["tsp_return"] = solve_tsp_with_return(matrix)
    
    # 2. TSP without return
    results["tsp_no_return"] = solve_tsp_no_return(matrix)
    
    # 3. Minimum Spanning Tree
    results["mst"] = prim_mst(matrix)
    
    # 4. Sum of all edges in MST (2*MST for tour)
    results["double_mst"] = 2 * prim_mst(matrix)
    
    # 5. Shortest path from node 0 to visit all (greedy)
    results["greedy_tour"] = greedy_tour(matrix)
    
    # 6. Sum of shortest paths from node 0
    results["shortest_paths_sum"] = sum_shortest_paths(matrix)
    
    # 7. Minimum edge sum to connect all nodes (different approach)
    results["min_connect"] = min_edge_sum_to_connect(matrix)
    
    # 8. Chinese Postman Problem approximation
    results["chinese_postman"] = chinese_postman_approx(matrix)
    
    return results

def solve_tsp_with_return(matrix):
    """TSP with return to start - nearest neighbor"""
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
        
        total_distance += matrix[current][start]
        best_distance = min(best_distance, total_distance)
    
    return best_distance

def solve_tsp_no_return(matrix):
    """TSP without return - nearest neighbor"""
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
        
        best_distance = min(best_distance, total_distance)
    
    return best_distance

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

def greedy_tour(matrix):
    """Greedy tour without return"""
    n = len(matrix)
    if n == 0:
        return 0
    
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

def sum_shortest_paths(matrix):
    """Sum of shortest paths from node 0"""
    n = len(matrix)
    if n == 0:
        return 0
    
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

def min_edge_sum_to_connect(matrix):
    """Minimum sum of edges to connect all nodes (like MST but different approach)"""
    n = len(matrix)
    if n <= 1:
        return 0
    
    # Sort all edges by weight
    edges = []
    for i in range(n):
        for j in range(i+1, n):
            edges.append((matrix[i][j], i, j))
    
    edges.sort()
    
    # Kruskal's algorithm
    parent = list(range(n))
    
    def find(x):
        while parent[x] != x:
            parent[x] = parent[parent[x]]
            x = parent[x]
        return x
    
    def union(x, y):
        px, py = find(x), find(y)
        if px != py:
            parent[px] = py
            return True
        return False
    
    total = 0
    edges_used = 0
    
    for weight, i, j in edges:
        if union(i, j):
            total += weight
            edges_used += 1
            if edges_used == n - 1:
                break
    
    return total

def chinese_postman_approx(matrix):
    """Chinese Postman Problem approximation"""
    # For undirected graph, approximate as sum of all edges + MST
    n = len(matrix)
    total_edge_weight = 0
    
    for i in range(n):
        for j in range(i+1, n):
            if matrix[i][j] > 0:
                total_edge_weight += matrix[i][j]
    
    mst_weight = prim_mst(matrix)
    return total_edge_weight + mst_weight

def main():
    print("Getting current graph from server...")
    n, matrix, server_output = get_current_graph()
    
    if matrix is None:
        print("Failed to get graph from server")
        return
    
    print(f"Graph has {n} nodes")
    
    print("Solving with all interpretations...")
    results = solve_all_interpretations(matrix)
    
    print("Results:")
    for name, value in results.items():
        print(f"  {name}: {value}")
    
    print("\nTesting answers against server...")
    for name, value in results.items():
        print(f"Testing {name}: {value}")
        success, output = test_answer(value)
        if success:
            print(f"SUCCESS! {name} with value {value} is correct!")
            print(f"Server output: {output}")
            return
        else:
            print(f"  Wrong answer")
        time.sleep(1)  # Avoid overwhelming the server
    
    print("None of the standard interpretations worked. Trying variations...")
    
    # Try some variations
    variations = {}
    variations["mst_plus_min_edge"] = results["mst"] + min(matrix[0][i] for i in range(1, n))
    variations["tsp_no_return_plus_min_edge"] = results["tsp_no_return"] + min(matrix[0][i] for i in range(1, n))
    variations["half_chinese_postman"] = results["chinese_postman"] // 2
    
    for name, value in variations.items():
        print(f"Testing variation {name}: {value}")
        success, output = test_answer(value)
        if success:
            print(f"SUCCESS! {name} with value {value} is correct!")
            return
        time.sleep(0.5)

if __name__ == "__main__":
    main()
