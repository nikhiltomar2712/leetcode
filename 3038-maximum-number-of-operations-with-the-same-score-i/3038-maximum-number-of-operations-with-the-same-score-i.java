class Solution {
    public int maxOperations(int[] nums) {
        int targetSum = nums[0] + nums[1];
        int count = 1;
        
        // Iterate in steps of 2, checking each subsequent pair
        for (int i = 2; i + 1 < nums.length; i += 2) {
            if (nums[i] + nums[i + 1] == targetSum) {
                count++;
            } else {
                break;
            }
        }
        
        return count;
    }
}