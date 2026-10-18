class Solution {
    public int countAnagrams(String s) {
        int mod = 1000000007;
        int maxLen = s.length();
        long[] fact = new long[maxLen + 1];
        long[] invFact = new long[maxLen + 1];
        fact[0] = 1;
        for (int i = 1; i <= maxLen; i++) fact[i] = fact[i - 1] * i % mod;
        invFact[maxLen] = modPow(fact[maxLen], mod - 2, mod);
        for (int i = maxLen - 1; i >= 0; i--) invFact[i] = invFact[i + 1] * (i + 1) % mod;

        long res = 1;
        for (String word : s.split(" ")) {
            int[] count = new int[26];
            for (char c : word.toCharArray()) count[c - 'a']++;
            long ways = fact[word.length()];
            for (int c : count) ways = ways * invFact[c] % mod;
            res = res * ways % mod;
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