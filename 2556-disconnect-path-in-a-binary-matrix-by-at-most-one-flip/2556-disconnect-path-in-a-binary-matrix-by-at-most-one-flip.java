class Solution {
    private int n;
    private int m;

    public boolean isPossibleToCutPath(int[][] grid) {
        n = grid.length;
        m = grid[0].length;

        // 1. Check if any path exists initially.
        // If not, the matrix is already disconnected.
        if (!dfs(0, 0, grid)) {
            return true;
        }

        // 2. The first DFS has flipped all cells on one valid path to 0.
        // Reset the start cell to 1, as we cannot flip it.
        grid[0][0] = 1;

        // 3. Check if another path exists.
        // If a second path is found, it's impossible to disconnect with one flip.
        // Otherwise, one flip is sufficient.
        return !dfs(0, 0, grid);
    }

    private boolean dfs(int r, int c, int[][] grid) {
        // Base case: reached the destination.
        if (r == n - 1 && c == m - 1) {
            return true;
        }

        // Boundary checks and blocked cell check.
        if (r >= n || c >= m || grid[r][c] == 0) {
            return false;
        }

        // Mark the current cell as visited by flipping it to 0.
        grid[r][c] = 0;

        // Explore right and down paths.
        return dfs(r, c + 1, grid) || dfs(r + 1, c, grid);
    }
}