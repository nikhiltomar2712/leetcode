class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        
        // dp[i][j] = maximum number of uncrossed lines using first i elements of nums1
        // and first j elements of nums2
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    // We can draw a line between these two numbers
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // Skip one of the numbers
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
}