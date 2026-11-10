class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long sum = 0;
        int minDiff = Integer.MAX_VALUE;
        int count = 0;
        for (int num : nums) {
            sum += num;
            int diff = (num ^ k) - num;
            if (diff > 0) {
                sum += diff;
                count++;
            }
            minDiff = Math.min(minDiff, Math.abs(diff));
        }
        if (count % 2 == 0) return sum;
        return sum - minDiff;
    }
}