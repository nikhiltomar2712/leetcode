class Solution {
    public List<String> printVertically(String s) {
        String[] words = s.split(" ");
        int maxLen = 0;
        for (String w : words) {
            maxLen = Math.max(maxLen, w.length());
        }
        
        List<String> result = new ArrayList<>();
        
        for (int i = 0; i < maxLen; i++) {
            StringBuilder sb = new StringBuilder();
            for (String w : words) {
                if (i < w.length()) {
                    sb.append(w.charAt(i));
                } else {
                    sb.append(' ');
                }
            }
            // Remove trailing spaces
            int end = sb.length();
            while (end > 0 && sb.charAt(end - 1) == ' ') {
                end--;
            }
            sb.setLength(end);
            result.add(sb.toString());
        }
        
        return result;
    }
}