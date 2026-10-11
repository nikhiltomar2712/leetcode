class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int mod = 1000000007;
        long[] dp = new long[n + 1];
        dp[1] = 1;
        long share = 0;
        for (int i = 2; i <= n; i++) {
            if (i - delay >= 1) {
                share = (share + dp[i - delay]) % mod;
            }
            if (i - forget >= 1) {
                share = (share - dp[i - forget] + mod) % mod;
            }
            dp[i] = share;
        }
        long res = 0;
        for (int i = Math.max(1, n - forget + 1); i <= n; i++) {
            res = (res + dp[i]) % mod;
        }
        return (int) res;
    }
}