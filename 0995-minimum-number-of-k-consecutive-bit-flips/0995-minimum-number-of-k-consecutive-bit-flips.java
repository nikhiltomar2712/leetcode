class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int flips = 0;          // total number of flips performed
        int flipped = 0;        // current flip parity (0 or 1)

        for (int i = 0; i < n; i++) {
            // If a previous flip that started at i-k ends here, toggle the parity
            if (i >= k && nums[i - k] == 2) {
                flipped ^= 1;
            }

            // Current effective value is nums[i] XOR flipped
            // If it is still 0, we must flip starting at position i
            if ((nums[i] ^ flipped) == 0) {
                if (i + k > n) {
                    return -1;  // cannot flip beyond the array
                }
                flips++;
                flipped ^= 1;   // start a new flip
                nums[i] = 2;    // mark that a flip starts here
            }
        }

        return flips;
    }
}