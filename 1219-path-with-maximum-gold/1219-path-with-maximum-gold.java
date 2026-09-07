class Solution {
    private final int[] dirs = {-1, 0, 1, 0, -1}; // up, right, down, left
    private int m, n;
    
    public int getMaximumGold(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int maxGold = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    maxGold = Math.max(maxGold, dfs(grid, i, j));
                }
            }
        }
        return maxGold;
    }
    
    private int dfs(int[][] grid, int i, int j) {
        // Out of bounds or no gold
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            return 0;
        }
        
        int gold = grid[i][j];
        grid[i][j] = 0;          // mark as visited
        
        int maxPath = 0;
        for (int k = 0; k < 4; k++) {
            int ni = i + dirs[k];
            int nj = j + dirs[k + 1];
            maxPath = Math.max(maxPath, dfs(grid, ni, nj));
        }
        
        grid[i][j] = gold;       // backtrack
        return gold + maxPath;
    }
}