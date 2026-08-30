class Solution {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][1001];
        int ans = 2;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int d = nums[i] - nums[j] + 500;

                // If no previous length, start with 2, otherwise extend
                dp[i][d] = Math.max(dp[i][d], dp[j][d] == 0 ? 2 : dp[j][d] + 1);

                ans = Math.max(ans, dp[i][d]);
            }
        }
        return ans;
    }
}