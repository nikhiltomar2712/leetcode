class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[] dp = new double[query_row + 1];
        dp[0] = poured;                     // top glass receives everything

        for (int row = 0; row < query_row; row++) {
            double[] next = new double[query_row + 1];
            for (int j = 0; j <= row; j++) {
                if (dp[j] > 1) {
                    double overflow = (dp[j] - 1) / 2.0;
                    next[j]     += overflow;   // left child
                    next[j + 1] += overflow;   // right child
                }
            }
            dp = next;
        }

        return Math.min(1.0, dp[query_glass]);
    }
}