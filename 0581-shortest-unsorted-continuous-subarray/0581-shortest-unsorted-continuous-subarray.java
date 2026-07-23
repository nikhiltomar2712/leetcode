class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        
        // Find the right boundary: the last position where nums[i] > min from right
        int maxFromLeft = Integer.MIN_VALUE;
        int end = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] < maxFromLeft) {
                end = i;
            } else {
                maxFromLeft = nums[i];
            }
        }
        
        // Find the left boundary: the first position where nums[i] < max from left (backward)
        int minFromRight = Integer.MAX_VALUE;
        int start = n;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] > minFromRight) {
                start = i;
            } else {
                minFromRight = nums[i];
            }
        }
        
        return start > end ? 0 : end - start + 1;
    }
}