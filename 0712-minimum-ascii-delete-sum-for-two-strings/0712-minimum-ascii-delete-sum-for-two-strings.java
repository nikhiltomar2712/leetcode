class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        // dp[i][j] = minimum cost to make s1[0..i-1] and s2[0..j-1] equal
        int[][] dp = new int[m + 1][n + 1];
        
        // Initialize base cases
        // If s2 is empty, delete all characters from s1
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i-1][0] + s1.charAt(i-1);
        }
        // If s1 is empty, delete all characters from s2
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j-1] + s2.charAt(j-1);
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = s1.charAt(i-1);
                char c2 = s2.charAt(j-1);
                
                if (c1 == c2) {
                    // Characters match, no deletion needed for these characters
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    // Take minimum of deleting c1 or deleting c2
                    dp[i][j] = Math.min(
                        dp[i-1][j] + c1,  // Delete c1 from s1
                        dp[i][j-1] + c2   // Delete c2 from s2
                    );
                }
            }
        }
        
        return dp[m][n];
    }
}