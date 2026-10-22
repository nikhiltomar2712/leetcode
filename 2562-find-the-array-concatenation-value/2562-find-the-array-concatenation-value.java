class Solution {
    public long findTheArrayConcVal(int[] nums) {
        int left = 0, right = nums.length - 1;
        long res = 0;
        while (left < right) {
            String concat = Integer.toString(nums[left]) + Integer.toString(nums[right]);
            res += Long.parseLong(concat);
            left++;
            right--;
        }
        if (left == right) {
            res += nums[left];
        }
        return res;
    }
}