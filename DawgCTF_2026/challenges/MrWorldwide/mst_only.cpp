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
    
    // Prim's algorithm for MST - most likely CTF answer
    vector<bool> visited(n, false);
    vector<int> min_edge(n, INT_MAX);
    min_edge[0] = 0;
    int total_weight = 0;
    
    for (int i = 0; i < n; i++) {
        // Find unvisited vertex with minimum edge weight
        int u = -1;
        for (int v = 0; v < n; v++) {
            if (!visited[v] && (u == -1 || min_edge[v] < min_edge[u])) {
                u = v;
            }
        }
        
        if (u == -1) break;
        
        visited[u] = true;
        total_weight += min_edge[u];
        
        // Update min_edge for adjacent vertices
        for (int v = 0; v < n; v++) {
            if (!visited[v] && dist[u][v] < min_edge[v]) {
                min_edge[v] = dist[u][v];
            }
        }
    }
    
    cout << total_weight << endl;
    
    return 0;
}
