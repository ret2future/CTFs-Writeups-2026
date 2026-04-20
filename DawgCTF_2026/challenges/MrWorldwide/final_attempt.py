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

def floyd_warshall(matrix):
    """Floyd-Warshall algorithm for all-pairs shortest paths"""
    n = len(matrix)
    dist = [row[:] for row in matrix]
    
    for k in range(n):
        for i in range(n):
            for j in range(n):
                if dist[i][k] + dist[k][j] < dist[i][j]:
                    dist[i][j] = dist[i][k] + dist[k][j]
    
    return dist

def solve_final_attempts(matrix):
    """Final attempts with different interpretations"""
    n = len(matrix)
    results = {}
    
    # Floyd-Warshall for all pairs shortest paths
    shortest_dist = floyd_warshall(matrix)
    
    # Sum of all pairwise shortest paths
    total_all_pairs = 0
    for i in range(n):
        for j in range(i+1, n):
            total_all_pairs += shortest_dist[i][j]
    results["sum_all_pairs_shortest"] = total_all_pairs
    
    # Sum of shortest paths from node 0 to all others
    sum_from_0 = sum(shortest_dist[0][j] for j in range(1, n))
    results["sum_shortest_from_0"] = sum_from_0
    
    # Maximum shortest path from node 0
    max_from_0 = max(shortest_dist[0][j] for j in range(1, n))
    results["max_shortest_from_0"] = max_from_0
    
    # Sum of shortest paths from each node to all others (divided by 2)
    total_from_all = 0
    for i in range(n):
        total_from_all += sum(shortest_dist[i][j] for j in range(n) if i != j)
    results["sum_shortest_all_nodes"] = total_from_all // 2
    
    # Minimum spanning tree using shortest paths
    results["mst_shortest_paths"] = prim_mst(shortest_dist)
    
    # Try different starting points for shortest path sum
    for start in range(min(n, 5)):
        sum_from_start = sum(shortest_dist[start][j] for j in range(n) if start != j)
        results[f"sum_shortest_from_{start}"] = sum_from_start
    
    # Try variations of TSP with shortest paths
    results["tsp_shortest_paths_return"] = tsp_nearest_neighbor(shortest_dist, return_to_start=True)
    results["tsp_shortest_paths_no_return"] = tsp_nearest_neighbor(shortest_dist, return_to_start=False)
    
    # Try Steiner tree approximation
    results["steiner_approx"] = steiner_tree_approx(matrix)
    
    # Try minimum distance to connect all nodes (different approaches)
    results["min_connect_2approx"] = minimum_connecting_tour_2approx(matrix)
    
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

def steiner_tree_approx(matrix):
    """Approximation for Steiner tree problem"""
    # For Steiner tree with all terminals, it's just MST
    return prim_mst(matrix)

def minimum_connecting_tour_2approx(matrix):
    """2-approximation for minimum connecting tour"""
    # This is like TSP but we don't need to return to start
    # Use MST + matching approximation
    mst_weight = prim_mst(matrix)
    
    # Add minimum matching cost (simplified)
    n = len(matrix)
    min_edge = min(matrix[i][j] for i in range(n) for j in range(i+1, n))
    
    return mst_weight + min_edge

def main():
    print("Getting current graph from server...")
    n, matrix, server_output = get_current_graph()
    
    if matrix is None:
        print("Failed to get graph from server")
        return
    
    print(f"Graph has {n} nodes")
    
    print("Trying final approaches...")
    results = solve_final_attempts(matrix)
    
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
    
    print("Still no success. Let me try some manual analysis...")
    
    # Let's look at the matrix structure
    print(f"Matrix diagonal: {[matrix[i][i] for i in range(n)]}")
    print(f"Minimum edge: {min(matrix[i][j] for i in range(n) for j in range(n) if i != j)}")
    print(f"Maximum edge: {max(matrix[i][j] for i in range(n) for j in range(n) if i != j)}")
    
    # Maybe it's asking for something like the sum of a specific path
    # Let me try the sum of the first row (excluding diagonal)
    sum_first_row = sum(matrix[0][j] for j in range(1, n))
    print(f"Sum of first row (excluding diagonal): {sum_first_row}")
    success, output = test_answer(sum_first_row)
    if success:
        print(f"SUCCESS! Sum of first row: {sum_first_row}")
        return
    
    # Try sum of minimum edges from each node
    sum_min_edges = sum(min(matrix[i][j] for j in range(n) if i != j) for i in range(n))
    print(f"Sum of minimum edges from each node: {sum_min_edges}")
    success, output = test_answer(sum_min_edges)
    if success:
        print(f"SUCCESS! Sum of minimum edges: {sum_min_edges}")
        return

if __name__ == "__main__":
    main()
