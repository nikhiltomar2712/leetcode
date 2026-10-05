class Solution {
    public int countMaxOrSubsets(int[] nums) {
        int maxOr = 0;
        for (int num : nums) maxOr |= num;
        return dfs(nums, 0, 0, maxOr);
    }

    private int dfs(int[] nums, int index, int curOr, int maxOr) {
        if (index == nums.length) {
            return curOr == maxOr ? 1 : 0;
        }
        return dfs(nums, index + 1, curOr | nums[index], maxOr) +
               dfs(nums, index + 1, curOr, maxOr);
    }
}