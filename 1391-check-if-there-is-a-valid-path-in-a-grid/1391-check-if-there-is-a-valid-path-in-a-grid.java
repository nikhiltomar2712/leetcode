class Solution {
    // Directions: 0: left, 1: up, 2: right, 3: down
    private static final int[][] DIRS = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    
    // For each street type, which directions it connects to
    // index = street type, value = list of open directions
    private static final int[][] STREET = {
        {},                  // 0 (unused)
        {0, 2},              // 1: left, right
        {1, 3},              // 2: up, down
        {0, 3},              // 3: left, down
        {2, 3},              // 4: right, down
        {0, 1},              // 5: left, up
        {1, 2}               // 6: up, right
    };
    
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        return dfs(grid, 0, 0, visited);
    }
    
    private boolean dfs(int[][] grid, int r, int c, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        if (r == m - 1 && c == n - 1) return true;
        
        visited[r][c] = true;
        int type = grid[r][c];
        
        for (int dir : STREET[type]) {
            int nr = r + DIRS[dir][0];
            int nc = c + DIRS[dir][1];
            
            if (nr < 0 || nr >= m || nc < 0 || nc >= n || visited[nr][nc]) {
                continue;
            }
            
            // Check if the neighbor has a connection back to us
            int opposite = (dir + 2) % 4;   // opposite direction
            boolean connected = false;
            for (int d : STREET[grid[nr][nc]]) {
                if (d == opposite) {
                    connected = true;
                    break;
                }
            }
            
            if (connected && dfs(grid, nr, nc, visited)) {
                return true;
            }
        }
        
        return false;
    }
}