class Solution {
    public boolean isPossibleToSplit(int[] nums) {
        // Constraint: 1 <= nums[i] <= 100
        int[] count = new int[101];
        
        for (int num : nums) {
            if (++count[num] > 2) {
                return false;
            }
        }
        
        return true;
    }
}