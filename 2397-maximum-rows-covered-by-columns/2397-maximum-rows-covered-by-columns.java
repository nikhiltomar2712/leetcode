class Solution {
    public int maximumRows(int[][] matrix, int numSelect) {
        int m = matrix.length, n = matrix[0].length;
        int[] rowMask = new int[m];
        for (int i = 0; i < m; i++) {
            int mask = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) mask |= 1 << j;
            }
            rowMask[i] = mask;
        }
        int res = 0;
        for (int mask = 0; mask < (1 << n); mask++) {
            if (Integer.bitCount(mask) != numSelect) continue;
            int count = 0;
            for (int rm : rowMask) {
                if ((rm & mask) == rm) count++;
            }
            res = Math.max(res, count);
        }
        return res;
    }
}