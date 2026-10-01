class Solution {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length, n = grid1[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    if (dfs(grid1, grid2, i, j)) count++;
                }
            }
        }
        return count;
    }

    private boolean dfs(int[][] grid1, int[][] grid2, int i, int j) {
        if (i < 0 || i >= grid2.length || j < 0 || j >= grid2[0].length || grid2[i][j] == 0) {
            return true;
        }
        grid2[i][j] = 0;
        boolean res = grid1[i][j] == 1;
        res &= dfs(grid1, grid2, i + 1, j);
        res &= dfs(grid1, grid2, i - 1, j);
        res &= dfs(grid1, grid2, i, j + 1);
        res &= dfs(grid1, grid2, i, j - 1);
        return res;
    }
}