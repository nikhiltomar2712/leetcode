class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {
        int n = nums.length;
        int[] count = new int[n + 1]; // frequency map (nums[i] is between 1 and n)
        int left = 0;
        int distinct = 0;
        int result = 0;

        for (int right = 0; right < n; right++) {
            if (count[nums[right]]++ == 0) {
                distinct++;
            }

            // Shrink window until we have at most k distinct numbers
            while (distinct > k) {
                if (--count[nums[left]] == 0) {
                    distinct--;
                }
                left++;
            }

            // All subarrays ending at 'right' with left..right are valid
            result += right - left + 1;
        }

        return result;
    }
}