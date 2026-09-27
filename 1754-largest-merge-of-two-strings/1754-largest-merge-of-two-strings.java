class Solution {
    public String largestMerge(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        int n = word1.length(), m = word2.length();
        
        while (i < n && j < m) {
            // Compare the suffixes starting at i and j
            if (word1.substring(i).compareTo(word2.substring(j)) > 0) {
                sb.append(word1.charAt(i++));
            } else {
                sb.append(word2.charAt(j++));
            }
        }
        
        // Append whatever remains
        sb.append(word1.substring(i));
        sb.append(word2.substring(j));
        
        return sb.toString();
    }
}