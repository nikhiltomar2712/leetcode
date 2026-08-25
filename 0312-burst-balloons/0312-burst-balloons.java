class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        
        // Create new array with 1s at both ends
        int[] balloons = new int[n + 2];
        balloons[0] = 1;
        balloons[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            balloons[i + 1] = nums[i];
        }
        
        // dp[i][j] = maximum coins from bursting balloons between i and j (exclusive)
        int[][] dp = new int[n + 2][n + 2];
        
        // length represents the number of balloons in the subarray
        for (int length = 1; length <= n; length++) {
            for (int left = 1; left <= n - length + 1; left++) {
                int right = left + length - 1;
                
                // Try each balloon as the last to be burst in this subarray
                for (int last = left; last <= right; last++) {
                    int coins = balloons[left - 1] * balloons[last] * balloons[right + 1]
                              + dp[left][last - 1] + dp[last + 1][right];
                    dp[left][right] = Math.max(dp[left][right], coins);
                }
            }
        }
        
        return dp[1][n];
    }
}