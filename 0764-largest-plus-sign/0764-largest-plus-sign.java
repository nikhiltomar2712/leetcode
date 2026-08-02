class Solution {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] dp = new int[n][n];
        
        // Initialize every cell to n (maximum possible order)
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], n);
        }
        
        // Place the mines
        for (int[] mine : mines) {
            dp[mine[0]][mine[1]] = 0;
        }
        
        // Four directional sweeps
        for (int i = 0; i < n; i++) {
            // Left → Right
            int count = 0;
            for (int j = 0; j < n; j++) {
                count = (dp[i][j] == 0) ? 0 : count + 1;
                dp[i][j] = Math.min(dp[i][j], count);
            }
            
            // Right → Left
            count = 0;
            for (int j = n - 1; j >= 0; j--) {
                count = (dp[i][j] == 0) ? 0 : count + 1;
                dp[i][j] = Math.min(dp[i][j], count);
            }
            
            // Top → Bottom
            count = 0;
            for (int j = 0; j < n; j++) {
                count = (dp[j][i] == 0) ? 0 : count + 1;
                dp[j][i] = Math.min(dp[j][i], count);
            }
            
            // Bottom → Top
            count = 0;
            for (int j = n - 1; j >= 0; j--) {
                count = (dp[j][i] == 0) ? 0 : count + 1;
                dp[j][i] = Math.min(dp[j][i], count);
            }
        }
        
        // Find the maximum value in the DP table
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dp[i][j]);
            }
        }
        
        return ans;
    }
}