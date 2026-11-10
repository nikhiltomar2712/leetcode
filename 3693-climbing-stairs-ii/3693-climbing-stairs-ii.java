class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[i] represents the minimum cost to reach step i
        int[] dp = new int[n + 1];
        final int INF = Integer.MAX_VALUE / 2; // Prevent overflow
        
        // Initialize with a large value
        for (int i = 1; i <= n; i++) {
            dp[i] = INF;
        }
        dp[0] = 0; // Starting point costs 0
        
        // Iterate through each step
        for (int i = 1; i <= n; i++) {
            int stepCost = costs[i - 1]; // costs is 1-indexed, array is 0-indexed
            
            // Try jumping from the previous 1, 2, or 3 steps
            for (int j = Math.max(0, i - 3); j < i; j++) {
                int jumpCost = stepCost + (i - j) * (i - j);
                dp[i] = Math.min(dp[i], dp[j] + jumpCost);
            }
        }
        
        return dp[n];
    }
}