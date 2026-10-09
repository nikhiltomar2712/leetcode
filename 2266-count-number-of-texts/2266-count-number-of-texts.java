class Solution {
    public int countTexts(String pressedKeys) {
        int mod = 1000000007;
        int n = pressedKeys.length();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            char c = pressedKeys.charAt(i - 1);
            int maxLen = (c == '7' || c == '9') ? 4 : 3;
            for (int len = 2; len <= maxLen && len <= i; len++) {
                boolean same = true;
                for (int k = 0; k < len; k++) {
                    if (pressedKeys.charAt(i - 1 - k) != c) {
                        same = false;
                        break;
                    }
                }
                if (same) {
                    dp[i] = (dp[i] + dp[i - len]) % mod;
                } else {
                    break;
                }
            }
        }
        return (int) dp[n];
    }
}