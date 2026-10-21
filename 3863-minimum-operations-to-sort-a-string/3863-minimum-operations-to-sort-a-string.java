class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        // Check if already sorted
        boolean sorted = true;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;
        
        // Length 2 and unsorted → impossible
        if (n == 2) return -1;
        
        char mn = s.charAt(0), mx = s.charAt(0);
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            if (c < mn) mn = c;
            if (c > mx) mx = c;
        }
        
        // 1 operation: min is already at front or max is already at end
        if (s.charAt(0) == mn || s.charAt(n - 1) == mx) {
            return 1;
        }
        
        // Check if min or max appears in the middle
        boolean hasInMiddle = false;
        for (int i = 1; i < n - 1; i++) {
            char c = s.charAt(i);
            if (c == mn || c == mx) {
                hasInMiddle = true;
                break;
            }
        }
        
        // If min/max appears in middle → 2 operations
        // Otherwise (max only at start + min only at end) → 3 operations
        return hasInMiddle ? 2 : 3;
    }
}