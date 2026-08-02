class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        // dp[i][j] = maximum score difference (current player - opponent)
        // for the subarray piles[i..j]
        int[][] dp = new int[n][n];

        // Base case: single pile
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        // Fill for increasing length
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                // Current player takes piles[i] or piles[j]
                dp[i][j] = Math.max(
                    piles[i] - dp[i + 1][j],
                    piles[j] - dp[i][j - 1]
                );
            }
        }

        return dp[0][n - 1] > 0;   // Alice’s advantage > 0
    }
}