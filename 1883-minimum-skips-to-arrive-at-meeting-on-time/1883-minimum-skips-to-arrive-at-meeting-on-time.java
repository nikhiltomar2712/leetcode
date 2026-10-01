class Solution {
    public int minSkips(int[] dist, int speed, int hoursBefore) {
        int n = dist.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE / 2);
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j < i) {
                    int time = (dp[i - 1][j] + dist[i - 1] + speed - 1) / speed * speed;
                    dp[i][j] = Math.min(dp[i][j], time);
                }
                if (j > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + dist[i - 1]);
                }
            }
        }
        for (int j = 0; j <= n; j++) {
            if (dp[n][j] <= (long) hoursBefore * speed) return j;
        }
        return -1;
    }
}