class Solution {
    private static final int MOD = 1_000_000_007;
    
    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        
        // dp[i][j] = number of different palindromic subsequences in s[i..j]
        long[][] dp = new long[n][n];
        
        // nextPos[i][c] = next position of character c (0-3 for a-d) at or after index i
        // prevPos[i][c] = previous position of character c (0-3 for a-d) at or before index i
        int[][] nextPos = new int[n][4];
        int[][] prevPos = new int[n][4];
        
        // Initialize nextPos and prevPos arrays
        for (int c = 0; c < 4; c++) {
            int next = -1;
            // Fill nextPos from right to left
            for (int i = n - 1; i >= 0; i--) {
                if (chars[i] - 'a' == c) {
                    next = i;
                }
                nextPos[i][c] = next;
            }
            
            int prev = -1;
            // Fill prevPos from left to right
            for (int i = 0; i < n; i++) {
                if (chars[i] - 'a' == c) {
                    prev = i;
                }
                prevPos[i][c] = prev;
            }
        }
        
        // Base case: single characters
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        
        // Fill DP table for substrings of increasing length
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                
                // Start with 0
                long count = 0;
                
                // Try each character 'a', 'b', 'c', 'd'
                for (int c = 0; c < 4; c++) {
                    int first = nextPos[i][c];
                    int last = prevPos[j][c];
                    
                    // Character c not found in s[i..j]
                    if (first == -1 || first > j || last == -1 || last < i) {
                        continue;
                    }
                    
                    // Only one occurrence of character c
                    if (first == last) {
                        count = (count + 1) % MOD; // Just the single character palindrome
                    } else {
                        // Two or more occurrences
                        // Add 2 for "c" and "cc"
                        count = (count + 2) % MOD;
                        
                        // Add all palindromes inside s[first+1..last-1]
                        if (first + 1 <= last - 1) {
                            count = (count + dp[first + 1][last - 1]) % MOD;
                        }
                    }
                }
                
                dp[i][j] = count % MOD;
            }
        }
        
        return (int) dp[0][n - 1];
    }
}