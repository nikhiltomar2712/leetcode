class Solution {
    public int waysToReachTarget(int target, int[][] types) {
        int mod = 1000000007;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int[] type : types) {
            int count = type[0], marks = type[1];
            int[] next = new int[target + 1];
            for (int i = 0; i <= target; i++) {
                if (dp[i] == 0) continue;
                for (int k = 0; k <= count && i + k * marks <= target; k++) {
                    next[i + k * marks] = (next[i + k * marks] + dp[i]) % mod;
                }
            }
            dp = next;
        }
        return dp[target];
    }
}