class Solution {
    public int maxScore(int[] nums) {
        int n = nums.length;
        int m = n / 2;
        int[][] gcd = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                gcd[i][j] = gcd(nums[i], nums[j]);
            }
        }
        int[] dp = new int[1 << n];
        for (int mask = 0; mask < (1 << n); mask++) {
            int count = Integer.bitCount(mask);
            if (count % 2 != 0) continue;
            int op = count / 2 + 1;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) continue;
                for (int j = i + 1; j < n; j++) {
                    if ((mask & (1 << j)) != 0) continue;
                    int newMask = mask | (1 << i) | (1 << j);
                    dp[newMask] = Math.max(dp[newMask], dp[mask] + op * gcd[i][j]);
                }
            }
        }
        return dp[(1 << n) - 1];
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}