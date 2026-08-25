class Solution(object):
    def maxProfit(self, k, prices):
        n = len(prices)
        if n == 0 or k == 0:
            return 0
        
        # If k is large enough, it's equivalent to unlimited transactions
        if k >= n // 2:
            profit = 0
            for i in range(1, n):
                if prices[i] > prices[i - 1]:
                    profit += prices[i] - prices[i - 1]
            return profit
        
        # DP: dp[t][i] = max profit with at most t transactions using prices[0..i]
        dp = [[0] * n for _ in range(k + 1)]
        
        for t in range(1, k + 1):
            max_diff = -prices[0]
            for i in range(1, n):
                dp[t][i] = max(dp[t][i - 1], prices[i] + max_diff)
                max_diff = max(max_diff, dp[t - 1][i - 1] - prices[i])
        
        return dp[k][n - 1]