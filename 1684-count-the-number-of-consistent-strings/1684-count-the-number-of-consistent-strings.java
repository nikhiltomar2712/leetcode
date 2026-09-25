class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        // Build bitmask for allowed characters
        int allowedMask = 0;
        for (char c : allowed.toCharArray()) {
            allowedMask |= 1 << (c - 'a');
        }
        
        int count = 0;
        for (String word : words) {
            int wordMask = 0;
            for (char c : word.toCharArray()) {
                wordMask |= 1 << (c - 'a');
            }
            // Check if wordMask is a subset of allowedMask
            if ((wordMask & allowedMask) == wordMask) {
                count++;
            }
        }
        
        return count;
    }
}