class Solution {
    public String largestNumber(int[] cost, int target) {
        String[] dp = new String[target + 1];
        dp[0] = "";

        for (int t = 1; t <= target; t++) {
            for (int d = 0; d < 9; d++) {
                if (t >= cost[d] && dp[t - cost[d]] != null) {
                    String candidate = (d + 1) + dp[t - cost[d]];
                    if (dp[t] == null
                            || candidate.length() > dp[t].length()
                            || (candidate.length() == dp[t].length() && candidate.compareTo(dp[t]) > 0)) {
                        dp[t] = candidate;
                    }
                }
            }
        }

        return dp[target] == null ? "0" : dp[target];
    }
}