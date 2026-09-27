import java.util.Arrays;

class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        int n = events.length;

        // dp[i][j] = max value using first i events, attending at most j
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            int[] e = events[i - 1];
            int start = e[0], end = e[1], val = e[2];

            // Find the last event that ends before 'start' (binary search)
            int prev = binarySearch(events, i - 1, start);

            for (int j = 1; j <= k; j++) {
                // Skip current event
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                // Take current event
                dp[i][j] = Math.max(dp[i][j], dp[prev + 1][j - 1] + val);
            }
        }

        return dp[n][k];
    }

    // Returns index of the last event ending before 'start' (or -1)
    private int binarySearch(int[][] events, int right, int start) {
        int lo = 0, hi = right - 1, res = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (events[mid][1] < start) {
                res = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return res;
    }
}