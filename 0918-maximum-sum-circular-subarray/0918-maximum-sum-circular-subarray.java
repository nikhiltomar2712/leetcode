class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0;
        int maxSum = nums[0];
        int minSum = nums[0];
        int currentMax = 0;
        int currentMin = 0;
        
        for (int num : nums) {
            totalSum += num;
            
            // Kadane's for max (standard)
            currentMax = Math.max(num, currentMax + num);
            maxSum = Math.max(maxSum, currentMax);
            
            // Kadane's for min (to find the most negative subarray)
            currentMin = Math.min(num, currentMin + num);
            minSum = Math.min(minSum, currentMin);
        }
        
        // If all numbers are negative, maxSum is the answer (the least negative)
        if (maxSum < 0) {
            return maxSum;
        }
        
        // Otherwise, return max of (non-circular max, circular max)
        return Math.max(maxSum, totalSum - minSum);
    }
}