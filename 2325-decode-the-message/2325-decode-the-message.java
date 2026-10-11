class Solution {
    public String decodeMessage(String key, String message) {
        char[] map = new char[26];
        char cur = 'a';
        for (char c : key.toCharArray()) {
            if (c != ' ' && map[c - 'a'] == 0) {
                map[c - 'a'] = cur++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (c == ' ') sb.append(' ');
            else sb.append(map[c - 'a']);
        }
        return sb.toString();
    }
}
