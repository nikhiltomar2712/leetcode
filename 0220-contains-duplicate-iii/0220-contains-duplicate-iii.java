class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        // TreeSet to maintain sorted window of last k elements
        TreeSet<Long> set = new TreeSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            long num = nums[i];
            
            // Find the smallest element >= nums[i] - valueDiff
            Long floor = set.floor(num + valueDiff);
            // Find the largest element <= nums[i] + valueDiff
            Long ceiling = set.ceiling(num - valueDiff);
            
            // Check if any element in range [num - valueDiff, num + valueDiff]
            if (floor != null && floor >= num - valueDiff) {
                return true;
            }
            
            // Add current element to window
            set.add(num);
            
            // Remove element outside window
            if (i >= indexDiff) {
                set.remove((long) nums[i - indexDiff]);
            }
        }
        
        return false;
    }
}