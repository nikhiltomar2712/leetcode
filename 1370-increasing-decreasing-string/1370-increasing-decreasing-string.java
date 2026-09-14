class Solution {
    public String sortString(String s) {
        int[] count = new int[26];
        
        // Count frequency of each character
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        StringBuilder result = new StringBuilder();
        int n = s.length();
        
        while (result.length() < n) {
            // Increasing: a → z
            for (int i = 0; i < 26; i++) {
                if (count[i] > 0) {
                    result.append((char) ('a' + i));
                    count[i]--;
                }
            }
            
            // Decreasing: z → a
            for (int i = 25; i >= 0; i--) {
                if (count[i] > 0) {
                    result.append((char) ('a' + i));
                    count[i]--;
                }
            }
        }
        
        return result.toString();
    }
}