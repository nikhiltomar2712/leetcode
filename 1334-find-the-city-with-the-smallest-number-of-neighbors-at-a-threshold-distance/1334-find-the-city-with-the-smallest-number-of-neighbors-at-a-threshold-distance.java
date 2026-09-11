class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        final int INF = Integer.MAX_VALUE / 2; // avoid overflow when adding
        
        // Initialize distance matrix
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        
        // Add edges (bidirectional)
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            dist[u][v] = Math.min(dist[u][v], w);
            dist[v][u] = Math.min(dist[v][u], w);
        }
        
        // Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        // Find city with fewest reachable neighbors within threshold
        int bestCity = -1;
        int minCount = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] <= distanceThreshold) {
                    count++;
                }
            }
            // Tie-break: pick larger index (use <= to favor larger index)
            if (count <= minCount) {
                minCount = count;
                bestCity = i;
            }
        }
        
        return bestCity;
    }
}