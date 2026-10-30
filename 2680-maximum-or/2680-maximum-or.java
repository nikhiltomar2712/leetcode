class Solution {
    public long maximumOr(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n + 1];
        long[] suffix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] | nums[i];
        }
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] | nums[i];
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            long cur = prefix[i] | suffix[i + 1] | ((long) nums[i] << k);
            res = Math.max(res, cur);
        }
        return res;
    }
}