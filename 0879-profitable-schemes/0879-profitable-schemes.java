class Solution {
    private static final int MOD = 1_000_000_007;

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int[][] dp = new int[n + 1][minProfit + 1];
        
        // Base case: 0 profit with any number of members
        for (int j = 0; j <= n; j++) {
            dp[j][0] = 1;
        }

        for (int i = 0; i < group.length; i++) {
            int g = group[i];   // members needed
            int p = profit[i];  // profit gained

            // Iterate backwards to avoid using the same crime multiple times
            for (int j = n; j >= g; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    int prevProfit = Math.max(0, k - p);
                    dp[j][k] = (dp[j][k] + dp[j - g][prevProfit]) % MOD;
                }
            }
        }

        return dp[n][minProfit];
    }
}