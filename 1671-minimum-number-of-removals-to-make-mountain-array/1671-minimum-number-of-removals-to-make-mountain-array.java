class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;

        // LIS[i]: longest strictly increasing subsequence ending at i
        int[] LIS = new int[n];
        for (int i = 0; i < n; i++) {
            LIS[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1);
                }
            }
        }

        // LDS[i]: longest strictly decreasing subsequence starting at i
        int[] LDS = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            LDS[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (nums[j] < nums[i]) {
                    LDS[i] = Math.max(LDS[i], LDS[j] + 1);
                }
            }
        }

        int maxMountain = 0;
        for (int i = 0; i < n; i++) {
            // Valid peak must have at least one element on each side
            if (LIS[i] > 1 && LDS[i] > 1) {
                maxMountain = Math.max(maxMountain, LIS[i] + LDS[i] - 1);
            }
        }

        return n - maxMountain;
    }
}