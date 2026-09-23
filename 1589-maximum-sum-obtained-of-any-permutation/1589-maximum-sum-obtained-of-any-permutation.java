import java.util.Arrays;

class Solution {
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int n = nums.length;
        int MOD = 1_000_000_007;

        // Difference array for coverage counts
        int[] diff = new int[n + 1];
        for (int[] r : requests) {
            diff[r[0]]++;
            diff[r[1] + 1]--;
        }

        // Prefix sum to get actual coverage per index
        int[] count = new int[n];
        int running = 0;
        for (int i = 0; i < n; i++) {
            running += diff[i];
            count[i] = running;
        }

        // Sort both so largest value pairs with largest count
        Arrays.sort(nums);
        Arrays.sort(count);

        long total = 0;
        for (int i = 0; i < n; i++) {
            total = (total + (long) nums[i] * count[i]) % MOD;
        }

        return (int) total;
    }
}