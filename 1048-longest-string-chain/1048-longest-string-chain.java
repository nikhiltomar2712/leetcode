class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        Map<String, Integer> dp = new HashMap<>();
        int maxLen = 0;

        for (String word : words) {
            int best = 1;
            for (int i = 0; i < word.length(); i++) {
                // Form predecessor by removing one character
                String pred = word.substring(0, i) + word.substring(i + 1);
                best = Math.max(best, dp.getOrDefault(pred, 0) + 1);
            }
            dp.put(word, best);
            maxLen = Math.max(maxLen, best);
        }
        return maxLen;
    }
}