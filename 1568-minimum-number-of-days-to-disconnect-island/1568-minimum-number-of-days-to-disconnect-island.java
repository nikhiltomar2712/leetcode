class Solution {
    private int m, n;
    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public int minDays(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // Step 1: already disconnected?
        if (countIslands(grid) != 1) return 0;

        // Step 2: try removing each land cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    if (countIslands(grid) != 1) {
                        grid[i][j] = 1; // restore
                        return 1;
                    }
                    grid[i][j] = 1; // restore
                }
            }
        }

        // Step 3: always possible in 2 days
        return 2;
    }

    private int countIslands(int[][] grid) {
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    dfs(grid, visited, i, j);
                    count++;
                    if (count > 1) return count; // early exit
                }
            }
        }
        return count;
    }

    private void dfs(int[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) return;
        if (grid[i][j] == 0 || visited[i][j]) return;
        visited[i][j] = true;
        for (int[] d : dirs) {
            dfs(grid, visited, i + d[0], j + d[1]);
        }
    }
}