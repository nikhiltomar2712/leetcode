class Solution {
    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int moves = 0;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                // Need to make nums[i] at least nums[i-1] + 1
                int need = nums[i - 1] + 1 - nums[i];
                moves += need;
                nums[i] = nums[i - 1] + 1;
            }
        }
        
        return moves;
    }
}