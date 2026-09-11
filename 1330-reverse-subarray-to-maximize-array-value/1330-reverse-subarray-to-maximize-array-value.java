class Solution {
    public int maxValueAfterReverse(int[] nums) {
        int n = nums.length;
        int base = 0;
        
        // Compute base value
        for (int i = 0; i < n - 1; i++) {
            base += Math.abs(nums[i] - nums[i + 1]);
        }
        
        int extra = 0;
        
        // Case 1: Reversing a prefix [0, j] — only right boundary changes
        // delta = |nums[0] - nums[j+1]| - |nums[j] - nums[j+1]|
        for (int j = 0; j < n - 1; j++) {
            int delta = Math.abs(nums[0] - nums[j + 1]) - Math.abs(nums[j] - nums[j + 1]);
            extra = Math.max(extra, delta);
        }
        
        // Case 2: Reversing a suffix [i, n-1] — only left boundary changes
        // delta = |nums[i-1] - nums[n-1]| - |nums[i-1] - nums[i]|
        for (int i = 1; i < n; i++) {
            int delta = Math.abs(nums[i - 1] - nums[n - 1]) - Math.abs(nums[i - 1] - nums[i]);
            extra = Math.max(extra, delta);
        }
        
        // Case 3: Reversing an interior subarray [i, j] (0 < i <= j < n-1)
        // Max extra = 2 * (maxMin - minMax) if positive
        int maxMin = Integer.MIN_VALUE;
        int minMax = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            int a = nums[i], b = nums[i + 1];
            maxMin = Math.max(maxMin, Math.min(a, b));
            minMax = Math.min(minMax, Math.max(a, b));
        }
        extra = Math.max(extra, 2 * (maxMin - minMax));
        
        return base + extra;
    }
}