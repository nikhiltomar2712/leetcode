class Solution {
    public long maxPoints(int[][] points) {
        int m = points.length, n = points[0].length;
        long[] dp = new long[n];
        for (int j = 0; j < n; j++) {
            dp[j] = points[0][j];
        }
        for (int i = 1; i < m; i++) {
            long[] left = new long[n];
            long[] right = new long[n];
            left[0] = dp[0];
            for (int j = 1; j < n; j++) {
                left[j] = Math.max(left[j - 1] - 1, dp[j]);
            }
            right[n - 1] = dp[n - 1];
            for (int j = n - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1] - 1, dp[j]);
            }
            long[] ndp = new long[n];
            for (int j = 0; j < n; j++) {
                ndp[j] = Math.max(left[j], right[j]) + points[i][j];
            }
            dp = ndp;
        }
        long res = 0;
        for (long v : dp) res = Math.max(res, v);
        return res;
    }
}