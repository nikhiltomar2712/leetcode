class Solution {
    private static final int[][] DIRS = {{0,1}, {1,0}, {0,-1}, {-1,0}};

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        // min-heap: {maxElevationSoFar, row, col}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int t = cur[0], r = cur[1], c = cur[2];

            if (r == n - 1 && c == n - 1) {
                return t;
            }

            for (int[] d : DIRS) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    int nextT = Math.max(t, grid[nr][nc]);
                    pq.offer(new int[]{nextT, nr, nc});
                }
            }
        }
        return -1; // unreachable
    }
}