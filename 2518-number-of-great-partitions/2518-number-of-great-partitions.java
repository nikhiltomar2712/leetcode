class Solution {
    public int countPartitions(int[] nums, int k) {
        int mod = 1000000007;
        int n = nums.length;
        long total = 0;
        for (int num : nums) total += num;
        if (total < 2L * k) return 0;
        long[] dp = new long[k];
        dp[0] = 1;
        for (int num : nums) {
            if (num >= k) continue;
            for (int j = k - 1; j >= num; j--) {
                dp[j] = (dp[j] + dp[j - num]) % mod;
            }
        }
        long small = 0;
        for (int j = 0; j < k; j++) {
            small = (small + dp[j]) % mod;
        }
        long res = modPow(2, n, mod);
        res = (res - 2 * small % mod + mod) % mod;
        return (int) res;
    }

    private long modPow(long base, long exp, int mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = res * base % mod;
            base = base * base % mod;
            exp >>= 1;
        }
        return res;
    }
}