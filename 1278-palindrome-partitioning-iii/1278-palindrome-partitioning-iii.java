class Solution {
    public int palindromePartition(String s, int k) {
        int n = s.length();
        
        // cost[i][j] = min changes to make s[i..j] a palindrome
        int[][] cost = new int[n][n];
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                cost[i][j] = cost[i + 1][j - 1] + (s.charAt(i) == s.charAt(j) ? 0 : 1);
            }
        }
        
        // dp[i][p] = min changes to partition s[0..i-1] into p palindromes
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);  // avoid overflow
        }
        dp[0][0] = 0;
        
        for (int p = 1; p <= k; p++) {
            for (int i = p; i <= n; i++) {          // need at least p characters
                for (int j = p - 1; j < i; j++) {   // previous partition ends at j
                    dp[i][p] = Math.min(dp[i][p], dp[j][p - 1] + cost[j][i - 1]);
                }
            }
        }
        
        return dp[n][k];
    }
}