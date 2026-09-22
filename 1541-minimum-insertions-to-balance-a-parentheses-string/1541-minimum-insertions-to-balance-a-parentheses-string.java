class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0; // number of unmatched '('
        
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                open++;
                i++;
            } else {
                // We have a ')'
                // Check if next char is also ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    // We have "))"
                    if (open > 0) {
                        open--; // matches one '('
                    } else {
                        insertions++; // need one '(' before
                    }
                    i += 2;
                } else {
                    // Only one ')' -> need one more ')' to make "))"
                    insertions++; // insert a ')'
                    if (open > 0) {
                        open--;
                    } else {
                        insertions++; // need one '(' as well
                    }
                    i++;
                }
            }
        }
        
        // Each remaining '(' needs two ')'
        insertions += open * 2;
        
        return insertions;
    }
}