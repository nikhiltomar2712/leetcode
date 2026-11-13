class Solution {
    private static final int MOD = 1_000_000_007;
    private int n;
    private int[] nums;
    private Map<Long, Integer> memo;

    public int sumOfPowers(int[] nums, int k) {
        this.nums = nums;
        this.n = nums.length;
        Arrays.sort(this.nums);
        this.memo = new HashMap<>();
        return dfs(0, -1, k, Integer.MAX_VALUE);
    }

    // i: current index, last: last picked index (-1 if none), k: remaining picks, minDiff: current minimum difference
    private int dfs(int i, int last, int k, int minDiff) {
        if (k == 0) {
            return minDiff;
        }
        if (i >= n || n - i < k) {
            return 0;
        }

        long key = ((long) i << 42) | ((long) (last + 1) << 36) | ((long) k << 30) | (minDiff == Integer.MAX_VALUE ? 0 : (minDiff & 0x3FFFFFFF));
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Skip nums[i]
        int skip = dfs(i + 1, last, k, minDiff);

        // Pick nums[i]
        int newMin = minDiff;
        if (last != -1) {
            newMin = Math.min(minDiff, nums[i] - nums[last]);
        }
        int pick = dfs(i + 1, i, k - 1, newMin);

        int result = (skip + pick) % MOD;
        memo.put(key, result);
        return result;
    }
}