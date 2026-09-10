class Solution {
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        // dp[i][j] = side length of the largest square 
        // whose bottom-right corner is at (i, j)
        int[][] dp = new int[m][n];
        int count = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        // First row or first column → can only form 1×1 square
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), 
                                            dp[i - 1][j - 1]) + 1;
                    }
                    count += dp[i][j];  // every size from 1 to dp[i][j] is a valid square
                }
            }
        }
        
        return count;
    }
}