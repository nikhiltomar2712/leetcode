class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            for (int j = i - k; j >= 0; j--) {
                if (isPalindrome(s, j, i - 1)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    break;
                }
            }
        }
        return dp[n];
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