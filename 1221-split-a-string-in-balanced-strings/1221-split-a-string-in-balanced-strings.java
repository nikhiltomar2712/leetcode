class Solution {
    public int balancedStringSplit(String s) {
        int count = 0;      // Maximum number of balanced substrings
        int balance = 0;    // Tracks balance: L=+1, R=-1 (or opposite)
        
        for (char c : s.toCharArray()) {
            if (c == 'L') {
                balance++;
            } else { // c == 'R'
                balance--;
            }
            
            // When balance is 0, we have a balanced substring
            if (balance == 0) {
                count++;
            }
        }
        
        return count;
    }
}