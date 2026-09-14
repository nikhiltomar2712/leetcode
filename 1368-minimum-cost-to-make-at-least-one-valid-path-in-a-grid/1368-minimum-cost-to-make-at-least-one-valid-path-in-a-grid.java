class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Directions: index 1=right, 2=left, 3=down, 4=up
        int[][] dirs = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        boolean[][] visited = new boolean[m][n];
        Deque<int[]> dq = new ArrayDeque<>(); // {row, col, cost}
        
        dq.offer(new int[]{0, 0, 0});
        
        while (!dq.isEmpty()) {
            int[] curr = dq.pollFirst();
            int i = curr[0], j = curr[1], cost = curr[2];
            
            if (visited[i][j]) continue;
            visited[i][j] = true;
            
            // Reached the destination
            if (i == m - 1 && j == n - 1) {
                return cost;
            }
            
            // Try all 4 possible moves
            for (int k = 1; k <= 4; k++) {
                int ni = i + dirs[k][0];
                int nj = j + dirs[k][1];
                
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && !visited[ni][nj]) {
                    if (grid[i][j] == k) {
                        // Same direction → cost remains the same → push front
                        dq.offerFirst(new int[]{ni, nj, cost});
                    } else {
                        // Need to change direction → cost + 1 → push back
                        dq.offerLast(new int[]{ni, nj, cost + 1});
                    }
                }
            }
        }
        
        return -1; // unreachable (should not happen under constraints)
    }
}