class Solution {
    public int maxTotalReward(int[] rewardValues) {
        Arrays.sort(rewardValues);
        int max = rewardValues[rewardValues.length - 1];
        boolean[] dp = new boolean[2 * max + 1];
        dp[0] = true;
        int res = 0;
        for (int r : rewardValues) {
            for (int s = 2 * max; s >= 0; s--) {
                if (s < r && dp[s]) {
                    dp[s + r] = true;
                    res = Math.max(res, s + r);
                }
            }
        }
        return res;
    }
}