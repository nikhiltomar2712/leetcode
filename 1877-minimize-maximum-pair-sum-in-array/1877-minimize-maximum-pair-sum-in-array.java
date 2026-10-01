class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        int i = 0, j = nums.length - 1;
        while (i < j) {
            res = Math.max(res, nums[i] + nums[j]);
            i++;
            j--;
        }
        return res;
    }
}