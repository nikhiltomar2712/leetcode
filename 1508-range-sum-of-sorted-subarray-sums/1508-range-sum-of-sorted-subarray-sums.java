class Solution {
    public int rangeSum(int[] nums, int n, int left, int right) {
        int MOD = 1_000_000_007;
        int total = n * (n + 1) / 2;
        int[] sums = new int[total];
        int idx = 0;

        // Generate all subarray sums
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                sums[idx++] = sum;
            }
        }

        Arrays.sort(sums);

        long result = 0;
        for (int i = left - 1; i <= right - 1; i++) {
            result = (result + sums[i]) % MOD;
        }

        return (int) result;
    }
}