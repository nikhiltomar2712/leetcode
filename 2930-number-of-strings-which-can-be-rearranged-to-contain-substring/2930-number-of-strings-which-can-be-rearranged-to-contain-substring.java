class Solution {
    private final int mod = (int) 1e9 + 7;
    private Long[][][][] f;

    public int stringCount(int n) {
        // Memoization table: f[remaining_length][l_count][e_count][t_count]
        f = new Long[n + 1][2][3][2];
        return (int) dfs(n, 0, 0, 0);
    }

    private long dfs(int i, int l, int e, int t) {
        // Base case: no more characters to place
        if (i == 0) {
            return (l == 1 && e == 2 && t == 1) ? 1 : 0;
        }
        // Return memoized result if available
        if (f[i][l][e][t] != null) {
            return f[i][l][e][t];
        }

        // Option 1: Place one of the 23 other letters
        long a = dfs(i - 1, l, e, t) * 23 % mod;
        // Option 2: Place 'l'
        long b = dfs(i - 1, Math.min(1, l + 1), e, t);
        // Option 3: Place 'e'
        long c = dfs(i - 1, l, Math.min(2, e + 1), t);
        // Option 4: Place 't'
        long d = dfs(i - 1, l, e, Math.min(1, t + 1));

        return f[i][l][e][t] = (a + b + c + d) % mod;
    }
}