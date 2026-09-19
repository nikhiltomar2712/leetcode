public class Solution {
    public int RangeSum(int[] nums, int n, int left, int right) {
        const int MOD = 1_000_000_007;
        var sums = new List<long>();

        // Generate all subarray sums
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                sums.Add(sum);
            }
        }

        sums.Sort();

        long result = 0;
        for (int i = left - 1; i < right; i++) {
            result = (result + sums[i]) % MOD;
        }

        return (int)result;
    }
}