class Solution {
    public boolean checkValidString(String s) {
        int low = 0;   // minimum possible open parentheses
        int high = 0;  // maximum possible open parentheses

        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low = Math.max(low - 1, 0);
                high--;
            } else { // '*'
                low = Math.max(low - 1, 0);  // treat as ')'
                high++;                      // treat as '('
            }

            // Too many ')' that cannot be balanced
            if (high < 0) {
                return false;
            }
        }

        // All open parentheses must be closable
        return low == 0;
    }
}