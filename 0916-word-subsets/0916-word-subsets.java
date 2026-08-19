class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        // Step 1: Find the maximum frequency requirement from words2
        int[] maxFreq = new int[26];
        for (String word : words2) {
            int[] freq = getFrequency(word);
            for (int i = 0; i < 26; i++) {
                maxFreq[i] = Math.max(maxFreq[i], freq[i]);
            }
        }
        
        // Step 2: Check each word in words1 against the combined requirement
        List<String> result = new ArrayList<>();
        for (String word : words1) {
            int[] freq = getFrequency(word);
            if (isUniversal(freq, maxFreq)) {
                result.add(word);
            }
        }
        
        return result;
    }
    
    // Helper to count character frequencies in a word
    private int[] getFrequency(String word) {
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }
        return freq;
    }
    
    // Check if a word's frequency satisfies the maximum requirement
    private boolean isUniversal(int[] wordFreq, int[] requiredFreq) {
        for (int i = 0; i < 26; i++) {
            if (wordFreq[i] < requiredFreq[i]) {
                return false;
            }
        }
        return true;
    }
}