import java.util.Arrays;

class Solution {
    public long largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        long prefixSum = 0;
        long ans = -1;

        for (int i = 0; i < nums.length; i++) {
            // nums[i] is the largest side; check if it's < sum of the rest
            if (i >= 2 && prefixSum > nums[i]) {
                ans = prefixSum + nums[i];
            }
            prefixSum += nums[i];
        }

        return ans;
    }
}