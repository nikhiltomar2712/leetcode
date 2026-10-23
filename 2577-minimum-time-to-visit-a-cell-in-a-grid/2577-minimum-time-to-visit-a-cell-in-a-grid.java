class Solution {
    public int minimumTime(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][1] > 1 && grid[1][0] > 1) return -1;
        int[][] dist = new int[m][n];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0, 0});
        dist[0][0] = 0;
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int t = cur[0], r = cur[1], c = cur[2];
            if (t > dist[r][c]) continue;
            if (r == m - 1 && c == n - 1) return t;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                int nt = t + 1;
                if (nt < grid[nr][nc]) {
                    nt = grid[nr][nc] + ((grid[nr][nc] - nt) % 2);
                }
                if (nt < dist[nr][nc]) {
                    dist[nr][nc] = nt;
                    pq.offer(new int[]{nt, nr, nc});
                }
            }
        }
        return -1;
    }
}