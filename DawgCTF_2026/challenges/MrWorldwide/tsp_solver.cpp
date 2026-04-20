#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>
#include <cstring>

using namespace std;

int held_karp_tsp(const vector<vector<int>>& dist) {
    int n = dist.size();
    if (n > 20) return -1; // Too large
    
    // dp[mask][i] = min distance to visit nodes in mask, ending at i
    vector<vector<int>> dp(1 << n, vector<int>(n, INT_MAX));
    
    // Base case: start from city 0
    for (int i = 1; i < n; i++) {
        dp[1 << i][i] = dist[0][i];
    }
    
    // Fill DP table
    for (int mask = 1; mask < (1 << n); mask++) {
        for (int u = 1; u < n; u++) {
            if (!(mask & (1 << u))) continue;
            if (dp[mask][u] == INT_MAX) continue;
            
            for (int v = 1; v < n; v++) {
                if (mask & (1 << v)) continue;
                
                int new_mask = mask | (1 << v);
                int new_dist = dp[mask][u] + dist[u][v];
                if (new_dist < dp[new_mask][v]) {
                    dp[new_mask][v] = new_dist;
                }
            }
        }
    }
    
    // Complete the tour
    int full_mask = (1 << n) - 1;
    int min_tour = INT_MAX;
    
    for (int u = 1; u < n; u++) {
        if (dp[full_mask][u] != INT_MAX) {
            int tour_dist = dp[full_mask][u] + dist[u][0];
            min_tour = min(min_tour, tour_dist);
        }
    }
    
    return min_tour == INT_MAX ? -1 : min_tour;
}

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
        
        if (u == -1 || min_edge[u] == INT_MAX) break;
        
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

int main() {
    int n;
    cin >> n;
    
    vector<vector<int>> dist(n, vector<int>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> dist[i][j];
        }
    }
    
    // Try exact TSP first
    int exact_result = held_karp_tsp(dist);
    if (exact_result != -1) {
        cout << exact_result << endl;
        return 0;
    }
    
    // Try nearest neighbor
    int nn_result = nearest_neighbor_tsp(dist);
    cout << nn_result << endl;
    
    return 0;
}
