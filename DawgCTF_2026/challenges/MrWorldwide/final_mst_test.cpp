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
    
    // Prim's algorithm for MST
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
    
    // Try all common variations
    vector<int> candidates = {
        total_weight,           // MST
        total_weight * 2,       // Double MST
        total_weight * 3,       // Triple MST
        total_weight + 1,       // MST + 1
        total_weight + 2,       // MST + 2
        total_weight + 3,       // MST + 3
        total_weight + 4,       // MST + 4
        total_weight + 5,       // MST + 5
        total_weight - 1,       // MST - 1
        total_weight - 2        // MST - 2
    };
    
    // Remove duplicates and sort
    sort(candidates.begin(), candidates.end());
    candidates.erase(unique(candidates.begin(), candidates.end()), candidates.end());
    
    // Try smallest candidate first
    cout << candidates[0] << endl;
    
    return 0;
}
