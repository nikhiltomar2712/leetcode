class Solution {
    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int i = k, j = k;
        int min = nums[k];
        int res = nums[k];
        while (i > 0 || j < n - 1) {
            if (i == 0) {
                j++;
            } else if (j == n - 1) {
                i--;
            } else if (nums[i - 1] > nums[j + 1]) {
                i--;
            } else {
                j++;
            }
            min = Math.min(min, Math.min(nums[i], nums[j]));
            res = Math.max(res, min * (j - i + 1));
        }
        return res;
    }
}