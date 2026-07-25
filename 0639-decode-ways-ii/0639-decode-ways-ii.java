class Solution {
    private static final int MOD = 1_000_000_007;

    public int numDecodings(String s) {
        int n = s.length();
        long prev2 = 1;                       // dp[i-2]
        long prev1 = waysOne(s.charAt(0));    // dp[i-1]

        for (int i = 1; i < n; i++) {
            long curr = 0;

            // Single digit
            curr = (curr + waysOne(s.charAt(i)) * prev1) % MOD;

            // Two digits
            curr = (curr + waysTwo(s.charAt(i - 1), s.charAt(i)) * prev2) % MOD;

            prev2 = prev1;
            prev1 = curr;
        }

        return (int) prev1;
    }

    // Number of ways to decode a single character
    private int waysOne(char c) {
        if (c == '*') return 9;
        return c == '0' ? 0 : 1;
    }

    // Number of ways to decode two characters
    private int waysTwo(char c1, char c2) {
        if (c1 == '*' && c2 == '*') return 15;          // 11-19 + 21-26
        if (c1 == '*') {
            return (c2 >= '0' && c2 <= '6') ? 2 : 1;    // *0-*6 → 10-16 or 20-26; *7-*9 → 17-19
        }
        if (c2 == '*') {
            if (c1 == '1') return 9;                    // 11-19
            if (c1 == '2') return 6;                    // 21-26
            return 0;
        }
        // Both digits
        int num = (c1 - '0') * 10 + (c2 - '0');
        return (num >= 10 && num <= 26) ? 1 : 0;
    }
}