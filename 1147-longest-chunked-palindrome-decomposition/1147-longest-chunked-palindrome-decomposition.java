class Solution {
    public int longestDecomposition(String text) {
        return helper(text);
    }
    
    private int helper(String s) {
        int n = s.length();
        if (n == 0) return 0;
        
        for (int i = 1; i <= n/2; i++) {
            if (s.substring(0, i).equals(s.substring(n - i))) {
                return 2 + helper(s.substring(i, n - i));
            }
        }
        
        return 1;
    }
}