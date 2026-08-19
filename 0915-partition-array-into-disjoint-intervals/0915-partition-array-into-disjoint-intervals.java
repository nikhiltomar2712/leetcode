class Solution {
    public int partitionDisjoint(int[] nums) {
        int n = nums.length;
        
        // Array to store the minimum value from each position to the right
        int[] minFromRight = new int[n];
        minFromRight[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minFromRight[i] = Math.min(nums[i], minFromRight[i + 1]);
        }
        
        // Track the maximum value seen so far in the left partition
        int maxLeft = nums[0];
        
        // Try to partition at each index (left ends at i, right starts at i+1)
        for (int i = 0; i < n - 1; i++) {
            maxLeft = Math.max(maxLeft, nums[i]);
            // If max of left <= min of right, this is a valid partition
            if (maxLeft <= minFromRight[i + 1]) {
                return i + 1; // Length of left = i+1
            }
        }
        
        // Should never reach here as per problem guarantee
        return n;
    }
}