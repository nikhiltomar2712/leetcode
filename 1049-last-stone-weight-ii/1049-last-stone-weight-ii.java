class Solution {
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        
        int target = sum / 2;
        int[] dp = new int[target + 1];
        
        // 0/1 Knapsack: find the maximum subset sum <= target
        for (int stone : stones) {
            for (int j = target; j >= stone; j--) {
                dp[j] = Math.max(dp[j], dp[j - stone] + stone);
            }
        }
        
        // The minimum difference is sum - 2 * maxSubsetSum
        return sum - 2 * dp[target];
    }
}