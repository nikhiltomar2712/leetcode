class Solution {
    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length;
        int[] sum = new int[1 << n];
        for (int mask = 1; mask < (1 << n); mask++) {
            int i = Integer.numberOfTrailingZeros(mask);
            sum[mask] = sum[mask ^ (1 << i)] + tasks[i];
        }
        int[] dp = new int[1 << n];
        Arrays.fill(dp, n);
        dp[0] = 0;
        for (int mask = 1; mask < (1 << n); mask++) {
            for (int sub = mask; sub > 0; sub = (sub - 1) & mask) {
                if (sum[sub] <= sessionTime) {
                    dp[mask] = Math.min(dp[mask], dp[mask ^ sub] + 1);
                }
            }
        }
        return dp[(1 << n) - 1];
    }
}