class Solution {
    public int minSpaceWastedKResizing(int[] nums, int k) {
        int n = nums.length;
        int[][] waste = new int[n][n];
        for (int i = 0; i < n; i++) {
            int max = 0, sum = 0;
            for (int j = i; j < n; j++) {
                max = Math.max(max, nums[j]);
                sum += nums[j];
                waste[i][j] = max * (j - i + 1) - sum;
            }
        }
        int[][] dp = new int[k + 1][n];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE / 2);
        for (int i = 0; i < n; i++) {
            dp[0][i] = waste[0][i];
        }
        for (int op = 1; op <= k; op++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    dp[op][i] = Math.min(dp[op][i], dp[op - 1][j] + waste[j + 1][i]);
                }
            }
        }
        return dp[k][n - 1];
    }
}