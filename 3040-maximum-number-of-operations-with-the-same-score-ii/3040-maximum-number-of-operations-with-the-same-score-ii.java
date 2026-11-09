class Solution {
    public int maxOperations(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        
        // Try three possible first operations
        int ans1 = 1 + dfs(nums, 2, n - 1, nums[0] + nums[1], dp);
        int ans2 = 1 + dfs(nums, 0, n - 3, nums[n - 1] + nums[n - 2], dp);
        int ans3 = 1 + dfs(nums, 1, n - 2, nums[0] + nums[n - 1], dp);
        
        return Math.max(ans1, Math.max(ans2, ans3));
    }
    
    private int dfs(int[] nums, int i, int j, int target, int[][] dp) {
        if (i >= j) return 0;  // fewer than 2 elements remain
        if (dp[i][j] != -1) return dp[i][j];
        
        int max = 0;
        
        // Try removing first two
        if (nums[i] + nums[i + 1] == target) {
            max = Math.max(max, 1 + dfs(nums, i + 2, j, target, dp));
        }
        // Try removing last two
        if (nums[j - 1] + nums[j] == target) {
            max = Math.max(max, 1 + dfs(nums, i, j - 2, target, dp));
        }
        // Try removing first and last
        if (nums[i] + nums[j] == target) {
            max = Math.max(max, 1 + dfs(nums, i + 1, j - 1, target, dp));
        }
        
        return dp[i][j] = max;
    }
}