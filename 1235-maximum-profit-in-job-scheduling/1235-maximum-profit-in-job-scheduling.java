import java.util.*;

class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }

        // 1. Sort jobs by end time
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);

        // 2. DP array: dp[i] = max profit using first i jobs (sorted by end time)
        int[] dp = new int[n + 1];
        int[] endTimes = new int[n];
        for (int i = 0; i < n; i++) {
            endTimes[i] = jobs[i][1];
        }

        for (int i = 1; i <= n; i++) {
            // Option 1: Skip current job
            dp[i] = dp[i - 1];

            // Option 2: Take current job
            // Find the last job that ends <= current job's start time
            int lastNonConflict = binarySearch(endTimes, jobs[i - 1][0]);
            dp[i] = Math.max(dp[i], dp[lastNonConflict] + jobs[i - 1][2]);
        }

        return dp[n];
    }

    // Find the rightmost index where endTimes[index] <= target
    private int binarySearch(int[] endTimes, int target) {
        int left = 0, right = endTimes.length - 1;
        int result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (endTimes[mid] <= target) {
                result = mid + 1; // dp index
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
}