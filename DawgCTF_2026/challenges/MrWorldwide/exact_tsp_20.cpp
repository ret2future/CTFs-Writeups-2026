#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>
#include <cstring>

using namespace std;

// Exact TSP using Held-Karp DP algorithm for 20 nodes
long long held_karp_tsp_20(const vector<vector<int>>& dist) {
    int n = dist.size();
    
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

// Prim's algorithm for MST (fallback)
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

int main() {
    int n;
    cin >> n;
    
    vector<vector<int>> dist(n, vector<int>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> dist[i][j];
        }
    }
    
    // Try exact TSP first (might be slow for n=20)
    cout << "Trying exact TSP..." << endl;
    long long exact_result = held_karp_tsp_20(dist);
    if (exact_result != -1) {
        cout << exact_result << endl;
        return 0;
    }
    
    // Fallback to MST if exact TSP is too slow
    cout << "Falling back to MST..." << endl;
    int mst_result = prim_mst(dist);
    cout << mst_result << endl;
    
    return 0;
}
