class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        
        // Two cases: rob houses 0 to n-2 OR houses 1 to n-1
        return Math.max(
            robLinear(nums, 0, nums.length - 2),  // Exclude last house
            robLinear(nums, 1, nums.length - 1)   // Exclude first house
        );
    }
    
    private int robLinear(int[] nums, int start, int end) {
        int prev2 = 0;  // dp[i-2]
        int prev1 = 0;  // dp[i-1]
        
        for (int i = start; i <= end; i++) {
            int curr = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = curr;
        }
        
        return prev1;
    }
}