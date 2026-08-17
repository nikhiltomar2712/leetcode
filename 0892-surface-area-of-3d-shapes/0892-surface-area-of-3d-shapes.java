class Solution {
    public int surfaceArea(int[][] grid) {
        int n = grid.length;
        int totalSurface = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int height = grid[i][j];
                
                // If there's a tower, add its top and bottom faces
                if (height > 0) {
                    totalSurface += 2; // top and bottom
                }
                
                // Check the four adjacent directions
                // North (up)
                int north = (i > 0) ? grid[i-1][j] : 0;
                totalSurface += Math.max(0, height - north);
                
                // South (down)
                int south = (i < n-1) ? grid[i+1][j] : 0;
                totalSurface += Math.max(0, height - south);
                
                // West (left)
                int west = (j > 0) ? grid[i][j-1] : 0;
                totalSurface += Math.max(0, height - west);
                
                // East (right)
                int east = (j < n-1) ? grid[i][j+1] : 0;
                totalSurface += Math.max(0, height - east);
            }
        }
        
        return totalSurface;
    }
}