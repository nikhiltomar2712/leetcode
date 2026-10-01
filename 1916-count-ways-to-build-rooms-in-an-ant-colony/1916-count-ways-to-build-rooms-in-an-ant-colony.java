class Solution {
    public int waysToBuildRooms(int[] prevRoom) {
        int n = prevRoom.length;
        int mod = 1000000007;
        List<Integer>[] children = new List[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            children[prevRoom[i]].add(i);
        }
        long[] fact = new long[n + 1];
        long[] invFact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) fact[i] = fact[i - 1] * i % mod;
        invFact[n] = modPow(fact[n], mod - 2, mod);
        for (int i = n - 1; i >= 0; i--) invFact[i] = invFact[i + 1] * (i + 1) % mod;

        long[] dp = new long[n];
        int[] size = new int[n];
        dfs(0, children, dp, size, fact, invFact, mod);
        return (int) dp[0];
    }

    private void dfs(int u, List<Integer>[] children, long[] dp, int[] size, long[] fact, long[] invFact, int mod) {
        size[u] = 1;
        dp[u] = 1;
        for (int v : children[u]) {
            dfs(v, children, dp, size, fact, invFact, mod);
            dp[u] = dp[u] * dp[v] % mod * invFact[size[v]] % mod;
            size[u] += size[v];
        }
        dp[u] = dp[u] * fact[size[u] - 1] % mod;
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