class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int maxSum = 0, minSum = 0;
        int curMax = 0, curMin = 0;

        for (int num : nums) {
            curMax = Math.max(0, curMax + num);
            curMin = Math.min(0, curMin + num);
            maxSum = Math.max(maxSum, curMax);
            minSum = Math.min(minSum, curMin);
        }

        return Math.max(maxSum, -minSum);
    }
}