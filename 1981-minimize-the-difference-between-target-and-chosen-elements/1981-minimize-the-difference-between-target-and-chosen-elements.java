class Solution {
    public int minimizeTheDifference(int[][] mat, int target) {
        boolean[] dp = new boolean[4901];
        dp[0] = true;

        int maxSum = 0;

        for (int[] row : mat) {
            boolean[] next = new boolean[4901];

            for (int sum = 0; sum <= maxSum; sum++) {
                if (dp[sum]) {
                    for (int num : row) {
                        next[sum + num] = true;
                    }
                }
            }

            maxSum += 70;
            dp = next;
        }

        int ans = Integer.MAX_VALUE;

        for (int sum = 0; sum < dp.length; sum++) {
            if (dp[sum]) {
                ans = Math.min(ans, Math.abs(sum - target));
            }
        }

        return ans;
    }
}