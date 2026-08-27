class Solution {
    public int numSquarefulPerms(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[1 << n][n];

        for (int j = 0; j < n; j++) {
            dp[1 << j][j] = 1;
        }

        for (int mask = 0; mask < (1 << n); mask++) {
            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) == 0) continue;
                for (int k = 0; k < n; k++) {
                    if (k == j || (mask & (1 << k)) == 0) continue;
                    long sum = (long) nums[j] + nums[k];
                    long r = (long) Math.sqrt(sum);
                    if (r * r == sum) {
                        dp[mask][j] += dp[mask ^ (1 << j)][k];
                    }
                }
            }
        }

        long ans = 0;
        for (int j = 0; j < n; j++) {
            ans += dp[(1 << n) - 1][j];
        }

        // divide by factorial of frequencies of identical numbers
        java.util.Map<Integer, Integer> freq = new java.util.HashMap<>();
        for (int x : nums) freq.merge(x, 1, Integer::sum);

        int[] fact = new int[13];
        fact[0] = 1;
        for (int i = 1; i <= 12; i++) fact[i] = fact[i - 1] * i;

        for (int v : freq.values()) {
            ans /= fact[v];
        }
        return (int) ans;
    }
}