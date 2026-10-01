class Solution {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            graph[e[1]].add(new int[]{e[0], e[2]});
        }
        int[][] dp = new int[maxTime + 1][n];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        dp[0][0] = passingFees[0];
        for (int t = 1; t <= maxTime; t++) {
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                if (t >= w) {
                    if (dp[t - w][u] != Integer.MAX_VALUE) {
                        dp[t][v] = Math.min(dp[t][v], dp[t - w][u] + passingFees[v]);
                    }
                    if (dp[t - w][v] != Integer.MAX_VALUE) {
                        dp[t][u] = Math.min(dp[t][u], dp[t - w][v] + passingFees[u]);
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int t = 0; t <= maxTime; t++) {
            res = Math.min(res, dp[t][n - 1]);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}