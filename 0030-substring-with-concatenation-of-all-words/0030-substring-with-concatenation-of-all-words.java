class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words.length == 0) return res;
        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;
        if (s.length() < totalLen) return res;
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String w : words) {
            wordFreq.merge(w, 1, Integer::sum);
        }
        for (int i = 0; i < wordLen; i++) {
            int left = i, right = i, count = 0;
            Map<String, Integer> window = new HashMap<>();
            while (right + wordLen <= s.length()) {
                String word = s.substring(right, right + wordLen);
                right += wordLen;
                if (wordFreq.containsKey(word)) {
                    window.merge(word, 1, Integer::sum);
                    count++;
                    while (window.get(word) > wordFreq.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        window.merge(leftWord, -1, Integer::sum);
                        count--;
                        left += wordLen;
                    }
                    if (count == wordCount) {
                        res.add(left);
                        String leftWord = s.substring(left, left + wordLen);
                        window.merge(leftWord, -1, Integer::sum);
                        count--;
                        left += wordLen;
                    }
                } else {
                    window.clear();
                    count = 0;
                    left = right;
                }
            }
        }
        return res;
    }
}