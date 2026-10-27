class Solution {
    public String[] createGrid(int m, int n) {
        char[][] g = new char[m][n];
        
        // Fill entire grid with obstacles
        for (int i = 0; i < m; i++) {
            Arrays.fill(g[i], '#');
        }
        
        // Open the entire first row
        Arrays.fill(g[0], '.');
        
        // Open the entire last column
        for (int i = 0; i < m; i++) {
            g[i][n - 1] = '.';
        }
        
        // Convert to String[]
        String[] ans = new String[m];
        for (int i = 0; i < m; i++) {
            ans[i] = new String(g[i]);
        }
        return ans;
    }
}