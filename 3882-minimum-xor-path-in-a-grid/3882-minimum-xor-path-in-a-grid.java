class Solution {
    private int ans = Integer.MAX_VALUE;
    private boolean[][][] memo;

    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // XOR values are bounded by 1024 (since grid values <= 1023)
        memo = new boolean[m][n][1024];
        dfs(grid, 0, 0, 0);
        return ans;
    }

    private void dfs(int[][] grid, int i, int j, int currentXor) {
        // Early exit if we already found the absolute minimum (0)
        if (ans == 0) return;

        int m = grid.length;
        int n = grid[0].length;
        int newXor = currentXor ^ grid[i][j];

        // Memoization check: if this state was visited, skip
        if (memo[i][j][newXor]) return;
        memo[i][j][newXor] = true;

        // If we reached the destination, update the answer
        if (i == m - 1 && j == n - 1) {
            ans = Math.min(ans, newXor);
            return;
        }

        // Move Down
        if (i + 1 < m) {
            dfs(grid, i + 1, j, newXor);
        }
        // Move Right
        if (j + 1 < n) {
            dfs(grid, i, j + 1, newXor);
        }
    }
}