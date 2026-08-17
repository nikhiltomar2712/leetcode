class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        
        for (String word : words) {
            if (matchesPattern(word, pattern)) {
                result.add(word);
            }
        }
        
        return result;
    }
    
    private boolean matchesPattern(String word, String pattern) {
        // Maps for bijection check: pattern char -> word char, and word char -> pattern char
        Map<Character, Character> patternToWord = new HashMap<>();
        Map<Character, Character> wordToPattern = new HashMap<>();
        
        for (int i = 0; i < word.length(); i++) {
            char pChar = pattern.charAt(i);
            char wChar = word.charAt(i);
            
            // Check pattern -> word mapping
            if (patternToWord.containsKey(pChar)) {
                if (patternToWord.get(pChar) != wChar) {
                    return false;
                }
            } else {
                patternToWord.put(pChar, wChar);
            }
            
            // Check word -> pattern mapping (ensure bijection)
            if (wordToPattern.containsKey(wChar)) {
                if (wordToPattern.get(wChar) != pChar) {
                    return false;
                }
            } else {
                wordToPattern.put(wChar, pChar);
            }
        }
        
        return true;
    }
}