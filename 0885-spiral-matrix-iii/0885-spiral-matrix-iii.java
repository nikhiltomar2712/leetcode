class Solution {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] result = new int[rows * cols][2];
        int index = 0;
        
        // Directions: East, South, West, North
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dir = 0; // Start facing East
        int step = 1; // Current step length for this direction
        
        // Add starting position
        result[index++] = new int[]{rStart, cStart};
        
        while (index < rows * cols) {
            // Move in current direction for 'step' times
            for (int i = 0; i < 2; i++) { // Need to increase step length every two directions
                for (int j = 0; j < step; j++) {
                    rStart += directions[dir][0];
                    cStart += directions[dir][1];
                    
                    // If current position is within grid, add to result
                    if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
                        result[index++] = new int[]{rStart, cStart};
                    }
                }
                // Turn clockwise to next direction
                dir = (dir + 1) % 4;
            }
            // Increase step length after completing two directions (East+South or West+North)
            step++;
        }
        
        return result;
    }
}