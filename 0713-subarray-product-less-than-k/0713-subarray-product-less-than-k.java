class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // If k is 0 or 1, no subarray can have a product less than k
        // (since all numbers are positive as per constraints)
        if (k <= 1) {
            return 0;
        }

        int count = 0;
        int product = 1;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            // Expand the window to the right
            product *= nums[right];

            // Shrink the window from the left while product is >= k
            while (product >= k) {
                product /= nums[left];
                left++;
            }

            // All subarrays ending at 'right' and starting from 'left' to 'right' are valid
            // Number of such subarrays is (right - left + 1)
            count += right - left + 1;
        }

        return count;
    }
}