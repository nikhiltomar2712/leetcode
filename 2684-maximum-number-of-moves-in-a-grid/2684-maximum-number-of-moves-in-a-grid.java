class Solution {
    public int maxMoves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int j = n - 2; j >= 0; j--) {
            for (int i = 0; i < m; i++) {
                int best = 0;
                if (i > 0 && grid[i][j] < grid[i - 1][j + 1]) {
                    best = Math.max(best, dp[i - 1][j + 1] + 1);
                }
                if (grid[i][j] < grid[i][j + 1]) {
                    best = Math.max(best, dp[i][j + 1] + 1);
                }
                if (i < m - 1 && grid[i][j] < grid[i + 1][j + 1]) {
                    best = Math.max(best, dp[i + 1][j + 1] + 1);
                }
                dp[i][j] = best;
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            res = Math.max(res, dp[i][0]);
        }
        return res;
    }
}