class Solution {
    public int matrixScore(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Step 1: Make sure the leftmost bit of every row is 1
        // (this maximizes the contribution of the highest bit)
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) {
                // Flip the entire row
                for (int j = 0; j < n; j++) {
                    grid[i][j] ^= 1;
                }
            }
        }
        
        // Step 2: For each remaining column, choose the orientation
        // that has more 1s (because we can still flip the whole column)
        int score = 0;
        for (int j = 0; j < n; j++) {
            int ones = 0;
            for (int i = 0; i < m; i++) {
                ones += grid[i][j];
            }
            // Take the maximum number of 1s we can get in this column
            int best = Math.max(ones, m - ones);
            // Contribution of this bit position: best * 2^(n-j-1)
            score += best * (1 << (n - j - 1));
        }
        
        return score;
    }
}