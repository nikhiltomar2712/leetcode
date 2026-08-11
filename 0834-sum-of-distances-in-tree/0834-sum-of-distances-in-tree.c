/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* sumOfDistancesInTree(int n, int** edges, int edgesSize, int* edgesColSize, int* returnSize) {
    *returnSize = n;
    int* answer = (int*)calloc(n, sizeof(int));
    if (n == 1) return answer;
    
    // Build adjacency list
    int* degree = (int*)calloc(n, sizeof(int));
    for (int i = 0; i < edgesSize; i++) {
        degree[edges[i][0]]++;
        degree[edges[i][1]]++;
    }
    
    int** adj = (int**)malloc(n * sizeof(int*));
    int* adjSize = (int*)calloc(n, sizeof(int));
    for (int i = 0; i < n; i++) {
        adj[i] = (int*)malloc(degree[i] * sizeof(int));
    }
    for (int i = 0; i < edgesSize; i++) {
        int a = edges[i][0], b = edges[i][1];
        adj[a][adjSize[a]++] = b;
        adj[b][adjSize[b]++] = a;
    }
    
    int* count = (int*)malloc(n * sizeof(int));  // subtree sizes
    for (int i = 0; i < n; i++) count[i] = 1;
    
    // First DFS: compute count[] and answer[0] (sum of distances from root 0)
    void dfs1(int node, int parent) {
        for (int i = 0; i < adjSize[node]; i++) {
            int child = adj[node][i];
            if (child == parent) continue;
            dfs1(child, node);
            count[node] += count[child];
            answer[node] += answer[child] + count[child];
        }
    }
    dfs1(0, -1);
    
    // Second DFS: reroot
    void dfs2(int node, int parent) {
        for (int i = 0; i < adjSize[node]; i++) {
            int child = adj[node][i];
            if (child == parent) continue;
            // When moving root from node to child:
            // answer[child] = answer[node] - count[child] + (n - count[child])
            answer[child] = answer[node] - count[child] + (n - count[child]);
            dfs2(child, node);
        }
    }
    dfs2(0, -1);
    
    // Cleanup
    for (int i = 0; i < n; i++) free(adj[i]);
    free(adj);
    free(adjSize);
    free(degree);
    free(count);
    
    return answer;
}