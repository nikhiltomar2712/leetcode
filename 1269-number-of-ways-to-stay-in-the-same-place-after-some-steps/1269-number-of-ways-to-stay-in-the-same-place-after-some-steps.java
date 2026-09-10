class Solution {
    public int numWays(int steps, int arrLen) {
        final int MOD = 1_000_000_007;
        
        // The farthest we can go is min(arrLen - 1, steps)
        // (we must return to 0, so even tighter, but this is safe)
        int maxPos = Math.min(arrLen - 1, steps);
        
        // dp[pos] = number of ways to be at 'pos' after current number of steps
        long[] dp = new long[maxPos + 1];
        dp[0] = 1;  // start at position 0
        
        for (int step = 1; step <= steps; step++) {
            long[] next = new long[maxPos + 1];
            
            for (int pos = 0; pos <= maxPos; pos++) {
                // Stay
                next[pos] = (next[pos] + dp[pos]) % MOD;
                
                // Move right → come from pos - 1
                if (pos - 1 >= 0) {
                    next[pos] = (next[pos] + dp[pos - 1]) % MOD;
                }
                
                // Move left → come from pos + 1
                if (pos + 1 <= maxPos) {
                    next[pos] = (next[pos] + dp[pos + 1]) % MOD;
                }
            }
            
            dp = next;
        }
        
        return (int) dp[0];
    }
}