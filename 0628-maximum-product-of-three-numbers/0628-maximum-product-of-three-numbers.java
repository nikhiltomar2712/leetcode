class Solution {
    public int maximumProduct(int[] nums) {
        // Track three largest numbers
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        
        // Track two smallest numbers (most negative)
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        
        for (int num : nums) {
            // Update max1, max2, max3
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
            
            // Update min1, min2
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        
        // Maximum product can be:
        // 1. Three largest positives
        // 2. Two smallest (negatives) * largest positive
        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}