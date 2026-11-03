class Solution {
    public int minimumScore(String s, String t) {
        int n = t.length();
        int[] leftmost  = new int[n];   // leftmost[i] = min index in s that matches t[0..i]
        int[] rightmost = new int[n];   // rightmost[j] = max index in s that matches t[j..]
        Arrays.fill(leftmost,  -1);
        Arrays.fill(rightmost, -1);

        // Build leftmost
        int j = 0;
        for (int i = 0; i < s.length() && j < n; i++) {
            if (s.charAt(i) == t.charAt(j)) {
                leftmost[j] = i;
                j++;
            }
        }

        // Build rightmost
        j = n - 1;
        for (int i = s.length() - 1; i >= 0 && j >= 0; i--) {
            if (s.charAt(i) == t.charAt(j)) {
                rightmost[j] = i;
                j--;
            }
        }

        // Worst-case answer: delete a prefix so that the remaining suffix matches
        int ans = j + 1;          // after the right-to-left pass j is the first unmatched index

        // Now try every possible prefix length
        j = 0;
        for (int i = 0; i < n; i++) {
            if (leftmost[i] == -1) break;          // longer prefixes are impossible

            // Advance j until the suffix starting at j can be placed after the prefix
            while (j < n && leftmost[i] >= rightmost[j]) {
                j++;
            }

            if (i == j) return 0;                  // whole t is already a subsequence

            // Delete the open interval (i, j)
            ans = Math.min(ans, j - i - 1);
        }
        return ans;
    }
}