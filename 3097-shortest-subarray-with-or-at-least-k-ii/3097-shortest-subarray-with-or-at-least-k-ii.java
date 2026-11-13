class Solution {
    public int minimumSubarrayLength(int[] nums, int k) {
        int[] bitCount = new int[32];
        int left = 0;
        int minLen = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            // Add nums[right] into the window
            addBits(bitCount, nums[right], 1);

            // While the current OR is >= k, try shrinking the window
            while (left <= right && getOR(bitCount) >= k) {
                minLen = Math.min(minLen, right - left + 1);
                // Remove nums[left] from the window
                addBits(bitCount, nums[left], -1);
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }

    // delta = 1 to add, delta = -1 to remove
    private void addBits(int[] bitCount, int num, int delta) {
        for (int b = 0; b < 32; b++) {
            if (((num >> b) & 1) == 1) {
                bitCount[b] += delta;
            }
        }
    }

    // Rebuild the OR value from the bit counts
    private int getOR(int[] bitCount) {
        int value = 0;
        for (int b = 0; b < 32; b++) {
            if (bitCount[b] > 0) {
                value |= (1 << b);
            }
        }
        return value;
    }
}