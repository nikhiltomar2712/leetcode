class Solution {
    public int minimumOperationsToWriteY(int[][] grid) {
        int n = grid.length;
        int[] yCount = new int[3];
        int[] nonYCount = new int[3];
        int totalY = 0, totalNonY = 0;
        
        boolean[][] isY = new boolean[n][n];
        for (int i = 0; i <= n / 2; i++) {
            isY[i][i] = true;
            isY[i][n - 1 - i] = true;
        }
        for (int i = n / 2; i < n; i++) {
            isY[i][n / 2] = true;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isY[i][j]) {
                    yCount[grid[i][j]]++;
                    totalY++;
                } else {
                    nonYCount[grid[i][j]]++;
                    totalNonY++;
                }
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int yVal = 0; yVal < 3; yVal++) {
            for (int nonYVal = 0; nonYVal < 3; nonYVal++) {
                if (yVal == nonYVal) continue;
                int changes = (totalY - yCount[yVal]) + (totalNonY - nonYCount[nonYVal]);
                res = Math.min(res, changes);
            }
        }
        return res;
    }
}