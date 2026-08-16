class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // Directions: 0=North, 1=East, 2=South, 3=West
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        // Store obstacles for O(1) lookup
        Set<Long> obstacleSet = new HashSet<>();
        for (int[] obs : obstacles) {
            obstacleSet.add(encode(obs[0], obs[1]));
        }
        
        int x = 0, y = 0;
        int dir = 0;          // start facing North
        int maxDist = 0;
        
        for (int cmd : commands) {
            if (cmd == -2) {               // turn left
                dir = (dir + 3) % 4;
            } else if (cmd == -1) {        // turn right
                dir = (dir + 1) % 4;
            } else {                       // move forward
                for (int step = 0; step < cmd; step++) {
                    int nx = x + dirs[dir][0];
                    int ny = y + dirs[dir][1];
                    
                    if (obstacleSet.contains(encode(nx, ny))) {
                        break;             // blocked by obstacle
                    }
                    
                    x = nx;
                    y = ny;
                    maxDist = Math.max(maxDist, x * x + y * y);
                }
            }
        }
        
        return maxDist;
    }
    
    // Encode (x, y) into a single long to avoid collisions
    private long encode(int x, int y) {
        return ((long) x + 30000) * 60001L + (y + 30000);
    }
}