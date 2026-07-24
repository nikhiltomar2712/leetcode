class Solution {
    public int uniqueXorTriplets(int[] nums) {
        java.util.BitSet pairs = new java.util.BitSet(2048);
        for (int x : nums) {
            for (int y : nums) {
                pairs.set(x ^ y);
            }
        }
        java.util.BitSet res = new java.util.BitSet(2048);
        for (int p = pairs.nextSetBit(0); p >= 0; p = pairs.nextSetBit(p + 1)) {
            for (int z : nums) {
                res.set(p ^ z);
            }
        }
        return res.cardinality();
    }
}