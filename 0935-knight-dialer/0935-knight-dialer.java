class Solution {
    private static final int MOD = 1_000_000_007;
    
    // hops[i] = list of digits the knight can jump to from i
    private static final int[][] hops = {
        {4, 6},     // 0
        {6, 8},     // 1
        {7, 9},     // 2
        {4, 8},     // 3
        {0, 3, 9},  // 4
        {},         // 5
        {0, 1, 7},  // 6
        {2, 6},     // 7
        {1, 3},     // 8
        {2, 4}      // 9
    };
    
    public int knightDialer(int n) {
        // dp[i] = number of ways to be at digit i after current number of hops
        long[] dp = new long[10];
        Arrays.fill(dp, 1);          // for length 1, 1 way for each digit
        
        for (int len = 2; len <= n; len++) {
            long[] next = new long[10];
            
            for (int digit = 0; digit < 10; digit++) {
                for (int nextDigit : hops[digit]) {
                    next[nextDigit] = (next[nextDigit] + dp[digit]) % MOD;
                }
            }
            dp = next;
        }
        
        long ans = 0;
        for (long ways : dp) {
            ans = (ans + ways) % MOD;
        }
        return (int) ans;
    }
}