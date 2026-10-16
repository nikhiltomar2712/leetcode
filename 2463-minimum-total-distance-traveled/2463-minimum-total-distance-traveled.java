class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);
        int n = robot.size();
        int m = factory.length;
        long[][] dp = new long[m + 1][n + 1];
        for (long[] row : dp) Arrays.fill(row, Long.MAX_VALUE / 2);
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            int pos = factory[i - 1][0], cap = factory[i - 1][1];
            for (int j = 0; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];
                long cost = 0;
                for (int k = 1; k <= cap && j - k >= 0; k++) {
                    cost += Math.abs((long) robot.get(j - k) - pos);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k] + cost);
                }
            }
        }
        return dp[m][n];
    }
}