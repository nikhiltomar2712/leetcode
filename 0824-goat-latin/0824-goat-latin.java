class Solution {
    public String toGoatLatin(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        String vowels = "aeiouAEIOU";
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            char first = word.charAt(0);
            
            // Build transformed word
            StringBuilder sb = new StringBuilder();
            if (vowels.indexOf(first) >= 0) {
                sb.append(word);
            } else {
                sb.append(word.substring(1));
                sb.append(first);
            }
            
            sb.append("ma");
            // Add 'a's: i+1 times (since i is 0-indexed)
            sb.append("a".repeat(i + 1));
            
            if (i > 0) result.append(' ');
            result.append(sb);
        }
        
        return result.toString();
    }
}