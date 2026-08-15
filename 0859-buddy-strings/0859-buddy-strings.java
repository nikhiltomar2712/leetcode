class Solution {
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        
        // Case 1: strings are already equal
        // We can swap two identical characters if any letter appears more than once
        if (s.equals(goal)) {
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                if (++count[c - 'a'] > 1) {
                    return true;
                }
            }
            return false;
        }
        
        // Case 2: strings differ
        // There must be exactly two positions that differ, and swapping them should make the strings equal
        int first = -1, second = -1;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                if (first == -1) {
                    first = i;
                } else if (second == -1) {
                    second = i;
                } else {
                    // More than two differences
                    return false;
                }
            }
        }
        
        // Exactly two differences and they form a valid swap
        return second != -1 
            && s.charAt(first) == goal.charAt(second) 
            && s.charAt(second) == goal.charAt(first);
    }
}