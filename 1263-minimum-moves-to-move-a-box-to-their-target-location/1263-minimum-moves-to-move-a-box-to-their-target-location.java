class Solution {
    private int m, n;
    private char[][] grid;

    public int minPushBox(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        int si = 0, sj = 0, bi = 0, bj = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    si = i; sj = j;
                } else if (grid[i][j] == 'B') {
                    bi = i; bj = j;
                }
            }
        }

        int[] dirs = {-1, 0, 1, 0, -1};
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] vis = new boolean[m * n][m * n];

        q.offer(new int[]{f(si, sj), f(bi, bj), 0});
        vis[f(si, sj)][f(bi, bj)] = true;

        while (!q.isEmpty()) {
            int[] p = q.poll();
            int s = p[0], b = p[1], d = p[2];

            bi = b / n;
            bj = b % n;
            if (grid[bi][bj] == 'T') return d;

            si = s / n;
            sj = s % n;

            for (int k = 0; k < 4; k++) {
                int sx = si + dirs[k];
                int sy = sj + dirs[k + 1];
                if (!check(sx, sy)) continue;

                // Player is trying to step onto the box → push
                if (sx == bi && sy == bj) {
                    int bx = bi + dirs[k];
                    int by = bj + dirs[k + 1];
                    if (!check(bx, by) || vis[f(sx, sy)][f(bx, by)]) continue;

                    vis[f(sx, sy)][f(bx, by)] = true;
                    q.offer(new int[]{f(sx, sy), f(bx, by), d + 1}); // cost 1
                } 
                // Normal player move (no push)
                else if (!vis[f(sx, sy)][f(bi, bj)]) {
                    vis[f(sx, sy)][f(bi, bj)] = true;
                    q.offerFirst(new int[]{f(sx, sy), f(bi, bj), d}); // cost 0
                }
            }
        }
        return -1;
    }

    private int f(int i, int j) {
        return i * n + j;
    }

    private boolean check(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n && grid[i][j] != '#';
    }
}