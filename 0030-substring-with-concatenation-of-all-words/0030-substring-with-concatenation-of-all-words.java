import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }
        
        int wordLen = words[0].length();
        int numWords = words.length;
        int totalLen = wordLen * numWords;
        int n = s.length();
        
        if (n < totalLen) return result;
        
        // Build required frequency map
        Map<String, Integer> required = new HashMap<>();
        for (String word : words) {
            required.put(word, required.getOrDefault(word, 0) + 1);
        }
        
        // Try all possible starting offsets (0 to wordLen - 1)
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int matched = 0;
            Map<String, Integer> seen = new HashMap<>();
            
            // Slide word by word
            for (int right = i; right + wordLen <= n; right += wordLen) {
                String word = s.substring(right, right + wordLen);
                
                if (required.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    
                    if (seen.get(word) <= required.get(word)) {
                        matched++;
                    }
                    
                    // Shrink window if we have too many of this word
                    while (seen.get(word) > required.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        if (seen.get(leftWord) < required.get(leftWord)) {
                            matched--;
                        }
                        left += wordLen;
                    }
                    
                    // Check if we found a valid window
                    if (matched == numWords) {
                        result.add(left);
                        
                        // Remove the leftmost word to look for the next valid window
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        if (seen.get(leftWord) < required.get(leftWord)) {
                            matched--;
                        }
                        left += wordLen;
                    }
                } else {
                    // Invalid word found, reset the window
                    seen.clear();
                    matched = 0;
                    left = right + wordLen;
                }
            }
        }
        
        return result;
    }
}