class Solution {
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Special case: already at destination
        if (m == 1 && n == 1) return 0;
        
        // visited[i][j][remain] = whether we have visited cell (i,j) with 'remain' eliminations left
        boolean[][][] visited = new boolean[m][n][k + 1];
        
        Queue<int[]> queue = new LinkedList<>();
        // state: {row, col, remaining eliminations, steps}
        queue.offer(new int[]{0, 0, k, 0});
        visited[0][0][k] = true;
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], remain = curr[2], steps = curr[3];
            
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                
                int newRemain = remain - grid[nr][nc];  // spend 1 if obstacle
                
                if (newRemain >= 0 && !visited[nr][nc][newRemain]) {
                    if (nr == m - 1 && nc == n - 1) {
                        return steps + 1;
                    }
                    visited[nr][nc][newRemain] = true;
                    queue.offer(new int[]{nr, nc, newRemain, steps + 1});
                }
            }
        }
        
        return -1;
    }
}