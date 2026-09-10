class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1;
        int right = 0;
        for (int num : nums) {
            right = Math.max(right, num);
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (computeSum(nums, mid) <= threshold) {
                right = mid;          // mid works → try smaller
            } else {
                left = mid + 1;       // mid too small → need larger divisor
            }
        }
        
        return left;
    }
    
    private long computeSum(int[] nums, int divisor) {
        long sum = 0;
        for (int num : nums) {
            // ceil(num / divisor) = (num + divisor - 1) / divisor
            sum += (num + divisor - 1) / divisor;
        }
        return sum;
    }
}