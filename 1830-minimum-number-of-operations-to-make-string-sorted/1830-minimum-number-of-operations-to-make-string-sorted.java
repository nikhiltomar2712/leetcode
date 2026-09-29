class Solution {
    public int makeStringSorted(String s) {
        int mod = 1000000007;
        int n = s.length();
        long[] fact = new long[n + 1];
        long[] invFact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) fact[i] = fact[i - 1] * i % mod;
        invFact[n] = modPow(fact[n], mod - 2, mod);
        for (int i = n - 1; i >= 0; i--) invFact[i] = invFact[i + 1] * (i + 1) % mod;
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        long res = 0;
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            for (int j = 0; j < c; j++) {
                if (count[j] == 0) continue;
                count[j]--;
                long perm = fact[n - i - 1];
                for (int k = 0; k < 26; k++) perm = perm * invFact[count[k]] % mod;
                res = (res + perm) % mod;
                count[j]++;
            }
            count[c]--;
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