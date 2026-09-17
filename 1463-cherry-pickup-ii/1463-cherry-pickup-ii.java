class Solution {
    public int cherryPickup(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        Integer[][][] memo = new Integer[rows][cols][cols];
        return dfs(grid, 0, 0, cols - 1, memo);
    }

    private int dfs(int[][] grid, int r, int c1, int c2, Integer[][][] memo) {
        int cols = grid[0].length;
        if (c1 < 0 || c1 >= cols || c2 < 0 || c2 >= cols) return Integer.MIN_VALUE;
        if (r == grid.length - 1) {
            return c1 == c2 ? grid[r][c1] : grid[r][c1] + grid[r][c2];
        }
        if (memo[r][c1][c2] != null) return memo[r][c1][c2];

        int curr = (c1 == c2) ? grid[r][c1] : grid[r][c1] + grid[r][c2];
        int best = Integer.MIN_VALUE;

        for (int d1 = -1; d1 <= 1; d1++) {
            for (int d2 = -1; d2 <= 1; d2++) {
                int next = dfs(grid, r + 1, c1 + d1, c2 + d2, memo);
                if (next != Integer.MIN_VALUE) {
                    best = Math.max(best, curr + next);
                }
            }
        }

        memo[r][c1][c2] = best;
        return best;
    }
}