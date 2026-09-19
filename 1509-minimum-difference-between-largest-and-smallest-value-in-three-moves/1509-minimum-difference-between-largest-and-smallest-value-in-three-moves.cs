public class Solution {
    public int MinDifference(int[] nums) {
        int n = nums.Length;
        if (n <= 4) return 0;

        Array.Sort(nums);

        // Four possible ways to use the 3 moves
        int diff1 = nums[n - 4] - nums[0];  // change 3 largest
        int diff2 = nums[n - 3] - nums[1];  // change 2 largest + 1 smallest
        int diff3 = nums[n - 2] - nums[2];  // change 1 largest + 2 smallest
        int diff4 = nums[n - 1] - nums[3];  // change 3 smallest

        return Math.Min(Math.Min(diff1, diff2), Math.Min(diff3, diff4));
    }
}