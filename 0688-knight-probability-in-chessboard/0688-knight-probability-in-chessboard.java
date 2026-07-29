class Solution {
    private static final int[][] DIRS = {
        {1, 2}, {1, -2}, {-1, 2}, {-1, -2},
        {2, 1}, {2, -1}, {-2, 1}, {-2, -1}
    };

    public double knightProbability(int n, int k, int row, int column) {
        // dp[i][j] = probability of being at (i, j)
        double[][] dp = new double[n][n];
        dp[row][column] = 1.0;

        for (int step = 0; step < k; step++) {
            double[][] next = new double[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[i][j] == 0) continue;

                    for (int[] dir : DIRS) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];

                        if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
                            next[ni][nj] += dp[i][j] / 8.0;
                        }
                    }
                }
            }
            dp = next;
        }

        // Sum all probabilities still on the board
        double ans = 0.0;
        for (double[] rowProb : dp) {
            for (double p : rowProb) {
                ans += p;
            }
        }
        return ans;
    }
}