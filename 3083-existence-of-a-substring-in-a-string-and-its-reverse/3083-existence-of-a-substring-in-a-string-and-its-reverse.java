class Solution {
    public boolean isSubstringPresent(String s) {
        boolean[][] seen = new boolean[26][26];
        
        for (int i = 1; i < s.length(); i++) {
            int c1 = s.charAt(i - 1) - 'a';
            int c2 = s.charAt(i) - 'a';
            
            seen[c1][c2] = true;
            
            if (seen[c2][c1]) {
                return true;
            }
        }
        
        return false;
    }
}