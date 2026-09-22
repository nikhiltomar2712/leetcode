class Solution {
    public char findKthBit(int n, int k) {
        if (n == 1) return '0';
        int mid = 1 << (n - 1); // length of S_{n-1} + 1
        if (k == mid) return '1';
        if (k < mid) return findKthBit(n - 1, k);
        // k > mid → corresponding bit in the inverted reversed part
        char bit = findKthBit(n - 1, mid * 2 - k);
        return bit == '0' ? '1' : '0';
    }
}