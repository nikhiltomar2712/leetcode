class Solution {
    public int countPalindromes(String s) {
        final int MOD = 1_000_000_007;
        final int PATTERN_SIZE = 5;
        long ans = 0;

        for (char a = '0'; a <= '9'; ++a) {
            for (char b = '0'; b <= '9'; ++b) {
                // pattern: a b . b a
                char[] pattern = {a, b, '.', b, a};

                // dp[i] = number of ways to match pattern[i..4]
                long[] dp = new long[PATTERN_SIZE + 1];
                dp[PATTERN_SIZE] = 1;          // empty suffix is matched

                for (char c : s.toCharArray()) {
                    // process from left to right of the pattern
                    for (int i = 0; i < PATTERN_SIZE; ++i) {
                        if (pattern[i] == '.' || pattern[i] == c) {
                            dp[i] = (dp[i] + dp[i + 1]) % MOD;
                        }
                    }
                }
                ans = (ans + dp[0]) % MOD;
            }
        }
        return (int) ans;
    }
}