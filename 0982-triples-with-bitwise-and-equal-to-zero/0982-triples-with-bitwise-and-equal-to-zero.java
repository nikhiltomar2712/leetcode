class Solution {
    public int countTriplets(int[] nums) {
        int[] cnt = new int[1 << 16];
        for (int a : nums) {
            for (int b : nums) {
                cnt[a & b]++;
            }
        }

        int res = 0;
        for (int c : nums) {
            // We only need to look at the bits that are 0 in c
            // Iterate over the subsets of the zero-bits of c
            int mask = (~c) & ((1 << 16) - 1);
            for (int sub = mask; ; sub = (sub - 1) & mask) {
                res += cnt[sub];
                if (sub == 0) break;
            }
        }
        return res;
    }
}