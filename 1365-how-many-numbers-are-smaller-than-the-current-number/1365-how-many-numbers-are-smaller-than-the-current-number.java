class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] count = new int[101]; // frequency of each number
        
        // Count frequency of each number
        for (int num : nums) {
            count[num]++;
        }
        
        // Convert to prefix sum: count[i] = how many numbers are < i
        for (int i = 1; i < 101; i++) {
            count[i] += count[i - 1];
        }
        
        // Build the result
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // If nums[i] == 0, there are 0 numbers smaller than it
            result[i] = (nums[i] == 0) ? 0 : count[nums[i] - 1];
        }
        
        return result;
    }
}