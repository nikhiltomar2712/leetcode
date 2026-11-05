class Solution {
    public int minimumCoins(int[] prices) {
        int n = prices.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + prices[i - 1];
            for (int j = i - 1; j >= 1 && j * 2 >= i; j--) {
                dp[i] = Math.min(dp[i], dp[j - 1] + prices[j - 1]);
            }
        }
        return dp[n];
    }
}