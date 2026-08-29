class Solution {
    private int m, n;
    private int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

    public int numEnclaves(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // Flood-fill all land cells connected to the boundary
        for (int i = 0; i < m; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            dfs(grid, 0, j);
            dfs(grid, m - 1, j);
        }

        // Count remaining land cells
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = 0; // mark as visited / sea
        for (int[] d : dirs) {
            dfs(grid, i + d[0], j + d[1]);
        }
    }
}