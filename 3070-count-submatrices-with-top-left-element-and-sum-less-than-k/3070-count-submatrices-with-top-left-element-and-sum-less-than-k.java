class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        // prefix[i][j] = sum of the submatrix from (0,0) to (i-1,j-1)
        int[][] prefix = new int[m + 1][n + 1];
        int ans = 0;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = grid[i - 1][j - 1]
                             + prefix[i - 1][j]
                             + prefix[i][j - 1]
                             - prefix[i - 1][j - 1];
                
                if (prefix[i][j] <= k) {
                    ans++;
                }
            }
        }
        
        return ans;
    }
}