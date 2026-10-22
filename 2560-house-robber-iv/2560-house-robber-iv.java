class Solution {
    public int minCapability(int[] nums, int k) {
        int left = 1, right = 0;
        for (int num : nums) right = Math.max(right, num);
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    count++;
                    i++;
                }
            }
            if (count >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}