class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> result = new ArrayList<>();
        
        for (String word : words) {
            if (result.isEmpty() || !isAnagram(result.get(result.size() - 1), word)) {
                result.add(word);
            }
            // else: word is anagram of the last kept word → skip it
        }
        
        return result;
    }
    
    private boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) return false;
        
        int[] freq = new int[26];
        for (char c : a.toCharArray()) {
            freq[c - 'a']++;
        }
        for (char c : b.toCharArray()) {
            if (--freq[c - 'a'] < 0) return false;
        }
        return true;
    }
}