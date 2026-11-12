class Solution {
    public int sumOfPower(int[] nums, int k) {
        final int MOD = 1_000_000_007;
        long[] dp = new long[k + 1];
        dp[0] = 1;

        for (int num : nums) {
            for (int s = k; s >= 0; s--) {
                dp[s] = (dp[s] * 2) % MOD;

                if (s >= num) {
                    dp[s] = (dp[s] + dp[s - num]) % MOD;
                }
            }
        }

        return (int) dp[k];
    }
}