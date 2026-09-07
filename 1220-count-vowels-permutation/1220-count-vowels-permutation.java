class Solution {
    public int countVowelPermutation(int n) {
        int MOD = 1_000_000_007;
        // dp for length 1: each vowel appears once
        long a = 1, e = 1, i = 1, o = 1, u = 1;
        
        for (int len = 1; len < n; len++) {
            // New counts based on which vowels can precede the current one
            long newA = (e + i + u) % MOD; // 'a' can be preceded by e, i, u
            long newE = (a + i) % MOD;     // 'e' can be preceded by a, i
            long newI = (e + o) % MOD;     // 'i' can be preceded by e, o
            long newO = i % MOD;           // 'o' can be preceded by i
            long newU = (i + o) % MOD;     // 'u' can be preceded by i, o
            
            a = newA;
            e = newE;
            i = newI;
            o = newO;
            u = newU;
        }
        
        return (int)((a + e + i + o + u) % MOD);
    }
}