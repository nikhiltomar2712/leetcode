class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        
        // Flip negative numbers (from most negative)
        for (int i = 0; i < nums.length && k > 0 && nums[i] < 0; i++) {
            nums[i] = -nums[i];
            k--;
        }
        
        // If k is still odd, flip the smallest remaining number
        if (k % 2 == 1) {
            // Find the minimum value after previous flips
            int minIdx = 0;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < nums[minIdx]) {
                    minIdx = i;
                }
            }
            nums[minIdx] = -nums[minIdx];
        }
        
        // Calculate the sum
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}
