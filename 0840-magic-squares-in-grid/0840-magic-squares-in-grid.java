class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int count = 0;
        
        // Iterate over all possible top-left corners of 3x3 subgrids
        for (int i = 0; i <= rows - 3; i++) {
            for (int j = 0; j <= cols - 3; j++) {
                if (isMagicSquare(grid, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }
    
    private boolean isMagicSquare(int[][] grid, int r, int c) {
        // Check if all numbers are distinct and between 1 and 9
        boolean[] seen = new boolean[10]; // index 0 unused
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                int val = grid[i][j];
                if (val < 1 || val > 9 || seen[val]) {
                    return false;
                }
                seen[val] = true;
            }
        }
        
        // Calculate sum of first row as reference
        int sum = grid[r][c] + grid[r][c+1] + grid[r][c+2];
        
        // Check rows
        if (grid[r+1][c] + grid[r+1][c+1] + grid[r+1][c+2] != sum) return false;
        if (grid[r+2][c] + grid[r+2][c+1] + grid[r+2][c+2] != sum) return false;
        
        // Check columns
        if (grid[r][c] + grid[r+1][c] + grid[r+2][c] != sum) return false;
        if (grid[r][c+1] + grid[r+1][c+1] + grid[r+2][c+1] != sum) return false;
        if (grid[r][c+2] + grid[r+1][c+2] + grid[r+2][c+2] != sum) return false;
        
        // Check diagonals
        if (grid[r][c] + grid[r+1][c+1] + grid[r+2][c+2] != sum) return false;
        if (grid[r][c+2] + grid[r+1][c+1] + grid[r+2][c] != sum) return false;
        
        return true;
    }
}