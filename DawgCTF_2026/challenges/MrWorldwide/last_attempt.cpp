#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

// Try exact TSP for smaller n using DP
int exact_tsp_dp(const vector<vector<int>>& dist) {
    int n = dist.size();
    if (n > 15) return -1; // Too large for exact
    
    const int INF = INT_MAX;
    vector<vector<int>> dp(1 << n, vector<int>(n, INF));
    
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
                int new_dist = dp[mask][u] + dist[u][v];
                if (new_dist < dp[new_mask][v]) {
                    dp[new_mask][v] = new_dist;
                }
            }
        }
    }
    
    // Complete the tour
    int full_mask = (1 << n) - 1;
    int min_tour = INF;
    
    for (int u = 1; u < n; u++) {
        if (dp[full_mask][u] != INF) {
            int tour_dist = dp[full_mask][u] + dist[u][0];
            min_tour = min(min_tour, tour_dist);
        }
    }
    
    return min_tour == INF ? -1 : min_tour;
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
    int exact_result = exact_tsp_dp(dist);
    if (exact_result != -1) {
        cout << exact_result << endl;
        return 0;
    }
    
    // If too large, try MST-based approaches
    // MST weight
    vector<bool> visited(n, false);
    vector<int> min_edge(n, INT_MAX);
    min_edge[0] = 0;
    int mst_weight = 0;
    
    for (int i = 0; i < n; i++) {
        int u = -1;
        for (int v = 0; v < n; v++) {
            if (!visited[v] && (u == -1 || min_edge[v] < min_edge[u])) {
                u = v;
            }
        }
        
        if (u == -1) break;
        
        visited[u] = true;
        mst_weight += min_edge[u];
        
        for (int v = 0; v < n; v++) {
            if (!visited[v] && dist[u][v] < min_edge[v]) {
                min_edge[v] = dist[u][v];
            }
        }
    }
    
    // Try different TSP approximations
    cout << mst_weight * 2 << endl; // Common TSP upper bound
    
    return 0;
}
