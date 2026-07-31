class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        
        // If the target color is the same as the original, no changes needed
        if (originalColor == color) {
            return image;
        }
        
        // Start DFS from the starting pixel
        dfs(image, sr, sc, originalColor, color);
        
        return image;
    }
    
    private void dfs(int[][] image, int row, int col, int originalColor, int newColor) {
        // Check boundaries and if current pixel matches original color
        if (row < 0 || row >= image.length || col < 0 || col >= image[0].length) {
            return;
        }
        
        if (image[row][col] != originalColor) {
            return;
        }
        
        // Change color
        image[row][col] = newColor;
        
        // Recursively visit all 4-directional neighbors
        dfs(image, row - 1, col, originalColor, newColor); // up
        dfs(image, row + 1, col, originalColor, newColor); // down
        dfs(image, row, col - 1, originalColor, newColor); // left
        dfs(image, row, col + 1, originalColor, newColor); // right
    }
}