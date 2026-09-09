class Solution {
    public int maxSumDivThree(int[] nums) {
        // dp[r] = maximum sum so far with sum % 3 == r
        int[] dp = new int[3];

        for (int num : nums) {
            // Make a copy so we use the values from the previous state
            int[] prev = dp.clone();
            for (int sum : prev) {
                int newSum = sum + num;
                int r = newSum % 3;
                dp[r] = Math.max(dp[r], newSum);
            }
        }
        return dp[0];
    }
}