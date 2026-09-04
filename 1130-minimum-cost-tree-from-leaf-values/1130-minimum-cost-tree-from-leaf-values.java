class Solution {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        
        // Step 1: Precompute maximum values for every subarray
        int[][] maxVal = new int[n][n];
        for (int i = 0; i < n; i++) {
            maxVal[i][i] = arr[i];
            for (int j = i + 1; j < n; j++) {
                maxVal[i][j] = Math.max(maxVal[i][j - 1], arr[j]);
            }
        }
        
        // Step 2: DP table - dp[i][j] = minimum cost for subarray arr[i..j]
        int[][] dp = new int[n][n];
        
        // Length from 2 to n (since single element has cost 0)
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                
                // Try all possible partitions
                for (int k = i; k < j; k++) {
                    int cost = maxVal[i][k] * maxVal[k + 1][j] 
                             + dp[i][k] + dp[k + 1][j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}