class Solution {
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int n = nums.length;
        int m = pattern.length;
        int count = 0;

        // Iterate over all possible starting positions for subarrays of size m + 1
        for (int i = 0; i <= n - m - 1; i++) {
            boolean matches = true;

            // Check each position against the pattern
            for (int k = 0; k < m; k++) {
                int diff = Integer.compare(nums[i + k + 1], nums[i + k]);
                // Integer.compare returns 1 if first > second, 0 if equal, -1 if first < second
                if (diff != pattern[k]) {
                    matches = false;
                    break;
                }
            }

            if (matches) {
                count++;
            }
        }

        return count;
    }
}