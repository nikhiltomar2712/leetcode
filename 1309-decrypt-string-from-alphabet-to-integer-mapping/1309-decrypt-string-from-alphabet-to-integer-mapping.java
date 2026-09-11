class Solution {
    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;
        
        while (i >= 0) {
            if (s.charAt(i) == '#') {
                // Two-digit number: characters at i-2 and i-1
                int num = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
                sb.append((char) ('a' + num - 1));
                i -= 3;
            } else {
                // Single digit
                int num = s.charAt(i) - '0';
                sb.append((char) ('a' + num - 1));
                i -= 1;
            }
        }
        
        return sb.reverse().toString();
    }
}