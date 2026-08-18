class Solution {
    public int smallestRangeI(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        // After adjusting, the new max can be decreased by at most k
        // and the new min can be increased by at most k
        // So the smallest possible range is max(0, (max - k) - (min + k))
        return Math.max(0, max - min - 2 * k);
    }
}