class Solution {
    public int numberOfCombinations(String num) {
        if (num.charAt(0) == '0') return 0;

        final int MOD = 1_000_000_007;
        final int n = num.length();

        // dp[i][k] = number of ways to partition num[0..i]
        //            where the last number has length 1..k
        long[][] dp = new long[n][n + 1];

        // lcs[i][j] = LCP length of suffixes starting at i and j
        int[][] lcs = new int[n + 1][n + 1];

        // Precompute LCP
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (num.charAt(i) == num.charAt(j)) {
                    lcs[i][j] = lcs[i + 1][j + 1] + 1;
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int k = 1; k <= i + 1; ++k) {
                // prefix sum: dp[i][k] accumulates all lengths 1..k
                dp[i][k] = (dp[i][k] + dp[i][k - 1]) % MOD;

                int s = i - k + 1;               // start index of the last number
                if (num.charAt(s) == '0') continue; // leading zero → invalid

                if (s == 0) {
                    // The whole prefix is one valid number
                    dp[i][k] = (dp[i][k] + 1) % MOD;
                    continue;
                }

                if (s < k) {
                    // Previous part is shorter than k → just take all ways of previous
                    dp[i][k] = (dp[i][k] + dp[s - 1][s]) % MOD;
                    continue;
                }

                // Compare previous number of length k with current number of length k
                int l = lcs[s - k][s];
                if (l >= k || num.charAt(s - k + l) <= num.charAt(s + l)) {
                    // previous <= current
                    dp[i][k] = (dp[i][k] + dp[s - 1][k]) % MOD;
                } else {
                    // previous > current → only take lengths 1..(k-1)
                    dp[i][k] = (dp[i][k] + dp[s - 1][k - 1]) % MOD;
                }
            }
        }

        return (int) (dp[n - 1][n] % MOD);
    }
}