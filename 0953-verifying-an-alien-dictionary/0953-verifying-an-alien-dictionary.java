class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        // Create a mapping from character to its rank in the alien order
        int[] rank = new int[26];
        for (int i = 0; i < order.length(); i++) {
            rank[order.charAt(i) - 'a'] = i;
        }
        
        // Compare consecutive words
        for (int i = 0; i < words.length - 1; i++) {
            if (!isSorted(words[i], words[i + 1], rank)) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean isSorted(String word1, String word2, int[] rank) {
        int len = Math.min(word1.length(), word2.length());
        
        for (int i = 0; i < len; i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            
            if (c1 != c2) {
                return rank[c1 - 'a'] < rank[c2 - 'a'];
            }
        }
        
        // If all characters matched so far, the shorter word should come first
        return word1.length() <= word2.length();
    }
}