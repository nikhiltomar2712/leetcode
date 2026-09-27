class Solution {
    public boolean checkPartitioning(String s) {
        int n = s.length();

        // isPal[i][j] = true if s[i..j] is a palindrome
        boolean[][] isPal = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || isPal[i + 1][j - 1])) {
                    isPal[i][j] = true;
                }
            }
        }

        // Try every split point between two cut positions
        for (int i = 0; i < n - 2; i++) {
            if (!isPal[0][i]) continue;
            for (int j = i + 1; j < n - 1; j++) {
                if (isPal[i + 1][j] && isPal[j + 1][n - 1]) {
                    return true;
                }
            }
        }

        return false;
    }
}