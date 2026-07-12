bool isPowerOfTwo(int n) {
    // A power of two must be positive and have exactly one '1' bit
    // Using bit manipulation: n & (n-1) removes the lowest set bit
    // If result is 0, there was only one '1' bit
    return n > 0 && (n & (n - 1)) == 0;
}