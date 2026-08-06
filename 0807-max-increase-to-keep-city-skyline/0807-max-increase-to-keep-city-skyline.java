class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length;
        int[] maxRow = new int[n];
        int[] maxCol = new int[n];

        // Step 1: Find the maximum height in each row and each column
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxRow[i] = Math.max(maxRow[i], grid[i][j]);
                maxCol[j] = Math.max(maxCol[j], grid[i][j]);
            }
        }

        int totalIncrease = 0;

        // Step 2: For each building, calculate the maximum possible new height
        // and add the increase to the total.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // The new height is limited by the row maximum and column maximum
                int newHeight = Math.min(maxRow[i], maxCol[j]);
                totalIncrease += newHeight - grid[i][j];
            }
        }

        return totalIncrease;
    }
}