class Solution {
    private static final int K_MAX = 101;
    private int[][] dp;

    public int getLengthOfOptimalCompression(String s, int k) {
        dp = new int[s.length()][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, K_MAX);
        }
        return compression(s, 0, k);
    }

    private int compression(String s, int i, int k) {
        if (k < 0) {
            return K_MAX;
        }
        if (i == s.length() || s.length() - i <= k) {
            return 0;
        }
        if (dp[i][k] != K_MAX) {
            return dp[i][k];
        }

        int maxFreq = 0;
        int[] count = new int[128];

        for (int j = i; j < s.length(); ++j) {
            maxFreq = Math.max(maxFreq, ++count[s.charAt(j)]);
            int deletionsNeeded = (j - i + 1) - maxFreq;
            dp[i][k] = Math.min(
                dp[i][k],
                getLength(maxFreq) + compression(s, j + 1, k - deletionsNeeded)
            );
        }
        return dp[i][k];
    }

    private int getLength(int maxFreq) {
        if (maxFreq == 1) return 1;
        if (maxFreq < 10) return 2;
        if (maxFreq < 100) return 3;
        return 4;
    }
}