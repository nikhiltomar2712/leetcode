class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) {
            index.put(arr[i], i);
        }

        // dp[j][k] = longest Fib subsequence ending with arr[j], arr[k]
        int[][] dp = new int[n][n];
        int ans = 0;

        for (int k = 0; k < n; k++) {
            for (int j = 0; j < k; j++) {
                int prev = arr[k] - arr[j];
                // prev must be smaller than arr[j] (because array is strictly increasing)
                // and must appear before index j
                if (prev < arr[j] && index.containsKey(prev)) {
                    int i = index.get(prev);
                    if (i < j) {
                        dp[j][k] = dp[i][j] + 1;
                        ans = Math.max(ans, dp[j][k]);
                    }
                }
                // every pair has at least length 2
                if (dp[j][k] == 0) {
                    dp[j][k] = 2;
                }
            }
        }
        return ans >= 3 ? ans : 0;
    }
}