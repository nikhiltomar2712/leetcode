class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int mod = 12345;
        int m = grid.length, n = grid[0].length;
        int total = m * n;
        long[] prefix = new long[total];
        long[] suffix = new long[total];
        int[] flat = new int[total];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                flat[idx++] = grid[i][j];
            }
        }
        prefix[0] = 1;
        for (int i = 1; i < total; i++) {
            prefix[i] = prefix[i - 1] * flat[i - 1] % mod;
        }
        suffix[total - 1] = 1;
        for (int i = total - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * flat[i + 1] % mod;
        }
        int[][] res = new int[m][n];
        idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = (int) (prefix[idx] * suffix[idx] % mod);
                idx++;
            }
        }
        return res;
    }
}