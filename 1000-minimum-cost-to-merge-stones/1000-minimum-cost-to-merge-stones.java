class Solution {
    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        // Impossible to reduce to 1 pile
        if ((n - 1) % (k - 1) != 0) {
            return -1;
        }

        // prefix sum: prefix[i] = sum of first i stones
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stones[i];
        }

        // dp[i][j] = min cost to merge stones[i..j]
        int[][] dp = new int[n][n];

        // length of the interval
        for (int len = k; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                // try every possible split point (step = k-1)
                for (int mid = i; mid < j; mid += k - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid + 1][j]);
                }

                // if this interval can be merged into one pile, add the merge cost
                if ((j - i) % (k - 1) == 0) {
                    dp[i][j] += prefix[j + 1] - prefix[i];
                }
            }
        }

        return dp[0][n - 1];
    }
}