class Solution {
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        if (n == 1) return grid[0][0];
        
        // dp[j] = minimum falling path sum ending at column j of the previous row
        int[] dp = grid[0].clone();
        
        for (int row = 1; row < n; row++) {
            // Find the smallest and second-smallest values (and their indices) in dp
            int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
            int idx1 = -1;
            
            for (int j = 0; j < n; j++) {
                if (dp[j] < min1) {
                    min2 = min1;
                    min1 = dp[j];
                    idx1 = j;
                } else if (dp[j] < min2) {
                    min2 = dp[j];
                }
            }
            
            int[] next = new int[n];
            for (int j = 0; j < n; j++) {
                // If we pick the same column as the overall minimum, use the second minimum
                next[j] = grid[row][j] + (j == idx1 ? min2 : min1);
            }
            dp = next;
        }
        
        int ans = Integer.MAX_VALUE;
        for (int val : dp) {
            ans = Math.min(ans, val);
        }
        return ans;
    }
}

