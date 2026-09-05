class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] charCount = new int[26];
        for (char c : chars.toCharArray()) {
            charCount[c - 'a']++;
        }
        
        int total = 0;
        for (String word : words) {
            int[] wordCount = new int[26];
            boolean good = true;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                wordCount[idx]++;
                if (wordCount[idx] > charCount[idx]) {
                    good = false;
                    break;
                }
            }
            if (good) {
                total += word.length();
            }
        }
        return total;
    }
}