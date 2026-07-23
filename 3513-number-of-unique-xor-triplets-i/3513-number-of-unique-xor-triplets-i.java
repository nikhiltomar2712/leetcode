class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;
        if (n == 2) return 2;
        // Find the highest power of 2 <= n
        int msb = 1 << (31 - Integer.numberOfLeadingZeros(n));
        return 2 * msb;
    }
}