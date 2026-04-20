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
    
    // Try different interpretations
    
    // 1. Sum of minimum spanning tree
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
    
    // 2. Sum of shortest paths from node 0
    vector<int> shortest_dist(n, INT_MAX);
    shortest_dist[0] = 0;
    vector<bool> spt_set(n, false);
    
    for (int count = 0; count < n; count++) {
        int u = -1;
        for (int v = 0; v < n; v++) {
            if (!spt_set[v] && (u == -1 || shortest_dist[v] < shortest_dist[u])) {
                u = v;
            }
        }
        
        if (u == -1) break;
        
        spt_set[u] = true;
        
        for (int v = 0; v < n; v++) {
            if (!spt_set[v] && dist[u][v] != INT_MAX && 
                shortest_dist[u] != INT_MAX && 
                shortest_dist[u] + dist[u][v] < shortest_dist[v]) {
                shortest_dist[v] = shortest_dist[u] + dist[u][v];
            }
        }
    }
    
    int sum_shortest_paths = 0;
    for (int i = 1; i < n; i++) {
        sum_shortest_paths += shortest_dist[i];
    }
    
    // 3. Sum of minimum edge from each node
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
    
    // 4. Simple nearest neighbor tour
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
    
    // Try different outputs
    cout << mst_weight << endl;
    
    return 0;
}
