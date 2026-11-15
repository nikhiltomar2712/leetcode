class Solution {
    public int minimumOperations(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] cost = new int[n][10];
        for (int j = 0; j < n; j++) {
            int[] freq = new int[10];
            for (int i = 0; i < m; i++) {
                freq[grid[i][j]]++;
            }
            for (int d = 0; d < 10; d++) {
                cost[j][d] = m - freq[d];
            }
        }
        int[][] dp = new int[n][10];
        for (int d = 0; d < 10; d++) {
            dp[0][d] = cost[0][d];
        }
        for (int j = 1; j < n; j++) {
            for (int d = 0; d < 10; d++) {
                int best = Integer.MAX_VALUE;
                for (int prev = 0; prev < 10; prev++) {
                    if (prev != d) {
                        best = Math.min(best, dp[j - 1][prev]);
                    }
                }
                dp[j][d] = best + cost[j][d];
            }
        }
        int res = Integer.MAX_VALUE;
        for (int d = 0; d < 10; d++) {
            res = Math.min(res, dp[n - 1][d]);
        }
        return res;
    }
}