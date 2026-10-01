class Solution {
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int m = students.length;
        int[][] score = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                int s = 0;
                for (int k = 0; k < students[i].length; k++) {
                    if (students[i][k] == mentors[j][k]) s++;
                }
                score[i][j] = s;
            }
        }
        int[] dp = new int[1 << m];
        for (int mask = 0; mask < (1 << m); mask++) {
            int i = Integer.bitCount(mask);
            if (i >= m) continue;
            for (int j = 0; j < m; j++) {
                if ((mask & (1 << j)) != 0) continue;
                int newMask = mask | (1 << j);
                dp[newMask] = Math.max(dp[newMask], dp[mask] + score[i][j]);
            }
        }
        return dp[(1 << m) - 1];
    }
}