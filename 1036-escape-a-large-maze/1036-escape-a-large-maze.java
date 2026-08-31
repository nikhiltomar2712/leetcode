class Solution {
    private static final int N = 1_000_000;
    private static final int[] DIRS = {-1, 0, 1, 0, -1};
    
    private Set<Long> blockedSet;
    private int maxSteps;   // maximum area that can be enclosed by the blocked cells

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        blockedSet = new HashSet<>();
        for (int[] b : blocked) {
            blockedSet.add(encode(b[0], b[1]));
        }
        
        // With ≤ 200 blocked cells, the largest enclosed area is at most ~ blocked.length² / 2
        maxSteps = blocked.length * (blocked.length + 1) / 2;
        
        // Source must be able to reach the target OR escape any enclosure,
        // AND the target must be able to reach the source OR escape any enclosure.
        return canEscape(source[0], source[1], target[0], target[1])
            && canEscape(target[0], target[1], source[0], source[1]);
    }
    
    private boolean canEscape(int sx, int sy, int tx, int ty) {
        Set<Long> visited = new HashSet<>();
        return dfs(sx, sy, tx, ty, visited);
    }
    
    private boolean dfs(int x, int y, int tx, int ty, Set<Long> visited) {
        // If we have visited more cells than the largest possible enclosure,
        // we must have escaped into the open grid.
        if (visited.size() > maxSteps) {
            return true;
        }
        
        for (int k = 0; k < 4; k++) {
            int nx = x + DIRS[k];
            int ny = y + DIRS[k + 1];
            
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            
            long key = encode(nx, ny);
            if (blockedSet.contains(key) || !visited.add(key)) continue;
            
            // Reached the other point
            if (nx == tx && ny == ty) return true;
            
            if (dfs(nx, ny, tx, ty, visited)) return true;
        }
        return false;
    }
    
    private long encode(int x, int y) {
        return (long) x * N + y;
    }
}