class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        
        // Feasibility: need at least one job per day
        if (n < d) return -1;
        
        final int INF = Integer.MAX_VALUE / 2;
        
        // dp[i][k] = min total difficulty to schedule first i jobs in k days
        int[][] dp = new int[n + 1][d + 1];
        for (int[] row : dp) Arrays.fill(row, INF);
        dp[0][0] = 0;
        
        for (int k = 1; k <= d; k++) {
            // Schedule first i jobs in k days (i >= k)
            for (int i = k; i <= n; i++) {
                int maxDiff = 0;
                // Last day covers jobs j..i-1; iterate j from i-1 down to k-1
                for (int j = i - 1; j >= k - 1; j--) {
                    maxDiff = Math.max(maxDiff, jobDifficulty[j]);
                    if (dp[j][k - 1] != INF) {
                        dp[i][k] = Math.min(dp[i][k], dp[j][k - 1] + maxDiff);
                    }
                }
            }
        }
        
        return dp[n][d] == INF ? -1 : dp[n][d];
    }
}