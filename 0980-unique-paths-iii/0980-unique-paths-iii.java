class Solution {
    private int rows, cols;
    private int emptyCount = 0;
    private int result = 0;
    private final int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

    public int uniquePathsIII(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        int startR = 0, startC = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) emptyCount++;
                else if (grid[i][j] == 1) {
                    startR = i;
                    startC = j;
                }
            }
        }

        // The start cell itself counts as a step we must cover
        dfs(grid, startR, startC, emptyCount + 1);
        return result;
    }

    private void dfs(int[][] grid, int r, int c, int remaining) {
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == -1) {
            return;
        }

        if (grid[r][c] == 2) {
            if (remaining == 0) result++;
            return;
        }

        // Mark as visited
        int temp = grid[r][c];
        grid[r][c] = -1;

        for (int[] d : dirs) {
            dfs(grid, r + d[0], c + d[1], remaining - 1);
        }

        // Backtrack
        grid[r][c] = temp;
    }
}