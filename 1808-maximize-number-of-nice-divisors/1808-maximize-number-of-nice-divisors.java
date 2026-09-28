class Solution {
    public int maxNiceDivisors(int primeFactors) {
        int mod = 1000000007;
        if (primeFactors <= 3) return primeFactors;
        long res;
        if (primeFactors % 3 == 0) {
            res = power(3, primeFactors / 3, mod);
        } else if (primeFactors % 3 == 1) {
            res = power(3, (primeFactors - 4) / 3, mod) * 4 % mod;
        } else {
            res = power(3, primeFactors / 3, mod) * 2 % mod;
        }
        return (int) res;
    }

    private long power(long base, long exp, int mod) {
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