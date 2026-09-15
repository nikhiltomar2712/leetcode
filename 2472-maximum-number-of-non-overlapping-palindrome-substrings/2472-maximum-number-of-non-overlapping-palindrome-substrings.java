class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int i = 0;
        while (i <= n - k) {
            if (isPalindrome(s, i, i + k - 1)) {
                count++;
                i += k;
            } else if (i + k < n && isPalindrome(s, i, i + k)) {
                count++;
                i += k + 1;
            } else {
                i++;
            }
        }
        return count;
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
}