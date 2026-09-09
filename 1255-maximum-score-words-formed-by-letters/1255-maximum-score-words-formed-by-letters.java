class Solution {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] count = new int[26];
        for (char c : letters) {
            count[c - 'a']++;
        }
        return dfs(words, 0, count, score);
    }

    private int dfs(String[] words, int index, int[] count, int[] score) {
        if (index == words.length) return 0;

        // Option 1: skip current word
        int max = dfs(words, index + 1, count, score);

        // Option 2: try to use current word
        int earned = use(words[index], count, score);
        if (earned >= 0) {                       // can form the word
            max = Math.max(max, earned + dfs(words, index + 1, count, score));
            // backtrack
            restore(words[index], count);
        }
        return max;
    }

    // Try to use the word. Returns the score if possible, otherwise -1
    private int use(String word, int[] count, int[] score) {
        int[] temp = count.clone();              // work on a copy first
        int sum = 0;
        for (char c : word.toCharArray()) {
            if (--temp[c - 'a'] < 0) return -1;  // not enough letters
            sum += score[c - 'a'];
        }
        // commit the change
        System.arraycopy(temp, 0, count, 0, 26);
        return sum;
    }

    private void restore(String word, int[] count) {
        for (char c : word.toCharArray()) {
            count[c - 'a']++;
        }
    }
}