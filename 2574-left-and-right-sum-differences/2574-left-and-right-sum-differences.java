class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int total = 0;
        for (int num : nums) total += num;
        int[] res = new int[n];
        int leftSum = 0;
        for (int i = 0; i < n; i++) {
            int rightSum = total - leftSum - nums[i];
            res[i] = Math.abs(leftSum - rightSum);
            leftSum += nums[i];
        }
        return res;
    }
}