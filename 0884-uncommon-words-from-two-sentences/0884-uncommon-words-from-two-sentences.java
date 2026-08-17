class Solution {
    public String[] uncommonFromSentences(String s1, String s2) {
        // Split both sentences into words
        String[] words1 = s1.split(" ");
        String[] words2 = s2.split(" ");
        
        // Use a map to count frequency of each word across both sentences
        Map<String, Integer> wordCount = new HashMap<>();
        
        // Count words from first sentence
        for (String word : words1) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        
        // Count words from second sentence
        for (String word : words2) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        
        // Collect words that appear exactly once
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if (entry.getValue() == 1) {
                result.add(entry.getKey());
            }
        }
        
        // Convert list to array
        return result.toArray(new String[0]);
    }
}