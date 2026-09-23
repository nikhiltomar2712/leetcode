class Solution {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length;
        int n = colSum.length;
        int[][] result = new int[m][n];

        int i = 0, j = 0;
        while (i < m && j < n) {
            int val = Math.min(rowSum[i], colSum[j]);
            result[i][j] = val;
            rowSum[i] -= val;
            colSum[j] -= val;

            if (rowSum[i] == 0) {
                i++;
            } else {
                j++;
            }
        }

        return result;
    }
}
