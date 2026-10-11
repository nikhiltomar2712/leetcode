class Solution {
    public long minimumReplacement(int[] nums) {
        long res = 0;
        int n = nums.length;
        int prev = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= prev) {
                prev = nums[i];
            } else {
                long parts = (nums[i] + prev - 1) / prev;
                res += parts - 1;
                prev = nums[i] / (int) parts;
            }
        }
        return res;
    }
}