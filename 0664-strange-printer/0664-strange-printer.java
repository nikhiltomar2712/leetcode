class Solution {
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        
        // Base case: single character needs 1 turn
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        
        // len = length of substring - 1
        for (int len = 1; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                
                // Worst case: print each character separately
                dp[i][j] = len + 1;
                
                // If s[i] == s[j], we can print them in the same turn
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    // Try all split points
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    }
                }
            }
        }
        
        return dp[0][n - 1];
    }
}