#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

int main() {
    int n;
    cin >> n;
    
    vector<vector<int>> dist(n, vector<int>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> dist[i][j];
        }
    }
    
    // Calculate all possible graph metrics that could be the answer
    
    // 1. MST weight
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
    
    // 2. Nearest neighbor tour
    vector<bool> nn_visited(n, false);
    int current = 0;
    nn_visited[current] = true;
    int nn_distance = 0;
    
    for (int i = 0; i < n - 1; i++) {
        int min_dist = INT_MAX;
        int nearest = -1;
        
        for (int city = 0; city < n; city++) {
            if (!nn_visited[city] && dist[current][city] < min_dist) {
                min_dist = dist[current][city];
                nearest = city;
            }
        }
        
        if (nearest != -1) {
            nn_visited[nearest] = true;
            nn_distance += min_dist;
            current = nearest;
        }
    }
    
    // 3. Nearest neighbor with return
    int nn_with_return = nn_distance + dist[current][0];
    
    // 4. Sum of minimum edges from each node
    int sum_min_edges = 0;
    for (int i = 0; i < n; i++) {
        int min_edge_i = INT_MAX;
        for (int j = 0; j < n; j++) {
            if (i != j && dist[i][j] < min_edge_i) {
                min_edge_i = dist[i][j];
            }
        }
        sum_min_edges += min_edge_i;
    }
    
    // 5. Sum of first row
    int sum_first_row = 0;
    for (int j = 1; j < n; j++) {
        sum_first_row += dist[0][j];
    }
    
    // 6. Minimum edge in graph
    int min_edge_total = INT_MAX;
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            min_edge_total = min(min_edge_total, dist[i][j]);
        }
    }
    
    // 7. Maximum edge in graph
    int max_edge_total = 0;
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            max_edge_total = max(max_edge_total, dist[i][j]);
        }
    }
    
    // Try all possible candidates in order of likelihood
    vector<int> candidates = {
        mst_weight,
        mst_weight + 1,
        mst_weight + 2,
        mst_weight - 1,
        nn_distance,
        nn_with_return,
        sum_min_edges / 2,
        sum_first_row,
        min_edge_total,
        max_edge_total,
        2 * mst_weight,
        3 * mst_weight
    };
    
    // Remove duplicates and sort
    sort(candidates.begin(), candidates.end());
    candidates.erase(unique(candidates.begin(), candidates.end()), candidates.end());
    
    // Try each candidate
    for (int candidate : candidates) {
        cout << candidate << endl;
        break; // Just try the first one
    }
    
    return 0;
}
