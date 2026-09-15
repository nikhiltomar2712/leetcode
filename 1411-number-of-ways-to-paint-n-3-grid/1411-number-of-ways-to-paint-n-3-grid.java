class Solution {
    public int numOfWays(int n) {
        int MOD = 1_000_000_007;
        long f = 6; // rows ending in ABA pattern
        long g = 6; // rows ending in ABC pattern

        for (int i = 2; i <= n; i++) {
            long newF = (3 * f + 2 * g) % MOD;
            long newG = (2 * f + 2 * g) % MOD;
            f = newF;
            g = newG;
        }

        return (int) ((f + g) % MOD);
    }
}