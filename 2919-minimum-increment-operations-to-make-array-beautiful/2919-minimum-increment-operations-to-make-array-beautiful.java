class Solution {
    public long minIncrementOperations(int[] nums, int k) {
        int n = nums.length;
        long[] dp = new long[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i + 1] = Math.max(0, k - nums[i]);
        }
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1], Math.min(dp[i - 2], dp[i - 3])) + Math.max(0, k - nums[i - 1]);
        }
        return Math.min(dp[n], Math.min(dp[n - 1], dp[n - 2]));
    }
}