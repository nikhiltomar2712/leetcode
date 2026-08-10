class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        // dp[i][m] = max stones current player can get from piles[i:] with m
        int[][] dp = new int[n + 1][n + 1];
        
        for (int i = n - 1; i >= 0; i--) {
            for (int m = 1; m <= n; m++) {
                // If can take all remaining
                if (2 * m >= n - i) {
                    dp[i][m] = suffixSum[i];
                    continue;
                }
                
                int maxStones = 0;
                for (int x = 1; x <= 2 * m && i + x <= n; x++) {
                    int currentTake = suffixSum[i] - suffixSum[i + x];
                    int remaining = suffixSum[i + x];
                    int opponentOptimal = dp[i + x][Math.max(m, x)];
                    int currentPlayerTotal = currentTake + (remaining - opponentOptimal);
                    maxStones = Math.max(maxStones, currentPlayerTotal);
                }
                dp[i][m] = maxStones;
            }
        }
        
        return dp[0][1];
    }
}