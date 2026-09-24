class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        // dp[i][j] = ways to choose j segments among points 0..i (points 0..i available, ending at or before i)
        // We use: dp[i][j] = dp[i-1][j] + sum_{m=0}^{i-1} dp[m][j-1]
        // where m is the left endpoint of the last segment (right endpoint = i-1)
        long[][] dp = new long[n][k + 1];
        long[][] prefix = new long[n][k + 1];

        // Base: 0 segments → 1 way (empty set)
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
            prefix[i][0] = (i == 0 ? 1 : (prefix[i - 1][0] + 1) % MOD);
        }

        for (int j = 1; j <= k; j++) {
            for (int i = 1; i < n; i++) {
                // Two cases:
                // 1. Point i not used as an endpoint: dp[i-1][j]
                // 2. Point i is the right endpoint of the j-th segment,
                //    left endpoint m ranges from 0 to i-1:
                //    sum dp[m][j-1] for m in [0, i-1] = prefix[i-1][j-1]
                long notUsed = (i >= 1) ? dp[i - 1][j] : 0;
                long used = prefix[i - 1][j - 1];
                dp[i][j] = (notUsed + used) % MOD;
            }
            // Build prefix sums for this j
            for (int i = 0; i < n; i++) {
                prefix[i][j] = dp[i][j];
                if (i > 0) {
                    prefix[i][j] = (prefix[i][j] + prefix[i - 1][j]) % MOD;
                }
            }
        }

        return (int) dp[n - 1][k];
    }
}