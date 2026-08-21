class Solution {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        boolean found = false;
        
        // 1. Find the first island and mark all its cells as 2
        //    Also put them into the queue for BFS
        for (int i = 0; i < n && !found; i++) {
            for (int j = 0; j < n && !found; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, queue);
                    found = true;
                }
            }
        }
        
        // 2. Multi-source BFS from the first island
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];
                
                for (int[] dir : dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        if (grid[nx][ny] == 1) {
                            // Reached the second island
                            return steps;
                        }
                        if (grid[nx][ny] == 0) {
                            grid[nx][ny] = 2; // mark as visited
                            queue.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
            steps++;
        }
        
        return -1; // should never reach here
    }
    
    // DFS to mark the entire first island and collect its cells
    private void dfs(int[][] grid, int i, int j, Queue<int[]> queue) {
        int n = grid.length;
        if (i < 0 || i >= n || j < 0 || j >= n || grid[i][j] != 1) {
            return;
        }
        
        grid[i][j] = 2;          // mark as visited / first island
        queue.offer(new int[]{i, j});
        
        for (int[] dir : dirs) {
            dfs(grid, i + dir[0], j + dir[1], queue);
        }
    }
}