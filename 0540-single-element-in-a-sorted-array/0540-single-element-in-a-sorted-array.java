class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // Check if mid is even or odd index pair
            if (nums[mid] != nums[mid ^ 1]) {
                // Single element is on the left side (including mid)
                right = mid;
            } else {
                // Single element is on the right side
                left = mid + 1;
            }
        }
        
        return nums[left];
    }
}