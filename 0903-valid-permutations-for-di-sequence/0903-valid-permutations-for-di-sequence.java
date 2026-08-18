class Solution {
    public int numPermsDISequence(String s) {
        int n = s.length();
        final int MOD = 1_000_000_007;
        
        int[][] dp = new int[n + 1][n + 1];
        Arrays.fill(dp[0], 1);          // or just dp[0][0] = 1; both work
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'I') {
                for (int j = 0, sum = 0; j <= i + 1; j++) {
                    dp[i + 1][j] = sum;
                    if (j <= i) sum = (sum + dp[i][j]) % MOD;
                }
            } else {
                for (int j = i + 1, sum = 0; j >= 0; j--) {
                    dp[i + 1][j] = sum;
                    if (j - 1 >= 0) sum = (sum + dp[i][j - 1]) % MOD;
                }
            }
        }
        
        int ans = 0;
        for (int x : dp[n]) ans = (ans + x) % MOD;
        return ans;
    }
}