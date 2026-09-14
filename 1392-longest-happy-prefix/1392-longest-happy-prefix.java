class Solution {
    public String longestPrefix(String s) {
        int n = s.length();
        if (n == 1) return "";
        
        // Build LPS (Longest Prefix Suffix) array
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
        
        // The last value in LPS array gives the length of longest happy prefix
        int happyLen = lps[n - 1];
        return s.substring(0, happyLen);
    }
}
