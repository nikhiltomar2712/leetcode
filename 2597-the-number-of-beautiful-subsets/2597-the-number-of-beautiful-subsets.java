class Solution {
    int res = 0;

    public int beautifulSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        Map<Integer, Integer> count = new HashMap<>();
        dfs(nums, 0, k, count);
        return res;
    }

    private void dfs(int[] nums, int idx, int k, Map<Integer, Integer> count) {
        if (idx == nums.length) {
            if (!count.isEmpty()) res++;
            return;
        }
        if (!count.containsKey(nums[idx] - k) && !count.containsKey(nums[idx] + k)) {
            count.merge(nums[idx], 1, Integer::sum);
            dfs(nums, idx + 1, k, count);
            count.merge(nums[idx], -1, Integer::sum);
            if (count.get(nums[idx]) == 0) count.remove(nums[idx]);
        }
        dfs(nums, idx + 1, k, count);
    }
}