class Solution {
    // Memo: f[l][r][n] stores encoded (earliest, latest)
    // l, r are 0-based positions of the two special players
    static int[][][] f = new int[30][30][31];

    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        // Convert to 0-based
        return dfs(firstPlayer - 1, secondPlayer - 1, n);
    }

    private int[] dfs(int l, int r, int n) {
        if (f[l][r][n] != 0) {
            return decode(f[l][r][n]);
        }
        // They face each other this round
        if (l + r == n - 1) {
            f[l][r][n] = encode(1, 1);
            return new int[]{1, 1};
        }

        int minRound = Integer.MAX_VALUE;
        int maxRound = Integer.MIN_VALUE;
        int m = n >> 1;   // number of matches

        // Enumerate all possible outcomes of the m matches (2^m possibilities)
        for (int mask = 0; mask < (1 << m); mask++) {
            boolean[] win = new boolean[n];

            // Decide winners for each pair (i vs n-1-i)
            for (int j = 0; j < m; j++) {
                if (((mask >> j) & 1) == 1) {
                    win[j] = true;          // front wins
                } else {
                    win[n - 1 - j] = true;  // back wins
                }
            }

            // Middle player advances automatically when n is odd
            if ((n & 1) == 1) {
                win[m] = true;
            }

            // Force the two special players to always win
            // (they never lose until they meet)
            win[n - 1 - l] = false;
            win[n - 1 - r] = false;
            win[l] = true;
            win[r] = true;

            // Compute new positions of the two special players
            // among the winners (who keep original relative order)
            int a = 0, b = 0, c = 0;
            for (int j = 0; j < n; j++) {
                if (j == l) a = c;
                if (j == r) b = c;
                if (win[j]) c++;
            }

            // Recurse on the next round
            int[] next = dfs(a, b, c);
            minRound = Math.min(minRound, next[0] + 1);
            maxRound = Math.max(maxRound, next[1] + 1);
        }

        f[l][r][n] = encode(minRound, maxRound);
        return new int[]{minRound, maxRound};
    }

    private int encode(int x, int y) {
        return (x << 8) | y;
    }

    private int[] decode(int val) {
        return new int[]{val >> 8, val & 255};
    }
}