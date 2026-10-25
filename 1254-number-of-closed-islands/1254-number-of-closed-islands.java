class Solution {
    private int m, n;
    private final int[] dx = {-1, 0, 1, 0};
    private final int[] dy = {0, 1, 0, -1};

    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        m = grid.length;
        n = grid[0].length;
        int count = 0;

        // Iterate through the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    // Start DFS from this land cell
                    if (dfs(grid, i, j)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private boolean dfs(int[][] grid, int i, int j) {
        // If we go out of bounds, it's not a closed island
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return false;
        }
        // If we hit water, stop (this path is fine)
        if (grid[i][j] == 1) {
            return true;
        }

        // Mark current land as visited (sink it)
        grid[i][j] = 1;

        boolean isClosed = true;
        // Check all 4 directions
        for (int k = 0; k < 4; k++) {
            int ni = i + dx[k];
            int nj = j + dy[k];
            // Use & (bitwise AND) to ensure we check all directions even if one fails
            isClosed = dfs(grid, ni, nj) & isClosed;
        }

        return isClosed;
    }
}