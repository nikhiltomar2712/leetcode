class Solution {
    public String toLowerCase(String s) {
        StringBuilder result = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            // If character is uppercase (ASCII between 65 and 90)
            if (c >= 'A' && c <= 'Z') {
                // Convert to lowercase by adding 32
                result.append((char)(c + 32));
            } else {
                result.append(c);
            }
        }
        
        return result.toString();
    }
}