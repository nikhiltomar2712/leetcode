int countCompleteComponents(int n, int** edges, int edgesSize, int* edgesColSize) {
    // Build adjacency matrix
    bool** adj = (bool**)calloc(n, sizeof(bool*));
    for (int i = 0; i < n; i++) {
        adj[i] = (bool*)calloc(n, sizeof(bool));
    }
    
    // Count degrees for each vertex
    int* degree = (int*)calloc(n, sizeof(int));
    
    // Fill adjacency matrix and degree counts
    for (int i = 0; i < edgesSize; i++) {
        int u = edges[i][0];
        int v = edges[i][1];
        adj[u][v] = true;
        adj[v][u] = true;
        degree[u]++;
        degree[v]++;
    }
    
    // Find connected components using BFS/DFS
    bool* visited = (bool*)calloc(n, sizeof(bool));
    int completeCount = 0;
    
    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            // BFS to find component
            int* queue = (int*)malloc(n * sizeof(int));
            int front = 0, rear = 0;
            int componentSize = 0;
            int edgeCount = 0;
            
            queue[rear++] = i;
            visited[i] = true;
            
            while (front < rear) {
                int node = queue[front++];
                componentSize++;
                edgeCount += degree[node];
                
                for (int j = 0; j < n; j++) {
                    if (adj[node][j] && !visited[j]) {
                        visited[j] = true;
                        queue[rear++] = j;
                    }
                }
            }
            
            free(queue);
            
            // Each edge is counted twice in edgeCount (once from each endpoint)
            edgeCount /= 2;
            
            // Check if component is complete
            int expectedEdges = componentSize * (componentSize - 1) / 2;
            if (edgeCount == expectedEdges) {
                completeCount++;
            }
        }
    }
    
    // Free memory
    for (int i = 0; i < n; i++) {
        free(adj[i]);
    }
    free(adj);
    free(degree);
    free(visited);
    
    return completeCount;
}