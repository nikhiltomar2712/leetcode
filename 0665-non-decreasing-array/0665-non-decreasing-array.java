class Solution {
    public boolean checkPossibility(int[] nums) {
        int count = 0;  // number of modifications needed
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                count++;
                if (count > 1) return false;
                
                // Decide which element to change
                // Prefer changing nums[i-1] if possible
                if (i == 1 || nums[i] >= nums[i - 2]) {
                    // Change previous element to current
                    nums[i - 1] = nums[i];
                } else {
                    // Change current element to previous
                    nums[i] = nums[i - 1];
                }
            }
        }
        
        return true;
    }
}