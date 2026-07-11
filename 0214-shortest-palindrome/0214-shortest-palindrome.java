class Solution {
    public String shortestPalindrome(String s) {
        // Create string s + "#" + reverse(s)
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        
        // Compute KMP lookup table (LPS - Longest Proper Prefix which is also Suffix)
        int[] lps = computeLPS(temp);
        
        // Length of longest palindromic prefix
        int longestPalPrefix = lps[lps.length - 1];
        
        // Add the remaining characters in reverse to the front
        return new StringBuilder(s.substring(longestPalPrefix))
                    .reverse()
                    .toString() + s;
    }
    
    private int[] computeLPS(String s) {
        int n = s.length();
        int[] lps = new int[n];
        int len = 0; // length of previous longest prefix suffix
        int i = 1;
        
        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        
        return lps;
    }
}