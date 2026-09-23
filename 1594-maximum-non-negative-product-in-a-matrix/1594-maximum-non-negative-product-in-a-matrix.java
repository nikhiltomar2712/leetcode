class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long MOD = 1_000_000_007L;

        long[][] maxDP = new long[m][n];
        long[][] minDP = new long[m][n];
        maxDP[0][0] = minDP[0][0] = grid[0][0];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                long v = grid[i][j];
                long maxVal = Long.MIN_VALUE;
                long minVal = Long.MAX_VALUE;

                if (i > 0) {
                    maxVal = Math.max(maxVal, Math.max(maxDP[i-1][j] * v, minDP[i-1][j] * v));
                    minVal = Math.min(minVal, Math.min(maxDP[i-1][j] * v, minDP[i-1][j] * v));
                }
                if (j > 0) {
                    maxVal = Math.max(maxVal, Math.max(maxDP[i][j-1] * v, minDP[i][j-1] * v));
                    minVal = Math.min(minVal, Math.min(maxDP[i][j-1] * v, minDP[i][j-1] * v));
                }

                maxDP[i][j] = maxVal;
                minDP[i][j] = minVal;
            }
        }

        long res = maxDP[m-1][n-1];
        return res < 0 ? -1 : (int) (res % MOD);
    }
}