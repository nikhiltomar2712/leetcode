class Solution {
    public int countSpecialSubsequences(int[] nums) {
        int mod = 1000000007;
        long dp0 = 0, dp1 = 0, dp2 = 0;
        for (int num : nums) {
            if (num == 0) {
                dp0 = (dp0 * 2 + 1) % mod;
            } else if (num == 1) {
                dp1 = (dp1 * 2 + dp0) % mod;
            } else {
                dp2 = (dp2 * 2 + dp1) % mod;
            }
        }
        return (int) dp2;
    }
}