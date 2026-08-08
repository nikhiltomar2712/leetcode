import java.util.Arrays;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int m = word2.length();
        int[] ans = new int[m];
        // last[j] = rightmost index i in word1 such that word1[i] == word2[j]
        // (computed by matching the longest possible suffix of word2 from the end)
        int[] last = new int[m];
        Arrays.fill(last, -1);

        int i = word1.length() - 1;
        int j = m - 1;
        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j--] = i;
            }
            i--;
        }

        boolean canSkip = true; // still allowed to use the single mismatch
        j = 0;
        for (i = 0; i < word1.length(); i++) {
            if (j == m) break;
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j++] = i;
            } else if (canSkip && (j == m - 1 || i < last[j + 1])) {
                // Use the mismatch here only if the remaining suffix of word2
                // can still be matched strictly afterwards
                canSkip = false;
                ans[j++] = i;
            }
        }

        return j == m ? ans : new int[0];
    }
}