class Solution {
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        for (int j = 0; j < n; j++) {
            dp[j] = grid[0][j];
        }
        for (int i = 0; i < m - 1; i++) {
            int[] next = new int[n];
            Arrays.fill(next, Integer.MAX_VALUE);
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                for (int k = 0; k < n; k++) {
                    next[k] = Math.min(next[k], dp[j] + moveCost[val][k] + grid[i + 1][k]);
                }
            }
            dp = next;
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            res = Math.min(res, dp[j]);
        }
        return res;
    }
}