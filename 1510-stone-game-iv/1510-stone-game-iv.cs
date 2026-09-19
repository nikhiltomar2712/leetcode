public class Solution {
    public bool WinnerSquareGame(int n) {
        bool[] dp = new bool[n + 1];
        // dp[0] = false by default

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (!dp[i - j * j]) {
                    dp[i] = true;   // found a move that forces opponent to lose
                    break;
                }
            }
        }

        return dp[n];
    }
}