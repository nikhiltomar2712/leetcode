class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeroCount = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            // Expand the window
            if (nums[right] == 0) {
                zeroCount++;
            }

            // Shrink the window if we have too many zeros
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // Update the maximum length
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}