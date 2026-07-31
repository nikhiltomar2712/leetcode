class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        int n = bits.length;
        
        // Traverse the array
        while (i < n - 1) {
            // If current bit is 1, it must be a 2-bit character (10 or 11)
            if (bits[i] == 1) {
                i += 2;
            } else {
                // If current bit is 0, it's a 1-bit character (0)
                i += 1;
            }
        }
        
        // If we stopped at the last index, the last character is 1-bit (0)
        // If we went past the last index, the last character was part of a 2-bit character
        return i == n - 1;
    }
}