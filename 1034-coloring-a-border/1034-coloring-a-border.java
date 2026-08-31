class Solution {
    private int[][] grid;
    private int m, n;
    private boolean[][] visited;
    private int originalColor;
    private List<int[]> borders = new ArrayList<>();

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        this.visited = new boolean[m][n];
        this.originalColor = grid[row][col];
        
        dfs(row, col);
        
        // Paint the borders only after the whole component has been explored
        for (int[] cell : borders) {
            grid[cell[0]][cell[1]] = color;
        }
        return grid;
    }
    
    private void dfs(int i, int j) {
        visited[i][j] = true;
        boolean isBorder = false;
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        for (int[] d : dirs) {
            int x = i + d[0];
            int y = j + d[1];
            
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != originalColor) {
                isBorder = true;          // out of bounds or different color
            } else if (!visited[x][y]) {
                dfs(x, y);
            }
        }
        
        if (isBorder) {
            borders.add(new int[]{i, j});
        }
    }
}