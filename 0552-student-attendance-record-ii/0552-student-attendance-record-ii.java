class Solution {
    public int checkRecord(int n) {
        final int MOD = 1_000_000_007;
        // dp[i][j] = number of valid records of length 'current'
        // i: number of 'A's (0 or 1)
        // j: number of consecutive 'L's at the end (0, 1, or 2)
        long[][] dp = new long[2][3];
        dp[0][0] = 1; // empty string is valid
        
        while (n-- > 0) {
            long[][] prev = new long[2][3];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    prev[i][j] = dp[i][j];
                }
            }
            
            // Reset current dp
            dp = new long[2][3];
            
            // Append 'P'
            dp[0][0] = (prev[0][0] + prev[0][1] + prev[0][2]) % MOD;
            
            // Append 'L'
            dp[0][1] = prev[0][0] % MOD;
            dp[0][2] = prev[0][1] % MOD;
            
            // Append 'A' (only if no 'A' yet)
            dp[1][0] = (prev[0][0] + prev[0][1] + prev[0][2] + 
                       prev[1][0] + prev[1][1] + prev[1][2]) % MOD;
            
            // Append 'L' after previous 'A'
            dp[1][1] = prev[1][0] % MOD;
            dp[1][2] = prev[1][1] % MOD;
        }
        
        long result = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                result = (result + dp[i][j]) % MOD;
            }
        }
        return (int) result;
    }
}