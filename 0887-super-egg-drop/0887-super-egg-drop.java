class Solution {
    public int superEggDrop(int k, int n) {
        // dp[m][k] = maximum number of floors we can check with m moves and k eggs
        int[][] dp = new int[n + 1][k + 1];
        
        // For m moves from 1 to n (in worst case, we need at most n moves)
        for (int m = 1; m <= n; m++) {
            for (int eggs = 1; eggs <= k; eggs++) {
                // If egg breaks: we have eggs-1 eggs and m-1 moves for lower part
                // If egg doesn't break: we have eggs eggs and m-1 moves for upper part
                // Plus 1 for the current floor we are testing
                dp[m][eggs] = dp[m - 1][eggs - 1] + dp[m - 1][eggs] + 1;
                
                // If we can cover all n floors with current m moves and eggs, return m
                if (dp[m][eggs] >= n) {
                    return m;
                }
            }
        }
        
        return n; // fallback, should not reach here
    }
}