class Solution {
    public int projectionArea(int[][] grid) {
        int n = grid.length;
        int top = 0;
        int front = 0;
        int side = 0;
        
        // Calculate top projection (count of non-zero cells)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    top++;
                }
            }
        }
        
        // Calculate front projection (sum of max in each row)
        for (int i = 0; i < n; i++) {
            int maxRow = 0;
            for (int j = 0; j < n; j++) {
                maxRow = Math.max(maxRow, grid[i][j]);
            }
            front += maxRow;
        }
        
        // Calculate side projection (sum of max in each column)
        for (int j = 0; j < n; j++) {
            int maxCol = 0;
            for (int i = 0; i < n; i++) {
                maxCol = Math.max(maxCol, grid[i][j]);
            }
            side += maxCol;
        }
        
        return top + front + side;
    }
}