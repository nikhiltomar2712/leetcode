class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        int n = s.length();
        
        // dp[i] = number of distinct subsequences using the first i characters
        long[] dp = new long[n + 1];
        dp[0] = 1; // empty subsequence
        
        // last[c] = the last index where character c appeared
        int[] last = new int[26];
        Arrays.fill(last, -1);
        
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            
            // All previous subsequences + append current character
            dp[i + 1] = (dp[i] * 2) % MOD;
            
            // If this character appeared before, subtract the duplicates
            if (last[c] >= 0) {
                dp[i + 1] = (dp[i + 1] - dp[last[c]] + MOD) % MOD;
            }
            
            last[c] = i;
        }
        
        // Subtract the empty subsequence
        return (int) ((dp[n] - 1 + MOD) % MOD);
    }
}