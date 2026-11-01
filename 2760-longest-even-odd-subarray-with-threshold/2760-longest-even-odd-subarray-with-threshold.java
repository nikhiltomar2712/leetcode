class Solution {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int n = nums.length;
        int res = 0;
        int left = 0;
        while (left < n) {
            if (nums[left] % 2 == 0 && nums[left] <= threshold) {
                int right = left;
                while (right + 1 < n && nums[right + 1] <= threshold && nums[right + 1] % 2 != nums[right] % 2) {
                    right++;
                }
                res = Math.max(res, right - left + 1);
                left = right + 1;
            } else {
                left++;
            }
        }
        return res;
    }
}