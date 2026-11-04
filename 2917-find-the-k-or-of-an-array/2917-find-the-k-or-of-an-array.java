class Solution {
    public int findKOr(int[] nums, int k) {
        int res = 0;
        for (int bit = 0; bit < 32; bit++) {
            int count = 0;
            for (int num : nums) {
                if ((num >> bit & 1) == 1) count++;
            }
            if (count >= k) {
                res |= (1 << bit);
            }
        }
        return res;
    }
}