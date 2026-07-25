class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            // Odd-length palindromes (center at i)
            count += expand(s, i, i);
            // Even-length palindromes (center between i and i+1)
            count += expand(s, i, i + 1);
        }

        return count;
    }

    private int expand(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }
}