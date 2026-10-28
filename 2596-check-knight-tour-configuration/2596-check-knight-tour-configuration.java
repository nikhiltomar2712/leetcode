class Solution {
    public boolean checkValidGrid(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0) return false;
        int[][] pos = new int[n * n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pos[grid[i][j]][0] = i;
                pos[grid[i][j]][1] = j;
            }
        }
        for (int i = 1; i < n * n; i++) {
            int dr = Math.abs(pos[i][0] - pos[i - 1][0]);
            int dc = Math.abs(pos[i][1] - pos[i - 1][1]);
            if (!((dr == 1 && dc == 2) || (dr == 2 && dc == 1))) return false;
        }
        return true;
    }
}