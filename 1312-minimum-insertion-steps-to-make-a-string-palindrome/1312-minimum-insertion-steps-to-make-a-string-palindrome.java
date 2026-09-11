class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        // dp[i][j] = length of longest palindromic subsequence in s[i..j]
        int[][] dp = new int[n][n];
        
        // Base case: single characters are palindromes of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        
        // Fill by increasing length of substring
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    // Inner substring length (handle len == 2 case)
                    int inner = (len == 2) ? 0 : dp[i + 1][j - 1];
                    dp[i][j] = inner + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return n - dp[0][n - 1];
    }
}