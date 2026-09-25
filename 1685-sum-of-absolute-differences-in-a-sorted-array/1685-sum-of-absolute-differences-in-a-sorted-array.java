class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int totalSum = 0;
        for (int num : nums) totalSum += num;
        
        int[] result = new int[n];
        int leftSum = 0;
        
        for (int i = 0; i < n; i++) {
            int rightSum = totalSum - leftSum - nums[i];
            result[i] = (nums[i] * i - leftSum) 
                      + (rightSum - nums[i] * (n - i - 1));
            leftSum += nums[i];
        }
        
        return result;
    }
}