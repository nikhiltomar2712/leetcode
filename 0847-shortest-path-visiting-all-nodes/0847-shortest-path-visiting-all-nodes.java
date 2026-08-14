class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        if (n == 1) return 0;
        
        // State: (node, visitedMask)
        // visitedMask bit i = 1 means we've visited node i
        int targetMask = (1 << n) - 1;
        boolean[][] visited = new boolean[n][1 << n];
        Queue<int[]> queue = new LinkedList<>();
        
        // Initialize: start from each node with only that node visited
        for (int i = 0; i < n; i++) {
            int mask = 1 << i;
            queue.offer(new int[]{i, mask, 0}); // {node, visitedMask, distance}
            visited[i][mask] = true;
        }
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int mask = curr[1];
            int dist = curr[2];
            
            // If all nodes visited, return distance
            if (mask == targetMask) {
                return dist;
            }
            
            // Explore neighbors
            for (int neighbor : graph[node]) {
                int newMask = mask | (1 << neighbor);
                if (!visited[neighbor][newMask]) {
                    visited[neighbor][newMask] = true;
                    queue.offer(new int[]{neighbor, newMask, dist + 1});
                }
            }
        }
        
        return -1; // Should never reach here
    }
}