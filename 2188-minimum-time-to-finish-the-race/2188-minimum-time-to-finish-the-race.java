class Solution {
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        int n = tires.length;
        long[] best = new long[numLaps + 1];
        Arrays.fill(best, Long.MAX_VALUE / 2);
        for (int[] tire : tires) {
            long f = tire[0], r = tire[1];
            long time = 0;
            for (int lap = 1; lap <= numLaps; lap++) {
                if (lap == 1) time = f;
                else time += f * (long) Math.pow(r, lap - 1);
                if (time > Integer.MAX_VALUE) break;
                best[lap] = Math.min(best[lap], time);
            }
        }
        long[] dp = new long[numLaps + 1];
        Arrays.fill(dp, Long.MAX_VALUE / 2);
        dp[0] = 0;
        for (int i = 1; i <= numLaps; i++) {
            for (int j = 1; j <= i; j++) {
                if (best[j] == Long.MAX_VALUE / 2) continue;
                dp[i] = Math.min(dp[i], dp[i - j] + best[j] + (i - j > 0 ? changeTime : 0));
            }
        }
        return (int) dp[numLaps];
    }
}