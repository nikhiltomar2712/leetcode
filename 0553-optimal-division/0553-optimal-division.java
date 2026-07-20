class Solution {
    public String optimalDivision(int[] nums) {
        if (nums.length == 1) {
            return String.valueOf(nums[0]);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]);
        
        if (nums.length == 2) {
            sb.append('/').append(nums[1]);
            return sb.toString();
        }
        
        // For length > 2: nums[0] / (nums[1] / nums[2] / ... / nums[n-1])
        sb.append("/(").append(nums[1]);
        for (int i = 2; i < nums.length; i++) {
            sb.append('/').append(nums[i]);
        }
        sb.append(')');
        
        return sb.toString();
    }
}