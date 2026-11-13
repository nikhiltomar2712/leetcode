class Solution {
    public long countAlternatingSubarrays(int[] nums) {
        long ans = 1;        // First element alone is one alternating subarray
        long curLen = 1;     // Length of current alternating streak

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                curLen++;    // Streak extends
            } else {
                curLen = 1;  // Streak breaks, restart from current element
            }
            ans += curLen;   // All subarrays ending at i
        }

        return ans;
    }
}