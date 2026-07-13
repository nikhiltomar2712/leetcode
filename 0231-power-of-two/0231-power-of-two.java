class Solution {
    public boolean isPowerOfTwo(int n) {
        // A power of two has exactly one '1' bit
        // n & (n-1) clears the lowest set bit
        return n > 0 && (n & (n - 1)) == 0;
    }
}