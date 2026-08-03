class Solution {
    public int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }
        int mid = 1 << (n - 2);          // length of previous row = 2^{n-2}
        if (k <= mid) {
            return kthGrammar(n - 1, k);          // same as previous row
        } else {
            return kthGrammar(n - 1, k - mid) ^ 1; // flipped
        }
    }
}