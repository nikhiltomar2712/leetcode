class Solution {
    public int uniquePaths(int[][] grid) {
        final int MOD = 1_000_000_007;
        int m = grid.length;
        int n = grid[0].length;
        
        // down[j] = number of ways to arrive at current cell from above
        int[] down = new int[n];
        down[0] = 1;  // start at (0,0)
        
        for (int i = 0; i < m; i++) {
            int right = 0;  // number of ways to arrive at current cell from the left
            
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // Mirror: swap the incoming flows
                    // paths from left become the "down" outgoing
                    // paths from above become the "right" outgoing
                    int temp = right;
                    right = down[j];
                    down[j] = temp;
                } else {
                    // Empty cell: merge the incoming paths
                    // both directions can leave with the total ways
                    right = down[j] = (right + down[j]) % MOD;
                }
            }
        }
        
        return down[n - 1];
    }
}
