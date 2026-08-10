class Solution {
    private int[][] grid;
    private int n;
    private int[] islandSize;
    private int islandId = 2; // Start from 2 to avoid confusion with 0 and 1
    private int[] dirs = {0, 1, 0, -1, 0}; // For 4-directional movement
    
    public int largestIsland(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        
        // Step 1: Find all islands and their sizes
        islandSize = new int[n * n + 2]; // Max possible islands
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int size = dfs(i, j, islandId);
                    islandSize[islandId] = size;
                    islandId++;
                }
            }
        }
        
        // Step 2: Handle edge cases
        // If no islands, flipping one 0 creates island of size 1
        if (islandId == 2) return 1;
        
        // If all cells are land
        if (islandId == 2 && islandSize[2] == n * n) return n * n;
        
        // Step 3: Try flipping each 0 and calculate resulting island size
        int maxIsland = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int currentSize = 1; // The flipped cell
                    Set<Integer> neighboringIslands = new HashSet<>();
                    
                    // Check all 4 directions
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dirs[d];
                        int nj = j + dirs[d + 1];
                        
                        if (ni >= 0 && ni < n && nj >= 0 && nj < n && grid[ni][nj] > 1) {
                            int id = grid[ni][nj];
                            if (!neighboringIslands.contains(id)) {
                                neighboringIslands.add(id);
                                currentSize += islandSize[id];
                            }
                        }
                    }
                    
                    maxIsland = Math.max(maxIsland, currentSize);
                }
            }
        }
        
        // If there were no zeros, return the largest existing island
        return maxIsland == 0 ? islandSize[2] : maxIsland;
    }
    
    private int dfs(int i, int j, int id) {
        if (i < 0 || i >= n || j < 0 || j >= n || grid[i][j] != 1) {
            return 0;
        }
        
        grid[i][j] = id; // Mark cell with island ID
        int size = 1;
        
        for (int d = 0; d < 4; d++) {
            size += dfs(i + dirs[d], j + dirs[d + 1], id);
        }
        
        return size;
    }
}