import java.util.*;

class Solution {
    public int minimumMoves(int[][] grid) {
        int n = grid.length;
        // State: (tailRow, tailCol, direction)
        // direction: 0 = horizontal, 1 = vertical
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[n][n][2];
        
        // Start: tail at (0,0), horizontal
        queue.offer(new int[]{0, 0, 0});
        visited[0][0][0] = true;
        
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0]; // tail row
                int c = curr[1]; // tail col
                int dir = curr[2]; // 0 = horizontal, 1 = vertical
                
                // Check if we reached the target
                if (r == n - 1 && c == n - 2 && dir == 0) {
                    return steps;
                }
                
                // 1. Move Right
                if (canMoveRight(grid, r, c, dir, n)) {
                    int newC = c + 1;
                    if (!visited[r][newC][dir]) {
                        visited[r][newC][dir] = true;
                        queue.offer(new int[]{r, newC, dir});
                    }
                }
                
                // 2. Move Down
                if (canMoveDown(grid, r, c, dir, n)) {
                    int newR = r + 1;
                    if (!visited[newR][c][dir]) {
                        visited[newR][c][dir] = true;
                        queue.offer(new int[]{newR, c, dir});
                    }
                }
                
                // 3. Rotate
                if (dir == 0) { // currently horizontal → try clockwise
                    if (canRotateClockwise(grid, r, c, n)) {
                        if (!visited[r][c][1]) {
                            visited[r][c][1] = true;
                            queue.offer(new int[]{r, c, 1});
                        }
                    }
                } else { // currently vertical → try counterclockwise
                    if (canRotateCounterClockwise(grid, r, c, n)) {
                        if (!visited[r][c][0]) {
                            visited[r][c][0] = true;
                            queue.offer(new int[]{r, c, 0});
                        }
                    }
                }
            }
            steps++;
        }
        
        return -1;
    }
    
    private boolean canMoveRight(int[][] grid, int r, int c, int dir, int n) {
        if (dir == 0) { // horizontal: need cell (r, c+2) empty
            return c + 2 < n && grid[r][c + 2] == 0;
        } else { // vertical: need cells (r, c+1) and (r+1, c+1) empty
            return c + 1 < n && grid[r][c + 1] == 0 && grid[r + 1][c + 1] == 0;
        }
    }
    
    private boolean canMoveDown(int[][] grid, int r, int c, int dir, int n) {
        if (dir == 0) { // horizontal: need cells (r+1, c) and (r+1, c+1) empty
            return r + 1 < n && grid[r + 1][c] == 0 && grid[r + 1][c + 1] == 0;
        } else { // vertical: need cell (r+2, c) empty
            return r + 2 < n && grid[r + 2][c] == 0;
        }
    }
    
    private boolean canRotateClockwise(int[][] grid, int r, int c, int n) {
        // Need both cells below empty: (r+1, c) and (r+1, c+1)
        return r + 1 < n && grid[r + 1][c] == 0 && grid[r + 1][c + 1] == 0;
    }
    
    private boolean canRotateCounterClockwise(int[][] grid, int r, int c, int n) {
        // Need both cells to the right empty: (r, c+1) and (r+1, c+1)
        return c + 1 < n && grid[r][c + 1] == 0 && grid[r + 1][c + 1] == 0;
    }
}