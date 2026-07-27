class Solution {
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int n = digits.length;
        
        // last[d] = rightmost index of digit d
        int[] last = new int[10];
        for (int i = 0; i < n; i++) {
            last[digits[i] - '0'] = i;
        }
        
        // Find the leftmost digit that can be improved
        for (int i = 0; i < n; i++) {
            // Look for a larger digit from 9 down to current+1
            for (int d = 9; d > digits[i] - '0'; d--) {
                if (last[d] > i) {          // larger digit exists to the right
                    // Swap
                    char temp = digits[i];
                    digits[i] = digits[last[d]];
                    digits[last[d]] = temp;
                    return Integer.parseInt(new String(digits));
                }
            }
        }
        
        // Already the maximum
        return num;
    }
}