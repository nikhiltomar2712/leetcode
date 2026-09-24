class Solution {
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        // Precompute all-pairs shortest paths with Floyd-Warshall
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], n);   // n is larger than any possible distance
            dist[i][i] = 0;
        }
        for (int[] e : edges) {
            int u = e[0] - 1, v = e[1] - 1;
            dist[u][v] = dist[v][u] = 1;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int[] ans = new int[n - 1];
        int maxMask = 1 << n;

        // Enumerate every non-empty subset
        for (int mask = 1; mask < maxMask; mask++) {
            int maxDist = getMaxDist(mask, dist, n);
            if (maxDist > 0) {
                ans[maxDist - 1]++;
            }
        }
        return ans;
    }

    // Returns the diameter of the subset if it forms a tree, otherwise 0
    private int getMaxDist(int mask, int[][] dist, int n) {
        int maxDist = 0;
        int edgeCount = 0;
        int cityCount = 0;

        for (int u = 0; u < n; u++) {
            if ((mask & (1 << u)) == 0) continue;
            cityCount++;
            for (int v = u + 1; v < n; v++) {
                if ((mask & (1 << v)) == 0) continue;
                if (dist[u][v] == 1) edgeCount++;          // direct edge inside the subset
                maxDist = Math.max(maxDist, dist[u][v]);
            }
        }

        // A tree on k nodes has exactly k-1 edges
        return (edgeCount == cityCount - 1) ? maxDist : 0;
    }
}