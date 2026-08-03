class Solution {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        // Player's distance from (0,0) to target
        int playerDist = Math.abs(target[0]) + Math.abs(target[1]);

        for (int[] ghost : ghosts) {
            int ghostDist = Math.abs(ghost[0] - target[0]) 
                          + Math.abs(ghost[1] - target[1]);
            
            // If any ghost can reach the target as fast or faster → impossible
            if (ghostDist <= playerDist) {
                return false;
            }
        }
        return true;
    }
}