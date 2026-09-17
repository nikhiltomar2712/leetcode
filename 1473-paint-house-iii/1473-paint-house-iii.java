class Solution {
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        final int INF = Integer.MAX_VALUE / 2;
        int[][][] dp = new int[m + 1][n + 1][target + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        dp[0][0][0] = 0;

        for (int i = 1; i <= m; i++) {
            for (int c = 1; c <= n; c++) {
                if (houses[i - 1] != 0 && houses[i - 1] != c) continue;

                int paintCost = (houses[i - 1] == 0) ? cost[i - 1][c - 1] : 0;

                for (int k = 1; k <= target; k++) {
                    for (int pc = 0; pc <= n; pc++) {
                        if (dp[i - 1][pc][k - (pc == c ? 0 : 1)] == INF) continue;

                        int prev = dp[i - 1][pc][k - (pc == c ? 0 : 1)];
                        dp[i][c][k] = Math.min(dp[i][c][k], prev + paintCost);
                    }
                }
            }
        }

        int result = INF;
        for (int c = 1; c <= n; c++) {
            result = Math.min(result, dp[m][c][target]);
        }

        return result == INF ? -1 : result;
    }
}