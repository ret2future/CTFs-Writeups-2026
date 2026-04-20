#!/usr/bin/env python3

import sys
import itertools

def read_graph():
    n = int(sys.stdin.readline().strip())
    dist = []
    for _ in range(n):
        row = list(map(int, sys.stdin.readline().strip().split()))
        dist.append(row)
    return n, dist

def prim_mst(dist):
    n = len(dist)
    visited = [False] * n
    min_edge = [float('inf')] * n
    min_edge[0] = 0
    total_weight = 0
    
    for _ in range(n):
        u = -1
        min_val = float('inf')
        for v in range(n):
            if not visited[v] and min_edge[v] < min_val:
                min_val = min_edge[v]
                u = v
        
        if u == -1:
            break
        
        visited[u] = True
        total_weight += min_edge[u]
        
        for v in range(n):
            if not visited[v] and dist[u][v] < min_edge[v]:
                min_edge[v] = dist[u][v]
    
    return total_weight

def nearest_neighbor_tsp(dist):
    n = len(dist)
    visited = [False] * n
    current = 0
    visited[0] = True
    tour_cost = 0
    
    for _ in range(n - 1):
        nearest = -1
        min_dist = float('inf')
        for j in range(n):
            if not visited[j] and dist[current][j] < min_dist:
                min_dist = dist[current][j]
                nearest = j
        
        if nearest != -1:
            tour_cost += min_dist
            visited[nearest] = True
            current = nearest
    
    tour_cost += dist[current][0]
    return tour_cost

def held_karp_tsp(dist):
    n = len(dist)
    if n > 15:
        return float('inf')
    
    dp = {}
    
    for i in range(1, n):
        dp[(1 << i, i)] = dist[0][i]
    
    for mask_size in range(2, n):
        for subset in itertools.combinations(range(1, n), mask_size):
            mask = 0
            for i in subset:
                mask |= (1 << i)
            
            for last in subset:
                prev_mask = mask ^ (1 << last)
                min_cost = float('inf')
                
                for prev_last in subset:
                    if prev_last != last:
                        if (prev_mask, prev_last) in dp:
                            cost = dp[(prev_mask, prev_last)] + dist[prev_last][last]
                            if cost < min_cost:
                                min_cost = cost
                
                dp[(mask, last)] = min_cost
    
    full_mask = (1 << n) - 2
    min_tour = float('inf')
    for last in range(1, n):
        if (full_mask, last) in dp:
            tour_cost = dp[(full_mask, last)] + dist[last][0]
            if tour_cost < min_tour:
                min_tour = tour_cost
    
    return min_tour

def main():
    n, dist = read_graph()
    
    candidates = []
    
    mst_weight = prim_mst(dist)
    candidates.append(("MST", mst_weight))
    candidates.append(("MST*2", mst_weight * 2))
    candidates.append(("MST*1.5", int(mst_weight * 1.5)))
    candidates.append(("Nearest Neighbor TSP", nearest_neighbor_tsp(dist)))
    
    if n <= 15:
        exact_tsp = held_karp_tsp(dist)
        if exact_tsp != float('inf'):
            candidates.append(("Exact TSP", exact_tsp))
    
    min_edge = float('inf')
    max_edge = 0
    total_edges = 0
    
    for i in range(n):
        for j in range(i + 1, n):
            if dist[i][j] < min_edge:
                min_edge = dist[i][j]
            if dist[i][j] > max_edge:
                max_edge = dist[i][j]
            total_edges += dist[i][j]
    
    avg_edge = total_edges // (n * (n - 1) // 2)
    
    candidates.append(("Min Edge", min_edge))
    candidates.append(("Max Edge", max_edge))
    candidates.append(("Avg Edge", avg_edge))
    
    sum_min_edges = 0
    for i in range(n):
        min_from_i = float('inf')
        for j in range(n):
            if i != j and dist[i][j] < min_from_i:
                min_from_i = dist[i][j]
        sum_min_edges += min_from_i
    
    candidates.append(("Sum Min Edges", sum_min_edges))
    candidates.append(("Sum Min Edges/2", sum_min_edges // 2))
    
    candidates.sort(key=lambda x: x[1])
    
    positive_candidates = [value for name, value in candidates if value > 0]
    if positive_candidates:
        print(positive_candidates[0])
    else:
        print(0)

if __name__ == "__main__":
    main()
