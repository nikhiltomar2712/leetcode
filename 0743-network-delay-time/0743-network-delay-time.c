int networkDelayTime(int** times, int timesSize, int* timesColSize, int n, int k) {
    // Build adjacency matrix (n is small, <= 100)
    int g[101][101];
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            g[i][j] = (i == j) ? 0 : 1e9;
        }
    }
    
    for (int i = 0; i < timesSize; i++) {
        int u = times[i][0];
        int v = times[i][1];
        int w = times[i][2];
        g[u][v] = w;
    }
    
    // Dijkstra
    int dist[101];
    int visited[101] = {0};
    
    for (int i = 1; i <= n; i++) {
        dist[i] = g[k][i];
    }
    dist[k] = 0;
    visited[k] = 1;
    
    for (int i = 1; i < n; i++) {
        // Find unvisited node with smallest distance
        int u = -1;
        int minDist = 1e9;
        for (int j = 1; j <= n; j++) {
            if (!visited[j] && dist[j] < minDist) {
                minDist = dist[j];
                u = j;
            }
        }
        
        if (u == -1) break;  // remaining nodes unreachable
        
        visited[u] = 1;
        
        // Relax edges from u
        for (int v = 1; v <= n; v++) {
            if (!visited[v] && dist[u] + g[u][v] < dist[v]) {
                dist[v] = dist[u] + g[u][v];
            }
        }
    }
    
    // Find the maximum time
    int ans = 0;
    for (int i = 1; i <= n; i++) {
        if (dist[i] == 1e9) return -1;
        if (dist[i] > ans) ans = dist[i];
    }
    
    return ans;
}