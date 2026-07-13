class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // Start from top-right corner
        int row = 0;
        int col = cols - 1;
        
        while (row < rows && col >= 0) {
            int current = matrix[row][col];
            
            if (current == target) {
                return true;
            } else if (current > target) {
                // Current is too large, move left
                col--;
            } else {
                // Current is too small, move down
                row++;
            }
        }
        
        return false;
    }
}