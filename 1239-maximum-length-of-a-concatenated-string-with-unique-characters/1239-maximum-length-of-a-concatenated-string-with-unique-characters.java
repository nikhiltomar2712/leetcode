class Solution {
    private int maxLen = 0;
    
    public int maxLength(List<String> arr) {
        // Pre-process: filter out strings with duplicate characters
        List<Integer> validMasks = new ArrayList<>();
        for (String s : arr) {
            int mask = getMask(s);
            if (mask != -1) {
                validMasks.add(mask);
            }
        }
        
        // Start backtracking
        backtrack(validMasks, 0, 0, 0);
        return maxLen;
    }
    
    // Convert string to bit mask, return -1 if it has duplicate characters
    private int getMask(String s) {
        int mask = 0;
        for (char c : s.toCharArray()) {
            int bit = c - 'a';
            if ((mask & (1 << bit)) != 0) {
                return -1; // Duplicate character found
            }
            mask |= (1 << bit);
        }
        return mask;
    }
    
    private void backtrack(List<Integer> masks, int index, int currentMask, int currentLen) {
        // Update maximum length
        maxLen = Math.max(maxLen, currentLen);
        
        // Try to add each subsequent string
        for (int i = index; i < masks.size(); i++) {
            int mask = masks.get(i);
            // Check if current string shares any character with what we have
            if ((currentMask & mask) == 0) {
                // No overlap, we can add this string
                backtrack(masks, i + 1, currentMask | mask, currentLen + Integer.bitCount(mask));
            }
        }
    }
}