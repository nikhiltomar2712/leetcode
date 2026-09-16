class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        // The answer is C(n + k - 1, 2 * k) mod (10^9 + 7)
        return (int) binomial(n + k - 1, 2 * k);
    }

    private long binomial(int N, int R) {
        if (R < 0 || R > N) return 0;
        if (R == 0 || R == N) return 1;
        // Optimize: C(N, R) == C(N, N - R)
        R = Math.min(R, N - R);

        long[] fact = new long[N + 1];
        long[] invFact = new long[N + 1];
        fact[0] = 1;
        for (int i = 1; i <= N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        invFact[N] = modInverse(fact[N]);
        for (int i = N - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }
        return fact[N] * invFact[R] % MOD * invFact[N - R] % MOD;
    }

    private long modInverse(long a) {
        return modPow(a, MOD - 2);
    }

    private long modPow(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = result * base % MOD;
            }
            base = base * base % MOD;
            exp >>= 1;
        }
        return result;
    }
}