class Solution {
    public int maximumLength(int[] nums, int k) {
        // dp[i][j] represents the maximum length of a valid subsequence 
        // where the last two elements have remainders i and j modulo k.
        int[][] dp = new int[k][k];
        int ans = 0;

        for (int x : nums) {
            int cur = x % k;
            // For the current remainder cur, try all possible previous remainders prev.
            for (int prev = 0; prev < k; prev++) {
                // To extend the subsequence, the current state (prev, cur) comes from 
                // the previous state (cur, prev). The +1 here means appending the current element.
                dp[prev][cur] = dp[cur][prev] + 1;
                ans = Math.max(ans, dp[prev][cur]);
            }
        }
        return ans;
    }
}