class Solution {
    public boolean checkPalindromeFormation(String a, String b) {
        return check(a, b) || check(b, a);
    }

    private boolean check(String a, String b) {
        int i = 0, j = a.length() - 1;
        // Match a's prefix with b's reversed suffix
        while (i < j && a.charAt(i) == b.charAt(j)) {
            i++;
            j--;
        }
        // Now check if the middle of a OR the middle of b is a palindrome
        return isPalindrome(a, i, j) || isPalindrome(b, i, j);
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}