class Solution {
    public int minimumTimeToInitialState(String word, int k) {
        int n = word.length();
        int[] lps = computeLPS(word);
        int suffix = lps[n - 1]; // Longest border length

        // Try borders from longest to shorter until divisible condition holds
        while (suffix > 0 && (n - suffix) % k != 0) {
            suffix = lps[suffix - 1];
        }

        // If a valid border works, minimal t = (n - suffix)/k
        if ((n - suffix) % k == 0) {
            return (n - suffix) / k;
        }

        // Otherwise, we need to remove the whole string
        return (n + k - 1) / k;
    }

    private int[] computeLPS(String s) {
        int n = s.length();
        int[] lps = new int[n];
        int len = 0;
        for (int i = 1; i < n; i++) {
            while (len > 0 && s.charAt(i) != s.charAt(len)) {
                len = lps[len - 1];
            }
            if (s.charAt(i) == s.charAt(len)) {
                len++;
            }
            lps[i] = len;
        }
        return lps;
    }
}