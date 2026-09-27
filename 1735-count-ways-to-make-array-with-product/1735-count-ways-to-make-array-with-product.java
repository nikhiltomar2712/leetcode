class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int MAX_N = 10020;
    private static long[] fact = new long[MAX_N];
    private static long[] invFact = new long[MAX_N];
    
    static {
        fact[0] = 1;
        for (int i = 1; i < MAX_N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        invFact[MAX_N - 1] = modPow(fact[MAX_N - 1], MOD - 2);
        for (int i = MAX_N - 2; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }
    }
    
    private static long modPow(long base, long exp) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) res = res * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return res;
    }
    
    private static long comb(int n, int k) {
        if (k < 0 || k > n) return 0;
        return fact[n] * invFact[k] % MOD * invFact[n - k] % MOD;
    }
    
    public int[] waysToFillArray(int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        
        for (int i = 0; i < m; i++) {
            int n = queries[i][0];
            int k = queries[i][1];
            long ways = 1;
            
            // Prime factorization of k
            for (int p = 2; p * p <= k; p++) {
                if (k % p == 0) {
                    int count = 0;
                    while (k % p == 0) {
                        k /= p;
                        count++;
                    }
                    // Stars and bars: distribute 'count' identical factors into 'n' bins
                    ways = ways * comb(count + n - 1, n - 1) % MOD;
                }
            }
            if (k > 1) {
                // k itself is prime
                ways = ways * comb(1 + n - 1, n - 1) % MOD;
            }
            
            ans[i] = (int) ways;
        }
        
        return ans;
    }
}