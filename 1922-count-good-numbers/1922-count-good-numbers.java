class Solution {
    public int countGoodNumbers(long n) {
        int mod = 1000000007;
        long even = (n + 1) / 2;
        long odd = n / 2;
        return (int) (modPow(5, even, mod) * modPow(4, odd, mod) % mod);
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