class Solution {
    public int balancedString(String s) {
        int n = s.length();
        int target = n / 4;
        int[] count = new int[4]; // Q, W, E, R
        
        // Count all characters
        for (char c : s.toCharArray()) {
            count[mapChar(c)]++;
        }
        
        // Check if already balanced
        if (isBalanced(count, target)) {
            return 0;
        }
        
        int left = 0;
        int minLen = n;
        
        // Sliding window: find minimum window containing all extra characters
        for (int right = 0; right < n; right++) {
            // Remove current character from window (consider it as part of replacement)
            count[mapChar(s.charAt(right))]--;
            
            // Try to shrink window from left
            while (isBalanced(count, target)) {
                minLen = Math.min(minLen, right - left + 1);
                // Add left character back to count (outside window)
                count[mapChar(s.charAt(left))]++;
                left++;
            }
        }
        
        return minLen;
    }
    
    private int mapChar(char c) {
        switch(c) {
            case 'Q': return 0;
            case 'W': return 1;
            case 'E': return 2;
            case 'R': return 3;
            default: return -1;
        }
    }
    
    private boolean isBalanced(int[] count, int target) {
        return count[0] <= target && count[1] <= target && 
               count[2] <= target && count[3] <= target;
    }
}