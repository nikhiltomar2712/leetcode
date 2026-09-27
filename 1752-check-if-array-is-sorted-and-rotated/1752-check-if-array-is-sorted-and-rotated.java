class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;
        int drops = 0;
        
        for (int i = 0; i < n; i++) {
            // Compare current with next (circularly)
            if (nums[i] > nums[(i + 1) % n]) {
                drops++;
            }
        }
        
        // At most one drop is allowed
        return drops <= 1;
    }
}