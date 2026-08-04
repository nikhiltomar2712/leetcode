class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        return count(nums, right) - count(nums, left - 1);
    }

    // Returns the number of subarrays whose maximum is ≤ bound
    private int count(int[] nums, int bound) {
        int ans = 0;
        int cur = 0;               // length of the current valid segment
        for (int v : nums) {
            if (v <= bound) {
                cur++;             // extend the segment
            } else {
                cur = 0;           // reset
            }
            ans += cur;            // all suffixes of the current segment are valid
        }
        return ans;
    }
}