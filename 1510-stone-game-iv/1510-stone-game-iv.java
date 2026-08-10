class Solution {
    public boolean winnerSquareGame(int n) {
        // dp[i] = true if current player can win with i stones
        boolean[] dp = new boolean[n + 1];
        
        // Base case: dp[0] = false (no moves -> losing)
        // We'll compute dp[1] to dp[n]
        
        for (int i = 1; i <= n; i++) {
            // Try removing every square number ≤ i
            for (int square = 1; square * square <= i; square++) {
                int remaining = i - square * square;
                
                // If there exists a move to a losing state for opponent
                // Then current position is winning
                if (!dp[remaining]) {
                    dp[i] = true;
                    break;
                }
            }
            // If no move leads to losing state, dp[i] remains false
        }
        
        return dp[n];
    }
}