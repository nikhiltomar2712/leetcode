class Solution {
    public int maxProduct(String s) {
        int n = s.length();
        int max = 0;
        for (int mask1 = 1; mask1 < (1 << n); mask1++) {
            if (!isPalindrome(s, mask1)) continue;
            int len1 = Integer.bitCount(mask1);
            for (int mask2 = 1; mask2 < (1 << n); mask2++) {
                if ((mask1 & mask2) != 0) continue;
                if (!isPalindrome(s, mask2)) continue;
                int len2 = Integer.bitCount(mask2);
                max = Math.max(max, len1 * len2);
            }
        }
        return max;
    }
    
    private boolean isPalindrome(String s, int mask) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if ((mask & (1 << i)) != 0) sb.append(s.charAt(i));
        }
        int l = 0, r = sb.length() - 1;
        while (l < r) {
            if (sb.charAt(l++) != sb.charAt(r--)) return false;
        }
        return true;
    }
}