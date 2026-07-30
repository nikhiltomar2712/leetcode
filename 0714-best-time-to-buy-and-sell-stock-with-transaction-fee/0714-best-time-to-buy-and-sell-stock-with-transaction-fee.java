class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        
        // dp[i][0] = max profit on day i when not holding stock
        // dp[i][1] = max profit on day i when holding stock
        int[][] dp = new int[n][2];
        
        // Base cases for day 0
        dp[0][0] = 0; // Not holding, no profit
        dp[0][1] = -prices[0]; // Holding, bought on day 0
        
        for (int i = 1; i < n; i++) {
            // Not holding: either we didn't hold yesterday, or we sold today (pay fee)
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i] - fee);
            
            // Holding: either we held yesterday, or we bought today
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        
        return dp[n-1][0]; // Maximum profit when we end with no stock
    }
}