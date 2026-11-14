class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        // Build prefix sum array, pre[i] represents the sum of the first i elements
        int[] pre = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + nums.get(i - 1);
        }

        int minSum = Integer.MAX_VALUE;

        // Enumerate all possible lengths
        for (int len = l; len <= r; len++) {
            // Enumerate the starting position of the subarray
            for (int i = 0; i + len <= n; i++) {
                int sum = pre[i + len] - pre[i];
                // Only update when sum is strictly greater than 0
                if (sum > 0) {
                    minSum = Math.min(minSum, sum);
                }
            }
        }

        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}