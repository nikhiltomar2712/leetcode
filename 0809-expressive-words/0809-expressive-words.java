class Solution {
    public int expressiveWords(String s, String[] words) {
        int count = 0;
        for (String word : words) {
            if (isStretchy(s, word)) {
                count++;
            }
        }
        return count;
    }

    private boolean isStretchy(String s, String word) {
        int i = 0, j = 0;
        int n = s.length(), m = word.length();

        while (i < n && j < m) {
            // Characters must match
            if (s.charAt(i) != word.charAt(j)) {
                return false;
            }

            // Count consecutive characters in s
            int countS = 0;
            char c = s.charAt(i);
            while (i < n && s.charAt(i) == c) {
                countS++;
                i++;
            }

            // Count consecutive characters in word
            int countW = 0;
            while (j < m && word.charAt(j) == c) {
                countW++;
                j++;
            }

            // Check stretchy condition
            if (countS < countW) {
                return false; // Can't shrink
            }
            if (countS > countW && countS < 3) {
                return false; // Can't stretch to size 1 or 2
            }
            // If countS >= 3 and countS >= countW, it's valid
        }

        // Both strings must be fully processed
        return i == n && j == m;
    }
}