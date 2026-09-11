class Solution {
    public int distinctEchoSubstrings(String text) {
        int n = text.length();
        
        // Two modular bases to minimize collision probability
        long MOD1 = 1_000_000_007L;
        long MOD2 = 1_000_000_009L;
        long BASE = 131L;
        
        // Precompute powers and prefix hashes
        long[] pow1 = new long[n + 1], pow2 = new long[n + 1];
        long[] pre1 = new long[n + 1], pre2 = new long[n + 1];
        pow1[0] = pow2[0] = 1;
        
        for (int i = 0; i < n; i++) {
            long c = text.charAt(i) - 'a' + 1;
            pow1[i + 1] = pow1[i] * BASE % MOD1;
            pow2[i + 1] = pow2[i] * BASE % MOD2;
            pre1[i + 1] = (pre1[i] * BASE + c) % MOD1;
            pre2[i + 1] = (pre2[i] * BASE + c) % MOD2;
        }
        
        Set<Long> seen = new HashSet<>();
        
        // Try every even-length substring
        for (int i = 0; i < n; i++) {
            for (int len = 2; i + len <= n; len += 2) {
                int half = len / 2;
                int mid = i + half;
                
                long h1a = hash(pre1, pow1, MOD1, i, mid - 1);
                long h1b = hash(pre1, pow1, MOD1, mid, i + len - 1);
                if (h1a != h1b) continue;
                
                long h2a = hash(pre2, pow2, MOD2, i, mid - 1);
                long h2b = hash(pre2, pow2, MOD2, mid, i + len - 1);
                if (h2a != h2b) continue;
                
                // Combine both hashes for deduplication
                long key = h1a * MOD2 + h2a;
                seen.add(key);
            }
        }
        
        return seen.size();
    }
    
    // Hash of text[l..r] inclusive
    private long hash(long[] pre, long[] pow, long mod, int l, int r) {
        return (pre[r + 1] - pre[l] * pow[r - l + 1] % mod + mod) % mod;
    }
}