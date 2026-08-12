import java.util.Arrays;

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = 0;

        // Step 1: Convert each column into consecutive 1s height ending at current row
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }
        }

        // Step 2: For each row, sort the heights and compute maximum area
        for (int[] row : matrix) {
            // Make a copy so we don't destroy the original heights for later rows
            int[] heights = row.clone();
            Arrays.sort(heights);               // ascending order

            // Traverse from largest height to smallest
            for (int j = n - 1, width = 1; j >= 0; j--, width++) {
                if (heights[j] == 0) break;
                ans = Math.max(ans, heights[j] * width);
            }
        }

        return ans;
    }
}