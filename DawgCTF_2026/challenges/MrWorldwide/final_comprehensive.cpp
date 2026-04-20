#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>
#include <cstring>

using namespace std;

// Exact TSP using Held-Karp DP algorithm
long long held_karp_tsp(const vector<vector<int>>& dist) {
    int n = dist.size();
    if (n > 20) return -1; // Too large for exact solution
    
    const long long INF = 1e18;
    vector<vector<long long>> dp(1 << n, vector<long long>(n, INF));
    
    // Base case: start from city 0
    for (int i = 1; i < n; i++) {
        dp[1 << i][i] = dist[0][i];
    }
    
    // Fill DP table
    for (int mask = 1; mask < (1 << n); mask++) {
        for (int u = 1; u < n; u++) {
            if (!(mask & (1 << u))) continue;
            if (dp[mask][u] == INF) continue;
            
            for (int v = 1; v < n; v++) {
                if (mask & (1 << v)) continue;
                
                int new_mask = mask | (1 << v);
                long long new_dist = dp[mask][u] + dist[u][v];
                if (new_dist < dp[new_mask][v]) {
                    dp[new_mask][v] = new_dist;
                }
            }
        }
    }
    
    // Complete the tour
    int full_mask = (1 << n) - 1;
    long long min_tour = INF;
    
    for (int u = 1; u < n; u++) {
        if (dp[full_mask][u] != INF) {
            long long tour_dist = dp[full_mask][u] + dist[u][0];
            min_tour = min(min_tour, tour_dist);
        }
    }
    
    return min_tour == INF ? -1 : min_tour;
}

// TSP without returning to start
long long tsp_no_return(const vector<vector<int>>& dist) {
    int n = dist.size();
    if (n > 15) return -1; // Too large for exact solution
    
    const long long INF = 1e18;
    vector<vector<long long>> dp(1 << n, vector<long long>(n, INF));
    
    // Base case: start from city 0
    for (int i = 1; i < n; i++) {
        dp[1 << i][i] = dist[0][i];
    }
    
    // Fill DP table
    for (int mask = 1; mask < (1 << n); mask++) {
        for (int u = 1; u < n; u++) {
            if (!(mask & (1 << u))) continue;
            if (dp[mask][u] == INF) continue;
            
            for (int v = 1; v < n; v++) {
                if (mask & (1 << v)) continue;
                
                int new_mask = mask | (1 << v);
                long long new_dist = dp[mask][u] + dist[u][v];
                if (new_dist < dp[new_mask][v]) {
                    dp[new_mask][v] = new_dist;
                }
            }
        }
    }
    
    // Find minimum without returning to start
    int full_mask = (1 << n) - 1;
    long long min_tour = INF;
    
    for (int u = 1; u < n; u++) {
        if (dp[full_mask][u] != INF) {
            min_tour = min(min_tour, dp[full_mask][u]);
        }
    }
    
    return min_tour == INF ? -1 : min_tour;
}

// Prim's algorithm for MST
int prim_mst(const vector<vector<int>>& dist) {
    int n = dist.size();
    if (n == 0) return 0;
    
    vector<bool> visited(n, false);
    vector<int> min_edge(n, INT_MAX);
    min_edge[0] = 0;
    int total_weight = 0;
    
    for (int i = 0; i < n; i++) {
        int u = -1;
        for (int v = 0; v < n; v++) {
            if (!visited[v] && (u == -1 || min_edge[v] < min_edge[u])) {
                u = v;
            }
        }
        
        if (u == -1) break;
        
        visited[u] = true;
        total_weight += min_edge[u];
        
        for (int v = 0; v < n; v++) {
            if (!visited[v] && dist[u][v] < min_edge[v]) {
                min_edge[v] = dist[u][v];
            }
        }
    }
    
    return total_weight;
}

// Nearest neighbor TSP
int nearest_neighbor_tsp(const vector<vector<int>>& dist) {
    int n = dist.size();
    vector<bool> visited(n, false);
    int current = 0;
    visited[current] = true;
    int total_distance = 0;
    
    for (int i = 0; i < n - 1; i++) {
        int min_dist = INT_MAX;
        int nearest = -1;
        
        for (int city = 0; city < n; city++) {
            if (!visited[city] && dist[current][city] < min_dist) {
                min_dist = dist[current][city];
                nearest = city;
            }
        }
        
        if (nearest != -1) {
            visited[nearest] = true;
            total_distance += min_dist;
            current = nearest;
        }
    }
    
    // Return to start
    total_distance += dist[current][0];
    return total_distance;
}

int main() {
    int n;
    cin >> n;
    
    vector<vector<int>> dist(n, vector<int>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> dist[i][j];
        }
    }
    
    // Try exact solutions first
    long long exact_tsp = held_karp_tsp(dist);
    if (exact_tsp != -1) {
        cout << exact_tsp << endl;
        return 0;
    }
    
    long long exact_no_return = tsp_no_return(dist);
    if (exact_no_return != -1) {
        cout << exact_no_return << endl;
        return 0;
    }
    
    // Fall back to approximations
    int mst_weight = prim_mst(dist);
    int nn_tsp = nearest_neighbor_tsp(dist);
    
    // Try most likely answers
    vector<long long> candidates = {
        mst_weight,
        2 * mst_weight,
        nn_tsp,
        mst_weight + 1,
        mst_weight + 2
    };
    
    sort(candidates.begin(), candidates.end());
    cout << candidates[0] << endl;
    
    return 0;
}
