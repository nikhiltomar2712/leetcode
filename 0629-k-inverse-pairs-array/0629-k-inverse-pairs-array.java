class Solution {
    public int kInversePairs(int n, int k) {
        final int MOD = 1_000_000_007;
        int[][] dp = new int[n + 1][k + 1];
        
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1; // only sorted permutation has 0 inversions
        }
        
        for (int i = 2; i <= n; i++) {
            long prefix = 0; // to optimize sum
            for (int j = 0; j <= k; j++) {
                // Add dp[i-1][j] (placing i at the end, 0 new inversions)
                prefix = (prefix + dp[i - 1][j]) % MOD;
                
                // Remove the element that is out of the sliding window
                if (j >= i) {
                    prefix = (prefix - dp[i - 1][j - i] + MOD) % MOD;
                }
                
                dp[i][j] = (int) prefix;
            }
        }
        
        return dp[n][k];
    }
}