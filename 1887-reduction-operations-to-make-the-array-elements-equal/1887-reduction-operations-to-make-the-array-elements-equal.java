class Solution {
    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);
        int res = 0, ops = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) ops++;
            res += ops;
        }
        return res;
    }
}