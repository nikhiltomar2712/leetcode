class Solution {
    public long maxTaxiEarnings(int n, int[][] rides) {
        List<int[]>[] ends = new List[n + 1];
        for (int i = 0; i <= n; i++) ends[i] = new ArrayList<>();
        for (int[] r : rides) {
            ends[r[1]].add(new int[]{r[0], r[1] - r[0] + r[2]});
        }
        long[] dp = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            for (int[] ride : ends[i]) {
                dp[i] = Math.max(dp[i], dp[ride[0]] + ride[1]);
            }
        }
        return dp[n];
    }
}