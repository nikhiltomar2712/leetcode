class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // dp[i][j][0] = consecutive 1s to the right (including current)
        // dp[i][j][1] = consecutive 1s downwards (including current)
        int[][][] dp = new int[m][n][2];
        
        // Fill dp from bottom-right to top-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    dp[i][j][0] = 1 + (j + 1 < n ? dp[i][j + 1][0] : 0);
                    dp[i][j][1] = 1 + (i + 1 < m ? dp[i + 1][j][1] : 0);
                } else {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = 0;
                }
            }
        }
        
        int maxSize = 0;
        
        // Try every cell as the top-left corner
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Maximum possible square size from this cell
                int maxPossible = Math.min(m - i, n - j);
                
                // Try all possible sizes, starting from largest
                for (int size = maxPossible; size > maxSize; size--) {
                    // Check if square of this size has all 1s on border
                    if (dp[i][j][0] >= size &&      // top edge
                        dp[i][j][1] >= size &&      // left edge
                        dp[i][j + size - 1][1] >= size && // right edge
                        dp[i + size - 1][j][0] >= size) { // bottom edge
                        maxSize = size;
                        break; // Found largest for this cell
                    }
                }
            }
        }
        
        return maxSize * maxSize;
    }
}