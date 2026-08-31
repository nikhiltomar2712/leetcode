class Solution {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        
        // dp[i][j] = minimum score to triangulate the polygon formed by vertices i..j
        // Length of the current interval (number of vertices = length + 1)
        for (int len = 2; len < n; len++) {          // at least 3 vertices → len >= 2
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;
                
                // Try every possible third vertex k between i and j
                for (int k = i + 1; k < j; k++) {
                    int score = dp[i][k] + dp[k][j] + values[i] * values[k] * values[j];
                    dp[i][j] = Math.min(dp[i][j], score);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}