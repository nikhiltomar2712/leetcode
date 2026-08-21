class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        
        // Start from the second-last row and go upwards
        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col < n; col++) {
                // Find the minimum of the three possible next cells
                int best = matrix[row + 1][col]; // directly below
                
                if (col > 0) {
                    best = Math.min(best, matrix[row + 1][col - 1]);
                }
                if (col < n - 1) {
                    best = Math.min(best, matrix[row + 1][col + 1]);
                }
                
                matrix[row][col] += best;
            }
        }
        
        // The answer is the minimum value in the first row
        int minSum = Integer.MAX_VALUE;
        for (int val : matrix[0]) {
            minSum = Math.min(minSum, val);
        }
        return minSum;
    }
}