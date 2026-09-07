class Solution {
    private static final int MOD = 1_000_000_007;
    
    public int dieSimulator(int n, int[] rollMax) {
        // dp[pos][last][count] - but we can optimize by using 3D array or memoization
        // Using memoization for clarity
        Integer[][][] memo = new Integer[n + 1][7][16]; // count max is 15
        return dfs(0, 0, 0, n, rollMax, memo);
    }
    
    private int dfs(int pos, int last, int count, int n, int[] rollMax, Integer[][][] memo) {
        if (pos == n) {
            return 1; // Found one valid sequence
        }
        
        if (memo[pos][last][count] != null) {
            return memo[pos][last][count];
        }
        
        long total = 0;
        for (int next = 1; next <= 6; next++) {
            int newCount = (next == last) ? count + 1 : 1;
            // Check if we can roll 'next' based on the constraint
            if (newCount <= rollMax[next - 1]) {
                total += dfs(pos + 1, next, newCount, n, rollMax, memo);
                total %= MOD;
            }
        }
        
        memo[pos][last][count] = (int) total;
        return memo[pos][last][count];
    }
}