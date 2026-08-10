class Solution {
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] result = new int[n];
        
        // Initialize with a large value (representing infinity)
        for (int i = 0; i < n; i++) {
            result[i] = Integer.MAX_VALUE;
        }
        
        // First pass: left to right
        // Track the position of the last seen character c
        int lastSeen = -1; // -1 means not seen yet
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                lastSeen = i;
            }
            if (lastSeen != -1) {
                result[i] = Math.min(result[i], i - lastSeen);
            }
        }
        
        // Second pass: right to left
        lastSeen = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                lastSeen = i;
            }
            if (lastSeen != -1) {
                result[i] = Math.min(result[i], lastSeen - i);
            }
        }
        
        return result;
    }
}