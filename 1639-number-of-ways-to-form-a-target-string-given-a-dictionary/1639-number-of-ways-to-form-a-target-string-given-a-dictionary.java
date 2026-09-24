class Solution {
    public int numWays(String[] words, String target) {
        int wordLen = words[0].length();
        int targetLen = target.length();
        int MOD = 1_000_000_007;

        if (targetLen > wordLen) return 0;

        // Precompute character frequencies per column
        int[][] freq = new int[wordLen][26];
        for (String word : words) {
            for (int i = 0; i < wordLen; i++) {
                freq[i][word.charAt(i) - 'a']++;
            }
        }

        // dp[j] = ways to form first j characters of target
        long[] dp = new long[targetLen + 1];
        dp[0] = 1;  // empty target

        for (int col = 0; col < wordLen; col++) {
            // Traverse target from right to left
            for (int j = targetLen; j >= 1; j--) {
                int ch = target.charAt(j - 1) - 'a';
                if (freq[col][ch] > 0) {
                    dp[j] = (dp[j] + dp[j - 1] * freq[col][ch]) % MOD;
                }
            }
        }

        return (int) dp[targetLen];
    }
}