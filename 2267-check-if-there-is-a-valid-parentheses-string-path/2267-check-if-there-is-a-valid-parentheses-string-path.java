class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        if ((m + n - 1) % 2 != 0) return false;
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;
        boolean[][][] dp = new boolean[m][n][m + n];
        dp[0][0][1] = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                int diff = grid[i][j] == '(' ? 1 : -1;
                for (int k = 0; k < m + n; k++) {
                    int prev = k - diff;
                    if (prev < 0 || prev >= m + n) continue;
                    if (i > 0 && dp[i - 1][j][prev]) dp[i][j][k] = true;
                    if (j > 0 && dp[i][j - 1][prev]) dp[i][j][k] = true;
                }
            }
        }
        return dp[m - 1][n - 1][0];
    }
}