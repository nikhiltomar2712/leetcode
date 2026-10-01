class Solution {
    public String removeOccurrences(String s, String part) {
        StringBuilder sb = new StringBuilder();
        int m = part.length();
        for (char c : s.toCharArray()) {
            sb.append(c);
            if (sb.length() >= m && sb.substring(sb.length() - m).equals(part)) {
                sb.delete(sb.length() - m, sb.length());
            }
        }
        return sb.toString();
    }
}