class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];
        char current = 'a';

        // Phase 1: Greedy construction of lexicographically smallest candidate
        for (int i = 0; i < n; i++) {
            if (word[i] == 0) {
                if (current > 'z') {
                    return ""; // More than 26 distinct characters needed
                }
                word[i] = current;
                for (int j = i + 1; j < n; j++) {
                    if (lcp[i][j] > 0) {
                        word[j] = word[i]; // Must be equal
                    }
                }
                current++;
            }
        }

        // Phase 2: Verify the constructed string against the LCP matrix
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (word[i] != word[j]) {
                    // Different characters => LCP must be 0
                    if (lcp[i][j] != 0) {
                        return "";
                    }
                } else {
                    // Same characters => LCP must follow the recurrence
                    if (i == n - 1 || j == n - 1) {
                        // At the end of the string, LCP can only be 1
                        if (lcp[i][j] != 1) {
                            return "";
                        }
                    } else {
                        // Otherwise, lcp[i][j] = 1 + lcp[i+1][j+1]
                        if (lcp[i][j] != lcp[i + 1][j + 1] + 1) {
                            return "";
                        }
                    }
                }
            }
        }

        return new String(word);
    }
}