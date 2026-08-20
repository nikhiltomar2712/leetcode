class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0;   // unmatched '('
        int close = 0;  // unmatched ')'

        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;      // matched with a previous '('
                } else {
                    close++;     // needs a '('
                }
            }
        }
        return open + close;
    }
}