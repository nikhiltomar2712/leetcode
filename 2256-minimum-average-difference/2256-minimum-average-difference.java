class Solution {
    public int minimumAverageDifference(int[] nums) {
        int n = nums.length;
        long total = 0;
        for (int num : nums) total += num;
        long prefix = 0;
        long minDiff = Long.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < n; i++) {
            prefix += nums[i];
            long leftAvg = prefix / (i + 1);
            long rightAvg = (i == n - 1) ? 0 : (total - prefix) / (n - i - 1);
            long diff = Math.abs(leftAvg - rightAvg);
            if (diff < minDiff) {
                minDiff = diff;
                res = i;
            }
        }
        return res;
    }
}