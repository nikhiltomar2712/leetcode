class Solution {
    public boolean isMonotonic(int[] nums) {
        // Initialize flags for increasing and decreasing
        boolean increasing = true;
        boolean decreasing = true;
        
        // Check each adjacent pair
        for (int i = 1; i < nums.length; i++) {
            // If we find an increasing pair, array cannot be decreasing
            if (nums[i] > nums[i - 1]) {
                decreasing = false;
            }
            // If we find a decreasing pair, array cannot be increasing
            else if (nums[i] < nums[i - 1]) {
                increasing = false;
            }
            
            // Early exit if both flags are false
            if (!increasing && !decreasing) {
                return false;
            }
        }
        
        // If either flag remains true, array is monotonic
        return increasing || decreasing;
    }
}