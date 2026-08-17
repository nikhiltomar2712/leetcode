class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        // Build adjacency list for the graph
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge : dislikes) {
            int a = edge[0];
            int b = edge[1];
            graph[a].add(b);
            graph[b].add(a); // Undirected graph
        }
        
        // Color array: 0 = uncolored, 1 = group A, -1 = group B
        int[] color = new int[n + 1];
        
        // Check each component of the graph
        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                // Start BFS/DFS from this node
                if (!bfsCheck(i, graph, color)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean bfsCheck(int start, List<Integer>[] graph, int[] color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        color[start] = 1; // Assign first group
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            int currentColor = color[current];
            int nextColor = -currentColor; // Opposite group
            
            for (int neighbor : graph[current]) {
                if (color[neighbor] == 0) {
                    // Assign opposite color and add to queue
                    color[neighbor] = nextColor;
                    queue.offer(neighbor);
                } else if (color[neighbor] == currentColor) {
                    // Conflict: same color as current
                    return false;
                }
            }
        }
        
        return true;
    }
}