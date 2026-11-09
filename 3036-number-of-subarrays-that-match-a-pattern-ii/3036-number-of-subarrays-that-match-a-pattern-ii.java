class Solution {
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int n = nums.length;
        int m = pattern.length;

        // Step 1: Build the trend array from nums
        int[] trend = new int[n - 1];
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                trend[i - 1] = 1;
            } else if (nums[i] < nums[i - 1]) {
                trend[i - 1] = -1;
            } else {
                trend[i - 1] = 0;
            }
        }

        // Step 2: Build LPS (Longest Prefix Suffix) array for pattern
        int[] lps = new int[m];
        for (int i = 1, len = 0; i < m; ) {
            if (pattern[i] == pattern[len]) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }

        // Step 3: KMP search for pattern in trend
        int count = 0;
        for (int i = 0, j = 0; i < trend.length; ) {
            if (trend[i] == pattern[j]) {
                i++;
                j++;
                if (j == m) {
                    count++;
                    j = lps[j - 1]; // continue searching for overlapping matches
                }
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }

        return count;
    }
}