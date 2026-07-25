class Solution {
    public double findMaxAverage(int[] nums, int k) {
        // Calculate the sum of the first window of size k
        long sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        long maxSum = sum;

        // Slide the window
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];   // add new element, remove the leftmost
            maxSum = Math.max(maxSum, sum);
        }

        return (double) maxSum / k;
    }
}