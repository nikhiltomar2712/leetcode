class Solution {
    public int minOperations(int[] nums) {
        int increments = 0;   // total set bits
        int maxBits = 0;      // max binary length

        for (int num : nums) {
            int bits = 0;
            while (num > 0) {
                increments += (num & 1);   // count set bits
                num >>= 1;
                bits++;
            }
            maxBits = Math.max(maxBits, bits);
        }

        // multiplications shared = maxBits - 1 (no multiply needed for 0)
        return increments + Math.max(0, maxBits - 1);
    }
}