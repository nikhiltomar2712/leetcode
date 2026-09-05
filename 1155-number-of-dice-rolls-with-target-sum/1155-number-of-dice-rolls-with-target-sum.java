class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        final int MOD = 1_000_000_007;
        int[] dp = new int[target + 1];
        dp[0] = 1; // 0 dice, sum 0
        
        for (int dice = 1; dice <= n; dice++) {
            int[] newDp = new int[target + 1];
            for (int sum = 0; sum <= target; sum++) {
                if (dp[sum] == 0) continue;
                for (int face = 1; face <= k; face++) {
                    if (sum + face <= target) {
                        newDp[sum + face] = (newDp[sum + face] + dp[sum]) % MOD;
                    }
                }
            }
            dp = newDp;
        }
        
        return dp[target];
    }
}