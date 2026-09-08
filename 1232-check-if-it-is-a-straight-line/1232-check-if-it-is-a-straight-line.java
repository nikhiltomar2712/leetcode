class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        // Get the first two points to define our line
        int x0 = coordinates[0][0];
        int y0 = coordinates[0][1];
        int x1 = coordinates[1][0];
        int y1 = coordinates[1][1];
        
        // Check all other points
        for (int i = 2; i < coordinates.length; i++) {
            int x = coordinates[i][0];
            int y = coordinates[i][1];
            
            // Using cross product to avoid division by zero
            // (y1 - y0) * (x - x0) should equal (y - y0) * (x1 - x0)
            // This checks if slope between point0-point1 equals slope between point0-point i
            if ((y1 - y0) * (x - x0) != (y - y0) * (x1 - x0)) {
                return false;
            }
        }
        return true;
    }
}