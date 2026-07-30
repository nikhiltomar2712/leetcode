class Solution {
public:
    int coinChange(vector<int>& coins, int amount) {
        // dp[i] = minimum number of coins to make amount i
        vector<int> dp(amount + 1, amount + 1); // amount+1 acts as "infinity"
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; ++i) {
                dp[i] = min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
};