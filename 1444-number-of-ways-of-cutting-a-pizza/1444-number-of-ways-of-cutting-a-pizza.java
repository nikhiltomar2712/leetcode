class Solution {
    public int ways(String[] pizza, int k) {
        int rows = pizza.length, cols = pizza[0].length();
        int MOD = 1_000_000_007;

        int[][] apples = new int[rows + 1][cols + 1];
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = cols - 1; c >= 0; c--) {
                apples[r][c] = (pizza[r].charAt(c) == 'A' ? 1 : 0)
                        + apples[r + 1][c]
                        + apples[r][c + 1]
                        - apples[r + 1][c + 1];
            }
        }

        int[][][] dp = new int[k][rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (apples[r][c] > 0) dp[0][r][c] = 1;
            }
        }

        for (int cuts = 1; cuts < k; cuts++) {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (apples[r][c] == 0) continue;

                    long ways = 0;

                    for (int nr = r + 1; nr < rows; nr++) {
                        if (apples[r][c] - apples[nr][c] > 0) {
                            ways += dp[cuts - 1][nr][c];
                        }
                    }

                    for (int nc = c + 1; nc < cols; nc++) {
                        if (apples[r][c] - apples[r][nc] > 0) {
                            ways += dp[cuts - 1][r][nc];
                        }
                    }

                    dp[cuts][r][c] = (int) (ways % MOD);
                }
            }
        }

        return dp[k - 1][0][0];
    }
}