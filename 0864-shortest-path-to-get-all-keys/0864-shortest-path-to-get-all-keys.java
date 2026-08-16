class Solution {
    private static final int[] DIRS = {-1, 0, 1, 0, -1}; // up, right, down, left

    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();
        
        int startX = 0, startY = 0;
        int keyCount = 0;
        
        // Find start position and count keys
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    startX = i;
                    startY = j;
                } else if (Character.isLowerCase(c)) {
                    keyCount++;
                }
            }
        }
        
        int fullMask = (1 << keyCount) - 1;
        
        // visited[x][y][mask]
        boolean[][][] visited = new boolean[m][n][1 << keyCount];
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startX, startY, 0}); // x, y, mask
        visited[startX][startY][0] = true;
        
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] curr = queue.poll();
                int x = curr[0], y = curr[1], mask = curr[2];
                
                // Collected all keys
                if (mask == fullMask) {
                    return steps;
                }
                
                // Explore 4 directions
                for (int d = 0; d < 4; d++) {
                    int nx = x + DIRS[d];
                    int ny = y + DIRS[d + 1];
                    
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                    
                    char c = grid[nx].charAt(ny);
                    
                    // Wall
                    if (c == '#') continue;
                    
                    // Lock without key
                    if (Character.isUpperCase(c) && ((mask >> (c - 'A')) & 1) == 0) {
                        continue;
                    }
                    
                    int newMask = mask;
                    // Pick up key
                    if (Character.isLowerCase(c)) {
                        newMask |= (1 << (c - 'a'));
                    }
                    
                    if (!visited[nx][ny][newMask]) {
                        visited[nx][ny][newMask] = true;
                        queue.offer(new int[]{nx, ny, newMask});
                    }
                }
            }
            steps++;
        }
        
        return -1;
    }
}