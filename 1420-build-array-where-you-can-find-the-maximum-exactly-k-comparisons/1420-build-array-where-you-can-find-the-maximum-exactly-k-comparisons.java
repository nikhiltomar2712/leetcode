class Solution {
    private static final int MOD = 1_000_000_007;

    public int numOfArrays(int n, int m, int k) {
        // dp[maxVal][cost] for current length
        // maxVal in [0, m], cost in [0, k]
        long[][] dp = new long[m + 1][k + 1];

        // Base case: length = 1
        for (int v = 1; v <= m; v++) {
            if (k >= 1) {
                dp[v][1] = 1;
            }
        }

        // Build up length from 2 to n
        for (int len = 2; len <= n; len++) {
            long[][] next = new long[m + 1][k + 1];

            // Suffix sums to compute "sum over x > maxVal of dp[x][cost-1]" quickly
            // suffix[cost][maxVal] = sum_{v=maxVal}^{m} dp[v][cost]
            long[][] suffix = new long[k + 1][m + 2];
            for (int cost = 0; cost <= k; cost++) {
                for (int v = m; v >= 1; v--) {
                    suffix[cost][v] = (suffix[cost][v + 1] + dp[v][cost]) % MOD;
                }
            }

            for (int maxVal = 1; maxVal <= m; maxVal++) {
                for (int cost = 1; cost <= k; cost++) {
                    // Case 1: new element <= maxVal -> max stays, cost stays
                    // maxVal choices for the new value
                    long stay = dp[maxVal][cost] * maxVal % MOD;

                    // Case 2: new element x > maxVal -> new max = x, cost increases
                    // Sum over x in (maxVal, m] of dp[x][cost - 1]
                    long increase = 0;
                    if (cost - 1 >= 1) {
                        increase = suffix[cost - 1][maxVal + 1];
                    }

                    next[maxVal][cost] = (stay + increase) % MOD;
                }
            }

            dp = next;
        }

        // Sum over all possible max values
        long answer = 0;
        for (int v = 1; v <= m; v++) {
            answer = (answer + dp[v][k]) % MOD;
        }
        return (int) answer;
    }
}