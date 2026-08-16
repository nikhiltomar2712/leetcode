class Solution {
    public int binaryGap(int n) {
        int ans = 0;
        // d starts at a large negative number so first 1 doesn't update ans
        for (int d = -32; n > 0; n >>= 1, d++) {
            if ((n & 1) == 1) {
                ans = Math.max(ans, d);
                d = 0;                   // reset distance counter
            }
        }
        return ans;
    }
}