class Solution {
    public int countPaths(int[][] grid) {
        int mod = 1000000007;
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = (res + dfs(grid, i, j, dp, mod)) % mod;
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j, int[][] dp, int mod) {
        if (dp[i][j] != -1) return dp[i][j];
        int m = grid.length, n = grid[0].length;
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        long count = 1;
        for (int[] d : dirs) {
            int ni = i + d[0], nj = j + d[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && grid[ni][nj] > grid[i][j]) {
                count = (count + dfs(grid, ni, nj, dp, mod)) % mod;
            }
        }
        dp[i][j] = (int) count;
        return dp[i][j];
    }
}