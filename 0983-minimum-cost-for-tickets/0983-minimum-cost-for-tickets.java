class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int lastDay = days[n - 1];
        int[] dp = new int[lastDay + 1];
        Set<Integer> travelDays = new HashSet<>();
        for (int d : days) travelDays.add(d);

        for (int i = 1; i <= lastDay; i++) {
            if (!travelDays.contains(i)) {
                dp[i] = dp[i - 1];
                continue;
            }
            // 1-day pass
            int cost1 = dp[i - 1] + costs[0];
            // 7-day pass
            int cost7 = dp[Math.max(0, i - 7)] + costs[1];
            // 30-day pass
            int cost30 = dp[Math.max(0, i - 30)] + costs[2];

            dp[i] = Math.min(cost1, Math.min(cost7, cost30));
        }
        return dp[lastDay];
    }
}