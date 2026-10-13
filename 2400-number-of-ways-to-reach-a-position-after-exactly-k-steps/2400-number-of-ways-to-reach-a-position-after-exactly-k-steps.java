class Solution {
    public int numberOfWays(int startPos, int endPos, int k) {
        int mod = 1000000007;
        int diff = Math.abs(endPos - startPos);
        if (diff > k || (k - diff) % 2 != 0) return 0;
        int n = (k + diff) / 2;
        long res = 1;
        for (int i = 0; i < n; i++) {
            res = res * (k - i) % mod * modPow(i + 1, mod - 2, mod) % mod;
        }
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