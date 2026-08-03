class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1];          // dp[n] = 0

        for (int i = n - 1; i >= 0; i--) {
            int maxDiff = Integer.MIN_VALUE;
            int take = 0;
            for (int k = 1; k <= 3 && i + k - 1 < n; k++) {
                take += stoneValue[i + k - 1];
                maxDiff = Math.max(maxDiff, take - dp[i + k]);
            }
            dp[i] = maxDiff;
        }

        if (dp[0] > 0) return "Alice";
        if (dp[0] < 0) return "Bob";
        return "Tie";
    }
}