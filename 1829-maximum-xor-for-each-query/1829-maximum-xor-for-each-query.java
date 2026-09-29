class Solution {
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int n = nums.length;
        int[] res = new int[n];
        int xor = 0;
        int max = (1 << maximumBit) - 1;
        for (int num : nums) xor ^= num;
        for (int i = 0; i < n; i++) {
            res[i] = xor ^ max;
            xor ^= nums[n - 1 - i];
        }
        return res;
    }
}