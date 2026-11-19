class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer>[] dp = new HashMap[k + 1];
        for (int j = 0; j <= k; j++) {
            dp[j] = new HashMap<>();
        }
        int[] best = new int[k + 1];
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = k; j >= 0; j--) {
                int cur = dp[j].getOrDefault(nums[i], 0) + 1;
                if (j > 0) {
                    cur = Math.max(cur, best[j - 1] + 1);
                }
                dp[j].put(nums[i], Math.max(dp[j].getOrDefault(nums[i], 0), cur));
                best[j] = Math.max(best[j], cur);
                res = Math.max(res, cur);
            }
        }
        return res;
    }
}