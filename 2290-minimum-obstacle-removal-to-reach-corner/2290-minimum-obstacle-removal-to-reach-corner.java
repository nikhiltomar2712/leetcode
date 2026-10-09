class Solution {
    public int minimumObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dist = new int[m][n];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        Deque<int[]> deque = new ArrayDeque<>();
        dist[0][0] = 0;
        deque.offerFirst(new int[]{0, 0});
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int r = cur[0], c = cur[1];
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                int w = grid[nr][nc];
                if (dist[r][c] + w < dist[nr][nc]) {
                    dist[nr][nc] = dist[r][c] + w;
                    if (w == 0) deque.offerFirst(new int[]{nr, nc});
                    else deque.offerLast(new int[]{nr, nc});
                }
            }
        }
        return dist[m - 1][n - 1];
    }
}