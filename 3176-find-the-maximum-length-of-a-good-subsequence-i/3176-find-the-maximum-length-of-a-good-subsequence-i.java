class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n][k + 1];
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = 1;
                for (int l = 0; l < i; l++) {
                    int cost = nums[l] == nums[i] ? 0 : 1;
                    if (j >= cost) {
                        dp[i][j] = Math.max(dp[i][j], dp[l][j - cost] + 1);
                    }
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}