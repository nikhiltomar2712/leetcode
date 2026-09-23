class Solution {
    public String modifyString(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        for (int i = 0; i < n; i++) {
            if (chars[i] == '?') {
                for (char c = 'a'; c <= 'z'; c++) {
                    boolean okLeft  = (i == 0)     || chars[i - 1] != c;
                    boolean okRight = (i == n - 1) || chars[i + 1] != c;
                    if (okLeft && okRight) {
                        chars[i] = c;
                        break;
                    }
                }
            }
        }

        return new String(chars);
    }
}