class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] row = new int[m][n + 1];
        int[][] col = new int[m + 1][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                row[i][j + 1] = row[i][j] + grid[i][j];
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                col[i + 1][j] = col[i][j] + grid[i][j];
            }
        }
        int res = 1;
        for (int k = Math.min(m, n); k >= 2; k--) {
            for (int i = 0; i + k <= m; i++) {
                for (int j = 0; j + k <= n; j++) {
                    if (isMagic(grid, row, col, i, j, k)) {
                        return k;
                    }
                }
            }
        }
        return res;
    }

    private boolean isMagic(int[][] grid, int[][] row, int[][] col, int r, int c, int k) {
        int target = row[r][c + k] - row[r][c];
        for (int i = 0; i < k; i++) {
            if (row[r + i][c + k] - row[r + i][c] != target) return false;
        }
        for (int j = 0; j < k; j++) {
            if (col[r + k][c + j] - col[r][c + j] != target) return false;
        }
        int d1 = 0, d2 = 0;
        for (int i = 0; i < k; i++) {
            d1 += grid[r + i][c + i];
            d2 += grid[r + i][c + k - 1 - i];
        }
        return d1 == target && d2 == target;
    }
}