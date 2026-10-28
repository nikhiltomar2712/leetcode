class Solution {
    public long findScore(int[] nums) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> nums[a] != nums[b] ? nums[a] - nums[b] : a - b);
        boolean[] marked = new boolean[n];
        long score = 0;
        for (int i : idx) {
            if (marked[i]) continue;
            score += nums[i];
            marked[i] = true;
            if (i > 0) marked[i - 1] = true;
            if (i < n - 1) marked[i + 1] = true;
        }
        return score;
    }
}