class Solution {
    public int minNonZeroProduct(int p) {
        int mod = 1000000007;
        long max = (1L << p) - 1;
        long res = modPow(max - 1, (1L << (p - 1)) - 1, mod);
        res = res * (max % mod) % mod;
        return (int) res;
    }

    private long modPow(long base, long exp, int mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = res * base % mod;
            base = base * base % mod;
            exp >>= 1;
        }
        return res;
    }
}