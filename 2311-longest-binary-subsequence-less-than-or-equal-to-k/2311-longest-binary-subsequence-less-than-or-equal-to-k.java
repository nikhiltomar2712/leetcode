class Solution {
    public int longestSubsequence(String s, int k) {
        int n = s.length();
        int zeros = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') zeros++;
        }
        int res = zeros;
        long val = 0;
        int bits = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                if (bits < 31) {
                    val += 1L << bits;
                    if (val <= k) {
                        res++;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            bits++;
        }
        return res;
    }
}