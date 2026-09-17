class Solution {
    public int minDistance(int[] houses, int k) {
        Arrays.sort(houses);
        int n = houses.length;

        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int median = houses[(i + j) / 2];
                for (int m = i; m <= j; m++) {
                    cost[i][j] += Math.abs(houses[m] - median);
                }
            }
        }

        int[][] dp = new int[k + 1][n];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            dp[1][i] = cost[0][i];
        }

        for (int boxes = 2; boxes <= k; boxes++) {
            for (int i = boxes - 1; i < n; i++) {
                for (int j = boxes - 2; j < i; j++) {
                    if (dp[boxes - 1][j] != Integer.MAX_VALUE) {
                        dp[boxes][i] = Math.min(dp[boxes][i], dp[boxes - 1][j] + cost[j + 1][i]);
                    }
                }
            }
        }

        return dp[k][n - 1];
    }
}