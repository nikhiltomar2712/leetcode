class Solution {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[] partner = new int[n];
        for (int[] p : pairs) {
            partner[p[0]] = p[1];
            partner[p[1]] = p[0];
        }

        // rank[i][j] = position of j in i's preference list
        int[][] rank = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int pos = 0; pos < preferences[i].length; pos++) {
                rank[i][preferences[i][pos]] = pos;
            }
        }

        int unhappy = 0;
        for (int x = 0; x < n; x++) {
            int y = partner[x];
            for (int u : preferences[x]) {
                if (u == y) break; // stop at current partner
                int v = partner[u];
                if (rank[u][x] < rank[u][v]) {
                    unhappy++;
                    break; // x is unhappy; count once
                }
            }
        }

        return unhappy;
    }
}